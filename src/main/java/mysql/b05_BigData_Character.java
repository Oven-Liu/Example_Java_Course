package mysql;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;

public class b05_BigData_Character {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * д���ַ������������ļ�
	 */
	@Test
	public void testSaveText() {
		String sql = "UPDATE bigData SET content=? WHERE id=1";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			// ��ȡ�ַ������ݵ�·��
			String path = b05_BigData_Character.class.getResource("/Text/Character.txt").getPath();
			// ����FileReader
			FileReader reader = new FileReader(new File(path));
			// ���ò���
			pstmt.setCharacterStream(1, reader);
			pstmt.executeUpdate();
			// �ر�������
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
	
	/**
	 * �������ַ����������ļ�
	 */
	@Test
	public void testGetText() {
		String sql = "SELECT content FROM bigData WHERE id=1";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				/*
				 * ��ȡ���ַ������ļ���ʽһ
				 */
				// ��������ַ���
				Writer writer = new FileWriter("F:/Java/OutputFile/Example_JavaCourse4/BigDataText.txt");
				// ��ȡ���ַ������ļ���������
				Reader reader = rs.getCharacterStream("content");
				int length = 0;
				while ((length = reader.read()) != -1) {
					writer.write(length);
				}
				writer.flush();
				writer.close();
				/*
				 * ��ȡ�������ļ���ʽ��
				 */
//				System.out.println(rs.getString("content"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
}
