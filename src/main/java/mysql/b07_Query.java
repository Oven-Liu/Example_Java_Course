package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import basedao.Admin;
import util.JdbcUtil;

public class b07_Query {
	
	Connection conn = null;
	
	/**
	 * 1���Զ����ѯ�������������װ���ݣ�һ�����ݣ�
	 */
	@Test
	public void testQuery() throws Exception {
		String sql = "SELECT * FROM Admin WHERE id=?";
		conn = JdbcUtil.getConnection();
		// ����queryRunner����
		QueryRunner qr = new QueryRunner();
		// ��װ��ѯ�����
		Admin admin = qr.query(conn, sql, new ResultSetHandler<Admin>() {
			@Override
			public Admin handle(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Admin admin = new Admin();
					admin.setId(rs.getInt("id"));
					admin.setUserName(rs.getString("userName"));
					admin.setPwd(rs.getString("pwd"));
					return admin;
				}
				return null;
			}
		}, 4);
		System.out.println(admin);
		// �ر�connection
		conn.close();
	}
	
	/**
	 * 2���Զ����ѯ�������������װ���ݣ��������ݣ�
	 */
	@Test
	public void testQuery2() throws Exception {
		String sql = "SELECT * FROM Admin";
		conn = JdbcUtil.getConnection();
		// ����queryRunner����
		QueryRunner qr = new QueryRunner();
		// ��װ��ѯ�����
		ArrayList<Admin> list = qr.query(conn, sql, new ResultSetHandler<ArrayList<Admin>>() {
			@Override
			public ArrayList<Admin> handle(ResultSet rs) throws SQLException {
				ArrayList<Admin> list = new ArrayList<Admin>();
				Admin admin = null;
				while (rs.next()) {
					admin = new Admin();
					admin.setId(rs.getInt("id"));
					admin.setUserName(rs.getString("userName"));
					admin.setPwd(rs.getString("pwd"));
					list.add(admin);
				}
				return list;
			}
		});
		System.out.println(list);
		// �ر�connection
		conn.close();
	}
	
	/**
	 * 3��ʹ�ò�ѯ�������������װ������ĵ�һ������Ϊ����Ķ���
	 */
	@Test
	public void testBeanHandler() throws Exception {
		String sql = "SELECT * FROM Admin WHERE id=?";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		Admin admin = qr.query(conn, sql, new BeanHandler<Admin>(Admin.class), 5);
		System.out.println(admin);
	}
	
	/**
	 * 4��ʹ�ò�ѯ�������������װ���������������Ϊ��������List����
	 */
	@Test
	public void testBeanListHandler() throws Exception {
		String sql = "SELECT * FROM Admin";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		List<Admin> list = qr.query(conn, sql, new BeanListHandler<Admin>(Admin.class));
		System.out.println(list);
	}
	
	/**
	 * 5����������ĵ�һ�е���ֵ��װΪObject��������
	 */
	@Test
	public void testArrayHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin WHERE id=? AND userName=?";
		QueryRunner qr = new QueryRunner();
		Object[] obj = qr.query(conn, sql, new ArrayHandler(), 6, "�ư�");
		for (Object values : obj) {
			System.out.println(values);
		}
	}
	
	/**
	 * 5����������ĵ�һ�е���ֵ��װΪObject��������
	 */
	@Test
	public void testArrayHandler2() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
		Object[] obj = qr.query(conn, sql, new ArrayHandler());
		for (Object values : obj) {
			System.out.println(values);
		}
	}
	
	/**
	 * 6�����������ÿһ�е���ֵ��װΪObject�������飬�ٽ�������ӵ�List������
	 */
	@Test
	public void testArrayListHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
		List<Object[]> list = qr.query(conn, sql, new ArrayListHandler());
		for (Object[] objects : list) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
	}
	
	/**
	 * 7����������ĵ�һ��ĳһ�е�ֵ��װΪ�������
	 */
	@Test
	public void testScalarHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
