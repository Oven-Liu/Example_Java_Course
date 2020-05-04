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
	 * 获取节点信息
	 * 
	 * @throws DocumentException 
	 */
	@Test
	public void test1() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		
		//获取当前节点下的所有子节点
		Iterator<Node> it = doc.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			System.out.println(node.getName());
			//获取子节点下的子节点
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
	 * 遍历xml文档的所节点
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
	 * 获取传入的标签的所有子节点
	 * 
	 * @param elt
	 */
	private void getChildNodes(Element elt) {
		System.out.println(elt.getName());
		
		//获取子节点
		Iterator<Node> it = elt.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (node instanceof Element) {
				Element element = (Element)node;
				//递归获取子节点
				getChildNodes(element);
			}
		}
	}
	
	/**
	 * 获取标签
	 * 
	 * @throws Exception 
	 */
	@Test
	public void test3() throws Exception {
		//读取xml文档，获取document对象
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		//获取根标签
		Element rootElement = doc.getRootElement();
		System.out.println(rootElement.getName());
		
		//获取当前标签下指定名称的第一个子标签
		Element contactElement = rootElement.element("contact");
		System.out.println(contactElement.getName());
		
		//获取当前标签下指定名称的所有子标签
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Element element = elementIterator.next();
			System.out.println(element.getName());
		}
		
		//获取当前标签下的所有子标签
		List<Element> list = rootElement.elements();
		//for循环遍历
		/*
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			System.out.println(element.getName());
		}
		*/
		//增强for循环遍历
		/*
		for (Element e : list) {
			System.out.println(e.getName());
		}
		*/
		//iterator迭代器遍历
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			System.out.println(element.getName());
		}
		
		//获取更深层次的标签，只能一层层地获取
		Element element = doc.getRootElement().element("contact").element("name");
		System.out.println(element.getName());
	}
	
	/**
	 * 获取属性
	 * @throws DocumentException 
	 */
	@Test
	public void test4() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		Element contactElement = doc.getRootElement().element("contact");
		/*
		//获取标签指定属性名称的属性值
		String idAttribute = contactElement.attributeValue("id");
		System.out.println(idAttribute);
		*/
		/*
		//获取标签指定属性名称的属性值对象
		Attribute attribute = contactElement.attribute("id");
		String name = attribute.getName();
		String value = attribute.getValue();
		System.out.println(name);
		System.out.println(value);
		*/
		/*
		//获取标签的所有属性对象，返回属性集合
		List<Attribute> list = contactElement.attributes();
		for (Attribute attribute : list) {
			System.out.println(attribute.getName()+"="+attribute.getValue());
		}
		*/
		
		//获取标签所有属性对象，返回属性迭代器
		Iterator<Attribute> it = contactElement.attributeIterator();
		while (it.hasNext()) {
			Attribute attribute = it.next();
			System.out.println(attribute.getName()+"="+attribute.getValue());
		}
	}
	
	/**
	 * 获取文本
	 * @throws DocumentException 
	 */
	@Test
	public void test5() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		
		//获取当前标签文本内容
		Element element = doc.getRootElement().element("contact").element("name");
		String name = element.getText();
		System.out.println(name);
		
		//获取当前标签的子标签文本内容
		Element element2 = doc.getRootElement().element("contact");
		String name2 = element2.elementText("name");
		System.out.println(name2);
	} 
}
