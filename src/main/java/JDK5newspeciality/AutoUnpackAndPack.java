package JDK5newspeciality;

public class AutoUnpackAndPack {

	public static void main(String[] args) {
		Integer a = new Integer(5);
		Integer b = 5;
		Integer c = 5;
		int d = 5;
		System.out.println(a == b); // false�����õ�ַ��ͬ
		System.out.println(b == c); // true��-128~127 ��������
		System.out.println(a == d); // true��a �Զ�����
		System.out.println(b == d); // true��b �Զ�����
		
		Integer e = 128;
		Integer f = 128;
		System.out.println(e == f); // false������ -128~127 ��������
	}
}
