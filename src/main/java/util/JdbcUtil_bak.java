package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc mysqlע���������򡢻�ȡ���Ӷ��󡢹ر���Դ����
 * 
 * @author mengs
 */
public class JdbcUtil_bak {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	/**
	 * ע��mysql��������
	 */
	static {
		try {
			Properties properties = new Properties();
//			FileInputStream in = new FileInputStream("./src/db.properties");
			/*
			 * ʹ����·���Ķ�ȡ��ʽ��ȡ
			 * /��б�ܱ�ʾclasspath�ĸ�Ŀ¼
			 * 		��java��Ŀ�У�classpath�ĸ�Ŀ¼��binĿ¼��ʼ
			 * 		��web�У�classpath�ĸ�Ŀ¼��WEB-INF/classesĿ¼��ʼ
			 */
			InputStream in = JdbcUtil_bak.class.getResourceAsStream("/db.properties");
			properties.load(in);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			driverClass = properties.getProperty("driverClass");
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ȡ���Ӷ���
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �ر���Դ
	 * @param connection
	 * @param statement
	 */
	public static void closeResource(Connection con, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
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
	
	/**
	 * �ر���Դ����������
	 * 
	 * @param connection
	 * @param statement
	 * @param presta
	 */
	public static void closeResource(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;	// ������������վ������Դ
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;	// ������������վ������Դ
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (con != null) {
			try {
				con.close();
				con = null;	// ������������վ������Դ
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
