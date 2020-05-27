package com.icss.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.icss.entity.TMovie;

public class HotMovInfo {
	
	private static HotMovInfo hotMovInfo;
	private static List<TMovie> hotMov;
	private Document docment;
	
	private HotMovInfo(){
		this.hotMov = new ArrayList<TMovie>();
	}
	
	/**
	 * 单例获取该mov对象集合
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static HotMovInfo getInstance() throws ParserConfigurationException, SAXException, IOException{
		if(hotMovInfo == null){
			hotMovInfo = new HotMovInfo();
			hotMovInfo.init();
		}
		return hotMovInfo;
	}


	/**
	 * 从XML文件中获取即将上映的影片信息
	 * 获取上在该HOTMOVINFO单例类中村储，显示时获取该类循环显示即可
	 * TODO
	 * HotMovInfo.java
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * 
	 * 
	 */
	private void init() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String path = HotMovInfo.class.getResource("/").getPath() + "hotMov.xml";
		docment = db.parse(new File(path));
		NodeList nodes = docment.getElementsByTagName("movie");
		for(int j=0;j<nodes.getLength();j++){
			
			NodeList list = nodes.item(j).getChildNodes();
			TMovie movie = new TMovie();
			
			for(int i=0;i<list.getLength();i++){
				Node nd = list.item(i);
				if(nd.getNodeName().equals("movid")){
					movie.setMid(nd.getTextContent());
				}else if(nd.getNodeName().equals("mname")){
					movie.setMname(nd.getTextContent());
				}else if(nd.getNodeName().equals("length")){
					movie.setLength(nd.getTextContent());
				}else if(nd.getNodeName().equals("picurl")){
					movie.setPicurl(nd.getTextContent());
				}else if(nd.getNodeName().equals("type")){
					movie.setType(nd.getTextContent());
				}
				else if(nd.getNodeName().equals("director")){
					movie.setDirector(nd.getTextContent());
				}
				else if(nd.getNodeName().equals("actor")){
					movie.setActor(nd.getTextContent());
				}
				else if(nd.getNodeName().equals("score")){
					movie.setScore(Double.parseDouble(nd.getTextContent()));
				}else if(nd.getNodeName().equals("area")){
					movie.setArea(nd.getTextContent());
				}
			}
			this.hotMov.add(movie);
		}
		
	}
	

}
