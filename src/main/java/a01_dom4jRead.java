import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * 第一个Dom4j读取xml文档的例子
 * 
 * @author mengs
 *
 */
public class a01_dom4jRead {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//创建xml解析对象
			SAXReader reader = new SAXReader();
			//读取xml文件，返回Document对象
			Document doc = reader.read(new File("./contact.xml"));
			System.out.println(doc);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
