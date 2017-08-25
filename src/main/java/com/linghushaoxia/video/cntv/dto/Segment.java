package com.linghushaoxia.video.cntv.dto;
/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月19日下午1:22:23
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class Segment {
	/**
	 * id
	 */
	private String guid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 开始时间
	 */
	private String start;
	/**
	 * 结束时间
	 */
	private String end;
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}

/**
* 现实就是实现理想的过程
*/