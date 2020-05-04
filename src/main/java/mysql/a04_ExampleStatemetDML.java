package mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class a04_ExampleStatemetDML {
	Connection connection = null;
	Statement statement = null;
	
	/**
	 * �������
	 */
	@Test
	public void testAdd() {
		try {
			/*
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			*/
			connection = JdbcUtil.getConnection();
			statement = connection.createStatement();
			String sql = "INSERT INTO statement (NAME,gender) VALUES ('����','��')";
			int content = statement.executeUpdate(sql);
			System.out.println("Ӱ����"+content+"��");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			/*if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}*/
			JdbcUtil.closeResource(connection, statement, null);
		}
	}
	
	/**
	 * ɾ������
	 */
	@Test
	public void testDelete() {
		int row = 4;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.createStatement();
			String sql = "DELETE FROM statement WHERE id="+row+"";
			int content = statement.executeUpdate(sql);
			System.out.println("Ӱ����"+content+"��");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(connection, statement, null);
		}
	}
	
	/**
	 * �޸�����
	 */
	@Test
	public void testUpdate() {
		String gender = "Ů";
		int id = 1;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.createStatement();
			String sql = "UPDATE statement SET gender='"+gender+"' WHERE id="+id+"";
			int content = statement.executeUpdate(sql);
			System.out.println("Ӱ����"+content+"��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(connection, statement, null);
		}
	}
}
