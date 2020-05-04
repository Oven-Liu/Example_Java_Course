package JDK8newspeciality.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class TestBase64 {
	public static void main(String args[]) {
		try {
			// ʹ�û�������
			String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
			System.out.println("Base64 �����ַ��� (����) :" + base64encodedString);
			// ����
			byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
			System.out.println("ԭʼ�ַ���: " + new String(base64decodedBytes, "utf-8"));
			
			base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
			System.out.println("Base64 �����ַ��� (URL) :" + base64encodedString);

			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < 3; ++i) {
				stringBuilder.append(UUID.randomUUID().toString());
			}
			String mimeEncodedString = Base64.getMimeEncoder().encodeToString(stringBuilder.toString().getBytes("utf-8"));
			System.out.println("Base64 �����ַ��� (MIME) :" + mimeEncodedString);

		} catch(UnsupportedEncodingException e) {
			System.out.println("Error :" + e.getMessage());
		}
	}
}
