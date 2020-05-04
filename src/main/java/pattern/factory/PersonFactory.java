package pattern.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


public interface PersonFactory {
	
	//需求： 编写一个工厂方法根据配置文件返回对应的对象。
	public static Object getInstance(String path) throws Exception{
		//读取配置文件
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		//读取第一行 : 读取类文件的信息
		String className = bufferedReader.readLine();
		//通过完整类名获取对应 的Class对象
		Class clazz = Class.forName(className);
		//获取到对应的构造方法
		Constructor constructor = clazz.getDeclaredConstructor(null);
		constructor.setAccessible(true);
		Object o = constructor.newInstance(null);
		//给对象设置对应的属性值
		String line = null;
		while((line = bufferedReader.readLine()) != null){
			String[] datas = line.split("=");
			Field field = clazz.getDeclaredField(datas[0]);
			//设置可以访问
			field.setAccessible(true);
			if(field.getType() == int.class) {
				field.set(o, Integer.parseInt(datas[1]));
			} else {
				field.set(o, datas[1]);
			}
		}
		return o;
	}
}
