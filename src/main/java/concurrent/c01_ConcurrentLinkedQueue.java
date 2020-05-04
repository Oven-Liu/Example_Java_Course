package concurrent;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class c01_ConcurrentLinkedQueue {
	public static int threadCount = 10;
	public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

	static class Offer implements Runnable {
		public void run() {
			// 不建议使用 queue.size()==0，影响效率。可以使用!queue.isEmpty()
			if(queue.size()==0){
				String ele = new Random().nextInt(Integer.MAX_VALUE)+"";
				queue.offer(ele);
				System.out.println("入队元素为"+ele);
			}
		}
	}

	static class Poll implements Runnable {
		public void run() {
			if(!queue.isEmpty()){
				String ele = queue.poll();System.out.println("出队元素为"+ele);
			}
		}
	}

	public static void main(String[] agrs) {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for(int x=0;x<threadCount;x++){
			executorService.submit(new Offer());
			executorService.submit(new Poll());
		}
		executorService.shutdown();
	}
}
