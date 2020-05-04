package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �������� - ����ʱ�ޣ��̳߳��Ի�ȡ����ʱ�����һ����ʱ�ޣ�����ʱ��������Ը��������󣬲��ͷ��Լ�ռ�е�����
 * 
 * @author Xudong.Liu  2018��9��6�� ����3:38:05
 */
public class a02_SolveDeadlock {
	public int flag = 1; 
	// ��̬������������ж������
	private static Object o1 = new Object(), o2 = new Object(); 
	
	public void money(int flag) {
		this.flag=flag;
		if( flag ==1){
			synchronized (o1) { 
				try { 
					Thread.sleep(500); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				synchronized (o2) { 
					System.out.println("��ǰ���߳���"+
							Thread.currentThread().getName()+" "+"flag ��ֵ"+"1"); 
				} 
			} 
		} 
		if(flag ==0){
			synchronized (o2) { 
				try { 
					Thread.sleep(500); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				synchronized (o1) { 
					System.out.println("��ǰ���߳���"+
							Thread.currentThread().getName()+" "+"flag ��ֵ"+"0"); 
				} 
			} 
		}
	} 
	
	public static void main(String[] args) { 
		final Lock lock = new ReentrantLock();
		final a02_SolveDeadlock td1 = new a02_SolveDeadlock(); 
		final a02_SolveDeadlock td2 = new a02_SolveDeadlock(); 
		td1.flag = 1; 
		td2.flag = 0; 
		// td1,td2 �����ڿ�ִ��״̬���� JVM �̵߳�����ִ���ĸ��߳��ǲ�ȷ���ġ�
		// td2 �� run()������ td1 �� run()֮ǰ����
		final Thread t1=new Thread(new Runnable(){
			public void run() {
				String tName = Thread.currentThread().getName();
				td1.flag = 1;
				try {
					// ��ȡ���������͵� 5 �룬��� 5 ����ǻ�ȡ�����ͷ��� false 
					if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
						System.out.println(tName + "��ȡ������");
					} else {
						System.out.println(tName + "��ȡ��������");
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					td1.money(1);
				} catch (Exception e) {
					System.out.println(tName + "�����ˣ�����");
				} finally {
					System.out.println("��ǰ���߳���"+Thread.currentThread().getName()+"�ͷ�������");
					lock.unlock(); 
				}
			} 
		});
		t1.start();

		final Thread t2= new Thread(new Runnable(){
			public void run() {
				String tName = Thread.currentThread().getName();
				td1.flag = 0;
				try {
					// ��ȡ���������͵� 5 �룬��� 5 ����ǻ�ȡ�����ͷ��� false 
					if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
						System.out.println(tName + "��ȡ������");
					} else {
						System.out.println(tName + "��ȡ��������");
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					td2.money(0);
				} catch (Exception e) {
					System.out.println(tName + "�����ˣ�����");
				} finally {
					System.out.println("��ǰ���߳���"+Thread.currentThread().getName()+"�ͷ�������");
					lock.unlock(); 
				}
			} 
		});
		t2.start();
	} 
}
