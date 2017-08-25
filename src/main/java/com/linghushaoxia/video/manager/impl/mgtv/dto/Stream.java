
package com.linghushaoxia.video.manager.impl.mgtv.dto;
/**
 * 功能说明：流信息
 * @author:linghushaoxia
 * @time:2017年7月2日下午9:14:09
 * @version:1.0
 *
 */
public class Stream {
	/**
	 * 清晰度
	 */
    private String def;
    /**
     * 清晰度名称
     */
    private String name;
    /**
     * 画面宽高比
     */
    private String scale;
    /**
     * 流url
     */
    private String url;
    /**
     * vip资格标记
     */
    private String vip;
    public void setDef(String def) {
         this.def = def;
     }
     public String getDef() {
         return def;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setScale(String scale) {
         this.scale = scale;
     }
     public String getScale() {
         return scale;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setVip(String vip) {
         this.vip = vip;
     }
     public String getVip() {
         return vip;
     }

}