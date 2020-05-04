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
			// ָ����������������
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "����");
			pstmt.setString(2, "Ů");
			pstmt.executeUpdate();
			// ��ȡ����������
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int content = rs.getInt(1);
				System.out.println("statment�����������Ϊ��"+content);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
}
