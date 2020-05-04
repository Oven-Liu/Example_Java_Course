package JDK8newspeciality.methodreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferences {
	public static void main(String[] args) {
		// 构造器引用
		final Car car = Car.create(Car::new);
		final List<Car> cars = Arrays.asList(car);
		
		// 静态方法引用
		cars.forEach(Car::collide);
		
		// 特定类的任意对象的方法引用
		cars.forEach(Car::repair);
		
		// 特定对象的方法引用
		final Car police = Car.create(Car::new);
		cars.forEach(police::follow);
		
		/*List<String> names = new ArrayList<String>();
		names.add("Google");
		names.add("Runoob");
		names.add("Taobao");
		names.add("Baidu");
		names.add("Sina");*/
		List<String> names = new ArrayList<String>() {
			{
				add("Google");
				add("Runoob");
				add("Taobao");
				add("Baidu");
				add("Sina");
			}
		};

		names.forEach(System.out::println);
		
		// 使用 Lambda 表达式创建对象，无参构造函数
		Supplier<Apple> s1 = () -> new Apple();
		Apple apple1 = s1.get();
		// 使用方法引入创建对象，无参构造函数
		Supplier<Apple> s2 = Apple::new;
		Apple apple2 = s2.get();
		System.out.println("apple1：" + apple1 + "，apple2：" + apple2);
		
		// 使用 Lambda 表达式创建对象，有参构造函数
		Function<String, Apple> s3 = (name) -> new Apple(name);
		Apple apple3 = s3.apply("苹果");
		// 使用 方法引入创建对象，有参构造函数
		Function<String, Apple> s4 = Apple::new;
		Apple apple4 = s4.apply("香蕉");
		System.out.println("apple3：" + apple3 + "，apple4：" + apple4);
	}
}
 
class Car {
    // Supplier 是 jdk1.8 的接口，这里和 lambda 一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

class Apple {
	String name;
	String color;
	Integer weight;
	
	public Apple() {
		super();
	}
	
	public Apple(String name) {
		this.name = name;
	}

	public Apple(String name, String color, Integer weight) {
		this.name = name;
		this.color = color;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Apple [name=" + name + ", color=" + color + ", weight=" + weight + "]";
	}
	
}