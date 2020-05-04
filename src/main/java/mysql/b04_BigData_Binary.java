package mysql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;
import util.JdbcUtil;

public class b04_BigData_Binary {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * д����ļ���������������
	 */
	@Test
	public void testSaveImage() {
		String sql = "INSERT INTO bigData(img) VALUES(?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			// ��ȡͼƬ����������ͼƬӦ�÷���һ�������İ��У�·����/����ʾ��Ŀ�ĸ�Ŀ¼
			InputStream in = b04_BigData_Binary.class.getResourceAsStream("/image/2014-03-30.jpg");
			// ����PreparedStatement�Ĳ�����������
			pstmt.setBinaryStream(1, in);
			pstmt.executeUpdate();
			// �ر�������
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
	
	/**
	 * ��ȡ���ļ���������������
	 */
	@Test
	public void testGetimage() {
		String sql = "SELECT img FROM bigData WHERE id=1";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// ��ȡͼƬ������
				InputStream in = rs.getBinaryStream("img");
				// ����ͼƬ�����
				FileOutputStream out = new FileOutputStream(new File("F:/Java/OutputFile/Example_JavaCourse4/Picture/bigData_Binary.jpg"));
				byte buf[] = new byte[1024]; 
				int length = 0;
				// �߶���д
				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				// �ر���Դ
				out.close();
				in.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
}
