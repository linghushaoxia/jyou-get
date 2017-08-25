
package com.linghushaoxia.video.manager.impl.mgtv.dto;
/**
 * 功能说明：流质量
 * @author:linghushaoxia
 * @time:2017年7月2日下午9:17:36
 * @version:1.0
 *
 */
public class StreamQuality {
	/**
	 * 默认质量
	 */
    private String default_quality;
    /**
     * 建议默认质量
     */
    private String default_quality_force;
    public void setDefault_quality(String default_quality) {
         this.default_quality = default_quality;
     }
     public String getDefault_quality() {
         return default_quality;
     }

    public void setDefault_quality_force(String default_quality_force) {
         this.default_quality_force = default_quality_force;
     }
     public String getDefault_quality_force() {
         return default_quality_force;
     }

}