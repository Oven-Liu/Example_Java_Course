import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * ��һ��Dom4j��ȡxml�ĵ�������
 * 
 * @author mengs
 *
 */
public class a01_dom4jRead {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//����xml��������
			SAXReader reader = new SAXReader();
			//��ȡxml�ļ�������Document����
			Document doc = reader.read(new File("./contact.xml"));
			System.out.println(doc);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
