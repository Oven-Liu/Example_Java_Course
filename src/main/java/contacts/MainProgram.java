package contacts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * ������
 * 
 * @author mengs
 */
public class MainProgram {
	public static void main(String[] args) {
		try {
			/*
			Scanner in = new Scanner(System.in);
			*/
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			ContactOperatorImplement operator = new ContactOperatorImplement();
			while (true) {
				/*
				 * �鿴�˵�
				 */
				printMenu();
				//�����û�����ѡ����
				String option = br.readLine();
				/*
				 * �����û���ѡ������ִ����Ӧ������
				 */
				if ("1".equals(option)) {
					//�����ϵ��
					
					//������ϵ�˶���
					Contact contact = new Contact();
					//��ȡ�û�������ϵ������
					System.out.println("�������ţ�");
					String id = br.readLine();
					System.out.println("������������");
					String name = br.readLine();
					System.out.println("�������Ա�");
					String gender = br.readLine();
					System.out.println("���������䣺");
					String age = br.readLine();
					System.out.println("������绰���룺");
					String phone = br.readLine();
					System.out.println("������qq�ţ�");
					String qq = br.readLine();
					System.out.println("���������䣺");
					String email = br.readLine();
					//����ϵ�����ݴ���Contact������
					contact.setId(id);
					contact.setName(name);
					contact.setGender(gender);
					contact.setAge(age);
					contact.setPhone(phone);
					contact.setQq(qq);
					contact.setEmail(email);
					//������ϵ����Ϣ��XML�ļ���
					operator.addContact(contact);
				} else if ("2".equals(option)) {
					//�޸���ϵ��
					
					//������ϵ�˶���
					Contact contact = new Contact();
					//��ȡ�û������޸���ϵ����Ϣ
					System.out.println("�������޸ĺ���ϵ�˵ı��");
					String id = br.readLine();
					System.out.println("�������޸ĺ���ϵ�˵�����");
					String name = br.readLine();
					System.out.println("�������޸ĺ���ϵ�˵��Ա�");
					String gender = br.readLine();
					System.out.println("�������޸ĺ���ϵ�˵�����");
					String age = br.readLine();
					System.out.println("�������޸ĺ���ϵ�˵ĵ绰����");
					String phone = br.readLine();
					System.out.println("�������޸ĺ���ϵ�˵�qq");
					String qq = br.readLine();
					System.out.println("�������޸ĺ���ϵ�˵�����");
					String email = br.readLine();
					//����ϵ����Ϣ����contact������
					contact.setId(id);
					contact.setName(name);
					contact.setGender(gender);
					contact.setAge(age);
					contact.setPhone(phone);
					contact.setQq(qq);
					contact.setEmail(email);
					//���޸ĵ�contact���浽XML�ļ���
					operator.updateContact(contact);
				} else if ("3".equals(option)) {
					//ɾ����ϵ��
					
					System.out.println("������Ҫɾ����ϵ�˵ı��");
					String id = br.readLine();
					operator.deleteContact(id);
				} else if ("4".equals(option)) {
					//��ʾ������ϵ��
					
					List<Contact> list = operator.findAll();
					for (Contact contact : list) {
						System.out.println(contact);
					}
				} else if ("Q".equalsIgnoreCase(option)) {
					break;
				} else {
					System.out.println("���������������������");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ӡѡ��˵�
	 */
	private static void printMenu() {
		System.out.println("========================");
		System.out.println("[1]�����ϵ��");
		System.out.println("[2]�޸���ϵ��");
		System.out.println("[3]ɾ����ϵ��");
		System.out.println("[4]�鿴������ϵ��");
		System.out.println("[Q]�˳�ϵͳ");
		System.out.println("========================");
	}
}
