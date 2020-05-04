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
	 * javaBean��������ķ���
	 */
	@Test
	public void test1() {
		Student stu = new Student();
		stu.setName("����");
		stu.setGender("��");
		stu.setAge(21);
//		stu.setBirthday(new Date("1990-10-12"));
		System.out.println(stu);
	}
	
	/**
	 * �������ԡ�����Map����
	 */
	@Test
	public void test2() throws Exception {
		
		// ��������
		Student stu = new Student();
		BeanUtils.copyProperty(stu, "name", "����");
		BeanUtils.setProperty(stu, "gender", "��");
		BeanUtils.setProperty(stu, "age", 21);
		System.out.println(stu);
		
		// ��������
		Student newStu = new Student();
		BeanUtils.copyProperties(newStu, stu);
		System.out.println(newStu);
		
		// ����Map����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "����");
		map.put("gender", "��");
		map.put("age", 21);
		Student student = new Student();
		BeanUtils.populate(student, map);
		System.out.println(student);
	}
	
	/**
	 * ���������������ݣ�ʹ������ת������
	 */
	@Test
	public void test3() throws Exception {
		String name = "����";
		String gender = "��";
		int age = 21;
//		String birthday = "1990-10-12";
		String birthday = "";
		Student stu = new Student();
		// ע��ת����
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		BeanUtils.copyProperty(stu, "name", name);
		BeanUtils.copyProperty(stu, "gender", gender);
		BeanUtils.copyProperty(stu, "age", age);
		BeanUtils.copyProperty(stu, "birthday", birthday);
		System.out.println(stu);
	}
	
	/**
	 * ���������������ݣ�ʹ���Զ�������ת�����ߣ��ڲ������ࣩ
	 */
	@Test
	public void test4() throws Exception {
		String name = "����";
		String gender = "��";
		int age = 21;
		String birthday = "1990-10-12";
//		String birthday = "";
		Student stu = new Student();
		
		// �Զ�ת����
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
	 * ���������������ݣ�ʹ���Զ�������ת�����ߣ��ⲿ�ࣩ
	 */
	@Test
	public void test5() throws Exception {
		String name = "����";
		String gender = "��";
		int age = 21;
//		String birthday = "1990-10-12";
		String birthday = "";
		Student stu = new Student();
		
		// �Զ���ת����
		ConvertUtils.register(new MyConverter(), Date.class);
		
		BeanUtils.copyProperty(stu, "name", name);
		BeanUtils.copyProperty(stu, "gender", gender);
		BeanUtils.copyProperty(stu, "age", age);
		BeanUtils.copyProperty(stu, "birthday", birthday);
		System.out.println(stu);
	}
}
