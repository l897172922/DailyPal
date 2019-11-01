package com.pal.entity;
/**
 * 原因类别表ReasonType
 * @author Administrator
 *
 */
public class ReasonType {
	private int rt_typeId;//原因类别id,主键，自增
	private String rt_name;//原因类别名称
	public int getRt_typeId() {
		return rt_typeId;
	}
	public void setRt_typeId(int rt_typeId) {
		this.rt_typeId = rt_typeId;
	}
	public String getRt_name() {
		return rt_name;
	}
	public void setRt_name(String rt_name) {
		this.rt_name = rt_name;
	}
	public ReasonType() {
		super();
	}
	public ReasonType(int rt_typeId, String rt_name) {
		super();
		this.rt_typeId = rt_typeId;
		this.rt_name = rt_name;
	}
	@Override
	public String toString() {
		return "ReasonType [rt_typeId=" + rt_typeId + ", rt_name=" + rt_name
				+ "]";
	}
	
	
}
