package com.linghushaoxia.video.service;
/**功能说明：视频下载
 * @author:linghushaoxia
 * @time:2017年8月25日下午8:59:23
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public interface IDownLoaderService {
	/**
	 * 
	 * 功能说明:下载视频并保存
	 * @param url
	 * 远程地址
	 * @param dst
	 * 保存到的路径
	 * @return String
	 * @time:2017年8月25日下午9:02:45
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public String downLoadAndSave(String url,String dst);
	/**
	 * 
	 * 功能说明:下载视频返回字节数组
	 * @param url
	 * 远程地址
	 * @return byte[]
	 * @time:2017年8月25日下午9:02:52
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public byte[] downLoad(String url);
}

/**
* 现实就是实现理想的过程
*/