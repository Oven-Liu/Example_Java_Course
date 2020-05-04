package mysql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class c01_ConnectionPool {

	private int initCount = 3;	// ��ʼ����������
	private int maxCount = 6;	// ��������������
	private int currentCount = 0;	// ��¼��ǰ��ʹ�õ�������
	// ����ʼ�����ӷ���list�У������ӳ��г�ʼ��������
	private LinkedList<Connection> list = new LinkedList<Connection>();

	/**
	 * ���캯������ʼ�����ӷ������ӳ�
	 */
	public c01_ConnectionPool() {
		for (int i = 0; i < initCount; i++) {
			Connection con = createConnection();
			currentCount++;
			list.addFirst(con);
		}
	}

	/**
	 * ����һ��������
	 * 
	 * @return Connection
	 */
	private Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlforjava?useSSL=true", "root", "root");
			/*
			 * ����Connection����Ĵ������
			 */
			Connection proxy = (Connection) Proxy.newProxyInstance(
					Connection.class.getClassLoader(), // ��con.getClass().getClassLoader()
					new Class[]{Connection.class}, 
					new InvocationHandler() {
		
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							Object result = null;
							String methodName = method.getName();
							/*
							 * ��չclose�����Ĺ���
							 */
							if ("close".equals(methodName)) {
								System.out.println("�����˴������");
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
	 * ��ȡ����
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
			throw new RuntimeException("��ǰ�������Դﵽ����ֵ");
		}
	}
	
	/**
	 * �ͷ�������Դ
	 * 
	 * @param con Connection
	 */
	public void releaseConnection(Connection con) {
		if (list.size() < initCount) {
			list.addFirst(con);
		} else {
			try {
				con.close();
//				currentCount--;	// ��Ϊ���ø÷�����ʱ�򣬾͵����˴�����������������У��ұ����У�currentCount--�ķ�����
								// ����Ϊ�����ظ�ʹ�øô��룬����Ͳ�����ִ�С�
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
//		cp.getConnection();	// �����û��Լ��ر����ӣ�û��ͳ�Ƶ����ݣ�������쳣
		System.out.println("��ǰ�û�����������" + cp.currentCount);
		System.out.println("��ǰ���ӳ�������" + cp.list.size());
	}
}
