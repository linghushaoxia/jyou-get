package com.linghushaoxia.video.manager.impl.mgtv.dto;

public class User {
	/**
	 * id
	 */
    private String id;
    /**
     * 用户IP
     */
    private String ip;
    /**
     * IP限制
     */
    private String iplimit;
    /**
     * 是否是vip
     */
    private String isvip;
    /**
     * 预览时长
     */
    private String purview;
    /**
     * 用户UUID
     */
    private String uuid;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setIp(String ip) {
         this.ip = ip;
     }
     public String getIp() {
         return ip;
     }

    public void setIplimit(String iplimit) {
         this.iplimit = iplimit;
     }
     public String getIplimit() {
         return iplimit;
     }

    public void setIsvip(String isvip) {
         this.isvip = isvip;
     }
     public String getIsvip() {
         return isvip;
     }

    public void setPurview(String purview) {
         this.purview = purview;
     }
     public String getPurview() {
         return purview;
     }

    public void setUuid(String uuid) {
         this.uuid = uuid;
     }
     public String getUuid() {
         return uuid;
     }

}