//		Integer value = qr.query(conn, sql, new ScalarHandler<Integer>());
//		int value = qr.query(conn, sql, new ScalarHandler<Integer>());
		String value = qr.query(conn, sql, new ScalarHandler<String>(2));
		System.out.println(value);
	}
	
	/**
	 * 8����������ĵ�һ�е��е�������Ϊ������ֵ��Ϊֵ��װΪMap����
	 */
	@Test
	public void testMapHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
		Map<String, Object> map = qr.query(conn, sql, new MapHandler());
		Set<Map.Entry<String, Object>> entrys = map.entrySet();
		Iterator<Map.Entry<String, Object>> it = entrys.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		System.out.println(map);
	}
	
	/**
	 * 9�����������ĳһ��ֵ��Ϊ����ÿһ�з�װΪ����Ķ�����Ϊֵ������ӵ�Map������
	 * ���������ı���ʹ��������ֵ��ΪMap�ļ�
	 */
	@Test
	public void testBeanMapHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
//		Map<Integer, Admin> map = qr.query(conn, sql, new BeanMapHandler<Integer, Admin>(Admin.class), "id");
		Map<Integer, Admin> map = qr.query(conn, sql, new BeanMapHandler<Integer, Admin>(Admin.class));
//		Map<Integer, Admin> map = qr.query(conn, sql, new BeanMapHandler<Integer, Admin>(Admin.class), 1);
		Set<Entry<Integer, Admin>> entrys = map.entrySet();
		Iterator<Entry<Integer, Admin>> it = entrys.iterator();
		while (it.hasNext()) {
			Entry<Integer, Admin> entry = it.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
//		System.out.println(map);
	}
	
	/**
	 * 9�����������ĳһ��ֵ��Ϊ����ÿһ�з�װΪ����Ķ�����Ϊֵ������ӵ�Map������
	 * ��û�������ı���Ĭ�ϵ�һ�е�ֵ��ΪMap�ļ�
	 */
	@Test
	public void testBeanMapHandler2() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin2";
		QueryRunner qr = new QueryRunner();
		Map<Integer, Admin> map = qr.query(conn, sql, new BeanMapHandler<Integer, Admin>(Admin.class));
		Set<Entry<Integer, Admin>> entrys = map.entrySet();
		Iterator<Entry<Integer, Admin>> it = entrys.iterator();
		while (it.hasNext()) {
			Entry<Integer, Admin> entry = it.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		System.out.println(map);
	}
	
	/**
	 * 10�����������ĳһ�У�Ĭ�ϵ�һ�У���ֵ��װΪList����
	 */
	@Test
	public void testColumnListHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
//		List<String> list = qr.query(conn, sql, new ColumnListHandler<String>("userName"));
		List<String> list = qr.query(conn, sql, new ColumnListHandler<String>(3));
		for (String value : list) {
			System.out.println(value);
		}
	}
	
	/**
	 * 11�����������������Ϊ����ÿһ�е�ֵ��Ϊֵ����ӵ�Map�����У�ÿһ�ж���װΪһ��Map���ϣ�
	 * �ٽ��������ĳһ�У�Ĭ�ϵ�һ�л������У���ֵ��Ϊ����ÿһ�з�װΪ��Map������Ϊֵ����װ��Map�����С�
	 */
	@Test
	public void testKeyedHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
		Map<Integer, Map<String, Object>> map = qr.query(conn, sql, new KeyedHandler<Integer>());
		Set<Entry<Integer, Map<String, Object>>> set = map.entrySet();
		Iterator<Entry<Integer, Map<String, Object>>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Integer, Map<String, Object>> entry = it.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		System.out.println(map);
	}
	
	/**
	 * 12�����������������Ϊ����ÿһ�е�ֵ��Ϊֵ����ӵ�Map�����У�ÿһ�ж���װΪһ��Map���ϣ�
	 * �ٽ����е�Map��װ��List�����С�
	 */
	@Test
	public void testMapListHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin";
		QueryRunner qr = new QueryRunner();
		List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		System.out.println(list);
	}
}
