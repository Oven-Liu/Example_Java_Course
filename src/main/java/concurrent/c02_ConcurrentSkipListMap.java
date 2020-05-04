package concurrent;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/*
 * ConcurrentSkipListMap �ǡ��̰߳�ȫ���Ĺ�ϣ���� TreeMap �Ƿ��̰߳�ȫ�ġ�
 *
 * �����ǡ�����߳�ͬʱ�������ұ��� map����ʾ��
 * (01) �� map �� ConcurrentSkipListMap ����ʱ���������������С�
 * (02) �� map �� TreeMap ����ʱ���������� ConcurrentModificationException �쳣��
 *
 */
public class c02_ConcurrentSkipListMap {
	// map �� TreeMap ����ʱ����������
	//private static Map<String, String> map = new TreeMap<String, String>();
	private static Map<String, String> map = new ConcurrentSkipListMap<String, String>();
	
	public static void main(String[] args) {
		// ͬʱ���������̶߳� map ���в���
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
				// ���߳����� + "���"
				String val = Thread.currentThread().getName()+i;
				map.put(val, "0");
				// ͨ����Iterator������ map
				printAll();
			}
		}
	}
}
