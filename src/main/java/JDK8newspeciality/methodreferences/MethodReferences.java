package JDK8newspeciality.methodreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferences {
	public static void main(String[] args) {
		// ����������
		final Car car = Car.create(Car::new);
		final List<Car> cars = Arrays.asList(car);
		
		// ��̬��������
		cars.forEach(Car::collide);
		
		// �ض�����������ķ�������
		cars.forEach(Car::repair);
		
		// �ض�����ķ�������
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
		
		// ʹ�� Lambda ���ʽ���������޲ι��캯��
		Supplier<Apple> s1 = () -> new Apple();
		Apple apple1 = s1.get();
		// ʹ�÷������봴�������޲ι��캯��
		Supplier<Apple> s2 = Apple::new;
		Apple apple2 = s2.get();
		System.out.println("apple1��" + apple1 + "��apple2��" + apple2);
		
		// ʹ�� Lambda ���ʽ���������вι��캯��
		Function<String, Apple> s3 = (name) -> new Apple(name);
		Apple apple3 = s3.apply("ƻ��");
		// ʹ�� �������봴�������вι��캯��
		Function<String, Apple> s4 = Apple::new;
		Apple apple4 = s4.apply("�㽶");
		System.out.println("apple3��" + apple3 + "��apple4��" + apple4);
	}
}
 
class Car {
    // Supplier �� jdk1.8 �Ľӿڣ������ lambda һ��ʹ����
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