import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX�������
 * 
 * @author mengs
 */
public class a16_MyDefaultHandler3 extends DefaultHandler {
	//����������ϵ�˶���
	private List<a04_contact> list = new ArrayList<a04_contact>();
	private a04_contact contact;
	private String curTag;
	
	/**
	 * ��ȡcontact����
	 * 
	 * @return
	 */
	public List<a04_contact> getList() {
		return list;
	}
	
	/**
	 * ��ʼ��ǩ
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		curTag = qName;
		/*
		 * 1������contact����
		 */
		if ("contact".equals(qName)) {
			contact = new a04_contact();
			//��������id
			contact.setId(attributes.getValue("id"));
		}
	}
	
	/**
	 * �ı�����
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		/*
		 * 2����contact��ǩ���ݴ���contact������
		 */
		String content = new String(ch, start, length);
		if ("name".equals(curTag)) {
			contact.setName(content);
		}
		if ("age".equals(curTag)) {
			contact.setAge(content);
		}
		if ("phone".equals(curTag)) {
			contact.setPhone(content);
		}
		if ("email".equals(curTag)) {
			contact.setEmail(content);
		}
		if ("qq".equals(curTag)) {
			contact.setQq(content);
		}
	}
	
	/**
	 * ������ǩ
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//�������age��phone�Ƚ�����ǩʱ��������Ŀո񼰻����ı�����֮ǰ���ı�
		curTag = null;
		/*
		 * 3����contact�������List��
		 */
		if ("contact".equals(qName)) {
			list.add(contact);
		}
	}
}
