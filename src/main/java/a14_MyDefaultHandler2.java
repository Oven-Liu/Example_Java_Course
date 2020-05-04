import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX处理器程序
 * 
 * @author mengs
 */
public class a14_MyDefaultHandler2 extends DefaultHandler {
	//储存文档信息
	private StringBuffer sb = new StringBuffer();
	
	/**
	 * 获取xml信息
	 * 
	 * @return String
	 */
	public String getContent() {
		return sb.toString();
	}
	
	/**
	 * 开始标签，包括属性
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		sb.append("<"+qName);
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String name = attributes.getQName(i);
				String value = attributes.getValue(i);
				sb.append(" "+name+"=\""+value+"\"");
			}
		}
		sb.append(">");
	}
	
	/**
	 * 文本内容
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		sb.append(content);
	}
	
	/**
	 * 结束标签
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		sb.append("</"+qName+">");
	}
}
