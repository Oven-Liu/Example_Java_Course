package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import util.JdbcUtil;


/**
 * ʹ��CallableStatement���ô������
 * 
 * @author mengs
 */
public class a08_ExamlpeCallableStatement {

	Connection con = null;
	
	/**
	 * ִ�д�����������Ĵ������
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
	 * ִ�д�����������Ĵ������
	 */
	@Test
	public void testOut() {
		CallableStatement callsta = null;
//		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "CALL pro_findById2(?,?)";
			callsta = con.prepareCall(sql);
			// �����������
			callsta.setInt(1, 5);
			// �������������ע�����������
			callsta.registerOutParameter(2, java.sql.Types.VARCHAR);
//			rs = callsta.executeQuery(); // ������Ƿ��ص�������У����Ƿ��ص����������
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
