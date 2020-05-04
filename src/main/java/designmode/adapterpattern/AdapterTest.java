package designmode.adapterpattern;

/**
 * ������ģʽ
 * 
 * @author Xudong.Liu  2018��9��8�� ����8:39:23
 */
public class AdapterTest {
	
	public static void main(String[] args) {
		/* ��������ģʽ */
		Targetable target = new Adapter();
		target.method1();
		target.method2();
		
		/* ����������ģʽ */
		Source source = new Source();
		Wrapper wrapper = new Wrapper(source);
		wrapper.method1();
		wrapper.method2();
	}
}
