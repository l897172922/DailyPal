package com.pal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pal.dao.BlogsDao;
import com.pal.dao.FriendDao;
import com.pal.entity.Blogs;
import com.pal.entity.Friend;
import com.pal.entity.Users;
import com.pal.service.BlogsService;
import com.pal.service.MessagesService;

public class BlogTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void gethot() {
		BlogsService bs = new BlogsService();
		System.out.println(bs.getHotBlogs());
	}

	@Test
	public void getmessages() {
		MessagesService ms = new MessagesService();
		System.out.println(ms.getMessagesByuserId(2));
	}

	@Test
	public void FOREIGN() {
		BlogsDao bd = new BlogsDao();
		FriendDao fd = new FriendDao();
		ArrayList<Friend> flist = fd.getAllFriend(1);
		ArrayList<Integer> list = new ArrayList();
		for (Friend l : flist) {
			if (l.geTotUsers().getU_id() == 1) {
				list.add(l.getFromuserId());
			} else {
				list.add(l.getTouserId());
			}
		}
		ArrayList<Blogs> blist = new ArrayList<Blogs>();
		for (int i = 0; i < list.size(); i++) {

			blist.addAll(bd.getMyBlogs((int) list.get(i)));
		}
		System.out.println(flist);
		System.out.println(blist);
		System.out.println(list);
	}
}
