package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 避免死锁 - 加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）
 * 
 * @author Xudong.Liu  2018年9月6日 下午3:38:05
 */
public class a02_SolveDeadlock {
	public int flag = 1; 
	// 静态对象是类的所有对象共享的
	private static Object o1 = new Object(), o2 = new Object(); 
	
	public void money(int flag) {
		this.flag=flag;
		if( flag ==1){
			synchronized (o1) { 
				try { 
					Thread.sleep(500); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				synchronized (o2) { 
					System.out.println("当前的线程是"+
							Thread.currentThread().getName()+" "+"flag 的值"+"1"); 
				} 
			} 
		} 
		if(flag ==0){
			synchronized (o2) { 
				try { 
					Thread.sleep(500); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				synchronized (o1) { 
					System.out.println("当前的线程是"+
							Thread.currentThread().getName()+" "+"flag 的值"+"0"); 
				} 
			} 
		}
	} 
	
	public static void main(String[] args) { 
		final Lock lock = new ReentrantLock();
		final a02_SolveDeadlock td1 = new a02_SolveDeadlock(); 
		final a02_SolveDeadlock td2 = new a02_SolveDeadlock(); 
		td1.flag = 1; 
		td2.flag = 0; 
		// td1,td2 都处于可执行状态，但 JVM 线程调度先执行哪个线程是不确定的。
		// td2 的 run()可能在 td1 的 run()之前运行
		final Thread t1=new Thread(new Runnable(){
			public void run() {
				String tName = Thread.currentThread().getName();
				td1.flag = 1;
				try {
					// 获取不到锁，就等 5 秒，如果 5 秒后还是获取不到就返回 false 
					if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
						System.out.println(tName + "获取到锁！");
					} else {
						System.out.println(tName + "获取不到锁！");
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					td1.money(1);
				} catch (Exception e) {
					System.out.println(tName + "出错了！！！");
				} finally {
					System.out.println("当前的线程是"+Thread.currentThread().getName()+"释放锁！！");
					lock.unlock(); 
				}
			} 
		});
		t1.start();

		final Thread t2= new Thread(new Runnable(){
			public void run() {
				String tName = Thread.currentThread().getName();
				td1.flag = 0;
				try {
					// 获取不到锁，就等 5 秒，如果 5 秒后还是获取不到就返回 false 
					if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
						System.out.println(tName + "获取到锁！");
					} else {
						System.out.println(tName + "获取不到锁！");
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					td2.money(0);
				} catch (Exception e) {
					System.out.println(tName + "出错了！！！");
				} finally {
					System.out.println("当前的线程是"+Thread.currentThread().getName()+"释放锁！！");
					lock.unlock(); 
				}
			} 
		});
		t2.start();
	} 
}
