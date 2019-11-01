package com.pal.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 页面类Page
 * @author Administrator
 *
 * @param <T>
 */
public class Page<T> {
	private int pageSize;//页面容量
	private int total;//总笔数
	private int privPage;//上一页
	private int nextPage;//下一页
	private int currPage;//当前页
	private int allPage;//总页数
	
	private List<T> list=new ArrayList<T>();
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		
		if(this.total%this.pageSize==0){
			this.allPage=this.total/this.pageSize;
		}else{
			this.allPage=this.total/this.pageSize+1;
		}
	}
	public int getPrivPage() {
		return privPage;
	}
	/*public void setPrivPage(int privPage) {
		this.privPage = privPage;
	}*/
	public int getNextPage() {
		return nextPage;
	}
	/*public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}*/
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		if(this.allPage==1){//只有一页
			this.privPage=1;
			this.currPage=1;
			this.nextPage=1;
		}else if(currPage==1){//当前页是首页
			this.privPage=1;
			this.currPage=1;
			this.nextPage=2;
		}else if(currPage==this.allPage){//当前页是尾页
			this.privPage=this.allPage-1;
			this.currPage=this.allPage;
			this.nextPage=this.allPage;					
		}else{
			this.privPage=currPage-1;
			this.currPage=currPage;
			this.nextPage=currPage+1;
		}
	}
	public int getAllPage() {
		return allPage;
	}
	/*public void setAllPage(int allPage) {
		this.allPage = allPage;
	}*/
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Page() {
		super();
	}
	public Page(int pageSize, int total, int privPage, int nextPage,
			int currPage, int allPage, List<T> list) {
		super();
		this.pageSize = pageSize;
		this.total = total;
		this.privPage = privPage;
		this.nextPage = nextPage;
		this.currPage = currPage;
		this.allPage = allPage;
		this.list = list;
	}
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", total=" + total
				+ ", privPage=" + privPage + ", nextPage=" + nextPage
				+ ", currPage=" + currPage + ", allPage=" + allPage + ", list="
				+ list + "]";
	}
	
	
}
