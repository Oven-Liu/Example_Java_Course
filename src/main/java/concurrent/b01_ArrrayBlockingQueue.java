package concurrent;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 在多线程操作下，一个数组中最多只能存入 3 个元素。多放入不可以存入数组，
 * 或等待某线程对数组中某个元素取走才能放入，要求使用 java 的多线程来实现
 * @author Xudong.Liu  2018年9月3日 下午10:07:40
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
							System.out.println(Thread.currentThread().getName() + "准备放数据");
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "已经放了数据，" + 
									"队列目前有" + queue.size() + "个数据");
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
					// 将此处的睡眠时间分别改为 100 和 1000，观察运行结果
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "准备取数据");
					System.out.println(queue.take());
					System.out.println(Thread.currentThread().getName() + "已经取走了数据，" +
							"队列目前有" + queue.size() + "个数据");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
