package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.FriendDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Friend;
import com.pal.entity.Page;

public class FriendService {

	FriendDao friendDao = new FriendDao();
	UsersDao usersDao = new UsersDao();

	public ArrayList<Friend> getAllFriend() {
		return friendDao.getAllFriend();
	}
	public ArrayList<Friend> getAllFriend(int userId) {
		return friendDao.getAllFriend(userId);
	}

	public Friend getFriend(int f_id) {
		return friendDao.getFriend(f_id);
	}

	public boolean addFriend(Friend friend) {
		return friendDao.addFriend(friend) > 0 ? true : false;
	}

	public boolean delFriend(int fid,int tid) {
		return friendDao.delFriend(fid,tid) > 0 ? true : false;
	}

	public boolean modFriend(Friend friend) {
		return friendDao.modFriend(friend) > 0 ? true : false;
	}

	public Page<Friend> getPage(int currPage, int pageSize) {

		Page<Friend> page = new Page<Friend>();

		page.setPageSize(pageSize);
		int total = friendDao.getTotal();
		page.setTotal(total);

		page.setCurrPage(currPage);

		int start = (page.getCurrPage() - 1) * page.getPageSize();

		List<Friend> friendList = friendDao.getAllFriend(start, pageSize);

		for (Friend friend : friendList) {
			friend.setFromUsers(usersDao.getUser(friend.getFromuserId()));
			friend.setToUsers(usersDao.getUser(friend.getTouserId()));
		}

		page.setList(friendList);

		return page;
	}
	
	/**
	 * 个人
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Page<Friend> getPage(int myId,int currPage, int pageSize) {

		Page<Friend> page = new Page<Friend>();

		page.setPageSize(pageSize);
		int total = friendDao.getTotal(myId);
		page.setTotal(total);

		page.setCurrPage(currPage);

		int start = (page.getCurrPage() - 1) * page.getPageSize();

		List<Friend> friendList = friendDao.getAllFriend(myId, start, pageSize);

		for (Friend friend : friendList) {
			friend.setFromUsers(usersDao.getUser(friend.getFromuserId()));
			friend.setToUsers(usersDao.getUser(friend.getTouserId()));
		}

		page.setList(friendList);

		return page;
	}
}
