package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c03_C3P0 {

	/**
	 * c3p0���ӳع������ӣ�ʹ��Ӳ���뷽ʽ
	 */
	@Test
	public void test1() throws Exception {
		// �������ӳ�
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		// �������Ӳ���
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true");
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setUser("root");
		cpds.setPassword("root");
		cpds.setInitialPoolSize(3);
		cpds.setMaxPoolSize(6);
		cpds.setMaxIdleTimeExcessConnections(1000);
		cpds.setMaxIdleTime(3000);
		cpds.setMaxConnectionAge(60*60*3);
		// ��ȡ����
		Connection conn = cpds.getConnection();
		ResultSet rs = conn.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}
		// �ر���Դ
		conn.close();
	}
	
	/**
	 * c3p0���ӳع������ӣ�XML���÷�ʽ
	 */
	@Test
	public void test2() throws Exception {
		// �������ӳأ��Զ�����src�µ�c3p0-config.xml�����ļ�
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		// ��ȡ����
		Connection conn = cpds.getConnection();
		/*ResultSet rs = conn.prepareStatement("SELECT * FROM Admin").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + ":" + rs.getString("userName") + "," + rs.getString("pwd"));
		}*/
		String sql = "INSERT INTO employee2(empName,depId) VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < 13; i++) {
			pstmt.setString(1, "����"+(i+1)+"");
			pstmt.setInt(2, i+1);
			pstmt.executeUpdate();
		}
		// �ر���Դ
		conn.close();
	}
}
