package com.linghushaoxia.video.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.linghushaoxia.video.config.ConfigConst;

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
    public static String bash(String bash) {
	// 返回结果
	String result = "";
	// 命令
	String[] cmds = new String[3];
	// 判断系统类型
	if (isWindows()) {
	    cmds[0] = "cmd";
	    cmds[1] = "/c";
	    cmds[2] = bash;
	} else {
	    cmds[0] = "/bin/sh";
	    cmds[1] = "-c";
	    cmds[2] = bash;
	}
	/**
	 * 执行命令并获取结果
	 */
	Process pro = null;
	BufferedInputStream resultInputStream = null;
	try {
	    pro = Runtime.getRuntime().exec(cmds);
	    int resultPro = pro.waitFor();
	    /**
	     * 执行失败,则封装详细结果
	     */
	    if (resultPro != 0) {
		resultInputStream = new BufferedInputStream(pro.getErrorStream());
		//编码取sun.jun.encoding
		BufferedReader read = new BufferedReader(new InputStreamReader(resultInputStream,System.getProperty("sun.jnu.encoding")));
		String line = null;
		StringBuilder builder = new StringBuilder();
		while ((line = read.readLine()) != null) {
		    builder.append(line);
		}
		result = builder.toString();
	    } else {
		resultInputStream = new BufferedInputStream(pro.getInputStream());
		result = ConfigConst.EXE_SUCCESS;
	    }
	} catch (Exception e) {
	    System.out.println("执行命令异常,bash="+bash);
	    e.printStackTrace();
	} finally {
	    if (resultInputStream != null) {
		try {
		    resultInputStream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return result;
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
		if(System.getProperty("os.name").contains("dows")
			||System.getProperty("os.name").contains("Win")
			||System.getProperty("os.name").contains("win")){
			return true;
			
		}
		
		return false;
	}
}

