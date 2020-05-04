package contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 实现联系人操作接口
 * 
 * @author mengs
 */
public class ContactOperatorImplement implements ContactOperator {

	/**
	 * 添加联系人
	 */
	@Override
	public void addContact(Contact contact) {
		try {
			File file = new File("F:/Java/OutputFile/Example_JavaCourse4/Contacts.xml");
			
			/*
			 * 创建或获取XML文件
			 */
			Document doc = null; 
			Element rootElement = null;
			if (!file.exists()) {
				//创建XML文件
				doc = DocumentHelper.createDocument();
				rootElement = doc.addElement("contactList");
			} else {
				//获取XML文件
				doc = XMLUtil.getDocument();
				rootElement = doc.getRootElement();
			}
			
			//添加contact标签
			Element contactElem = rootElement.addElement("contact");
			contactElem.addAttribute("id", contact.getId());
			contactElem.addElement("name").setText(contact.getName());;
			contactElem.addElement("gender").setText(contact.getGender());;
			contactElem.addElement("age").setText(contact.getAge());;
			contactElem.addElement("phone").setText(contact.getPhone());;
			contactElem.addElement("qq").setText(contact.getQq());;
			contactElem.addElement("email").setText(contact.getEmail());;
			
			//将doc文件写出到XML文件
			XMLUtil.writexml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改联系人
	 */
	@Override
	public void updateContact(Contact contact) {
		try {
			//读取XML文件
			Document doc = XMLUtil.getDocument();
			//根据id获取相应contact对象
			Element contactElem = (Element)doc.selectSingleNode("//contact[@id="+contact.getId()+"]");
			//修改contact标签内容
			contactElem.element("name").setText(contact.getName());
			contactElem.element("gender").setText(contact.getGender());
			contactElem.element("age").setText(contact.getAge());
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("qq").setText(contact.getQq());
			contactElem.element("email").setText(contact.getEmail());
			//把Document写出到XML文件
			XMLUtil.writexml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除联系人
	 */
	@Override
	public void deleteContact(String id) {
		//读取XML文件
		Document doc = XMLUtil.getDocument();
		//根据id获取相应的从contact
		Element contact = (Element)doc.selectSingleNode("//contact[@id="+id+"]");
		//删除contact标签
		contact.detach();
		//写出到XML文件
		XMLUtil.writexml(doc);
	}

	/**
	 * 显示所有联系人
	 */
	@Override
	public List<Contact> findAll() {
		//读取XML文件
		Document doc = XMLUtil.getDocument();
		//创建List对象
		List<Contact> list = new ArrayList<Contact>();
		//读取XML中的contact标签
		List<Element> contacts = (List<Element>)doc.selectNodes("//contact");
		for (Element element : contacts) {
			//创建Contact对象，并将XML中contact数据传给Contact
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
