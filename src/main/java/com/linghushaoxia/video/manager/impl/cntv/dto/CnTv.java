package com.linghushaoxia.video.manager.impl.cntv.dto;

import java.util.List;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月19日下午1:19:14
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class CnTv {
	/**
	 * 确认信息
	 */
	private String ack;
	/**
	 * 标签
	 */
	private String tag;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 频道
	 */
	private String play_channel;
	/**
	 * 产品
	 */
	private String produce;
	/**
	 * 编辑者
	 */
	private String editer_name;
	/**
	 * 产品ID
	 */
	private String produce_id;
	/**
	 * 专栏
	 */
	private String column;
	/**
	 * 
	 */
	private String f_pgmtime;
	/**
	 * 片段信息
	 */
	private List<Segment> segments;
	/**
	 * cdn信息
	 */
	private CdnInfo cdn_info;
	/**
	 * 视频信息
	 */
	private Video video;
	/**
	 * hls，cdn信息
	 */
	private CdnInfo hls_cdn_info;
	/**
	 * 
	 */
	private String hls_url;
	/**
	 * 错误码
	 */
	private String asp_error_code;
	/**
	 * 客户端ID
	 */
	private String client_sid;
//	private String public;
	/**
	 * 版权标识
	 */
	private String is_invalid_copyright;
	/**
	 * 保护标识
	 */
	private String is_protected;
	/**
	 * 热门标识
	 */
	private String is_fn_hot;
	/**
	 * 
	 */
	private String is_p2p_use;
	/**
	 * 默认章节,清晰度
	 */
	private String default_stream;
	/**
	 * 用户位置
	 */
	private Location lc;
	/**
	 * 支持ipad标识
	 */
	private String is_ipad_support;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 
	 */
	private String embed;
	/**
	 * 
	 */
	private String is_fn_multi_stream;
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlay_channel() {
		return play_channel;
	}
	public void setPlay_channel(String play_channel) {
		this.play_channel = play_channel;
	}
	public String getProduce() {
		return produce;
	}
	public void setProduce(String produce) {
		this.produce = produce;
	}
	public String getEditer_name() {
		return editer_name;
	}
	public void setEditer_name(String editer_name) {
		this.editer_name = editer_name;
	}
	public String getProduce_id() {
		return produce_id;
	}
	public void setProduce_id(String produce_id) {
		this.produce_id = produce_id;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getF_pgmtime() {
		return f_pgmtime;
	}
	public void setF_pgmtime(String f_pgmtime) {
		this.f_pgmtime = f_pgmtime;
	}
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	public CdnInfo getCdn_info() {
		return cdn_info;
	}
	public void setCdn_info(CdnInfo cdn_info) {
		this.cdn_info = cdn_info;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public CdnInfo getHls_cdn_info() {
		return hls_cdn_info;
	}
	public void setHls_cdn_info(CdnInfo hls_cdn_info) {
		this.hls_cdn_info = hls_cdn_info;
	}
	public String getHls_url() {
		return hls_url;
	}
	public void setHls_url(String hls_url) {
		this.hls_url = hls_url;
	}
	public String getAsp_error_code() {
		return asp_error_code;
	}
	public void setAsp_error_code(String asp_error_code) {
		this.asp_error_code = asp_error_code;
	}
	public String getClient_sid() {
		return client_sid;
	}
	public void setClient_sid(String client_sid) {
		this.client_sid = client_sid;
	}
	public String getIs_invalid_copyright() {
		return is_invalid_copyright;
	}
	public void setIs_invalid_copyright(String is_invalid_copyright) {
		this.is_invalid_copyright = is_invalid_copyright;
	}
	public String getIs_protected() {
		return is_protected;
	}
	public void setIs_protected(String is_protected) {
		this.is_protected = is_protected;
	}
	public String getIs_fn_hot() {
		return is_fn_hot;
	}
	public void setIs_fn_hot(String is_fn_hot) {
		this.is_fn_hot = is_fn_hot;
	}
	public String getIs_p2p_use() {
		return is_p2p_use;
	}
	public void setIs_p2p_use(String is_p2p_use) {
		this.is_p2p_use = is_p2p_use;
	}
	public String getDefault_stream() {
		return default_stream;
	}
	public void setDefault_stream(String default_stream) {
		this.default_stream = default_stream;
	}
	public Location getLc() {
		return lc;
	}
	public void setLc(Location lc) {
		this.lc = lc;
	}
	public String getIs_ipad_support() {
		return is_ipad_support;
	}
	public void setIs_ipad_support(String is_ipad_support) {
		this.is_ipad_support = is_ipad_support;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEmbed() {
		return embed;
	}
	public void setEmbed(String embed) {
		this.embed = embed;
	}
	public String getIs_fn_multi_stream() {
		return is_fn_multi_stream;
	}
	public void setIs_fn_multi_stream(String is_fn_multi_stream) {
		this.is_fn_multi_stream = is_fn_multi_stream;
	}
}

/**
* 现实就是实现理想的过程
*/