/**
  * Copyright 2017 bejson.com 
  */
package com.linghushaoxia.video.manager.impl.mgtv.dto;
import java.util.List;
/**
 * 功能说明：帧缩略图
 * @author:linghushaoxia
 * @time:2017年7月2日下午8:57:06
 * @version:1.0
 *
 */
public class Frame {
	/**
	 * 视频缩略图
	 */
    private List<String> images;
    /**
     * 关键帧秒时刻
     */
    private List<String> second;
    public void setImages(List<String> images) {
         this.images = images;
     }
     public List<String> getImages() {
         return images;
     }

    public void setSecond(List<String> second) {
         this.second = second;
     }
     public List<String> getSecond() {
         return second;
     }

}