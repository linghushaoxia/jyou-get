package com.linghushaoxia.video.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**功能说明：文件处理工具类
 * @author:linghushaoxia
 * @time:2016年1月3日下午12:51:31
 * @version:1.0
 * 
 */
public class FileUtil {
	
	/**
	 * 
	 * 功能说明:对字符串进行排序
	 * @param textLit
	 * @return List<String>
	 * @time:2017年3月18日下午6:02:26
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static List<String> sortList(List<String> textLit){
		if (textLit==null||textLit.size()==0) {
			return textLit;
		}
		/**
		 * 排序
		 */
		List<OrderWrapper> orderWrappers = new ArrayList<OrderWrapper>();
		for (String text : textLit) {
			OrderWrapper orderWrapper =new OrderWrapper(text);
			orderWrappers.add(orderWrapper);
		}
		Collections.sort(orderWrappers);
		/**
		 * 重新赋值
		 */
		textLit.clear();
		for (OrderWrapper orderWrapper : orderWrappers) {
			textLit.add(orderWrapper.getName());
		}
		return textLit;
	}
	/**
	 * 
	 * 功能说明:获取跟路径下，指定后缀的所有文件
	 * @param path
	 * 根路径
	 * @param pathList
	 * 返回的结果
	 * @param postFix
	 * 文件后缀
	 * @return List<String>
	 * @time:2016年12月1日下午10:13:19
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static List<String> getFilePathList(String path,List<String> pathList,String postFix){
		File[] files = new File(path).listFiles();
		for(File file:files){
			//非隐藏、文件名不是.开头的系统文件
			if(!file.isHidden()&&!file.getName().startsWith(".")){
				if(file.isFile()){
					if(postFix!=null&&!postFix.equals("")){
						if(file.getName().toLowerCase().endsWith(postFix.toLowerCase())){
							pathList.add(file.getAbsolutePath());
						}
					}else {
						pathList.add(file.getAbsolutePath());
					}
				}
				//目录递归
				if(file.isDirectory()){
					getFilePathList(file.getAbsolutePath(), pathList,postFix);
				}
			}
			
		}
		return pathList;
	}
	/**
	 * 
	 * 功能说明:删除文件
	 * @param path
	 * @time:2017年8月25日下午11:47:18
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static void delete(String path){
		try {
			File file = new File(path);
			if (file.exists()&&file.isFile()) {
				if(file.delete()){
					System.out.println("删除文件成功:"+path);
				}
			}
		} catch (Exception e) {
			System.out.println("删除文件失败:"+path);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 功能说明:删除空目录
	 * @param path
	 * @time:2017年8月26日上午12:29:25
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static void deleteEmptyDir(String path){
		try {
			File file = new File(path);
			if (file.isDirectory()) {
				//不包含文件
				File[] files = file.listFiles();
				if (files==null||files.length==0) {
					if(file.delete()){
						System.out.println("删除文件成功:"+path);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

