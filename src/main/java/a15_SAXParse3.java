import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 使用SAX把xml封装为对象
 * 
 * @author mengs
 */
public class a15_SAXParse3 {
	public static void main(String[] args) throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		a16_MyDefaultHandler3 handler = new a16_MyDefaultHandler3();
		parser.parse(new File("./src/contact.xml"), handler);
		List<a04_contact> list = handler.getList();
		for (a04_contact contact : list) {
			System.out.println(contact);
		}
	}
}
