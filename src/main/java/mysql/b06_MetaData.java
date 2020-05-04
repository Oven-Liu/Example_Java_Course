package mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;
import util.JdbcUtil;

/**
 * ��ȡԪ����
 * 
 * @author Xudong.Liu
 */
public class b06_MetaData {

	/**
	 * ��ȡ���ݿ�Ԫ����
	 */
	@Test
	public void testDB() throws Exception {
		Connection conn = JdbcUtil.getConnection();
		// �������ݿ�Ԫ���ݶ���
		DatabaseMetaData dbmd = conn.getMetaData();
		System.out.println(dbmd.getURL());
		System.out.println(dbmd.getUserName());
		System.out.println(dbmd.getDatabaseProductName());
		System.out.println(dbmd.isReadOnly());
	}
	
	/**
	 * ��ȡ����Ԫ����
	 */
	@Test
	public void testPatams() throws Exception {
		
		String sql = "SELECT * FROM statement WHERE NAME=? AND gender=?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// ��������Ԫ����
		ParameterMetaData pmd = pstmt.getParameterMetaData();
		// ��ȡ�����ĸ���
		System.out.println(pmd.getParameterCount());
		// ��ȡ����������
		System.out.println(pmd.getParameterTypeName(1));
//		System.out.println(pmd.getParameterType(1));
	}
	
	@Test
	public void testRS() throws Exception {
		String sql ="SELECT * FROM statement";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// �����������Ԫ����
		ResultSetMetaData rsmd = pstmt.getMetaData();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			// ��ȡ������ϵ�����
			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				// ��ȡ��������е�ǰ�е�ָ���е�ֵ
				Object value = rs.getObject(i+1);
				// ��ȡ������
				String name = rsmd.getColumnName(i+1);
				
				// ��ȡ��������
				String type = rsmd.getColumnTypeName(i+1);
				System.out.print(name + "��" + type + "=" + value + ",");
			}
			System.out.println();
		}
	}
}
