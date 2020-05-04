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
		// ��ȡ��ǰ���ֱ�ӳ���
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		// ��ȡ�����ʵ�����Ͳ���
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		// ��ȡʵ�ʲ������b02_TableAnnotationע��
		b02_TableAnnotation tableAnnotation = clazz.getAnnotation(b02_TableAnnotation.class);
		tableName = tableAnnotation.tableName();
		// ��ȡʵ�ʲ���������г�Ա����
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// ����Field�ķ���Ȩ��
			field.setAccessible(true);
			// ��ȡʵ�ʲ�����ĳ�Ա������b04_IdAnnotationע��
			b04_IdAnnotation idAnnotation = field.getAnnotation(b04_IdAnnotation.class);
			if (idAnnotation != null) {
				// ��ȡʵ�ʲ�����ĳ�Ա������b03_ColumnAnnotationע��
				b03_ColumnAnnotation columnAnnotation = field.getAnnotation(b03_ColumnAnnotation.class);
				// ��ȡע�������
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
			// DBUtils�Ѿ���װ���˹�����BeanHandler������ǰ�������ǣ���Ա������=�ֶ��������Ե���дBeanHandler
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
 * �Զ�����������װ����Bean����
 * @author Xudong.Liu
 * @param <T>
 */
class BeanHandler<T> implements ResultSetHandler<T> {
	
	private Class<T> clazz;
	// �����вι��췽��
	public BeanHandler(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public T handle(ResultSet rs) throws SQLException {
		try {
			// ��������󣬷�װ�󣬷���
			T t = clazz.newInstance();
			// �жϽ�������Ƿ���ֵ
			if (rs.next()) {
				// ��ȡ���и����е���������
				Field[] fields = clazz.getDeclaredFields();
				// �����������ԣ���װ����
				for (Field field : fields) {
					// ��ȡ���Ե�����
					String FieldName = field.getName();
					// ͨ��ע�⣬�����ݿ��л�ȡ�ֶ�ֵ��������ֵ��
					b03_ColumnAnnotation columnAnnotation = field.getAnnotation(b03_ColumnAnnotation.class);
					String columnName = columnAnnotation.columnName();
					Object columnValue = rs.getObject(columnName);
					// ��װ����
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
 * �Զ�����������װ���Bean����
 * @author Xudong.Liu
 * @param <T>
 */
class BeanListHandler<T> implements ResultSetHandler<List<T>> {

	private Class<T> clazz;
	// �����вι��캯��
	public BeanListHandler(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		try {
			// ������װ�����list���ϣ���װ�󣬷���
			List<T> list = new ArrayList<T>();
			// �жϡ����������
			while (rs.next()) {
				// ��������Ķ��󣬷�װ����ӵ�list������
				T t = clazz.newInstance();
				// ��ȡ�����е���������
				Field[] fields = clazz.getDeclaredFields();
				// �����������ԣ���װ����
				for (Field field : fields) {
					// ��ȡ���Ե�����
					String fieldName = field.getName();
					// ͨ��ע�⣬�����ݿ��л�ȡ�ֶε�ֵ��������ֵ��
					b03_ColumnAnnotation columnAnnotation = field.getAnnotation(b03_ColumnAnnotation.class);
					String columnName = columnAnnotation.columnName();
					Object columnValue = rs.getObject(columnName);
					// �����Է�װ������
					BeanUtils.copyProperty(t, fieldName, columnValue);
				}
				// ��������ӵ�list����
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}