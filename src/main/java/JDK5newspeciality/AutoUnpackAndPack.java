package JDK5newspeciality;

public class AutoUnpackAndPack {

	public static void main(String[] args) {
		Integer a = new Integer(5);
		Integer b = 5;
		Integer c = 5;
		int d = 5;
		System.out.println(a == b); // false，引用地址不同
		System.out.println(b == c); // true，-128~127 缓冲数组
		System.out.println(a == d); // true，a 自动拆箱
		System.out.println(b == d); // true，b 自动拆箱
		
		Integer e = 128;
		Integer f = 128;
		System.out.println(e == f); // false，超出 -128~127 缓冲数组
	}
}
