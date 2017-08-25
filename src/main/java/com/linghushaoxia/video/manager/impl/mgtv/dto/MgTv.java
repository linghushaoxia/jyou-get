
package com.linghushaoxia.video.manager.impl.mgtv.dto;
/**
 * 功能说明：芒果台video,api的请求返回对象
 * @author:linghushaoxia
 * @time:2017年7月2日下午8:46:14
 * @version:1.0
 *
 */
public class MgTv {
	/**
	 * 返回码
	 */
    private int code;
    /**
     * 数据信息
     * 
     */
    private Data data;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 序列号ID
     */
    private String seqid;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setSeqid(String seqid) {
         this.seqid = seqid;
     }
     public String getSeqid() {
         return seqid;
     }

}