import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX处理程序
 * 
 * @author mengs
 */
public class a16_MyDefaultHandler3 extends DefaultHandler {
	//储蓄所有联系人对象
	private List<a04_contact> list = new ArrayList<a04_contact>();
	private a04_contact contact;
	private String curTag;
	
	/**
	 * 获取contact集合
	 * 
	 * @return
	 */
	public List<a04_contact> getList() {
		return list;
	}
	
	/**
	 * 开始标签
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		curTag = qName;
		/*
		 * 1、创建contact对象
		 */
		if ("contact".equals(qName)) {
			contact = new a04_contact();
			//设置属性id
			contact.setId(attributes.getValue("id"));
		}
	}
	
	/**
	 * 文本内容
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		/*
		 * 2、将contact标签内容存入contact对象中
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
	 * 结束标签
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//避免读到age、phone等结束标签时，将后面的空格及换行文本覆盖之前的文本
		curTag = null;
		/*
		 * 3、将contact对象存入List中
		 */
		if ("contact".equals(qName)) {
			list.add(contact);
		}
	}
}
