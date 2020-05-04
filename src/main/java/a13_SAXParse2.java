import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 使用SAX解析xml文档，完整输出文档内容
 * 
 * @author mengs
 */
public class a13_SAXParse2 {
	public static void main(String[] args) throws Exception {
		//获取SAXParser对象
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		//读取xml内容
		a14_MyDefaultHandler2 handler = new a14_MyDefaultHandler2();
		parser.parse(new File("./src/contact.xml"), handler);
		System.out.println(handler.getContent());
	}
}
