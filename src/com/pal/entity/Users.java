package com.pal.entity;

import java.sql.Date;
/**
 * 用户表Users
 * @author Administrator
 *
 */
public class Users {
	private int u_id;//用户编号
	private String u_name;//用户名
	private String u_pwd;//用户密码
	private String headPic;//头像路径
	private String nickName;//昵称
	private String sex;//性别
	private int age;//年龄
	private String address;//地址
	private String phone;//电话号码
	private String email;//邮箱
	private String hobby;//爱好
	private String introdution;//个人介绍
	private String isOpen;//公开
	private String remark;//备注
	private Date offTime;//下线时间
	private Date registerTime;//注册时间
	private String career;//职业
	private int isban;//封禁状态
	public int getIsban() {
		return isban;
	}
	public void setIsban(int isban) {
		this.isban = isban;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getIntrodution() {
		return introdution;
	}
	public void setIntrodution(String introdution) {
		this.introdution = introdution;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getOffTime() {
		return offTime;
	}
	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public Users() {
		super();
	}
	public Users(int u_id, String u_name, String u_pwd, String headPic,
			String nickName, String sex, int age, String address, String phone,
			String email, String hobby, String introdution, String isOpen,
			String remark, Date offTime, Date registerTime, String career) {
		super();
		this.u_id = u_id;
		this.u_name = u_name;
		this.u_pwd = u_pwd;
		this.headPic = headPic;
		this.nickName = nickName;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.hobby = hobby;
		this.introdution = introdution;
		this.isOpen = isOpen;
		this.remark = remark;
		this.offTime = offTime;
		this.registerTime = registerTime;
		this.career = career;
	}
	@Override
	public String toString() {
		return "Users [u_id=" + u_id + ", u_name=" + u_name + ", u_pwd="
				+ u_pwd + ", headPic=" + headPic + ", nickName=" + nickName
				+ ", sex=" + sex + ", age=" + age + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", hobby=" + hobby
				+ ", introdution=" + introdution + ", isOpen=" + isOpen
				+ ", remark=" + remark + ", offTime=" + offTime
				+ ", registerTime=" + registerTime + ", career=" + career + "]";
	}
	
	
}
