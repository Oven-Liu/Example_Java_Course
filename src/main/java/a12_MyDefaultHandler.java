import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX������򣬼���ν���xml�ĵ�
 * 
 * @author mengs
 */
public class a12_MyDefaultHandler extends DefaultHandler {

	/**
	 * ��ʼ�ĵ�ʱ����
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("a12_MyDefaultHandler.startDocument()");
	}
	
	/**
	 * ��ʼ��ǩʱ����
	 * 
	 * @param qName����ʾ��ʼ��ǩ�ı�ǩ��
	 * @param attribute����ʾ��ʼ��ǩ�ڰ��������������б�
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("a12_MyDefaultHandler.startElement()��������>"+qName);
	}
	
	/**
	 * �����ı�����ʱ����
	 * 
	 * @param ch����ʾ��ǰ����������ı�����
	 * @param start����ʾ��ǰ�ı��Ŀ�ʼλ��
	 * @param length����ʾ��ǰ�ı����ݵĳ���
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.println("a12_MyDefaultHandler.characters()��������>"+content);
	}
	
	/**
	 * ������ǩʱ����
	 * 
	 * @param qName����ʾ������ǩ�ı�ǩ��
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("a12_MyDefaultHandler.endElement()��������>"+qName);
	}
	
	/**
	 * �����ĵ�ʱ����
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("a12_MyDefaultHandler.endDocument()");
	}
}
