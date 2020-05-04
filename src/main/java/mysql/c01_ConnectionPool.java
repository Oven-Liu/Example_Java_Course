package mysql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class c01_ConnectionPool {

	private int initCount = 3;	// 初始化的连接数
	private int maxCount = 6;	// 允许的最大连接数
	private int currentCount = 0;	// 记录当前正使用的连接数
	// 将初始化连接放入list中，即连接池中初始化连接数
	private LinkedList<Connection> list = new LinkedList<Connection>();

	/**
	 * 构造函数，初始化连接放入连接池
	 */
	public c01_ConnectionPool() {
		for (int i = 0; i < initCount; i++) {
			Connection con = createConnection();
			currentCount++;
			list.addFirst(con);
		}
	}

	/**
	 * 创建一个新连接
	 * 
	 * @return Connection
	 */
	private Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true", "root", "root");
			/*
			 * 创建Connection对象的代理对象
			 */
			Connection proxy = (Connection) Proxy.newProxyInstance(
					Connection.class.getClassLoader(), // 或con.getClass().getClassLoader()
					new Class[]{Connection.class}, 
					new InvocationHandler() {
		
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							Object result = null;
							String methodName = method.getName();
							/*
							 * 扩展close方法的功能
							 */
							if ("close".equals(methodName)) {
								System.out.println("调用了代理对象");
								if (list.size() < initCount) {
									list.add(con);	
								} else {
									currentCount--;
								}
							} /*else {
								result = method.invoke(con, args);
							}*/
							return result;
						}
					});
			return proxy;
//			return con;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取连接
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		if (list.size() > 0) {
			return list.removeFirst();
		} else if (currentCount < maxCount) {
			currentCount++;
			return createConnection();
		} else {
			throw new RuntimeException("当前连接数以达到上限值");
		}
	}
	
	/**
	 * 释放连接资源
	 * 
	 * @param con Connection
	 */
	public void releaseConnection(Connection con) {
		if (list.size() < initCount) {
			list.addFirst(con);
		} else {
			try {
				con.close();
//				currentCount--;	// 因为调用该方法的时候，就调用了代理，而代理程序中已有（且必须有）currentCount--的方法，
								// 所以为避免重复使用该代码，这里就不得再执行。
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void main(String[] args) {
		c01_ConnectionPool cp = new c01_ConnectionPool();
		Connection con1 = cp.getConnection();
		Connection con2 = cp.getConnection();
		Connection con3 = cp.getConnection();
		Connection con4 = cp.getConnection();
		Connection con5 = cp.getConnection();
		Connection con6 = cp.getConnection();
		cp.releaseConnection(con1);
		cp.releaseConnection(con2);
		cp.releaseConnection(con3);
		cp.releaseConnection(con4);
		cp.releaseConnection(con5);
//		cp.getConnection();
		try {
//			con3.close();
//			con4.close();
//			con5.close();
			con6.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
//		cp.getConnection();	// 由于用户自己关闭连接，没有统计到数据，会出现异常
		System.out.println("当前用户连接数量：" + cp.currentCount);
		System.out.println("当前连接池数量：" + cp.list.size());
	}
}
