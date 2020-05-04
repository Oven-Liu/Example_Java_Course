package concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class d02_AtomicLong {
	public static void main(String[] args) {
		final AtomicLong orderIdGenerator = new AtomicLong(0);
		final List<Item> orders = Collections.synchronizedList(new ArrayList<Item>());
		for (int i = 0; i < 10; i++) {
			Thread orderCreationThread = new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						long orderId = orderIdGenerator.incrementAndGet();
						Item order = new Item(Thread.currentThread().getName(), orderId);
						orders.add(order);
					}
				}
			});
			orderCreationThread.setName("Order Creation Thread " + i);
			orderCreationThread.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<Long> orderIds = new HashSet<Long>();
		for (Item order : orders) {
			orderIds.add(order.getID());
			System.out.println("Order name:" + order.getItemName()+"----"+"Order id:" + order.getID());
		}
	}
}

class Item {
	String itemName;
	long id;
	Item(String n, long id) {
		this.itemName = n;
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public long getID() {
		return id;
	}
}
