package concurrent;

import java.util.concurrent.Semaphore;

public class b05_Semaphore {

	static Semaphore semaphore = new Semaphore(5, true);
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					test();
				}
			}).start();
		}
	}
	
	public static void test() {
		try {
			semaphore.acquire();
		} catch(InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "进来了");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "走了");
		semaphore.release();
	}
}
