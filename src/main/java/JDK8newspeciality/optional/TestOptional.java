package JDK8newspeciality.optional;

import java.util.Optional;

public class TestOptional {

	public static void main(String[] args) {
		Integer value1 = null;
		Integer value2 = new Integer(10);
		// #ofNullable ������ null ����
		Optional<Integer> a = Optional.ofNullable(value1);
		// #of ������ݵĲ����� NUll���׳��쳣 NullPointerException
		Optional<Integer> b = Optional.of(value2);
		System.out.println(sum(a, b));
	}
	
	public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
		// #isPresent �ж�ֵ�Ƿ����
		System.out.println("��һ������ֵ�Ƿ���ڣ�" + a.isPresent());
		System.out.println("�ڶ�������ֵ�Ƿ���ڣ�" + b.isPresent());
		// #orElse ��ȡֵ�����ֵ�����ڣ�����Ĭ��ֵ
		Integer value1 = a.orElse(new Integer(0));
		// #get ��ȡֵ��ֵ��Ҫ����
		Integer value2 = b.get();
		
		return value1 + value2;
	}
}
