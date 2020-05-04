package concurrent;

import java.util.concurrent.PriorityBlockingQueue;

public class b03_PriorityBlockingQueue {
	public static PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>();

	public static void main(String[] args) {
		queue.add(new User(1,"wu"));
		queue.add(new User(5,"wu5"));
		queue.add(new User(23,"wu23"));
		queue.add(new User(55,"wu55"));
		queue.add(new User(9,"wu9"));
		queue.add(new User(3,"wu3"));
		for (User user : queue) {
			try {
				System.out.println(queue.take().name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(queue.size());
	}
	//¾²Ì¬ÄÚ²¿Àà
	static class User implements Comparable<User> {
		public User(int age,String name) {
			this.age = age;
			this.name = name;
		}
		int age;
		String name;
		@Override
		public int compareTo(User o) {
			return this.age > o.age ? -1 : 1;
		}
	}

}
