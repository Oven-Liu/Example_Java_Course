package mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class b02_AutoIncrement {

	@Test
	public void testGeAutoIncrement() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtil.getConnection();
			String sql = "INSERT INTO statement (NAME,gender) VALUES (?,?);";
			// 指定返回自增长主键
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "李四");
			pstmt.setString(2, "女");
			pstmt.executeUpdate();
			// 获取自增长主键
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int content = rs.getInt(1);
				System.out.println("statment的自增长编号为："+content);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
}
