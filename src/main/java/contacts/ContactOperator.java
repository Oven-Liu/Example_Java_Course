package contacts;

import java.util.List;

/**
 * �ýӿ����ڴ�Ų�����ϵ�˵ķ���
 * 
 * @author mengs
 */
public interface ContactOperator {
	public void addContact(Contact contact);	//�����ϵ��
	public void updateContact(Contact contact);	//�޸�
	public void deleteContact(String id);	//����idɾ����ϵ��
	public List<Contact> findAll();	//��ѯ������ϵ��
}
