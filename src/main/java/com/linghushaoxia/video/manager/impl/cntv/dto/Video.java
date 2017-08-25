package com.linghushaoxia.video.manager.impl.cntv.dto;

import java.util.List;
import java.util.Map;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月19日下午1:25:34
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class Video {
	/**
	 * 总时长
	 */
	private String totalLength;
	/**
	 * 章节信息
	 * key是清晰度标识
	 * value,具体章节地址
	 */
	private Map<String, List<Chapter>> map;
	/**
	 * 有效的章节数
	 */
	private String validChapterNum;
	/**
	 * 地址
	 */
	private String url;
	public String getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}
	public Map<String, List<Chapter>> getMap() {
		return map;
	}
	public void setMap(Map<String, List<Chapter>> map) {
		this.map = map;
	}
	public String getValidChapterNum() {
		return validChapterNum;
	}
	public void setValidChapterNum(String validChapterNum) {
		this.validChapterNum = validChapterNum;
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