package mysql;

import java.sql.Connection;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import util.JdbcUtil;

public class b08_Update {
	
	Connection conn = null;
	QueryRunner qr = null;
	
	/**
	 * ���¶��ռλ����SQL���
	 */
	@Test
	public void testUpdate() throws Exception {
		conn = JdbcUtil.getConnection();
		qr = new QueryRunner();
		String sql = "UPDATE Admin SET pwd=? WHERE id=?";
		Object[] obj = {"555555", 6};
		int count = qr.update(conn, sql, obj);
		System.out.println(count);
		conn.close();
	}
	
	/**
	 * ���¶��ռλ����SQL���
	 */
	@Test
	public void testInsert() throws Exception {
		String sql = "INSERT INTO Admin(userName,pwd) VALUES (?,?)";
		conn = JdbcUtil.getConnection();
		qr = new QueryRunner();
		Object[] obj = {"����", "666666"};
		int count = qr.update(conn, sql, obj);
		System.out.println(count);
		DbUtils.close(conn);
	}
	
	/**
	 * ����һ��ռλ����SQL���
	 */
	@Test
	public void testDelete() throws Exception {
		String sql = "DELETE FROM Admin WHERE id=?";
		conn = JdbcUtil.getConnection();
		qr = new QueryRunner();
		int count = qr.update(conn, sql, "7");
		System.out.println(count);
		conn.close();
	}
	
	/**
	 * ����û��ռλ����SQL���
	 */
	@Test
	public void testName() throws Exception {
		String sql = "DELETE FROM Admin";
		conn = JdbcUtil.getConnection();
		qr = new QueryRunner();
		int count = qr.update(conn, sql);
		System.out.println(count);
		DbUtils.close(conn);
	}
	
	/**
	 * ����������ռλ����SQL���
	 */
	@Test
	public void testBatch() throws Exception {
		String sql = "INSERT INTO Admin(userName,pwd) VALUES (?,?)";
		conn = JdbcUtil.getConnection();
		qr = new QueryRunner();
		Object[][] obj = {{"����", 333333}, {"����", 444444}, {"����", 555555}};
		int[] count = qr.batch(conn, sql, obj);
		for (int i : count) {
			System.out.println(i);
		}
		conn.close();
	}
}
