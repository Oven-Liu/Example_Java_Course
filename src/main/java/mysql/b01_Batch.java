package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import util.JdbcUtil;

public class b01_Batch {
	Connection con = null;
	PreparedStatement presta = null;
	
	@Test
	public void testBatch() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "INSERT INTO statement (NAME,gender) VALUES (?,?)";
			presta = con.prepareStatement(sql);
			presta.setString(1, "张三2");
			presta.setString(2, "女");
			presta.addBatch();	// 如果漏写addBatch，后面的参数设置将覆盖前面的，导致前面的参数没有设置
			presta.setString(1, "张三3");
			presta.setString(2, "男");
			presta.addBatch();
			presta.setString(1, "张三4");
			presta.setString(2, "女");
			presta.addBatch();
			int[] content = presta.executeBatch();
			presta.clearBatch();
			for (int cont : content) {
				System.out.println(cont);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
}
