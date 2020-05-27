package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DBInfo {
	
	private static DBInfo dbInfo;
	private String driverName;
	private String url;
	private String uname;
	private String pwd;
	
	private DBInfo(){
		
	}
	
	public static DBInfo getInstance() throws Exception{
		if(dbInfo == null){
			dbInfo = new DBInfo();
			dbInfo.init();
		}
		return dbInfo;
	}
	private void init() throws Exception{
		Properties prop = new Properties();
		String path = DBInfo.class.getResource("/").getPath()+"db.properties";
		prop.load(new FileInputStream(new File(path)));
		this.driverName = prop.getProperty("dbDriver");
		this.url = prop.getProperty("dbURL");
		this.uname = prop.getProperty("username");
		this.pwd = prop.getProperty("password");
	}

	public static DBInfo getDbInfo() {
		return dbInfo;
	}

	public static void setDbInfo(DBInfo dbInfo) {
		DBInfo.dbInfo = dbInfo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
