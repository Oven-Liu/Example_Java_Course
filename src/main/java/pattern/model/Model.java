package pattern.model;

public class Model extends MyRunTime {
	
	public static void main(String[] args) {
		// main ����Ϊ static�����ܳ��ַǾ�̬�ķ���������ͨ��������Ķ��󽫷������ص��ڴ���
		Model m = new Model();
		m.getTime();
	}
	
	public void code() {
		for(int i = 0; i<100; i++) {
			System.out.println("���");
		}
	}
}

abstract class MyRunTime {
	public final void getTime() {
		long startTime = System.currentTimeMillis();
		code();
		long endTime = System.currentTimeMillis();
		System.out.println("���������ʱ��Ϊ��" + (endTime-startTime));
	}
	abstract void code();
}