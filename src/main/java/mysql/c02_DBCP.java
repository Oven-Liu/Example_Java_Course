package mysql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

public class c02_DBCP {
	
	/**
	 * 硬编码方式实现连接池
	 */
	@Test
	public void test1() throws Exception {
		// 创建连接池
		BasicDataSource bds = new BasicDataSource();
		// 设置连接池参数
		bds.setUrl("jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true");
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setInitialSize(3);
		bds.setMaxIdle(4);	// 连接池空闲最大连接数
//		bds.setMaxActive(6);
		bds.setMaxTotal(6);	// 最大连接数
		bds.setMaxWaitMillis(1000);	// 最大等待时间
		bds.setMaxConnLifetimeMillis(10000);	// 最大连接时间
		// 获取连接
		Connection con = bds.getConnection();
		ResultSet rs = con.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}
		// 关闭资源
		bds.close();
	}
	
	/**
	 * 配置方式实现连接池
	 */
	@Test
	public void test2() throws Exception {
		Properties prop = new Properties();
		InputStream inStream = c02_DBCP.class.getResourceAsStream("/Text/db.properties");
		prop.load(inStream);
		// 创建连接池的实例
		BasicDataSource bds = BasicDataSourceFactory.createDataSource(prop);
		Connection conn = bds.getConnection();
		ResultSet rs = conn.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}
		conn.close();
	}
}
