package com.icss.biz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CharStreamIO {
	
	public void writeFile11(String src,String fdesc){
		File file2 = new File(fdesc);
		if(file2.exists()){
			file2.delete();
		}
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			out=new BufferedWriter(new  OutputStreamWriter(new FileOutputStream(file2),"utf-8"));
			//out = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			out.write(src);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}if(out != null){
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	public void writeFile(String src,String fdesc){
		File file2 = new File(fdesc);
		if(file2.exists()){
			file2.delete();
		}
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			
			out = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			out.write(src);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}if(out != null){
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 文件拷贝
	 * TODO
	 * CharStreamIO.java
	 * @param fsrc
	 * @param fdesc
	 */
	public void  copyFile(String fsrc,String fdesc){
		File file1 = new File(fsrc);
		File file2 = new File(fdesc);
		if(file2.exists()){
			file2.delete();
		}
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new FileReader(file1));
			out = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			String str = null;
			while((str = in.readLine()) != null){
				out.println(str);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}if(out != null){
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取文本型的文件
	 * TODO
	 * CharStreamIO.java
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String readFile(String path) {
		File file = new File(path);
		StringBuilder build = new StringBuilder();
		if(file.exists()){
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(file));
				String strRet = null;
				while((strRet =in.readLine()) != null){
					build.append(strRet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
					try {
						if(in != null){
						in.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}else{
			System.out.println(path + "此文件不存在!");
		}
		System.out.println(build.toString());
		return build.toString();
	}
	
	public String readFile11(String path) {
		File file = new File(path);
		StringBuilder build = new StringBuilder();
		if(file.exists()){
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
				String strRet = null;
				while((strRet =in.readLine()) != null){
					build.append(strRet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
					try {
						if(in != null){
						in.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}else{
			System.out.println(path + "此文件不存在!");
		}
		System.out.println(build.toString());
		return build.toString();
	}
}
