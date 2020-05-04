package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 线程池执行者
 * @author Xudong.Liu  2018年9月2日 下午4:40:30
 */
public class a03_ThreadPoolExecutor {
	public static void main(String[] args) {
		
	}
	
	public static void testCreate() {
		int corePoolSize = 5; // 线程池中保存的线程数，包括空闲线程
		int maxPoolSize = 10;  // 线程池中允许的最大线程数
		long keepAliveTime = 5000; // 当线程数大于核心数时，此为终止前多余的空闲线程等待新任务的最长时间
		TimeUnit timeUnit = TimeUnit.SECONDS; // keepAliveTime 参数的时间单位
		// 执行前用于保持任务的队列，此队列仅保持由 execute 方法提交的 Runnable 任务
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
	}
}
