package com.pal.entity;
/**
 * 管理员表Admin
 * @author Administrator
 *
 */
public class Admin {
	private int a_id;//管理员编号id,主键，自增
	private String a_name;//管理员名字
	private String a_pwd;//管理员密码
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getA_pwd() {
		return a_pwd;
	}
	public void setA_pwd(String a_pwd) {
		this.a_pwd = a_pwd;
	}
	public Admin() {
		super();
	}
	public Admin(int a_id, String a_name, String a_pwd) {
		super();
		this.a_id = a_id;
		this.a_name = a_name;
		this.a_pwd = a_pwd;
	}
	@Override
	public String toString() {
		return "Admin [a_id=" + a_id + ", a_name=" + a_name + ", a_pwd="
				+ a_pwd + "]";
	}
	
	
}
