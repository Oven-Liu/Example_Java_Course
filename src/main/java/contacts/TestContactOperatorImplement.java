package contacts;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * ��ϵ�˲���ʵ����Ĳ�����
 * 
 * @author mengs
 */
public class TestContactOperatorImplement {
	ContactOperatorImplement operator = null;
	
	/**
	 * ��ʼ������
	 */
	@Before
	public void init() {
		operator = new ContactOperatorImplement();
	}
	
	/**
	 * �����ϵ��
	 */
	@Test
	public void testAddContact() {
		Contact contact = new Contact();
		contact.setId("3");
		contact.setName("����");
		contact.setGender("��");
		contact.setAge("22");
		contact.setPhone("13156474535");
		contact.setQq("16547657");
		contact.setEmail("wangwu@qq.com");
		operator.addContact(contact);
	}
	
	/**
	 * �޸���ϵ��
	 */
	@Test
	public void testUpdateContact() {
		Contact contact = new Contact();
		contact.setId("3");
		contact.setName("����");
		contact.setGender("��");
		contact.setAge("25");	//�޸�����
		contact.setPhone("13156474535");
		contact.setQq("16547657");
		contact.setEmail("wangwu@qq.com");
		operator.updateContact(contact);
	}
	
	/**
	 * ɾ����ϵ��
	 */
	@Test
	public void testDeleteContact() {
		operator.deleteContact("4");;
	}
	
	/**
	 * ��ʾ������ϵ��
	 */
	@Test
	public void testFindAll() {
		List<Contact> list = operator.findAll();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
