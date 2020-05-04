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
	 * �������ݿⷽʽһ
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
	 * �������ݿⷽʽ��
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
	 * �������ݿⷽʽ��
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}
}
