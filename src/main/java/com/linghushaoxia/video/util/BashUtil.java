package com.linghushaoxia.video.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**功能说明：java执行系统命令
 * bat或者bash脚本命令
 * @author:linghushaoxia
 * @time:2016年7月3日下午3:32:50
 * @version:1.0
 * 
 */
public class BashUtil {
	/**
	 * 
	 * 功能说明:执行命令,返回结果
	 * @param bash
	 * @return String
	 * @time:2016年7月2日上午12:32:25
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static String bashRun(String bash){
		System.out.println("bash="+bash);
		try {
			//返回结果
			StringBuffer result =new StringBuffer();
			//如果出现问题，看类开头的注释 
		    BufferedReader read = new BufferedReader(new InputStreamReader(exeBash(bash)));  
		    String line = null;  
		    while((line = read.readLine())!=null){  
		        System.out.println(line); 
		        result.append(line);
		    }  
		    return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ""; 
	}
	/**
	 * 
	 * 功能说明:执行bash命令，返回结果(list)
	 * @param bash
	 * @return List<String>
	 * @time:2017年1月21日下午6:39:06
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static List<String> bashRunList(String bash){
		//返回结果
		List<String> results = new ArrayList<String>();
		try {
		    BufferedReader read = new BufferedReader(new InputStreamReader(exeBash(bash)));  
		    String line = null;  
		    while((line = read.readLine())!=null){  
		        System.out.println(line); 
		        results.add(line);
		    }  
		    return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results; 
	}
	/**
	 * 
	 * 功能说明:执行bash命令,返回执行结果的输入流
	 * @param bash
	 * @return InputStream
	 * @time:2017年1月21日下午6:37:17
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private static InputStream exeBash(String bash){
		//命令
		String[] cmds=new String[3];
		//判断系统类型
		if(isWindows()){
			cmds[0]="cmd";
			cmds[1]="/c";
			cmds[2]=bash;
		}else {
			cmds[0]="/bin/sh";
			cmds[1]="-c";
			cmds[2]=bash;
		}
		//如果出现问题，看类开头的注释
	    Process pro = null;
		try {
			pro = Runtime.getRuntime().exec(cmds);
			int result= pro.waitFor();
			if (result!=0) {
				System.out.println("执行失败");
			}else {
				System.out.println("执行成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
	    InputStream in = pro.getInputStream(); 
		return in;
	}
	/**
	 * 
	 * 功能说明:判断当前系统是否是windows系统
	 * @return boolean
	 * @time:2016年7月5日下午3:28:55
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static boolean isWindows(){
		if(System.getProperty("os.name").contains("dows")||
				System.getProperty("os.name").contains("Win")||
				System.getProperty("os.name").contains("win")){
			return true;
			
		}
		
		return false;
	}
}

