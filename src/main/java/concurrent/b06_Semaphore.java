package concurrent;

import java.util.concurrent.Semaphore;

/**
 * 三个线程 a、b、c 并发运行，b,c 需要 a 线程的数据
 * 
 * @author Xudong.Liu  2018年9月6日 下午2:05:25
 */
public class b06_Semaphore {

	private static int num; // 定义线程 A 中的数据
	
	/* 
	 * 定义一个信号量，该类内部维持了多个线程锁，可以阻塞多个线程，释放多个线程，	
	 * 线程的阻塞和释放是通过 permit 概念来实现的线程通过 semaphore.acquire()方法获取 permit，
	 * 如果当前 semaphore 有 permit 则分配给该线程，如果没有则阻塞该线程直到 semaphore
	 * 调用 release() 方法释放 permit
	 */
	private static Semaphore semaphore = new Semaphore(0);

	public static void main(String[] args) {
		
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000); // 模拟耗时操作之后的初始化变量 num
					num = 1;
					semaphore.release(2); // 初始化完成之后释放两个 permit
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗一个
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"获取到 num 的值为："+num);
			}
		});
		
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗一个
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"获取到 num 的值为："+num);
			}
		});
		
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
