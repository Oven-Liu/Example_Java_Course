package contacts;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {
	
	/**
	 * 读取XML文件
	 * 
	 * @return Document
	 */
	public static Document getDocument() {
		try {
			File file = new File("F:/Java/OutputFile/Example_JavaCourse4/Contacts.xml");
			Document doc = new SAXReader().read(file);
			return doc; 
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 写出XML文件到本地
	 * 
	 * @param doc
	 */
	public static void writexml(Document doc) {
		try {
			FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Example_JavaCourse4/Contacts.xml");
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
     