package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.ResultSetHandler;

public class b05_BaseDao<T> {

	private Class<T> clazz;
	private String tableName;
	private String columnName_id;
	
	@SuppressWarnings("unchecked")
	public b05_BaseDao() {
		// 获取当前类的直接超类
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		// 获取父类的实际类型参数
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		// 获取实际参数类的b02_TableAnnotation注解
		b02_TableAnnotation tableAnnotation = clazz.getAnnotation(b02_TableAnnotation.class);
		tableName = tableAnnotation.tableName();
		// 获取实际参数类的所有成员变量
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 设置Field的访问权限
			field.setAccessible(true);
			// 获取实际参数类的成员变量的b04_IdAnnotation注解
			b04_IdAnnotation idAnnotation = field.getAnnotation(b04_IdAnnotation.class);
			if (idAnnotation != null) {
				// 获取实际参数类的成员变量的b03_ColumnAnnotation注解
				b03_ColumnAnnotation columnAnnotation = field.getAnnotation(b03_ColumnAnnotation.class);
				// 获取注解的名称
				columnName_id = columnAnnotation.columnName();
				break;
			}
		}
		System.out.println(tableName);
		System.out.println(columnName_id);
	}
	
	public T findById(int id) {
		String sql = "select * from " + tableName + " where " + columnName_id + "=?";
		try {
			// DBUtils已经封装好了工具类BeanHandler，但是前提条件是：成员变量名=字段名，所以得重写BeanHandler
			return b08_JdbcUtil.getQueryRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<T> getAll() {
		String sql = "select * from " + tableName;
		try {
			return b08_JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

/**
 * 自定义结果集，封装单个Bean对象
 * @author Xudong.Liu
 * @param <T>
 */
class BeanHandler<T> implements ResultSetHandler<T> {
	
	private Class<T> clazz;
	// 创建有参构造方法
	public BeanHandler(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public T handle(ResultSet rs) throws SQLException {
		try {
			// 创建类对象，封装后，返回
			T t = clazz.newInstance();
			// 判断结果集中是否有值
			if (rs.next()) {
				// 获取所有该类中的所有属性
				Field[] fields = clazz.getDeclaredFields();
				// 遍历所有属性，封装对象
				for (Field field : fields) {
					// 获取属性的名称
					String FieldName = field.getName();
					// 通过注解，从数据库中获取字段值（即属性值）
					b03_ColumnAnnotation columnAnnotation = field.getAnnotation(b03_ColumnAnnotation.class);
					String columnName = columnAnnotation.columnName();
					Object columnValue = rs.getObject(columnName);
					// 封装对象
					BeanUtils.copyProperty(t, FieldName, columnValue);
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}

/**
 * 自定义结果集，封装多个Bean对象
 * @author Xudong.Liu
 * @param <T>
 */
class BeanListHandler<T> implements ResultSetHandler<List<T>> {

	private Class<T> clazz;
	// 创建有参构造函数
	public BeanListHandler(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		try {
			// 创建封装对象的list集合，封装后，返回
			List<T> list = new ArrayList<T>();
			// 判断、遍历结果集
			while (rs.next()) {
				// 创建该类的对象，封装后，添加到list集合中
				T t = clazz.newInstance();
				// 获取对象中的所有属性
				Field[] fields = clazz.getDeclaredFields();
				// 遍历所有属性，封装对象
				for (Field field : fields) {
					// 获取属性的名称
					String fieldName = field.getName();
					// 通过注解，从数据库中获取字段的值（即属性值）
					b03_ColumnAnnotation columnAnnotation = field.getAnnotation(b03_ColumnAnnotation.class);
					String columnName = columnAnnotation.columnName();
					Object columnValue = rs.getObject(columnName);
					// 将属性封装到对象
					BeanUtils.copyProperty(t, fieldName, columnValue);
				}
				// 将对象添加到list集合
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}