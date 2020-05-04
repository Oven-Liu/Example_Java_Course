package reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * ����Dao��Ĺ��÷�������������ʵ��
 * 
 * @author Xudong.Liu
 *
 * @param <T>
 */
public class BaseDao<T> {

	private Class<T> clazz;
	private String tableName;
	
	/**
	 * ���췽������ȡ��ǰ����������ĸ���Ĳ��������ͣ����Ӳ����������л�ȡ��ʵ�����Ͳ���
	 */
	@SuppressWarnings("unchecked")
	public BaseDao() {
		// ��ȡ��ǰ�����������ĸ���
		Type type = this.getClass().getGenericSuperclass();
		// ������ת��Ϊ����������
		ParameterizedType pt = (ParameterizedType) type;
		// ��ȡ���������͵�ʵ�����Ͳ���
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		// ��ȡʵ��������ƣ���Ϊ������ʵ������������һ�£�
		tableName = clazz.getSimpleName();
	}
	
	/**
	 * ���÷���������id����T����Ҫ��ȡ����������װ�Ķ�������ͣ��������������������ͬ��
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
