package com.linghushaoxia.video.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 功能说明：http客户端工具类
 * @author:linghushaoxia
 * @time:2017年8月24日下午1:22:00
 * @version:1.0
 *
 */
public class HttpClientUtil {
    //字符
    private static String CHAR_SET="UTF-8";
    /**
     * 
     * 功能说明:发送post请求
     * @param url
     * 远程url
     * @param paramMap
     * 参数对
     * @return String
     * @time:2017年8月25日下午12:39:51
     * @author:linghushaoxia
     * @exception:
     *
     */
    public static String httpPost(String url,Map<String, String> paramMap){
	//返回结果
	String result="";
	//默认客户端
	try(CloseableHttpClient httpClient = HttpClients.createDefault();) {
	    HttpPost httpPost = new HttpPost(url);
	    //参数列表
	    List<NameValuePair> nameValuePairs = new ArrayList<>();
	    if (paramMap!=null&&!paramMap.isEmpty()) {
		 for(String key:paramMap.keySet()){
		     nameValuePairs.add(new BasicNameValuePair(key,paramMap.get(key)));
		 }
		 HttpEntity  urlEncodedFormEntity =new UrlEncodedFormEntity(nameValuePairs,CHAR_SET);
		 httpPost.setEntity(urlEncodedFormEntity);
	    }
	    CloseableHttpResponse response= httpClient.execute(httpPost);
	    result =parseReponse(response);
	} catch (Exception e) {
   	    System.out.println("http post请求异常,url="+url+";param="+paramMap);
	    e.printStackTrace();
	}
	
	return result;
    }
    /**
     * 
     * 功能说明:发送get请求
     * @param url
     * 远程url不包含参数,例如http://a.b.c
     * @param paramMap
     * @return String
     * @time:2017年8月25日下午12:40:27
     * @author:linghushaoxia
     * @exception:
     *
     */
    public static String httpGet(String url,Map<String, String> paramMap){
   	//返回结果
   	String result="";
   	if (paramMap==null||paramMap.isEmpty()) {
	    return httpGet(url);
	}
   	//默认客户端
   	try(CloseableHttpClient httpClient = HttpClients.createDefault();) {
   	    //组装参数
   	    StringBuilder builder = new StringBuilder();
   	    builder.append(url).append("?");
   	    for(String key:paramMap.keySet()){
   		builder.append(key).append("=").append(paramMap.get(key));
   		builder.append("&");
	    }
   	    /**
   	     * 发起get请求
   	     */
   	    HttpGet httpGet = new HttpGet(builder.toString());
   	    CloseableHttpResponse response= httpClient.execute(httpGet);
   	    result = parseReponse(response);
   	} catch (Exception e) {
   	    System.out.println("http get请求异常,url="+url+";param="+paramMap);
   	    e.printStackTrace();
   	}
   	
   	return result;
       }
    /**
     * 
     * 功能说明:发送get请求,url包含参数
     * http://a.b.c.net?key=value
     * @param url
     * 远程url
     * @return String
     * 返回结果
     * @time:2017年8月25日下午12:40:35
     * @author:linghushaoxia
     * @exception:
     *
     */
    public static String httpGet(String url){
	//返回结果
	String result="";
	try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
	   CloseableHttpResponse response=  httpClient.execute(new HttpGet(url));
	   result = parseReponse(response);
	} catch (Exception e) {
   	    System.out.println("http get请求异常,url="+url);
	    e.printStackTrace();
	}
	return result;
    }
    /**
     * 
     * 功能说明:解析返回值
     * @param response
     * @return String
     * @time:2017年8月25日下午12:40:40
     * @author:linghushaoxia
     * @exception:
     *
     */
    private static String parseReponse(CloseableHttpResponse response){
	try {
	    HttpEntity httpEntity= response.getEntity();
	    if (httpEntity!=null) {
		return EntityUtils.toString(httpEntity, CHAR_SET);
	    }
	} catch (Exception e) {
	    System.out.println("解析response异常:"+e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }
}  
