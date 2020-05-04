package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import util.JdbcUtil;

/**
 * 模拟用户登录
 * 
 * @author mengs
 */
public class a07_ExmapleLogin {
	
//	private String name = "eric";
//	private String pw = "123456";
	private String name = "xxx' OR 1=1 -- '";
	private String pw = "xxx";
	
	/**
	 * Statement存在sql被注入的风险
	 */
	@Test
	public void testLoginByStatement() {
		ResultSet rs = null;
		Connection con = null;
		Statement sta = null;
		try {
			con = JdbcUtil.getConnection();
			sta = con.createStatement();
			String sql = "SELECT * FROM users WHERE NAME='"+name+"' AND PASSWORD='"+pw+"'";
			rs = sta.executeQuery(sql);
			if (rs.next()) {
				System.out.println("登录成功");
			} else {
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, sta, rs);
		}
	}
	
	/**
	 * PreparedStatement可以用防止sql被注入的风险
	 */
	@Test
	public void testLoginByPreparedStatement() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement presta = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM users WHERE NAME=? AND PASSWORD=?";
			presta = con.prepareStatement(sql);
			presta.setString(1, name);
			presta.setString(2, pw);
			rs = presta.executeQuery();
			if (rs.next()) {
				System.out.println("登录成功");
			} else {
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, rs);
		}
	}
}
