package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class d01_AtomicBoolean {
	public static void main(String[] args) {
		/*BarWorker bar1 = new BarWorker("bar1");
		BarWorker bar2 = new BarWorker("bar2");
		new Thread(bar1).start();
		new Thread(bar2).start();*/
		/* 	bar1 enter
			bar2 enter
			bar2 working
			bar1 working
			bar1 leave
			bar2 leave
		 */
		
		/* 使用 AtomicBoolean */
		BarWorker2 bar1 = new BarWorker2("bar1");
		BarWorker2 bar2 = new BarWorker2("bar2");
		new Thread(bar1).start();
		new Thread(bar2).start();
		/*  bar1 enter
			bar1 working
			bar2 give up
			bar1 leave
		 */
	}
}

class BarWorker implements Runnable {
	//静态变量
	private static boolean exists = false;
	private String name;
	public BarWorker(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		if (!exists) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				// do nothing
			}
			exists = true;
			System.out.println(name + " enter");
			try {
				System.out.println(name + " working");
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// do nothing
			}
			System.out.println(name + " leave");
			exists = false;
		} else {
			System.out.println(name + " give up");
		}
	}
}

class BarWorker2 implements Runnable {
	// 静态变量使用 AtomicBoolean 进行操作
	private static AtomicBoolean exists2 = new AtomicBoolean(false);
	private String name;
	public BarWorker2(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		if (exists2.compareAndSet(false, true)) {
			System.out.println(name + " enter");
			try {
				System.out.println(name + " working");
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// do nothing
			}
			System.out.println(name + " leave");
			exists2.set(false);
		} else {
			System.out.println(name + " give up");
		}
	}
}

