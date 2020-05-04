import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 按指定格式写出文件
 * 
 * @author mengs
 */
public class a07_dom4jWrite2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read(new File("./contact.xml"));
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact.xml");
//		OutputFormat format = OutputFormat.createCompactFormat();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
}
