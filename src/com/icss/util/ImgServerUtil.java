package com.icss.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class ImgServerUtil {
	private static ImgServerUtil isUtil;
	private String ip;                //����IP
	private String username;          //�����˺�
	private String password;          //��������
	private String filename;          //�����ļ���
	private String accesspath;        //���������http·��
	
	private ImgServerUtil(){
		
	}
	
	public static ImgServerUtil newInstance() throws Exception{
		if(isUtil == null){
			isUtil = new ImgServerUtil();
			isUtil.getProperties();
		}
		
		return isUtil;
	}
	
	private void getProperties() throws Exception{
		Properties pro = new Properties();
		String path = ImgServerUtil.class.getResource("/").toURI().getPath();
		BufferedReader in
		   = new BufferedReader(new FileReader(path + "imgserver.properties"));
		pro.load(in);
		this.ip = pro.getProperty("server.ip");
		this.username = pro.getProperty("server.username").trim();
		this.password = pro.getProperty("server.password").trim();
		this.filename = pro.getProperty("server.filename").trim();
		this.accesspath = pro.getProperty("server.accesspath").trim();
		in.close();
	}

	public String getIp() {
		return ip;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFilename() {
		return filename;
	}

	public String getAccesspath() {
		return accesspath;
	}
}
