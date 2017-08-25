
package com.linghushaoxia.video.manager.impl.mgtv.dto;
import java.util.List;
/**
 * 功能说明：主要数据信息
 * @author:linghushaoxia
 * @time:2017年7月2日下午8:52:30
 * @version:1.0
 *
 */
public class Data {
	/**
	 * 帧信息
	 */
    private Frame frame;
    /**
     * 具体节目信息
     */
    private Info info;
    /**
     * 暂未明确,未用到
     */
    private Points points;
    /**
     * 分享
     */
    private Share share;
    /**
     * 数据流
     */
    private List<Stream> stream;
    /**
     * 域
     */
    private List<String> stream_domain;
    /**
     * 数据流
     */
    private StreamQuality stream_quality;
    /**
     * 用户信息
     */
    private User user;
    public void setFrame(Frame frame) {
         this.frame = frame;
     }
     public Frame getFrame() {
         return frame;
     }

    public void setInfo(Info info) {
         this.info = info;
     }
     public Info getInfo() {
         return info;
     }

    public void setPoints(Points points) {
         this.points = points;
     }
     public Points getPoints() {
         return points;
     }

    public void setShare(Share share) {
         this.share = share;
     }
     public Share getShare() {
         return share;
     }

    public void setStream(List<Stream> stream) {
         this.stream = stream;
     }
     public List<Stream> getStream() {
         return stream;
     }

    public void setStream_domain(List<String> stream_domain) {
         this.stream_domain = stream_domain;
     }
     public List<String> getStream_domain() {
         return stream_domain;
     }

    public void setStream_quality(StreamQuality stream_quality) {
         this.stream_quality = stream_quality;
     }
     public StreamQuality getStream_quality() {
         return stream_quality;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

}