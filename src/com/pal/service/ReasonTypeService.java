package com.pal.service;

import java.util.ArrayList;

import com.pal.dao.ReasonTypeDao;
import com.pal.entity.ReasonType;

public class ReasonTypeService {
	
	ReasonTypeDao rTypeDao=new ReasonTypeDao();
	
	public ArrayList<ReasonType> getAllReasonType(){
		return rTypeDao.getAllReasonType();
	}
	
	public ReasonType getReasonType(int rt_typeId){
		return rTypeDao.getReasonType(rt_typeId);
	}
	
	public boolean addReasonType(ReasonType rType){
		return rTypeDao.addReasonType(rType)>0?true:false;
	}
	
	public boolean delReasonType(int rt_typeId){
		return rTypeDao.delReasonType(rt_typeId)>0?true:false;
	}
	
	public boolean modReasonType(ReasonType rType){
		return rTypeDao.modReasonType(rType)>0?true:false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
