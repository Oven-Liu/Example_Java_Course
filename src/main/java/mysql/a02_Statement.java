package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

/**
 * 使用Statement对象执行静态SQL语句
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
			// 注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			con = DriverManager.getConnection(url, user, password);
//			Connection connection = DriverManager.getConnection(url, user, password);
			// 创建Statement对象
			statement = con.createStatement();
			// 准备sql语句
			String sql = "CREATE TABLE statement(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
			// 发送sql语句，执行sql语句，得到返回结果
			int content = statement.executeUpdate(sql);
			// 输出
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 关闭资源Statement和Connection，后打开的先关
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
