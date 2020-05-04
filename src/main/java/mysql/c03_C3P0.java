package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c03_C3P0 {

	/**
	 * c3p0连接池管理连接，使用硬编码方式
	 */
	@Test
	public void test1() throws Exception {
		// 创建连接池
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		// 设置连接参数
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true");
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setUser("root");
		cpds.setPassword("root");
		cpds.setInitialPoolSize(3);
		cpds.setMaxPoolSize(6);
		cpds.setMaxIdleTimeExcessConnections(1000);
		cpds.setMaxIdleTime(3000);
		cpds.setMaxConnectionAge(60*60*3);
		// 获取连接
		Connection conn = cpds.getConnection();
		ResultSet rs = conn.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}
		// 关闭资源
		conn.close();
	}
	
	/**
	 * c3p0连接池管理连接，XML配置方式
	 */
	@Test
	public void test2() throws Exception {
		// 创建连接池，自动加载src下的c3p0-config.xml配置文件
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		// 获取连接
		Connection conn = cpds.getConnection();
		/*ResultSet rs = conn.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}*/
		String sql = "INSERT INTO employee2(empName,depId) VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < 13; i++) {
			pstmt.setString(1, "李四"+(i+1)+"");
			pstmt.setInt(2, i+1);
			pstmt.executeUpdate();
		}
		// 关闭资源
		conn.close();
	}
}
