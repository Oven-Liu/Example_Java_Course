package contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * ʵ����ϵ�˲����ӿ�
 * 
 * @author mengs
 */
public class ContactOperatorImplement implements ContactOperator {

	/**
	 * �����ϵ��
	 */
	@Override
	public void addContact(Contact contact) {
		try {
			File file = new File("F:/Java/OutputFile/Example_JavaCourse4/Contacts.xml");
			
			/*
			 * �������ȡXML�ļ�
			 */
			Document doc = null; 
			Element rootElement = null;
			if (!file.exists()) {
				//����XML�ļ�
				doc = DocumentHelper.createDocument();
				rootElement = doc.addElement("contactList");
			} else {
				//��ȡXML�ļ�
				doc = XMLUtil.getDocument();
				rootElement = doc.getRootElement();
			}
			
			//���contact��ǩ
			Element contactElem = rootElement.addElement("contact");
			contactElem.addAttribute("id", contact.getId());
			contactElem.addElement("name").setText(contact.getName());;
			contactElem.addElement("gender").setText(contact.getGender());;
			contactElem.addElement("age").setText(contact.getAge());;
			contactElem.addElement("phone").setText(contact.getPhone());;
			contactElem.addElement("qq").setText(contact.getQq());;
			contactElem.addElement("email").setText(contact.getEmail());;
			
			//��doc�ļ�д����XML�ļ�
			XMLUtil.writexml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * �޸���ϵ��
	 */
	@Override
	public void updateContact(Contact contact) {
		try {
			//��ȡXML�ļ�
			Document doc = XMLUtil.getDocument();
			//����id��ȡ��Ӧcontact����
			Element contactElem = (Element)doc.selectSingleNode("//contact[@id="+contact.getId()+"]");
			//�޸�contact��ǩ����
			contactElem.element("name").setText(contact.getName());
			contactElem.element("gender").setText(contact.getGender());
			contactElem.element("age").setText(contact.getAge());
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("qq").setText(contact.getQq());
			contactElem.element("email").setText(contact.getEmail());
			//��Documentд����XML�ļ�
			XMLUtil.writexml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * ɾ����ϵ��
	 */
	@Override
	public void deleteContact(String id) {
		//��ȡXML�ļ�
		Document doc = XMLUtil.getDocument();
		//����id��ȡ��Ӧ�Ĵ�contact
		Element contact = (Element)doc.selectSingleNode("//contact[@id="+id+"]");
		//ɾ��contact��ǩ
		contact.detach();
		//д����XML�ļ�
		XMLUtil.writexml(doc);
	}

	/**
	 * ��ʾ������ϵ��
	 */
	@Override
	public List<Contact> findAll() {
		//��ȡXML�ļ�
		Document doc = XMLUtil.getDocument();
		//����List����
		List<Contact> list = new ArrayList<Contact>();
		//��ȡXML�е�contact��ǩ
		List<Element> contacts = (List<Element>)doc.selectNodes("//contact");
		for (Element element : contacts) {
			//����Contact���󣬲���XML��contact���ݴ���Contact
			Contact con = new Contact();
			con.setId(element.attributeValue("id"));
			con.setName(element.elementText("name"));
			con.setGender(element.elementText("gender"));
			con.setAge(element.elementText("age"));
			con.setPhone(element.elementText("phone"));
			con.setQq(element.elementText("qq"));
			con.setEmail(element.elementText("email"));
			list.add(con);
		}
		return list;
	}
}
