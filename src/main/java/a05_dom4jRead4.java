import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 把xml文档信息封装到对象中
 * 
 * @author mengs
 *
 */
public class a05_dom4jRead4 {
	public static void main(String[] args) throws DocumentException {
		ArrayList<a04_contact> list = new ArrayList<a04_contact>();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/contact.xml"));
		Iterator<Element> it = doc.getRootElement().elementIterator();
		while (it.hasNext()) {
			Element elt = it.next();
			a04_contact contact = new a04_contact();
			contact.setId(elt.attributeValue("id"));
			contact.setAge(elt.elementText("age"));
			contact.setName(elt.elementText("name"));
			contact.setPhone(elt.elementText("phone"));
			contact.setEmail(elt.elementText("email"));
			contact.setQq(elt.elementText("qq"));
			list.add(contact);
		}
		for (a04_contact contact : list) {
			System.out.println(contact);
		}
	}
}
