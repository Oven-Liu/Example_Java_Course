package contacts;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 联系人操作实现类的测试类
 * 
 * @author mengs
 */
public class TestContactOperatorImplement {
	ContactOperatorImplement operator = null;
	
	/**
	 * 初始化对象
	 */
	@Before
	public void init() {
		operator = new ContactOperatorImplement();
	}
	
	/**
	 * 添加联系人
	 */
	@Test
	public void testAddContact() {
		Contact contact = new Contact();
		contact.setId("3");
		contact.setName("王五");
		contact.setGender("男");
		contact.setAge("22");
		contact.setPhone("13156474535");
		contact.setQq("16547657");
		contact.setEmail("wangwu@qq.com");
		operator.addContact(contact);
	}
	
	/**
	 * 修改联系人
	 */
	@Test
	public void testUpdateContact() {
		Contact contact = new Contact();
		contact.setId("3");
		contact.setName("王五");
		contact.setGender("男");
		contact.setAge("25");	//修改年龄
		contact.setPhone("13156474535");
		contact.setQq("16547657");
		contact.setEmail("wangwu@qq.com");
		operator.updateContact(contact);
	}
	
	/**
	 * 删除联系人
	 */
	@Test
	public void testDeleteContact() {
		operator.deleteContact("4");;
	}
	
	/**
	 * 显示所有联系人
	 */
	@Test
	public void testFindAll() {
		List<Contact> list = operator.findAll();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
