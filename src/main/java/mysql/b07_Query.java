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
	 * 1、自定义查询结果集处理器封装数据（一条数据）
	 */
	@Test
	public void testQuery() throws Exception {
		String sql = "SELECT * FROM Admin WHERE id=?";
		conn = JdbcUtil.getConnection();
		// 创建queryRunner对象
		QueryRunner qr = new QueryRunner();
		// 封装查询结果集
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
		// 关闭connection
		conn.close();
	}
	
	/**
	 * 2、自定义查询结果集处理器封装数据（多条数据）
	 */
	@Test
	public void testQuery2() throws Exception {
		String sql = "SELECT * FROM Admin";
		conn = JdbcUtil.getConnection();
		// 创建queryRunner对象
		QueryRunner qr = new QueryRunner();
		// 封装查询结果集
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
		// 关闭connection
		conn.close();
	}
	
	/**
	 * 3、使用查询结果集处理器封装结果集的第一行数据为该类的对象
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
	 * 4、使用查询结果集处理器封装结果集的所有数据为该类对象的List集合
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
	 * 5、将结果集的第一行的列值封装为Object对象数组
	 */
	@Test
	public void testArrayHandler() throws Exception {
		conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM Admin WHERE id=? AND userName=?";
		QueryRunner qr = new QueryRunner();
		Object[] obj = qr.query(conn, sql, new ArrayHandler(), 6, "唐八");
		for (Object values : obj) {
			System.out.println(values);
		}
	}
	
	/**
	 * 5、将结果集的第一行的列值封装为Object对象数组
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
	 * 6、将结果集的每一行的列值封装为Object对象数组，再将数组添加到List集合中
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
	 * 7、将结果集的第一行某一列的值封装为该类对象
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
	 * 8、将结果集的第一行的列的名称作为键，列值作为值封装为Map对象
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
	 * 9、将结果集的某一列值作为键，每一行封装为该类的对象作为值，再添加到Map集合中
	 * 在有主键的表中使用主键列值作为Map的键
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
	 * 9、将结果集的某一列值作为键，每一行封装为该类的对象作为值，再添加到Map集合中
	 * 在没有主键的表中默认第一列的值作为Map的键
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
	 * 10、将结果集的某一行（默认第一行）的值封装为List集合
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
	 * 11、将结果集的列名作为键，每一列的值作为值，添加到Map集合中，每一行都封装为一个Map集合，
	 * 再将结果集中某一列（默认第一列或主键列）的值作为键，每一行封装为的Map集合作为值，封装到Map集合中。
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
	 * 12、将结果集的列名作为键，每一列的值作为值，添加到Map集合中，每一行都封装为一个Map集合，
	 * 再将所有的Map封装到List集合中。
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
