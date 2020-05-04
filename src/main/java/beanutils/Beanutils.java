package beanutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

public class Beanutils {
	
	/**
	 * javaBean创建对象的方法
	 */
	@Test
	public void test1() {
		Student stu = new Student();
		stu.setName("张三");
		stu.setGender("男");
		stu.setAge(21);
//		stu.setBirthday(new Date("1990-10-12"));
		System.out.println(stu);
	}
	
	/**
	 * 拷贝属性、对象、Map集合
	 */
	@Test
	public void test2() throws Exception {
		
		// 拷贝属性
		Student stu = new Student();
		BeanUtils.copyProperty(stu, "name", "张三");
		BeanUtils.setProperty(stu, "gender", "男");
		BeanUtils.setProperty(stu, "age", 21);
		System.out.println(stu);
		
		// 拷贝对象
		Student newStu = new Student();
		BeanUtils.copyProperties(newStu, stu);
		System.out.println(newStu);
		
		// 拷贝Map集合
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "张三");
		map.put("gender", "男");
		map.put("age", 21);
		Student student = new Student();
		BeanUtils.populate(student, map);
		System.out.println(student);
	}
	
	/**
	 * 拷贝日期类型数据，使用日期转换工具
	 */
	@Test
	public void test3() throws Exception {
		String name = "张三";
		String gender = "男";
		int age = 21;
//		String birthday = "1990-10-12";
		String birthday = "";
		Student stu = new Student();
		// 注册转换器
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		BeanUtils.copyProperty(stu, "name", name);
		BeanUtils.copyProperty(stu, "gender", gender);
		BeanUtils.copyProperty(stu, "age", age);
		BeanUtils.copyProperty(stu, "birthday", birthday);
		System.out.println(stu);
	}
	
	/**
	 * 拷贝日期类型数据，使用自定义日期转换工具（内部匿名类）
	 */
	@Test
	public void test4() throws Exception {
		String name = "张三";
		String gender = "男";
		int age = 21;
		String birthday = "1990-10-12";
//		String birthday = "";
		Student stu = new Student();
		
		// 自定转换器
		ConvertUtils.register(new Converter() {

			@Override
			public <T> T convert(Class<T> type, Object value) {
				if (type != Date.class) {
					return null;
				}
				if (value == null || "".equals(value.toString().trim())) {
					return null;
				}
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					return (T) sdf.parse(value.toString());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		
		BeanUtils.copyProperty(stu, "name", name);
		BeanUtils.copyProperty(stu, "gender", gender);
		BeanUtils.copyProperty(stu, "age", age);
		BeanUtils.copyProperty(stu, "birthday", birthday);
		System.out.println(stu);
	}
	
	/**
	 * 拷贝日期类型数据，使用自定义日期转换工具（外部类）
	 */
	@Test
	public void test5() throws Exception {
		String name = "张三";
		String gender = "男";
		int age = 21;
//		String birthday = "1990-10-12";
		String birthday = "";
		Student stu = new Student();
		
		// 自定义转换器
		ConvertUtils.register(new MyConverter(), Date.class);
		
		BeanUtils.copyProperty(stu, "name", name);
		BeanUtils.copyProperty(stu, "gender", gender);
		BeanUtils.copyProperty(stu, "age", age);
		BeanUtils.copyProperty(stu, "birthday", birthday);
		System.out.println(stu);
	}
}
