package pattern.factory;

public class TestFactory {

	public static void main(String[] args) throws Exception {
		String path = "src/pattern/factory/info.txt";
		Student s = (Student) PersonFactory.getInstance(path);
		s.say();
		System.out.println(s);
	}
}
