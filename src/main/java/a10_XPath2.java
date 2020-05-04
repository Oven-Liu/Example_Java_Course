import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xpath���У�ģ���û���¼��Ч��
 * 
 * @author mengs
 */
public class a10_XPath2 {
	public static void main(String[] args) throws Exception {
		//��ȡ�û�������û���������
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("�������û�����");
		String name = br.readLine();
		System.out.println("���������룺");
		String password = br.readLine();
		
		//�������ݿ⡱�в�ѯ�Ƿ��ж�Ӧ���û�
		Document doc = new SAXReader().read(new File("./src/user.xml"));
		Element userElem = (Element)doc.selectSingleNode("//user[@name='"+name+"' and @password='"+password+"']");
		if (userElem != null) {
			System.out.println("��¼�ɹ�");
		} else {
			System.out.println("��¼ʧ��");
		}
	}
}
