package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用定时器，间隔 4 秒执行一次，再间隔 2 秒执行一次，间隔 4 秒执行一次，以此类推执行。双重定时器
 * @author Xudong.Liu  2018年9月1日 下午3:15:06
 */
public class TestTimer {
	private static volatile int count = 0;
	
	public static void main(String[] args) {
		new Timer().schedule(new MyTimerTask(), 0);
		
		/* 每秒打印出当前秒数 */
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 静态内部类
	 */
	static class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			count = (count + 1) % 2;
			System.out.println("Hello Word");
			new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count); // 递归，每隔 4 或 2 秒调用一次
		}
	}
}
