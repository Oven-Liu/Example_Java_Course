import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX¶ÁÈ¡xmlÎÄ¼þ
 * 
 * @author mengs
 */
public class a11_SAXParse {
	
	public static void main(String[] args) throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(new File("./src/contact.xml"), new a12_MyDefaultHandler());
	}
}
