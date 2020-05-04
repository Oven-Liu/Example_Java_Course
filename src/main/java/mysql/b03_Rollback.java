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
	 * û��rollback���ڶ���sql���ִ�г����쳣ʱ����һ��sql���ִ�гɹ�
	 */
	@Test
	public void test1() {
		String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='����'";
		String sql_ls = "UPDATE1 account SET money=money+1000 WHERE accountName='����'";
		try {
			con = JdbcUtil.getConnection();	
			// Ĭ���Զ��ύ��sql�����Ϊ���������ύ
//			con.setAutoCommit(true);
			// ִ�е�һ��sql
			pstmt = con.prepareStatement(sql_zs);
			pstmt.execute();
			// ִ�еڶ���sql
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
	 * �лع�������£������һ��sql���ִ��ʧ�ܣ�����sql��䶼����ִ��
	 */
	@Test
	public void test2() {
		String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='����'";
		String sql_ls = "UPDATE1 account SET money=money+1000 WHERE accountName='����'";
		try {
			con = JdbcUtil.getConnection();
			// �����ֶ��ύ
			con.setAutoCommit(false);
			// ִ�е�һ��sql
			pstmt = con.prepareStatement(sql_zs);
			pstmt.execute();
			// ִ��s�ڶ���sql
			pstmt = con.prepareStatement(sql_ls);
			pstmt.execute();
			// �ύ�����������sql��佫��Ϊһ�������ύ
			con.commit();
		} catch(Exception e) {
			// �����ύʧ�ܣ����ع�
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
	 * �лع�������£����ûع���ָ�����봦
	 * ִ������ת�ˣ�����ڶ���ʧ�ܣ��ع�����һ�ν����󣬵�һ�λ�ִ�У��ڶ��β�ִ��
	 */
	@Test
	public void test3() {
		
		Savepoint sp = null;
		
		// ��һ��ת��sql���
		String sql_zs1 = "UPDATE account SET money=money-1000 WHERE accountName='����'";
		String sql_ls1 = "UPDATE account SET money=money+1000 WHERE accountName='����'";
		// �ڶ���ת��sql���
		String sql_zs2 = "UPDATE account SET money=money-500 WHERE accountName='����'";
		String sql_ls2 = "UPDATE1 account SET money=money+500 WHERE accountName='����'";
		try {
			con = JdbcUtil.getConnection();
			// �����ֶ��ύ
			con.setAutoCommit(false);
			/*
			 *  ��һ��ִ��ת��
			 */
			pstmt = con.prepareStatement(sql_zs1);
			pstmt.execute();
			pstmt = con.prepareStatement(sql_ls1);
			pstmt.execute();
			/*
			 * ���ûع��� 
			 */
//			sp = con.setSavepoint();
			sp = con.setSavepoint("point");
			/*
			 * �ڶ���ִ��ת��
			 */
			pstmt = con.prepareStatement(sql_zs2);
			pstmt.execute();
			pstmt = con.prepareStatement(sql_ls2);
			pstmt.execute();
			
		} catch(Exception e) {
			// �����ύʧ�ܣ����ع�
			try {
				// ���ûع���λ��
				con.rollback(sp);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// �ύ�����������sql��佫��Ϊһ�������ύ
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.closeResource(con, pstmt, null);
		}
	}
}
