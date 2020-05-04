package annotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class a01_Annotation {
	
	@a02_Author(age = 24, remark = "×¢½â")
	public void save() throws Exception {
		Method method = a01_Annotation.class.getDeclaredMethod("save");
		a02_Author a02_Author = method.getAnnotation(a02_Author.class);
		System.out.println(a02_Author.authorName());
		System.out.println(a02_Author.age());
		System.out.println(a02_Author.remark());
	}
	
	@Test
	public void test() throws Exception {
		save();
	}
}
