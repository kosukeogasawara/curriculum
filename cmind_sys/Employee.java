package jp.co.sss.sys.entity;

import java.sql.Date;

import javax.persistence.Column;
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
	@Column(name = "emp_id")
	private String empId;
	
	/**
	 * 社員名
	 */
	@Column(name = "emp_name")
	private String empName;
	/**
	 * パスワード
	 */
	@Column(name = "password")
	private String password;
	
	/**
	 * 誕生日
	 */
	@Column(name = "birthday")
	private Date birthday;
	
	/**
	 * 性別
	 */
	@Column(name = "gender")
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
	    return empName;
	}
	
	public void setEmpName(String emp_name) {
	    this.empName = emp_name;
	}
	
	//パスワード
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	//誕生日
	public Date getBirthday() {
	    return birthday;
	}
	
	public void setBirthday(Date birthday) {
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
