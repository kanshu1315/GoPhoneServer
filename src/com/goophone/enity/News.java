package com.goophone.enity;

import java.io.Serializable;

public class News {

	
	// NEWS ���������
	private String  id; // id
	private String title; // title
	private String imgUrl; // ͼƬ
	private String updataTime; // ����ʱ��
	private String body; // ���ŵ�����

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(String updataTime) {
		this.updataTime = updataTime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
