package JDK8newspeciality.lambda;

import java.util.Arrays;
import java.util.List;

public class a01_BasicGrammar {
	
	public static void main(String args[]) {
		test1();
		test2();
	}
	
	public static void test1() {
		a01_BasicGrammar tester = new a01_BasicGrammar();
		// ��������
		MathOperation addition = (int a, int b) -> a + b;
		// ����������
		MathOperation subtraction = (a, b) -> a - b;
		// �������з������
		MathOperation multiplication = (a ,b) -> {return a * b;};
		// û�д����ż�����ֵ
		MathOperation division = (a, b) -> a / b;
		
		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 + 5 = " + addition.operation(10, 5));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 �� 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 �� 5 = " + tester.operate(10, 5, division));
		
		// ��������
		GreetingService gs1 = message -> System.out.println("Hello " + message);
		// ������
		GreetingService gs2 = (message) -> System.out.println("Hello " + message);
		
		gs1.sayMessage("Java");
		gs2.sayMessage("Lambda");
	}
	
	public static void test2() {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
				"Stanislas Wawrinka",  
				"David Ferrer","Roger Federer",  
				"Andy Murray","Tomas Berdych",  
		"Juan Martin Del Potro"};  
		List<String> players =  Arrays.asList(atp);  

		// ��ǰ��ѭ����ʽ  
		for (String player : players) {  
			System.out.print(player + "; ");  
		}  
		System.out.println();

		// ʹ�� lambda ���ʽ�Լ���������(functional operation)  
		players.forEach((player) -> System.out.print(player + "; "));  
		System.out.println();

		// �� Java 8 ��ʹ��˫ð�Ų�����(double colon operator)  
		players.forEach(System.out::println);
	}
	
	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}

interface MathOperation {
	int operation(int a, int b);
	/*default int addition(int a, int b) {
		return a + b;
	}*/
}

interface GreetingService {
	void sayMessage(String message);
}
