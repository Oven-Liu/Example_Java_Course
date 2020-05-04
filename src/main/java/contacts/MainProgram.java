package contacts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 主程序
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
				 * 查看菜单
				 */
				printMenu();
				//接收用户输入选择结果
				String option = br.readLine();
				/*
				 * 根据用户的选择结果，执行相应的命令
				 */
				if ("1".equals(option)) {
					//添加联系人
					
					//创建联系人对象
					Contact contact = new Contact();
					//获取用户输入联系人数据
					System.out.println("请输入编号：");
					String id = br.readLine();
					System.out.println("请输入姓名：");
					String name = br.readLine();
					System.out.println("请输入性别：");
					String gender = br.readLine();
					System.out.println("请输入年龄：");
					String age = br.readLine();
					System.out.println("请输入电话号码：");
					String phone = br.readLine();
					System.out.println("请输入qq号：");
					String qq = br.readLine();
					System.out.println("请输入邮箱：");
					String email = br.readLine();
					//将联系人数据存入Contact对象中
					contact.setId(id);
					contact.setName(name);
					contact.setGender(gender);
					contact.setAge(age);
					contact.setPhone(phone);
					contact.setQq(qq);
					contact.setEmail(email);
					//保存联系人信息到XML文件中
					operator.addContact(contact);
				} else if ("2".equals(option)) {
					//修改联系人
					
					//创建联系人对象
					Contact contact = new Contact();
					//获取用户输入修改联系人信息
					System.out.println("请输入修改后联系人的编号");
					String id = br.readLine();
					System.out.println("请输入修改后联系人的姓名");
					String name = br.readLine();
					System.out.println("请输入修改后联系人的性别");
					String gender = br.readLine();
					System.out.println("请输入修改后联系人的年龄");
					String age = br.readLine();
					System.out.println("请输入修改后联系人的电话号码");
					String phone = br.readLine();
					System.out.println("请输入修改后联系人的qq");
					String qq = br.readLine();
					System.out.println("请输入修改后联系人的邮箱");
					String email = br.readLine();
					//将联系人信息存入contact对象中
					contact.setId(id);
					contact.setName(name);
					contact.setGender(gender);
					contact.setAge(age);
					contact.setPhone(phone);
					contact.setQq(qq);
					contact.setEmail(email);
					//将修改的contact保存到XML文件中
					operator.updateContact(contact);
				} else if ("3".equals(option)) {
					//删除联系人
					
					System.out.println("请输入要删除联系人的编号");
					String id = br.readLine();
					operator.deleteContact(id);
				} else if ("4".equals(option)) {
					//显示所有联系人
					
					List<Contact> list = operator.findAll();
					for (Contact contact : list) {
						System.out.println(contact);
					}
				} else if ("Q".equalsIgnoreCase(option)) {
					break;
				} else {
					System.out.println("你的输入有误，请重新输入");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 打印选择菜单
	 */
	private static void printMenu() {
		System.out.println("========================");
		System.out.println("[1]添加联系人");
		System.out.println("[2]修改联系人");
		System.out.println("[3]删除联系人");
		System.out.println("[4]查看所有联系人");
		System.out.println("[Q]退出系统");
		System.out.println("========================");
	}
}
