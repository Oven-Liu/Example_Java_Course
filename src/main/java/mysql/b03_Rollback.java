package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import util.JdbcUtil;

public class b03_Rollback {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	/**
	 * 没有rollback，第二句sql语句执行出现异常时，第一句sql语句执行成功
	 */
	@Test
	public void test1() {
		String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='张三'";
		String sql_ls = "UPDATE1 account SET money=money+1000 WHERE accountName='李四'";
		try {
			con = JdbcUtil.getConnection();	
			// 默认自动提交，sql语句作为单个事务提交
//			con.setAutoCommit(true);
			// 执行第一句sql
			pstmt = con.prepareStatement(sql_zs);
			pstmt.execute();
			// 执行第二句sql
			pstmt = con.prepareStatement(sql_ls);
			pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, null);
		}
	}
	
	/**
	 * 有回滚的情况下，如果有一句sql语句执行失败，两句sql语句都不会执行
	 */
	@Test
	public void test2() {
		String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='张三'";
		String sql_ls = "UPDATE1 account SET money=money+1000 WHERE accountName='李四'";
		try {
			con = JdbcUtil.getConnection();
			// 设置手动提交
			con.setAutoCommit(false);
			// 执行第一句sql
			pstmt = con.prepareStatement(sql_zs);
			pstmt.execute();
			// 执行s第二句sql
			pstmt = con.prepareStatement(sql_ls);
			pstmt.execute();
			// 提交，上面的所有sql语句将作为一个事务提交
			con.commit();
		} catch(Exception e) {
			// 设置提交失败，将回滚
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, pstmt, null);
		}
	}
	
	/**
	 * 有回滚的情况下，设置回滚到指定代码处
	 * 执行两次转账，如果第二次失败，回滚到第一次结束后，第一次会执行，第二次不执行
	 */
	@Test
	public void test3() {
		
		Savepoint sp = null;
		
		// 第一次转账sql语句
		String sql_zs1 = "UPDATE account SET money=money-1000 WHERE accountName='张三'";
		String sql_ls1 = "UPDATE account SET money=money+1000 WHERE accountName='李四'";
		// 第二次转账sql语句
		String sql_zs2 = "UPDATE account SET money=money-500 WHERE accountName='张三'";
		String sql_ls2 = "UPDATE1 account SET money=money+500 WHERE accountName='李四'";
		try {
			con = JdbcUtil.getConnection();
			// 设置手动提交
			con.setAutoCommit(false);
			/*
			 *  第一次执行转账
			 */
			pstmt = con.prepareStatement(sql_zs1);
			pstmt.execute();
			pstmt = con.prepareStatement(sql_ls1);
			pstmt.execute();
			/*
			 * 设置回滚点 
			 */
//			sp = con.setSavepoint();
			sp = con.setSavepoint("point");
			/*
			 * 第二次执行转账
			 */
			pstmt = con.prepareStatement(sql_zs2);
			pstmt.execute();
			pstmt = con.prepareStatement(sql_ls2);
			pstmt.execute();
			
		} catch(Exception e) {
			// 设置提交失败，将回滚
			try {
				// 设置回滚的位置
				con.rollback(sp);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 提交，上面的所有sql语句将作为一个事务提交
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.closeResource(con, pstmt, null);
		}
	}
}
