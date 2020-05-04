package mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;
import util.JdbcUtil;

/**
 * 获取元数据
 * 
 * @author Xudong.Liu
 */
public class b06_MetaData {

	/**
	 * 获取数据库元数据
	 */
	@Test
	public void testDB() throws Exception {
		Connection conn = JdbcUtil.getConnection();
		// 创建数据库元数据对象
		DatabaseMetaData dbmd = conn.getMetaData();
		System.out.println(dbmd.getURL());
		System.out.println(dbmd.getUserName());
		System.out.println(dbmd.getDatabaseProductName());
		System.out.println(dbmd.isReadOnly());
	}
	
	/**
	 * 获取参数元数据
	 */
	@Test
	public void testPatams() throws Exception {
		
		String sql = "SELECT * FROM statement WHERE NAME=? AND gender=?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 创建参数元数据
		ParameterMetaData pmd = pstmt.getParameterMetaData();
		// 获取参数的个数
		System.out.println(pmd.getParameterCount());
		// 获取参数的类型
		System.out.println(pmd.getParameterTypeName(1));
//		System.out.println(pmd.getParameterType(1));
	}
	
	@Test
	public void testRS() throws Exception {
		String sql ="SELECT * FROM statement";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 创建结果集合元数据
		ResultSetMetaData rsmd = pstmt.getMetaData();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			// 获取结果集合的列数
			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				// 获取结果集合中当前行的指定列的值
				Object value = rs.getObject(i+1);
				// 获取列名称
				String name = rsmd.getColumnName(i+1);
				
				// 获取数据类型
				String type = rsmd.getColumnTypeName(i+1);
				System.out.print(name + "：" + type + "=" + value + ",");
			}
			System.out.println();
		}
	}
}
