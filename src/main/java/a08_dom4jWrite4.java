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
 *  1.ʹ��dom4j��api���������µ�xml�ļ�
		<students>
			<student id="1">
				<name>����</name>
				<gender>��</gender>
				<grade>�����1��</grade>
				<address>�������</address>
			</student>
			<student id="2">
				<name>����</name>
				<gender>Ů</gender>
				<grade>�����2��</grade>
				<address>����Խ��</address>
			</student>
		</students>
	2.�޸�idΪ2��ѧ������������Ϊ�����塱
	3.ɾ��idΪ2��ѧ��
 * 
 * @author mengs
 */
public class a08_dom4jWrite4 {
	
	/**
	 * ʹ��dom4j����ָ��XML�ļ�
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Document doc = DocumentHelper.createDocument();
		Element rootElement = doc.addElement("students");
		
		//��ӵ�һ��ѧ��
		Element studentElement1 = rootElement.addElement("student");
		studentElement1.addAttribute("id", "1");
		Element nameElement1 = studentElement1.addElement("name");
		nameElement1.setText("����");
		Element genderElement1 = studentElement1.addElement("gender");
		genderElement1.setText("��");
		Element gradeElement1 = studentElement1.addElement("grade");
		gradeElement1.setText("�����һ��");
		Element addressElement1 = studentElement1.addElement("address");
		addressElement1.setText("�Ĵ��ɶ�");
		
		//��ӵڶ���ѧ��
		Element studentElement2 = rootElement.addElement("student");
		studentElement2.addAttribute("id", "2");
		studentElement2.addElement("name").setText("����");
		studentElement2.addElement("gender").setText("Ů");
		studentElement2.addElement("grade").setText("���������");
		studentElement2.addElement("address").setText("�Ĵ��ڽ�");
		
		//����д����xml�ļ�
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Students.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * �޸�idΪ2��ѧ������������Ϊ�����塱
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Document doc = new SAXReader().read(new File("F:/Java/OutputFile/Students.xml"));
		Iterator<Element> it = doc.getRootElement().elementIterator("student");
		while (it.hasNext()) {
			Element studentElement = it.next();
			if (studentElement.attributeValue("id").equals("2")) {
				studentElement.element("name").setText("����");
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
	 * ɾ��idΪ2��ѧ��
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Document doc = new SAXReader().read(new File("F:/Java/OutputFile/Students.xml"));
		
		//�õ�idΪ2��student����
		Iterator<Element> it = doc.getRootElement().elementIterator();
		while (it.hasNext()) {
			Element studentElement = it.next();
			if (studentElement.attributeValue("id").equals("2")) {
				studentElement.detach();
				break;
			}
		}
		
		//����д����xml�ļ�
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Students.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
}
