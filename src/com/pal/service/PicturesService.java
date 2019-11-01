package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.PicturesDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Page;
import com.pal.entity.Pictures;

public class PicturesService {

	PicturesDao picturesDao = new PicturesDao();
	UsersDao usersDao = new UsersDao();

	public ArrayList<Pictures> getAllPictures() {
		return picturesDao.getAllPictures();
	}
	public ArrayList<Pictures> getMyPictures(int userId) {
		return picturesDao.getMyPictures(userId);
	}

	public Pictures getPictures(int pictureId) {
		return picturesDao.getPictures(pictureId);
	}

	public boolean addPictures(Pictures pictures) {
		return picturesDao.addPictures(pictures) > 0 ? true : false;
	}
	
	
	
	public boolean delPictures(int pictureId) {
		return picturesDao.delPictures(pictureId) > 0 ? true : false;
	}

	public boolean modPictures(Pictures pictures) {
		return picturesDao.modPictures(pictures) > 0 ? true : false;
	}
/**
 * 所有分页查询
 * @param currPage
 * @param pageSize
 * @return
 */
	public Page<Pictures> getPage(int currPage, int pageSize) {

		Page<Pictures> page = new Page<Pictures>();

		// 设置属性
		page.setPageSize(pageSize);

		int total = picturesDao.getTotal();
		page.setTotal(total);

		// 设置当前页
		page.setCurrPage(currPage);

		int start = (page.getCurrPage() - 1) * page.getPageSize();

		List<Pictures> picList = picturesDao.getAllPictures(start, pageSize);

		// 处理外键关系
		for (Pictures pictures : picList) {

			pictures.setUsers(usersDao.getUser(pictures.getUserId()));
		}

		page.setList(picList);

		return page;
	}
	
	/**
	 * 个人相册分页查询
	 * @param userId
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Page<Pictures> getPage(int userId , int currPage , int pageSize){

		Page<Pictures> page = new Page<Pictures>();

		// 设置属性
		page.setPageSize(pageSize);

		int total = picturesDao.getTotal(userId);
		page.setTotal(total);

		// 设置当前页
		page.setCurrPage(currPage);

		int start = (page.getCurrPage() - 1) * page.getPageSize();

		List<Pictures> picList = picturesDao.getAllPictures(userId, start, pageSize);

		// 处理外键关系
		for (Pictures pictures : picList) {

			pictures.setUsers(usersDao.getUser(pictures.getUserId()));
		}

		page.setList(picList);

		return page;
	}
}
