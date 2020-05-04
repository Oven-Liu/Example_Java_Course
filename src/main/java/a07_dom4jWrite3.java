import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * 修改xml内容
 * 1.增加：文档，标签，属性
 * 2.修改：属性值，文本
 * 3.删除：标签，属性
 * 
 * @author mengs
 */
public class a07_dom4jWrite3 {

	/**
	 * 1.增加：文档，标签，属性
	 * @throws Exception 
	 */
	@Test
	public void test1() throws Exception {
		//创建文档
		Document doc = DocumentHelper.createDocument();
		//添加标签
		Element rootElement = doc.addElement("ContactList");
		Element contactElement = rootElement.addElement("contact");
		contactElement.addElement("name");
		//添加属性
		contactElement.addAttribute("id","001");
		contactElement.addAttribute("name", "eric");
		//把修改后的Document对象写到XML文档中
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact2.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 2.修改：属性值，文本
	 */
	@Test
	public void test2() throws Exception {
		Document doc = new SAXReader().read(new File("./contact.xml"));
		/*
		Element element = doc.getRootElement().element("contact");
		element.addAttribute("id", "003");
		*/
		
		//得到标签对象
		Element element = doc.getRootElement().element("contact");
		//得到属性对象
		Attribute attribute = element.attribute("id");
		//修改属性值
		attribute.setValue("003");
		
		//得到文本对象
		Element nameElement = doc.getRootElement().element("contact").element("name");
		//修改文本
		nameElement.setText("王五");
		
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact2.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 3.删除：标签，属性
	 */
	@Test
	public void test3() throws Exception {
		Document doc = new SAXReader().read(new File("./contact.xml"));
		//得到标签对象
		Element ageElement = doc.getRootElement().element("contact").element("name");
		//删除标签
		ageElement.detach();
//		ageElement.getParent().remove(ageElement);
		
		//得到标签对象
		Element contactElement = (Element)doc.getRootElement().elements("contact").get(1);
		//得到属性对象
		Attribute idAttribute = contactElement.attribute("id");
		//删除属性
		idAttribute.detach();
		
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact2.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
}
