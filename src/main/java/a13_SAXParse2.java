import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * ʹ��SAX����xml�ĵ�����������ĵ�����
 * 
 * @author mengs
 */
public class a13_SAXParse2 {
	public static void main(String[] args) throws Exception {
		//��ȡSAXParser����
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		//��ȡxml����
		a14_MyDefaultHandler2 handler = new a14_MyDefaultHandler2();
		parser.parse(new File("./src/contact.xml"), handler);
		System.out.println(handler.getContent());
	}
}
