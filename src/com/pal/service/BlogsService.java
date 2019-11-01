package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.BlogsDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Blogs;
import com.pal.entity.Page;
import com.pal.entity.Users;

public class BlogsService {

	BlogsDao blogsDao = new BlogsDao();
	UsersDao usersDao = new UsersDao();

	public ArrayList<Blogs> getAllBlogs() {
		return blogsDao.getAllBlogs();
	}
	public ArrayList<Blogs> getAllBlogs(int myId) {
		return blogsDao.getMyBlogs(myId);
	}

	public ArrayList<Blogs> getHotBlogs() {
		return blogsDao.getHotBlogs();
	}

	public boolean getNewBlgos() {
		return blogsDao.getNewBlogs() > 0 ? true : false;
	}

	public Blogs getBlogs(int b_id) {
		return blogsDao.getBlogs(b_id);
	}

	public boolean addBlogs(Blogs blogs) {
		return blogsDao.addBlogs(blogs) > 0 ? true : false;
	}
	public boolean addBloglike(Blogs blogs) {
		return blogsDao.addBlogslike(blogs) > 0 ? true : false;
	}
	public boolean addBlogcomment(Blogs blogs) {
		return blogsDao.addBlogscomment(blogs) > 0 ? true : false;
	}

	public boolean delBlogs(int b_id) {
		return blogsDao.delBlogs(b_id) > 0 ? true : false;
	}

	public boolean modBlogs(Blogs blogs) {
		return blogsDao.modBlogs(blogs) > 0 ? true : false;
	}

	public Page<Blogs> getPage(int currPage, int pageSize) {

		Page<Blogs> page = new Page<Blogs>();

		// 设置属性
		page.setPageSize(pageSize);

		int total = blogsDao.getTotal();
		page.setTotal(total);

		// 设置当前页
		page.setCurrPage(currPage);

		
		int start =(page.getCurrPage()-1)*pageSize;
		
		List<Blogs> blogsList=blogsDao.getAllBlogs(start, pageSize);
		
		for(Blogs blogs:blogsList){
			blogs.setUsers(usersDao.getUser(blogs.getUserId()));
		}
		
		page.setList(blogsList);
		return page;
		
	}
	
	public Page<Blogs> getHotPage(int currPage, int pageSize) {

		Page<Blogs> page = new Page<Blogs>();

		// 设置属性
		page.setPageSize(pageSize);

		int total = blogsDao.getTotal();
		page.setTotal(total);

		// 设置当前页
		page.setCurrPage(currPage);

		
		int start =(page.getCurrPage()-1)*pageSize;
		
		List<Blogs> blogsList=blogsDao.getHotBlogs(start, pageSize);
		
		for(Blogs blogs:blogsList){
			blogs.setUsers(usersDao.getUser(blogs.getUserId()));
		}
		
		page.setList(blogsList);
		return page;
		
	}

	/**
	 * 我的个人动态
	 * 
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Page<Blogs> getPage(int myId, int currPage, int pageSize) {

		Page<Blogs> page = new Page<Blogs>();

		// 设置属性
		page.setPageSize(pageSize);

		int total = blogsDao.getMyTotal(myId);
		page.setTotal(total);

		// 设置当前页
		page.setCurrPage(currPage);

		int start = (page.getCurrPage() - 1) * page.getPageSize();

		List<Blogs> blogsList = blogsDao.getMyBlogs(myId,start, pageSize);

		page.setList(blogsList);

		return page;
	}
}
