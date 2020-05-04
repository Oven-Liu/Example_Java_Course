import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 *  1.使用dom4j的api来生成以下的xml文件
		<students>
			<student id="1">
				<name>张三</name>
				<gender>男</gender>
				<grade>计算机1班</grade>
				<address>广州天河</address>
			</student>
			<student id="2">
				<name>李四</name>
				<gender>女</gender>
				<grade>计算机2班</grade>
				<address>广州越秀</address>
			</student>
		</students>
	2.修改id为2的学生的姓名，改为“王五”
	3.删除id为2的学生
 * 
 * @author mengs
 */
public class a08_dom4jWrite4 {
	
	/**
	 * 使用dom4j生成指定XML文件
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Document doc = DocumentHelper.createDocument();
		Element rootElement = doc.addElement("students");
		
		//添加第一个学生
		Element studentElement1 = rootElement.addElement("student");
		studentElement1.addAttribute("id", "1");
		Element nameElement1 = studentElement1.addElement("name");
		nameElement1.setText("张三");
		Element genderElement1 = studentElement1.addElement("gender");
		genderElement1.setText("男");
		Element gradeElement1 = studentElement1.addElement("grade");
		gradeElement1.setText("计算机一班");
		Element addressElement1 = studentElement1.addElement("address");
		addressElement1.setText("四川成都");
		
		//添加第二个学生
		Element studentElement2 = rootElement.addElement("student");
		studentElement2.addAttribute("id", "2");
		studentElement2.addElement("name").setText("李四");
		studentElement2.addElement("gender").setText("女");
		studentElement2.addElement("grade").setText("计算机二班");
		studentElement2.addElement("address").setText("四川内江");
		
		//内容写出到xml文件
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Students.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 修改id为2的学生的姓名，改为“王五”
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Document doc = new SAXReader().read(new File("F:/Java/OutputFile/Students.xml"));
		Iterator<Element> it = doc.getRootElement().elementIterator("student");
		while (it.hasNext()) {
			Element studentElement = it.next();
			if (studentElement.attributeValue("id").equals("2")) {
				studentElement.element("name").setText("王五");
				break;
			}
		}
		
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Students.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 删除id为2的学生
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Document doc = new SAXReader().read(new File("F:/Java/OutputFile/Students.xml"));
		
		//得到id为2的student对象
		Iterator<Element> it = doc.getRootElement().elementIterator();
		while (it.hasNext()) {
			Element studentElement = it.next();
			if (studentElement.attributeValue("id").equals("2")) {
				studentElement.detach();
				break;
			}
		}
		
		//内容写出到xml文件
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Students.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
}
