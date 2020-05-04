package basedao;

import java.util.List;

/**
 * �����ݽ�����ɾ��Ĳ���
 * 
 * @author Xudong.Liu
 */
public class Dao extends BaseDao{

	/**
	 * �������
	 */
	public void add(Admin admin) {
		String sql = "INSERT INTO Admin(userName,pwd) VALUES (?,?)"; 
		Object[] paramValues = {admin.getUserName(), admin.getPwd()};
		super.update(sql, paramValues);
	}
	
	/**
	 * ɾ����������
	 */
	public void delete(int id) {
		String sql = "DELETE FROM Admin WHERE id=?";
		Object[] paramValues = {id};
		super.update(sql, paramValues);
	}
	
	/**
	 * ɾ��ָ������
	 */
	public void delete() {
		String sql = "DELETE FROM Admin";
		Object[] paramValues = {null};
		super.update(sql, paramValues);
	}
	
	/**
	 * �޸�����
	 */
	public void update(int id, String passWord) {
		String sql = "UPDATE Admin SET pwd=? WHERE id=?";
		Object[] paramValues = {passWord, id};
		super.update(sql, paramValues);
	}
	
	/**
	 * ��ѯ��������
	 */
	public List<Admin> query() {
		String sql = "SELECT * FROM Admin";
		Object[] paramValues = {null};
		return super.query(sql, paramValues, Admin.class);
	}
	
	/**
	 * ��ѯָ��id����
	 */
	public List<Admin> query(int id) {
		String sql = "SELECT * FROM Admin WHERE id=?";
		Object[] paramValues = {id};
		return super.query(sql, paramValues, Admin.class);
	}
}
