package pattern.model;

public class Model extends MyRunTime {
	
	public static void main(String[] args) {
		// main 函数为 static，不能出现非静态的方法，可以通过创建类的对象将方法加载到内存中
		Model m = new Model();
		m.getTime();
	}
	
	public void code() {
		for(int i = 0; i<100; i++) {
			System.out.println("你好");
		}
	}
}

abstract class MyRunTime {
	public final void getTime() {
		long startTime = System.currentTimeMillis();
		code();
		long endTime = System.currentTimeMillis();
		System.out.println("代码的运行时间为：" + (endTime-startTime));
	}
	abstract void code();
}