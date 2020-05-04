import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class a09_XPath {
	/**
	 * 删除id为2的学生
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Document doc = new SAXReader().read(new File("F:/Java/OutputFile/Students.xml"));
		
		//得到id为2的student对象
		Element stuElem = (Element)doc.selectSingleNode("//student[@id='2']");
		stuElem.detach();
		
		//内容写出到xml文件
		FileOutputStream out = new FileOutputStream("F:/Java/OutputFile/Students.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
	}
}
