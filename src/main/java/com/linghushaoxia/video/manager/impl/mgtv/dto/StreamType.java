
package com.linghushaoxia.video.manager.impl.mgtv.dto;

/**
 * 功能说明：流类型
 * @author:linghushaoxia
 * @time:2017年7月2日下午9:20:09
 * @version:1.0
 *
 */
public class StreamType {
	/**
	 * ID
	 */
    private String id;
    /**
     * 类型
     */
    private String container;
    /**
     * 视频属性
     */
    private String video_profile;
    public StreamType(){
    	
    }
    public StreamType(String id,String container,String video_profile){
    	this.id = id;
    	this.container=container;
    	this.video_profile = video_profile;
    }
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setContainer(String container) {
         this.container = container;
     }
     public String getContainer() {
         return container;
     }

    public void setVideo_profile(String video_profile) {
         this.video_profile = video_profile;
     }
     public String getVideo_profile() {
         return video_profile;
     }

}