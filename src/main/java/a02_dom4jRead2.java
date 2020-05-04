import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class a02_dom4jRead2 {
	/**
	 * ��ȡ�ڵ���Ϣ
	 * 
	 * @throws DocumentException 
	 */
	@Test
	public void test1() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		
		//��ȡ��ǰ�ڵ��µ������ӽڵ�
		Iterator<Node> it = doc.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			System.out.println(node.getName());
			//��ȡ�ӽڵ��µ��ӽڵ�
			if (node instanceof Element) {
				Element elt = (Element)node;
				Iterator<Node> it2 = elt.nodeIterator();
				while (it2.hasNext()) {
					Node node2 = it2.next();
					System.out.println(node2.getName());
				}
			}
		}
	}
	
	/**
	 * ����xml�ĵ������ڵ�
	 * @throws Exception 
	 */
	@Test
	public void test2() throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		Element rootElement = doc.getRootElement();
		getChildNodes(rootElement);
	}
	
	/**
	 * ��ȡ����ı�ǩ�������ӽڵ�
	 * 
	 * @param elt
	 */
	private void getChildNodes(Element elt) {
		System.out.println(elt.getName());
		
		//��ȡ�ӽڵ�
		Iterator<Node> it = elt.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (node instanceof Element) {
				Element element = (Element)node;
				//�ݹ��ȡ�ӽڵ�
				getChildNodes(element);
			}
		}
	}
	
	/**
	 * ��ȡ��ǩ
	 * 
	 * @throws Exception 
	 */
	@Test
	public void test3() throws Exception {
		//��ȡxml�ĵ�����ȡdocument����
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		//��ȡ����ǩ
		Element rootElement = doc.getRootElement();
		System.out.println(rootElement.getName());
		
		//��ȡ��ǰ��ǩ��ָ�����Ƶĵ�һ���ӱ�ǩ
		Element contactElement = rootElement.element("contact");
		System.out.println(contactElement.getName());
		
		//��ȡ��ǰ��ǩ��ָ�����Ƶ������ӱ�ǩ
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Element element = elementIterator.next();
			System.out.println(element.getName());
		}
		
		//��ȡ��ǰ��ǩ�µ������ӱ�ǩ
		List<Element> list = rootElement.elements();
		//forѭ������
		/*
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			System.out.println(element.getName());
		}
		*/
		//��ǿforѭ������
		/*
		for (Element e : list) {
			System.out.println(e.getName());
		}
		*/
		//iterator����������
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			System.out.println(element.getName());
		}
		
		//��ȡ�����εı�ǩ��ֻ��һ���ػ�ȡ
		Element element = doc.getRootElement().element("contact").element("name");
		System.out.println(element.getName());
	}
	
	/**
	 * ��ȡ����
	 * @throws DocumentException 
	 */
	@Test
	public void test4() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		Element contactElement = doc.getRootElement().element("contact");
		/*
		//��ȡ��ǩָ���������Ƶ�����ֵ
		String idAttribute = contactElement.attributeValue("id");
		System.out.println(idAttribute);
		*/
		/*
		//��ȡ��ǩָ���������Ƶ�����ֵ����
		Attribute attribute = contactElement.attribute("id");
		String name = attribute.getName();
		String value = attribute.getValue();
		System.out.println(name);
		System.out.println(value);
		*/
		/*
		//��ȡ��ǩ���������Զ��󣬷������Լ���
		List<Attribute> list = contactElement.attributes();
		for (Attribute attribute : list) {
			System.out.println(attribute.getName()+"="+attribute.getValue());
		}
		*/
		
		//��ȡ��ǩ�������Զ��󣬷������Ե�����
		Iterator<Attribute> it = contactElement.attributeIterator();
		while (it.hasNext()) {
			Attribute attribute = it.next();
			System.out.println(attribute.getName()+"="+attribute.getValue());
		}
	}
	
	/**
	 * ��ȡ�ı�
	 * @throws DocumentException 
	 */
	@Test
	public void test5() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		
		//��ȡ��ǰ��ǩ�ı�����
		Element element = doc.getRootElement().element("contact").element("name");
		String name = element.getText();
		System.out.println(name);
		
		//��ȡ��ǰ��ǩ���ӱ�ǩ�ı�����
		Element element2 = doc.getRootElement().element("contact");
		String name2 = element2.elementText("name");
		System.out.println(name2);
	} 
}
