package annotation;

import java.util.List;

import org.junit.Test;



public class b06_TestAnnotation {

	@Test
	public void test() {
		b07_AdminDao baseDao = new b07_AdminDao();
		
		b01_Admin admin = baseDao.findById(2);
		List<b01_Admin> list = baseDao.getAll();
		
		System.out.println(admin);
		System.out.println(list);
	}
}
