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
	 * 1、添加数据
	 */
	@Test
	public void testInsert() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "INSERT INTO statement (NAME,gender) VALUES (?,?)";
			//	预编译，检查语法
			presta = con.prepareStatement(sql);
			//	设置参数，占位符按顺序从1开始编号
			presta.setString(1, "赵六");
			presta.setString(2, "男");
			//	发送参数，执行sql语句
			int content = presta.executeUpdate();
			System.out.println("影响了"+content+"行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
	
	/**
	 * 2、修改数据
	 */
	@Test
	public void testUpdate() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "UPDATE statement SET NAME=? WHERE id=?";
			//	预编译，检查语法
			presta = con.prepareStatement(sql);
			//	设置参数，占位符按顺序从1开始编号
			presta.setString(1, "马七");
			presta.setInt(2, 7);
			//	发送参数，执行sql语句
			int content = presta.executeUpdate();
			System.out.println("影响了"+content+"行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
	
	/**
	 * 3、删除数据
	 */
	@Test
	public void testDelete() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "DELETE FROM statement WHERE id=?";
			//	预编译，检查语法
			presta = con.prepareStatement(sql);
			//	设置参数，占位符按顺序从1开始编号
			presta.setInt(1, 9);
			//	发送参数，执行sql语句
			int content = presta.executeUpdate();
			System.out.println("影响了"+content+"行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
	
	/**
	 * 4、查询数据
	 */
	@Test
	public void testQuery() {
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM statement";
			//	预编译，检查语法
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
