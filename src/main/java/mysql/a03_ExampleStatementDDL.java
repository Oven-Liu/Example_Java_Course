package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class a03_ExampleStatementDDL {
	private String url = "jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true";
	private String user = "root";
	private String password = "root";
	Connection connection = null;
	Statement statement = null;
	
	@Test
	public void test() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			String sql = "CREATE TABLE employee(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2),age INT,post VARCHAR(20),email VARCHAR(20),phone VARCHAR(20))";
			int content = statement.executeUpdate(sql);
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
}

