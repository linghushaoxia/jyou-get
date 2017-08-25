package com.linghushaoxia.video.dto;

import java.util.Map;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月25日下午8:45:48
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class DownLoadInfo {
	/**
	 * 远程url
	 */
	private String url;
	/**
	 * 目的路径
	 */
	private String dst;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 是否合并
	 */
	private boolean merger;
	/**
	 * 只显示视频信息
	 */
	private boolean onlyInfo;
	/**
	 * 视频ID
	 */
	private String videoId;
	private Map<String, String> extMap;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public boolean isMerger() {
		return merger;
	}
	public void setMerger(boolean merger) {
		this.merger = merger;
	}
	public boolean isOnlyInfo() {
		return onlyInfo;
	}
	public void setOnlyInfo(boolean onlyInfo) {
		this.onlyInfo = onlyInfo;
	}
	public Map<String, String> getExtMap() {
		return extMap;
	}
	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

/**
* 现实就是实现理想的过程
*/