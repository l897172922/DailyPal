package com.pal.test;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.pal.dao.BlogsDao;
import com.pal.dao.FriendDao;
import com.pal.dao.UsersDao;
import com.pal.entity.ChatMessages;
import com.pal.entity.Friend;
import com.pal.entity.FriendRequest;
import com.pal.entity.Messages;
import com.pal.entity.Page;
import com.pal.entity.Users;
import com.pal.service.BlogsService;
import com.pal.service.ChatMessagesService;
import com.pal.service.FriendRequestService;
import com.pal.service.FriendService;
import com.pal.service.MessagesService;
import com.pal.service.PicturesService;
import com.pal.service.UsersService;

public class UsersTest {
	UsersDao userDao = new UsersDao();
	UsersService userSer = new UsersService();
	Users user = new Users();
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void find(){
		user = userSer.getUser("lisi");
		System.out.println(user);
	}
	
	@Test
	public void DaoUser(){
		user = userDao.getUser("lisi");
		System.out.println(user);
	}
	
	@Test
	public void add(){
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		user = new Users();
		user.setU_name("11880");
		user.setU_pwd("220");
		
//		默认头像
		user.setHeadPic("dailypal.png");
//		昵称默认账号
		user.setNickName("11880");
//		性别默认未知
		user.setSex("未知");
//		年龄默认0
		user.setAge(0);
//		注册时间
		user.setRegisterTime(sqlDate);
		System.out.println(user);
		userSer.addUser(user);

	}
	
@Test
public void time(){
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String date = String.valueOf(df.format(cal.getTime()));
	System.out.println(date);
}



BlogsService blogSer = new BlogsService();
BlogsDao blogDao = new BlogsDao();


MessagesService msgSer = new MessagesService();

@Test
public void hot(){
	Page<Messages> page = new Page<Messages>();
	page = msgSer.getPage(1, 5);
	System.out.println(page.getList());
}

@Test
public void hots(){

	Page<Messages> page = new Page<Messages>();
	page = msgSer.getPage(1,1, 5);
	System.out.println(page.getList());
}

Users u = new Users();

@Test
public void dynamic(){
	Page<Messages> page = msgSer.getPage( 1 , 1, 5);
	/*l.set(0,1);
	l.set(1,1);
	l.set(2,1);
	l.set(3,1);*/
	
	ArrayList arr = new ArrayList();
	List<Messages> list = page.getList();
	System.out.println(list);
	for(Messages mess:list){
		u = mess.getFromUsers();	
		ArrayList link = new ArrayList();
		link.add(mess);
		link.add(u.getNickName());
		
		arr.add(link);
		System.out.println(arr);
		
	}
	System.out.println(arr.get(0));
}


@Test
public void pic(){
	System.out.println(userSer.modUser("lisiyi", 1));
}


PicturesService picSer = new PicturesService();
@Test
public void picture(){
	Page page = picSer.getPage(1, 1, 10);
	System.out.println(page);
}

FriendService friSer = new FriendService();
FriendDao friDao = new FriendDao();

@Test
public void id(){
	int i = friDao.getTotal();
	System.out.println("一共有个人呢"+i);
	Page<Friend> page = friSer.getPage(1, 1, 10);
	System.out.println(page);
	ArrayList<Friend> list = friDao.getAllFriend(1, 1, 10);
	System.out.println(list);
	
}

@Test
public void uuid(){
	System.out.println(UUID.randomUUID().toString().replace("-",""));
}


@Test
public void center(){
	ChatMessagesService cms = new ChatMessagesService();
	ArrayList<ChatMessages> cmList = new ArrayList<ChatMessages>();
	cmList = cms.getAllChatMessagesW(1,2);
	System.out.println(cmList);
}

ChatMessagesService cmSer = new ChatMessagesService();

@Test
public void asdf(){
	
	ArrayList<ChatMessages> cmList = new ArrayList<ChatMessages>();
	int friId = 1;
	int myId = 2;
	cmList = cmSer.getAllChatMessagesWN(myId, friId);
	System.out.println(cmList);
	
}









}
