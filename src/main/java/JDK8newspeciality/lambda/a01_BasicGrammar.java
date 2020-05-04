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
		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;
		// 不声明类型
		MathOperation subtraction = (a, b) -> a - b;
		// 大括号中返回语句
		MathOperation multiplication = (a ,b) -> {return a * b;};
		// 没有大括号及返回值
		MathOperation division = (a, b) -> a / b;
		
		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 + 5 = " + addition.operation(10, 5));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 × 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 ÷ 5 = " + tester.operate(10, 5, division));
		
		// 不用括号
		GreetingService gs1 = message -> System.out.println("Hello " + message);
		// 用括号
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

		// 以前的循环方式  
		for (String player : players) {  
			System.out.print(player + "; ");  
		}  
		System.out.println();

		// 使用 lambda 表达式以及函数操作(functional operation)  
		players.forEach((player) -> System.out.print(player + "; "));  
		System.out.println();

		// 在 Java 8 中使用双冒号操作符(double colon operator)  
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
