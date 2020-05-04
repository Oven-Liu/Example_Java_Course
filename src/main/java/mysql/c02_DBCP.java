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
	 * Ӳ���뷽ʽʵ�����ӳ�
	 */
	@Test
	public void test1() throws Exception {
		// �������ӳ�
		BasicDataSource bds = new BasicDataSource();
		// �������ӳز���
		bds.setUrl("jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true");
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setInitialSize(3);
		bds.setMaxIdle(4);	// ���ӳؿ������������
//		bds.setMaxActive(6);
		bds.setMaxTotal(6);	// ���������
		bds.setMaxWaitMillis(1000);	// ���ȴ�ʱ��
		bds.setMaxConnLifetimeMillis(10000);	// �������ʱ��
		// ��ȡ����
		Connection con = bds.getConnection();
		ResultSet rs = con.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}
		// �ر���Դ
		bds.close();
	}
	
	/**
	 * ���÷�ʽʵ�����ӳ�
	 */
	@Test
	public void test2() throws Exception {
		Properties prop = new Properties();
		InputStream inStream = c02_DBCP.class.getResourceAsStream("/Text/db.properties");
		prop.load(inStream);
		// �������ӳص�ʵ��
		BasicDataSource bds = BasicDataSourceFactory.createDataSource(prop);
		Connection conn = bds.getConnection();
		ResultSet rs = conn.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}
		conn.close();
	}
}
