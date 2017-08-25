package com.linghushaoxia.video.util;

import java.util.HashMap;
import java.util.Map;

/**功能说明：url工具类
 * @author:linghushaoxia
 * @time:2017年7月2日下午9:39:24
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class UrlUtil {
	/**
	 * 模式,协议
	 */
	public static final String SCHEME = "scheme";
	/**
	 * 网络位置
	 */
	public static final String NET_LOC = "netloc";
	/**
	 * 路径
	 */
	public static final String PATH = "path";
	/**
	 * 查询参数
	 */
	public static final String QUERY="query";
	/**
	 * 
	 * 功能说明:url分割器
	 * 模拟python下的url分割器
	 * @param url
	 * @return Map<String,String>
	 * @time:2017年7月2日下午9:38:21
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static Map<String, String> urlSplit(String url){
		Map<String, String> urlMap = new HashMap<String, String>();
		String scheme=url.substring(0,url.indexOf(":"));
		urlMap.put(SCHEME, scheme);
		
		url = url.substring((scheme+":"+"//").length());
		String netloc=url.substring(0,url.indexOf("/"));
		urlMap.put(NET_LOC,netloc);
		
		String path = url.substring(url.indexOf("/"),url.indexOf("?")<0?url.length():url.indexOf("?"));
		urlMap.put(PATH, path);
		
		String query =url.substring(url.indexOf("?")<0?url.lastIndexOf("/"):url.indexOf("?"));
		urlMap.put(QUERY, query);
		return urlMap;
	}
	public static void main(String[] args) {
		System.out.println(urlSplit("http://tv.cctv.com/2014/08/15/VIDE1408113188467747.shtml"));
	}
}

/**
* 现实就是实现理想的过程
*/