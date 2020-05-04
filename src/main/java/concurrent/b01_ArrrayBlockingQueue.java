package concurrent;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * �ڶ��̲߳����£�һ�����������ֻ�ܴ��� 3 ��Ԫ�ء�����벻���Դ������飬
 * ��ȴ�ĳ�̶߳�������ĳ��Ԫ��ȡ�߲��ܷ��룬Ҫ��ʹ�� java �Ķ��߳���ʵ��
 * @author Xudong.Liu  2018��9��3�� ����10:07:40
 */
public class b01_ArrrayBlockingQueue {

	public static void main(String[] args) {
		final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
		
		for (int i = 0; i < 2; i++) {
			new Thread() {
				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep((long) (Math.random() * 1000));
							System.out.println(Thread.currentThread().getName() + "׼��������");
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "�Ѿ��������ݣ�" + 
									"����Ŀǰ��" + queue.size() + "������");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		
		new Thread() {
			@Override
			public void run() {
				try {
					// ���˴���˯��ʱ��ֱ��Ϊ 100 �� 1000���۲����н��
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "׼��ȡ����");
					System.out.println(queue.take());
					System.out.println(Thread.currentThread().getName() + "�Ѿ�ȡ�������ݣ�" +
							"����Ŀǰ��" + queue.size() + "������");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
