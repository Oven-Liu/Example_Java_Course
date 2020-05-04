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
 * �޸�xml����
 * 1.���ӣ��ĵ�����ǩ������
 * 2.�޸ģ�����ֵ���ı�
 * 3.ɾ������ǩ������
 * 
 * @author mengs
 */
public class a07_dom4jWrite3 {

	/**
	 * 1.���ӣ��ĵ�����ǩ������
	 * @throws Exception 
	 */
	@Test
	public void test1() throws Exception {
		//�����ĵ�
		Document doc = DocumentHelper.createDocument();
		//��ӱ�ǩ
		Element rootElement = doc.addElement("ContactList");
		Element contactElement = rootElement.addElement("contact");
		contactElement.addElement("name");
		//�������
		contactElement.addAttribute("id","001");
		contactElement.addAttribute("name", "eric");
		//���޸ĺ��Document����д��XML�ĵ���
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact2.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 2.�޸ģ�����ֵ���ı�
	 */
	@Test
	public void test2() throws Exception {
		Document doc = new SAXReader().read(new File("./contact.xml"));
		/*
		Element element = doc.getRootElement().element("contact");
		element.addAttribute("id", "003");
		*/
		
		//�õ���ǩ����
		Element element = doc.getRootElement().element("contact");
		//�õ����Զ���
		Attribute attribute = element.attribute("id");
		//�޸�����ֵ
		attribute.setValue("003");
		
		//�õ��ı�����
		Element nameElement = doc.getRootElement().element("contact").element("name");
		//�޸��ı�
		nameElement.setText("����");
		
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact2.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 3.ɾ������ǩ������
	 */
	@Test
	public void test3() throws Exception {
		Document doc = new SAXReader().read(new File("./contact.xml"));
		//�õ���ǩ����
		Element ageElement = doc.getRootElement().element("contact").element("name");
		//ɾ����ǩ
		ageElement.detach();
//		ageElement.getParent().remove(ageElement);
		
		//�õ���ǩ����
		Element contactElement = (Element)doc.getRootElement().elements("contact").get(1);
		//�õ����Զ���
		Attribute idAttribute = contactElement.attribute("id");
		//ɾ������
		idAttribute.detach();
		
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact2.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
}
