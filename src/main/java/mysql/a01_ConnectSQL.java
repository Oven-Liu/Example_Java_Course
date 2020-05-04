package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.junit.Test;
import com.mysql.jdbc.Driver;

public class a01_ConnectSQL {

	private String url="jdbc:mysql://localhost:3306/base1?useSSL=true";
	private String user="root";
	private String password="root";
	
	/**
	 * 连接数据库方式一
	 */
	@Test
	public void test1() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("user", user);
		properties.setProperty("password", password);
		
//		Driver driver = new com.mysql.jdbc.Driver();
		Driver driver = new com.mysql.jdbc.Driver();
		Connection connect = driver.connect(url, properties);
		
		System.out.println(connect);
	}
	
	/**
	 * 连接数据库方式二
	 * @throws Exception 
	 */
	@Test
	public void test2() throws Exception {
		Driver driver = new com.mysql.jdbc.Driver();
//		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		
		System.out.println(connection);
	}
	
	/**
	 * 连接数据库方式三
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}
}
