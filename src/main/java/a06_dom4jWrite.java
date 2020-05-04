import java.io.File;
import java.io.FileOutputStream;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * ½«xmlÎÄµµÐ´³ö
 * 
 * @author mengs
 */
public class a06_dom4jWrite {
	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read(new File("./contact.xml"));
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/contact.xml");
		XMLWriter writer = new XMLWriter(out);
		writer.write(doc);
		writer.close();
	}
}
