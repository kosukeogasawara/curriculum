package jp.co.sss.sys.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * エンティティクラス
 * @author Inoue Nami
 *
 */
@Entity
@Table(name = "employee")
public class Employee {

	/**
	 * 社員番号
	 */
	@Id
	private String empId;
	
	/**
	 * 社員名
	 */
	private String empname;
	/**
	 * パスワード
	 */
	private String password;
	
	/**
	 * 誕生日
	 */
	private String birthday;
	
	/**
	 * 性別
	 */
	private int gender;
	
	
	//ゲッターセッター
	
	//社員番号
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String emp_id) {
		this.empId = emp_id;
	}
	//社員名
	public String getEmpName() {
	    return empname;
	}
	
	public void setEmpName(String emp_name) {
	    this.empname = emp_name;
	}
	
	//パスワード
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	//誕生日
	public String getBirthday() {
	    return birthday;
	}
	
	public void setBirthday(String birthday) {
	    this.birthday = birthday;
	}
	
	//性別
	public int getGender() {
	    return gender;
	}
	
	public void setGender(int gender) {
	    this.gender = gender;
	}
}
