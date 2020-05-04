package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduleExecutorService 定时执行器服务
 * @author Xudong.Liu  2018年9月2日 下午5:19:46
 */
public class a04_ScheduleExecutorService {
	public static void main(String[] args) {
		testCreate();
	}
	
	public static void testCreate() {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		scheduledExecutorService.schedule(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("Executeed!");
				return "Callable";
			}
		}, 3, TimeUnit.SECONDS); // 3 秒后执行
	}
}
