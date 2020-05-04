package concurrent;

import java.util.concurrent.Semaphore;

/**
 * �����߳� a��b��c �������У�b,c ��Ҫ a �̵߳�����
 * 
 * @author Xudong.Liu  2018��9��6�� ����2:05:25
 */
public class b06_Semaphore {

	private static int num; // �����߳� A �е�����
	
	/* 
	 * ����һ���ź����������ڲ�ά���˶���߳�����������������̣߳��ͷŶ���̣߳�	
	 * �̵߳��������ͷ���ͨ�� permit ������ʵ�ֵ��߳�ͨ�� semaphore.acquire()������ȡ permit��
	 * �����ǰ semaphore �� permit ���������̣߳����û�����������߳�ֱ�� semaphore
	 * ���� release() �����ͷ� permit
	 */
	private static Semaphore semaphore = new Semaphore(0);

	public static void main(String[] args) {
		
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000); // ģ���ʱ����֮��ĳ�ʼ������ num
					num = 1;
					semaphore.release(2); // ��ʼ�����֮���ͷ����� permit
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// ��ȡ permit����� semaphore û�п��õ� permit ��ȴ��������������һ��
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ��"+num);
			}
		});
		
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// ��ȡ permit����� semaphore û�п��õ� permit ��ȴ��������������һ��
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ��"+num);
			}
		});
		
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
