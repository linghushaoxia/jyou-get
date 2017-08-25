package com.linghushaoxia.video.cntv.dto;
/**功能说明：章节信息
 * @author:linghushaoxia
 * @time:2017年8月19日下午1:27:24
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class Chapter {
	/**
	 * 持续时间
	 */
	private String duration;
	/**
	 * 概览图片地址
	 */
	private String image;
	/**
	 * 当前章节地址
	 */
	private String url;
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

/**
* 现实就是实现理想的过程
*/