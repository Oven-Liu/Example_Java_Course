package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳�
 * @author Xudong.Liu  2018��9��1�� ����11:17:51
 */
public class a01_Executor {

	public static void main(String[] args) {
		//fixedThreadPool();
		//singleThreadExecutor();
		//cachedThreadPool();
		scheduledThreadPool();

	}
	
	/**
	 * �����̶���С�����ӳ�
	 */
	public static void fixedThreadPool() {
		// ����һ�������á��̶��߳������̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(2);
		// �����̶߳���
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// ���̷߳����̳߳��н���ִ��
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// �ر��̳߳�
		pool.shutdown();
	}
	
	/**
	 * �������������ӳ�
	 */
	public static void singleThreadExecutor() {
		// ����һ��ʹ�õ��� worker �̵߳� Executor�����޽���з�ʽ�����и��߳�
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// �����̶߳���
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// ���̷߳����̳߳��н���ִ��
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// �ر��̳߳�
		pool.shutdown();
	}
	
	/**
	 * �����ɱ����ӳ�
	 */
	public static void cachedThreadPool() {
		// ����һ���ɱ����ӳ�
		ExecutorService pool = Executors.newCachedThreadPool();
		// �����̶߳���
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// ���̷߳����̳߳��н���ִ��
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// �ر��̳߳�
		pool.shutdown();
	}
	
	/**
	 * �����ӳ����ӳ�
	 */
	public static void scheduledThreadPool() {
		// ����һ���̳߳أ����ɰ����ڸ����ӳٺ�����������߶��ڵ�ִ��
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		// �����̶߳���
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// ���̷߳����̳߳��н���ִ��
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		// ʹ�ö�ʱִ��
		pool.schedule(t4, 3, TimeUnit.SECONDS); // �ӳ� 3 ���ִ��
		pool.schedule(t5, 5, TimeUnit.SECONDS); // �ӳ� 5 ���ִ�У�t4 ִ�к� 2 ����ִ��
		// �ر��̳߳�
		pool.shutdown();
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "����ִ��...");
	}
}