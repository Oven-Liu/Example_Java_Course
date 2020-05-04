package annotation;

@b02_TableAnnotation(tableName = "Admin")
public class b01_Admin {

	@b04_IdAnnotation
	@b03_ColumnAnnotation(columnName = "id")
	private int b_id;
	@b03_ColumnAnnotation(columnName = "userName")
	private String b_userName;
	@b03_ColumnAnnotation(columnName = "pwd")
	private String b_pwd;
	
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_userName() {
		return b_userName;
	}
	public void setB_userName(String b_userName) {
		this.b_userName = b_userName;
	}
	public String getB_pwd() {
		return b_pwd;
	}
	public void setB_pwd(String b_pwd) {
		this.b_pwd = b_pwd;
	}
	@Override
	public String toString() {
		return "b01_Admin [b_id=" + b_id + ", b_userName=" + b_userName + ", b_pwd=" + b_pwd + "]";
	}
	
}
