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
	 * 写入字符大数据类型文件
	 */
	@Test
	public void testSaveText() {
		String sql = "UPDATE bigData SET content=? WHERE id=1";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			// 获取字符大数据的路径
			String path = b05_BigData_Character.class.getResource("/Text/Character.txt").getPath();
			// 创建FileReader
			FileReader reader = new FileReader(new File(path));
			// 设置参数
			pstmt.setCharacterStream(1, reader);
			pstmt.executeUpdate();
			// 关闭输入流
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
	
	/**
	 * 读出大字符数据类型文件
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
				 * 获取大字符数据文件方式一
				 */
				// 创建输出字符流
				Writer writer = new FileWriter("F:/Java/OutputFile/Example_JavaCourse4/BigDataText.txt");
				// 获取大字符数据文件的输入流
				Reader reader = rs.getCharacterStream("content");
				int length = 0;
				while ((length = reader.read()) != -1) {
					writer.write(length);
				}
				writer.flush();
				writer.close();
				/*
				 * 获取大数据文件方式二
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
