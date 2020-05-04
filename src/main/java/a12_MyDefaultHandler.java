import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX处理程序，即如何解析xml文档
 * 
 * @author mengs
 */
public class a12_MyDefaultHandler extends DefaultHandler {

	/**
	 * 开始文档时调用
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("a12_MyDefaultHandler.startDocument()");
	}
	
	/**
	 * 开始标签时调用
	 * 
	 * @param qName：表示开始标签的标签名
	 * @param attribute：表示开始标签内包含的所有属性列表
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("a12_MyDefaultHandler.startElement()————>"+qName);
	}
	
	/**
	 * 读到文本内容时调用
	 * 
	 * @param ch：表示当前读完的所有文本内容
	 * @param start：表示当前文本的开始位置
	 * @param length：表示当前文本内容的长度
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.println("a12_MyDefaultHandler.characters()————>"+content);
	}
	
	/**
	 * 结束标签时调用
	 * 
	 * @param qName：表示结束标签的标签名
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("a12_MyDefaultHandler.endElement()————>"+qName);
	}
	
	/**
	 * 结束文档时调用
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("a12_MyDefaultHandler.endDocument()");
	}
}
