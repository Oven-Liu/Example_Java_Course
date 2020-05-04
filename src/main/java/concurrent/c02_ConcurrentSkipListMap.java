package concurrent;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/*
 * ConcurrentSkipListMap 是“线程安全”的哈希表，而 TreeMap 是非线程安全的。
 *
 * 下面是“多个线程同时操作并且遍历 map”的示例
 * (01) 当 map 是 ConcurrentSkipListMap 对象时，程序能正常运行。
 * (02) 当 map 是 TreeMap 对象时，程序会产生 ConcurrentModificationException 异常。
 *
 */
public class c02_ConcurrentSkipListMap {
	// map 是 TreeMap 对象时，程序会出错。
	//private static Map<String, String> map = new TreeMap<String, String>();
	private static Map<String, String> map = new ConcurrentSkipListMap<String, String>();
	
	public static void main(String[] args) {
		// 同时启动两个线程对 map 进行操作
		new MyThread("a").start();
		new MyThread("b").start();
	}

	private static void printAll() {
		String key, value;
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();
			key = (String)entry.getKey();
			value = (String)entry.getValue();
			System.out.print("("+key+", "+value+"), ");
		}
		System.out.println();
	}

	private static class MyThread extends Thread {
		MyThread(String name) {
			super(name);
		}
		@Override
		public void run() {
			int i = 0;
			while (i++ < 6) {
				// “线程名” + "序号"
				String val = Thread.currentThread().getName()+i;
				map.put(val, "0");
				// 通过“Iterator”遍历 map
				printAll();
			}
		}
	}
}
