package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import util.JdbcUtil;


/**
 * 使用CallableStatement调用储存过程
 * 
 * @author mengs
 */
public class a08_ExamlpeCallableStatement {

	Connection con = null;
	
	/**
	 * 执行带有输入参数的储存过程
	 */
	@Test
	public void testIn() {
		CallableStatement callsta = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtil.getConnection();
			String sql = "CALL pro_findById(?)";
			callsta = con.prepareCall(sql);
			callsta.setInt(1, 2);
			rs = callsta.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				System.out.println(id+","+name+","+gender);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, callsta, rs);
		}
	}
	
	/**
	 * 执行带有输出参数的储存过程
	 */
	@Test
	public void testOut() {
		CallableStatement callsta = null;
//		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "CALL pro_findById2(?,?)";
			callsta = con.prepareCall(sql);
			// 设置输入参数
			callsta.setInt(1, 5);
			// 设置输出参数（注册输出参数）
			callsta.registerOutParameter(2, java.sql.Types.VARCHAR);
//			rs = callsta.executeQuery(); // 结果不是返回到结果集中，而是返回到输出参数中
			callsta.executeQuery();
			String content = callsta.getString(2);
			System.out.println(content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
//			JdbcUtil.closeResource(con, callsta, rs);
			JdbcUtil.closeResource(con, callsta, null);
		}
	}
}
