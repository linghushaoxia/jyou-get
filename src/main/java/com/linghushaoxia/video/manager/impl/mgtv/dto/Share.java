
package com.linghushaoxia.video.manager.impl.mgtv.dto;
/**
 * 功能说明：分享信息
 * @author:linghushaoxia
 * @time:2017年7月2日下午9:13:37
 * @version:1.0
 *
 */
public class Share {
	/**
	 * qq分享
	 */
    private String qq;
    /**
     * qq空间
     */
    private String qzone;
    /**
     * 微博
     */
    private String weibo;
    /**
     * 微信
     */
    private String weixin;
    public void setQq(String qq) {
         this.qq = qq;
     }
     public String getQq() {
         return qq;
     }

    public void setQzone(String qzone) {
         this.qzone = qzone;
     }
     public String getQzone() {
         return qzone;
     }

    public void setWeibo(String weibo) {
         this.weibo = weibo;
     }
     public String getWeibo() {
         return weibo;
     }

    public void setWeixin(String weixin) {
         this.weixin = weixin;
     }
     public String getWeixin() {
         return weixin;
     }

}