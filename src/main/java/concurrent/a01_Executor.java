package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author Xudong.Liu  2018年9月1日 下午11:17:51
 */
public class a01_Executor {

	public static void main(String[] args) {
		//fixedThreadPool();
		//singleThreadExecutor();
		//cachedThreadPool();
		scheduledThreadPool();

	}
	
	/**
	 * 创建固定大小的连接池
	 */
	public static void fixedThreadPool() {
		// 创建一个可重用、固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		// 创建线程对象
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入线程池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}
	
	/**
	 * 创建单任务连接池
	 */
	public static void singleThreadExecutor() {
		// 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// 创建线程对象
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入线程池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}
	
	/**
	 * 创建可变连接池
	 */
	public static void cachedThreadPool() {
		// 创建一个可变连接池
		ExecutorService pool = Executors.newCachedThreadPool();
		// 创建线程对象
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入线程池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}
	
	/**
	 * 创建延迟连接池
	 */
	public static void scheduledThreadPool() {
		// 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		// 创建线程对象
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入线程池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		// 使用定时执行
		pool.schedule(t4, 3, TimeUnit.SECONDS); // 延迟 3 秒后执行
		pool.schedule(t5, 5, TimeUnit.SECONDS); // 延迟 5 秒后执行，t4 执行后 2 秒再执行
		// 关闭线程池
		pool.shutdown();
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行...");
	}
}