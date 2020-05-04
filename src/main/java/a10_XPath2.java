import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xpath案列：模拟用户登录的效果
 * 
 * @author mengs
 */
public class a10_XPath2 {
	public static void main(String[] args) throws Exception {
		//获取用户输入的用户名及密码
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名：");
		String name = br.readLine();
		System.out.println("请输入密码：");
		String password = br.readLine();
		
		//到“数据库”中查询是否有对应的用户
		Document doc = new SAXReader().read(new File("./src/user.xml"));
		Element userElem = (Element)doc.selectSingleNode("//user[@name='"+name+"' and @password='"+password+"']");
		if (userElem != null) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
	}
}
