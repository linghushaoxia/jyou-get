package com.linghushaoxia.video.manager;

import com.linghushaoxia.video.dto.DownLoadInfo;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月25日下午9:08:37
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public interface IVideoDownLoadManager {
	/**
	 * 
	 * 功能说明:下载视频,
	 * @param downLoadInfo
	 * 下载地址以及保存路径等参数
	 * @time:2017年8月25日下午10:38:56
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public void downVideo(DownLoadInfo downLoadInfo);
}

/**
* 现实就是实现理想的过程
*/