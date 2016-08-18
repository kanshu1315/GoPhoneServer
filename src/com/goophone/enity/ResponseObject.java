package com.goophone.enity;



public class ResponseObject {
	
	private String msg;
	private int state = 1;// 0:失败 1：成功
	private Object datas; // 数据源
	private Object datas2;
	private int page;
	private int size;
	private int count;
	public Object getDatas2() {
		return datas2;
	}
	public void setDatas2(Object datas2) {
		this.datas2 = datas2;
	}
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsq(String msq) {
		this.msg = msq;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getDatas() {
		return datas;
	}
	public void setDatas(Object datas) {
		this.datas = datas;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	public ResponseObject(int state,String msg) {
		this.state = state;
		this.msg= msg;
	}
	public ResponseObject(int state,Object datas) {
		this.state = state;
		this.datas= datas;
	}
	public ResponseObject(int state,Object datas,Object datas2) {
		this.state = state;
		this.datas= datas;
		this.datas2=datas2;
		
	}
	public ResponseObject(int state,String msg,Object datas) {
		this.state = state;
		this.msg= msg;
		this.datas=datas;
	}

}
