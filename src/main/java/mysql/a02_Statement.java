package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

/**
 * ʹ��Statement����ִ�о�̬SQL���
 * 
 * @author mengs
 */
public class a02_Statement {
	private String url="jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true";
	private String user="root";
	private String password="root";
	
	Statement statement = null;
	Connection con = null;
			
	@Test
	public void test() {
		try {
			// ע����������
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ���Ӷ���
			con = DriverManager.getConnection(url, user, password);
//			Connection connection = DriverManager.getConnection(url, user, password);
			// ����Statement����
			statement = con.createStatement();
			// ׼��sql���
			String sql = "CREATE TABLE statement(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
			// ����sql��䣬ִ��sql��䣬�õ����ؽ��
			int content = statement.executeUpdate(sql);
			// ���
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// �ر���ԴStatement��Connection����򿪵��ȹ�
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
}
