package reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * 所有Dao层的公用方法，都在这里实现
 * 
 * @author Xudong.Liu
 *
 * @param <T>
 */
public class BaseDao<T> {

	private Class<T> clazz;
	private String tableName;
	
	/**
	 * 构造方法，获取当前对象所属类的父类的参数化类型，并从参数化类型中获取其实际类型参数
	 */
	@SuppressWarnings("unchecked")
	public BaseDao() {
		// 获取当前对象的所属类的父类
		Type type = this.getClass().getGenericSuperclass();
		// 将父类转化为参数化类型
		ParameterizedType pt = (ParameterizedType) type;
		// 获取参数化类型的实际类型参数
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		// 获取实际类的名称，即为表名（实体类必须与表名一致）
		tableName = clazz.getSimpleName();
	}
	
	/**
	 * 公用方法，根据id查找T。需要获取表名，即封装的对象的类型（表名必须与对象类型相同）
	 * 
	 * @param id
	 * @return T
	 */
	public T findById(int id) {
		String sql = "SELECT * FROM " + tableName + " WHERE id=?";
		try {
			return JDBCUtil.getQueryRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
