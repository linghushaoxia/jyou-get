package com.linghushaoxia.video.manager.impl.cntv.dto;
/**功能说明：位置信息
 * @author:linghushaoxia
 * @time:2017年8月19日下午3:27:11
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class Location {
	/**
	 * isp提供商代码
	 */
	private String isp_code;
	/**
	 * 城市代码
	 */
	private String city_code;
	/**
	 * 省代码
	 */
	private String provice_code;
	/**
	 * 国家代码
	 */
	private String country_code;
	/**
	 * ip
	 */
	private String ip;
	public String getIsp_code() {
		return isp_code;
	}
	public void setIsp_code(String isp_code) {
		this.isp_code = isp_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getProvice_code() {
		return provice_code;
	}
	public void setProvice_code(String provice_code) {
		this.provice_code = provice_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}

/**
* 现实就是实现理想的过程
*/