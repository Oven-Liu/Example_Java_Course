package pattern.single.lazy;

public class Single {
	private static Single s;
	private Single() {}
	public static Single getInstance() {
		if (s == null) {
			s = new Single();
		}
		return s;
	}
}

class Single2 {
	private static Single2 s;
	private Single2() {}
	public static Single2 getInstance() {
		if (s == null) {
			synchronized(s) { // Ϊ�����Ч�ʣ�һ������������߳̿�������ͬ��
				if (s == null) {
					s = new Single2();
				}
			}
		}
		return s;
	}
}