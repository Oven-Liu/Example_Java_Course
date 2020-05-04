package JDK8newspeciality.optional;

import java.util.Optional;

public class TestOptional {

	public static void main(String[] args) {
		Integer value1 = null;
		Integer value2 = new Integer(10);
		// #ofNullable 允许传递 null 参数
		Optional<Integer> a = Optional.ofNullable(value1);
		// #of 如果传递的参数是 NUll，抛出异常 NullPointerException
		Optional<Integer> b = Optional.of(value2);
		System.out.println(sum(a, b));
	}
	
	public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
		// #isPresent 判断值是否存在
		System.out.println("第一个参数值是否存在：" + a.isPresent());
		System.out.println("第二个参数值是否存在：" + b.isPresent());
		// #orElse 获取值，如果值不存在，返回默认值
		Integer value1 = a.orElse(new Integer(0));
		// #get 获取值，值需要存在
		Integer value2 = b.get();
		
		return value1 + value2;
	}
}
