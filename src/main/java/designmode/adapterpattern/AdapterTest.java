package designmode.adapterpattern;

/**
 * 适配器模式
 * 
 * @author Xudong.Liu  2018年9月8日 下午8:39:23
 */
public class AdapterTest {
	
	public static void main(String[] args) {
		/* 类适配器模式 */
		Targetable target = new Adapter();
		target.method1();
		target.method2();
		
		/* 对象适配器模式 */
		Source source = new Source();
		Wrapper wrapper = new Wrapper(source);
		wrapper.method1();
		wrapper.method2();
	}
}
