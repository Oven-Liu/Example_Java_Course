import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 读取完整的xml内容
 * 
 * @author mengs
 *
 */
public class a03_dom4jRead3 {
	@Test
	public void test() throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./contact.xml"));
		Element rootElement = doc.getRootElement();
		StringBuffer sb = new StringBuffer();
		getChildNodes(rootElement, sb);
		System.out.println(sb.toString());
	} 
	
	//获取当前标签的所有子标签
	private void getChildNodes(Element elt, StringBuffer sb) {
		sb.append("<"+elt.getName());
		
		//获取属性列表
		List<Attribute> attributes = elt.attributes();
		if (attributes != null) {
			for (Attribute attr : attributes) {
				sb.append(" "+attr.getName()+"=\""+attr.getValue()+"\"");
			}
		}
		sb.append(">");
		
		//获取文本
		Iterator<Node> it = elt.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			//如果是标签就递归
			if (node instanceof Element) {
				Element element = (Element)node;
				getChildNodes(element, sb);
			}
			if (node instanceof Text) {
				Text text = (Text)node;
				sb.append(text.getText());
			}
		}
		sb.append("</"+elt.getName()+">");
	}
}
