package contacts;

import java.util.List;

/**
 * 该接口用于存放操作联系人的方法
 * 
 * @author mengs
 */
public interface ContactOperator {
	public void addContact(Contact contact);	//添加联系人
	public void updateContact(Contact contact);	//修改
	public void deleteContact(String id);	//根据id删除联系人
	public List<Contact> findAll();	//查询所有联系人
}
