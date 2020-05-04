package pattern.factory;

public class Student implements Person {

	private String name;
	private int age;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Student() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	@Override
	public void say() {
		System.out.println("Hello，我是学生");
	}

}
