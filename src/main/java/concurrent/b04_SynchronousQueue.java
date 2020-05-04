package concurrent;

import java.util.concurrent.SynchronousQueue;

public class b04_SynchronousQueue {
	
	public static void main(String[] args) throws InterruptedException {

		final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
		
		Thread putThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("put thread start");
				try {
					queue.put(1);
				} catch (InterruptedException e) {
				}
				System.out.println("put thread end");
			}
		});

		Thread takeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("take thread start");
				try {
					System.out.println("take from putThread: " + queue.take());
				} catch (InterruptedException e) {
				}
				System.out.println("take thread end");
			}
		});

		putThread.start();
		Thread.sleep(2000);
		takeThread.start();
	}
}
