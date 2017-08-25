package com.linghushaoxia.video.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 功能说明：下载文件工具类
 * 
 * @author:linghushaoxia
 * @time:2016年7月1日下午11:20:06
 * @version:1.0
 * 
 */
public class DownLoadUtil {
	/**
	 * 
	 * 功能说明:从指定url,下载文件到指定目录
	 * 
	 * @param url
	 *            远程url
	 * @param fileName
	 *            保存到磁盘上的文件名
	 * @param savePath
	 *            保存的路径
	 * @throws IOException
	 * @time:2016年7月1日下午11:21:33
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static void downLoadFromUrl(String url, String fileName,String savePath) throws IOException {
		System.out.println("开始下载:url=" + url);
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
		// 获取字节数组
		byte[] getData = readInputStream(inputStream);

		// 文件保存位置
		File saveDir = new File(savePath);
		// 已存在则删除,否则创建
		if (saveDir.exists()) {
			if (!saveDir.isDirectory()) {
				saveDir.delete();
			}
		} else {
			if (saveDir.getParentFile() != null&& !saveDir.getParentFile().exists()) {
				saveDir.getParentFile().mkdirs();
			}
			saveDir.mkdir();
		}
		File file = new File(saveDir + File.separator + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}
		System.out.println("下载文件成功:url=" + url);
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
	private static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
}
