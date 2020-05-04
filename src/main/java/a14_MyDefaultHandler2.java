import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX����������
 * 
 * @author mengs
 */
public class a14_MyDefaultHandler2 extends DefaultHandler {
	//�����ĵ���Ϣ
	private StringBuffer sb = new StringBuffer();
	
	/**
	 * ��ȡxml��Ϣ
	 * 
	 * @return String
	 */
	public String getContent() {
		return sb.toString();
	}
	
	/**
	 * ��ʼ��ǩ����������
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
	 * �ı�����
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		sb.append(content);
	}
	
	/**
	 * ������ǩ
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		sb.append("</"+qName+">");
	}
}
