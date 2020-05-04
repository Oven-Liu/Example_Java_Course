package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;

public class a06_ExamplePreparedStatement {
	Connection con = null;
	PreparedStatement presta = null;
	
	/**
	 * 1���������
	 */
	@Test
	public void testInsert() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "INSERT INTO statement (NAME,gender) VALUES (?,?)";
			//	Ԥ���룬����﷨
			presta = con.prepareStatement(sql);
			//	���ò�����ռλ����˳���1��ʼ���
			presta.setString(1, "����");
			presta.setString(2, "��");
			//	���Ͳ�����ִ��sql���
			int content = presta.executeUpdate();
			System.out.println("Ӱ����"+content+"��");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
	
	/**
	 * 2���޸�����
	 */
	@Test
	public void testUpdate() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "UPDATE statement SET NAME=? WHERE id=?";
			//	Ԥ���룬����﷨
			presta = con.prepareStatement(sql);
			//	���ò�����ռλ����˳���1��ʼ���
			presta.setString(1, "����");
			presta.setInt(2, 7);
			//	���Ͳ�����ִ��sql���
			int content = presta.executeUpdate();
			System.out.println("Ӱ����"+content+"��");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
	
	/**
	 * 3��ɾ������
	 */
	@Test
	public void testDelete() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "DELETE FROM statement WHERE id=?";
			//	Ԥ���룬����﷨
			presta = con.prepareStatement(sql);
			//	���ò�����ռλ����˳���1��ʼ���
			presta.setInt(1, 9);
			//	���Ͳ�����ִ��sql���
			int content = presta.executeUpdate();
			System.out.println("Ӱ����"+content+"��");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
	
	/**
	 * 4����ѯ����
	 */
	@Test
	public void testQuery() {
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM statement";
			//	Ԥ���룬����﷨
			presta = con.prepareStatement(sql);
			rs = presta.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				System.out.println(id+","+name+","+gender);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.closeResource(con, presta, rs);
		}
	}
}
