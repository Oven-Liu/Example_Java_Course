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
	 * 写入大文件二进制数据类型
	 */
	@Test
	public void testSaveImage() {
		String sql = "INSERT INTO bigData(img) VALUES(?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			// 获取图片的输入流，图片应该放在一个单独的包中，路径“/”表示项目的根目录
			InputStream in = b04_BigData_Binary.class.getResourceAsStream("/image/2014-03-30.jpg");
			// 设置PreparedStatement的参数，二进制
			pstmt.setBinaryStream(1, in);
			pstmt.executeUpdate();
			// 关闭输入流
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, rs);
		}
	}
	
	/**
	 * 读取大文件二进制数据类型
	 */
	@Test
	public void testGetimage() {
		String sql = "SELECT img FROM bigData WHERE id=1";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 获取图片输入流
				InputStream in = rs.getBinaryStream("img");
				// 创建图片输出流
				FileOutputStream out = new FileOutputStream(new File("F:/Java/OutputFile/Example_JavaCourse4/Picture/bigData_Binary.jpg"));
				byte buf[] = new byte[1024]; 
				int length = 0;
				// 边读边写
				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				// 关闭资源
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
