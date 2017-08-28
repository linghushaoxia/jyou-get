package com.linghushaoxia.video.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.linghushaoxia.video.service.IDownLoaderService;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月25日下午9:02:03
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class DownLoaderService implements IDownLoaderService{

	@Override
	public void downLoadAndSave(String url, String dst) {
		downLoadFromUrl(url,dst);
	}

	@Override
	public byte[] downLoad(String url) {
		InputStream inputStream = getInputStream(url);
		return readInputStream(inputStream);
	}
	/**
	 * 
	 * 功能说明:从指定url,下载文件到指定目录
	 * @param url
	 * 远程url
	 * @param savePath
	 * 保存的路径
	 * @throws IOException
	 * @time:2016年7月1日下午11:21:33
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private  void downLoadFromUrl(String url,String savePath) {
		System.out.println("开始下载:url=" + url);
		// 获取字节数组
		byte[] fileBytes = readInputStream(getInputStream(url));
		if (fileBytes==null) {
		    System.out.println("文件字节为空url="+url);
		    return ;
		}
		// 文件保存位置
		File saveDir = new File(savePath);
		/**
		 * 已存在则删除,否则创建
		 */
		if (saveDir.exists()) {
			if (!saveDir.isDirectory()) {
				saveDir.delete();
			}
		} else {
			if (saveDir.getParentFile() != null&& !saveDir.getParentFile().exists()) {
				saveDir.getParentFile().mkdirs();
			}
			//新建文件
			try {
				saveDir.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream  fileOutputStream = null;
		try {
			fileOutputStream= new FileOutputStream(saveDir);
			fileOutputStream.write(fileBytes);
		} catch (Exception e) {
		}finally{
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("下载文件成功:url=" + url);
	}
	private InputStream getInputStream(String url){
		try {
			URL httpURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			// 设置超时间为30秒
			conn.setConnectTimeout(3000 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			// conn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (compatible; MSIE 5.0;Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0)");
			// 得到输入流
			InputStream inputStream = conn.getInputStream();
			return inputStream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 
	 * 功能说明:读取输入流
	 * 
	 * @param inputStream
	 * @return byte[]
	 * @throws IOException
	 * @time:2016年7月1日下午11:21:45
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private byte[] readInputStream(InputStream inputStream){
	    	if (inputStream==null) {
	    	    System.out.println("输入流为空");
		    return null;
		}
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

}

/**
* 现实就是实现理想的过程
*/