package basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import util.JdbcUtil;

/**
 * ���и��ºͲ�ѯ���ݵ�ͨ�÷���
 * 
 * @author Xudong.Liu
 */
public class BaseDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * ��������
	 * 
	 * @param sql  ���µ�sql��䣨insert��update��delete��
	 * @param paramValues  sql�����ռλ����ֵ�����û�У�����null��
	 */
	public void update(String sql, Object[] paramValues) {

		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// ��ȡ��������
			int count = pstmt.getParameterMetaData().getParameterCount();
			if (paramValues != null && paramValues.length > 0) {
				// ���ò���ֵ
				for (int i = 0; i < count; i++) {
					pstmt.setObject(i+1, paramValues[i]);
				}
			}
			// ִ�и�������
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(conn, pstmt, null);
		}
	}
	
	/**
	 * ��ѯ����
	 * 
	 * @param sql  ��ѯ��sql���
	 * @param paramValues  sql�����ռλ����ֵ
	 * @param clazz  ���ض��������
	 * @return  ��ѯ����ļ���
	 */
	public <T>List<T> query(String sql, Object[] paramValues, Class<T> clazz) {
		
		try {
			// ����List����
			List<T> list = new ArrayList<T>();
			// 1����ȡ����
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// ��ȡռλ��������
			int paramCount = pstmt.getParameterMetaData().getParameterCount();
			// 2��Ϊ��������ֵ
			if (paramValues != null && paramValues.length > 0) {
				for (int i = 0; i < paramCount; i++) {
					pstmt.setObject(i + 1, paramValues[i]);
				}
			}
			// 3��ִ��sql���
			rs = pstmt.executeQuery();
			// 4����ȡ��ѯ�����������Class����Ϊ���װ����
			while (rs.next()) {
				// ʹ�÷��䣬����T�Ķ���
				T t = clazz.newInstance();
				// ��ȡ�����������
				int ColunmConunt = pstmt.getMetaData().getColumnCount();
				for (int i = 0; i < ColunmConunt; i++) {
					// ��ȡ�����������
					String columnName = pstmt.getMetaData().getColumnName(i + 1);
					// ��ȡ��ǰ�е�ָ���е�ֵ
					Object value = rs.getObject(columnName);
					// ʹ��BeanUilts�����ݷ�װ������
					BeanUtils.copyProperty(t, columnName, value);
				}
				// ��t������ӵ�
				list.add(t);
			}
			// ����list
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(conn, pstmt, rs);
		}
	}
}
