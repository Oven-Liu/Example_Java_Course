package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor �̳߳�ִ����
 * @author Xudong.Liu  2018��9��2�� ����4:40:30
 */
public class a03_ThreadPoolExecutor {
	public static void main(String[] args) {
		
	}
	
	public static void testCreate() {
		int corePoolSize = 5; // �̳߳��б�����߳��������������߳�
		int maxPoolSize = 10;  // �̳߳������������߳���
		long keepAliveTime = 5000; // ���߳������ں�����ʱ����Ϊ��ֹǰ����Ŀ����̵߳ȴ���������ʱ��
		TimeUnit timeUnit = TimeUnit.SECONDS; // keepAliveTime ������ʱ�䵥λ
		// ִ��ǰ���ڱ�������Ķ��У��˶��н������� execute �����ύ�� Runnable ����
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
	}
}
