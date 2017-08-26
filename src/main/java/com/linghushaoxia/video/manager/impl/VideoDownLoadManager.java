package com.linghushaoxia.video.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.linghushaoxia.video.dto.DownLoadInfo;
import com.linghushaoxia.video.manager.IVideoDownLoadManager;
import com.linghushaoxia.video.manager.impl.cntv.CntvVideoDownLoadManager;
import com.linghushaoxia.video.manager.impl.mgtv.MgtvVideoDownLoadManager;
import com.linghushaoxia.video.util.BashUtil;
import com.linghushaoxia.video.util.FileUtil;
import com.linghushaoxia.video.util.UrlUtil;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月25日下午8:47:49
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class VideoDownLoadManager implements IVideoDownLoadManager {
	private Map<String, IVideoDownLoadManager> downMap =new ConcurrentHashMap<String, IVideoDownLoadManager>();
	//cntv
	private IVideoDownLoadManager cntVideoDownLoadManager;
	//mgtv
	private IVideoDownLoadManager mgtvVideoDownLoadManager;
	@Override
	public void downVideo(DownLoadInfo downLoadInfo) {
		//根据地址判断目标网站
		IVideoDownLoadManager videoDownLoadManager = getDownLoadManager(downLoadInfo.getUrl());
		videoDownLoadManager.downVideo(downLoadInfo);
		if (downLoadInfo.isMerger()) {
			//合并视频,只有当前线程，其余线程执行完毕
			while(Thread.activeCount()>1){	
			}
			merger(downLoadInfo.getDst());
		}
		
	}
	/**
	 * 
	 * 功能说明:根据域名获取对应的下载器
	 * @param url
	 * 远程url
	 * @return IVideoDownLoadManager
	 * @time:2017年8月26日下午10:49:41
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private IVideoDownLoadManager getDownLoadManager(String url){
		Map<String, String> urlPropertyMap= UrlUtil.urlSplit(url);
		//根据域名判断
		String netLoc = urlPropertyMap.get(UrlUtil.NET_LOC);
		if (!downMap.containsKey(netLoc)) {
			//cntv
			if (isCntv(netLoc)) {
				System.out.println("判断目标网站为cntv");
				downMap.put(netLoc,getCntVideoDownLoadManager());
			}
			//芒果tv
			if (isMangGuotv(netLoc)) {
				System.out.println("判断目标网站为MangGuotv");
				downMap.put(netLoc,getMgtvVideoDownLoadManager());
			}
		}
		return downMap.get(netLoc);
	}
	/**
	 * 
	 * 功能说明:合并指定目录下的视频文件
	 * @param path
	 * @time:2017年8月25日下午11:14:12
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private void merger(String path){
		String tempPath=path;
		if (path.contains("/")||path.contains("\\")) {
			if (path.contains(".")) {
				tempPath= path.substring(0,path.lastIndexOf("/")<0?path.lastIndexOf("\\"):path.lastIndexOf("/"));
			}
		}
		formatTrans(tempPath);
		combineByConcatFull(tempPath, tempPath+".mp4", ".ts");
		//删除废弃目录
		FileUtil.deleteEmptyDir(tempPath);
	}
	/**
	 * 
	 * 功能说明:使用concat协议合并格式相同的视频文件
	 * @param srcPath
	 * 源文件路径
	 * @param dstPath
	 * 输出路径
	 * @param format
	 * 格式
	 * 视频文件根路径
	 * @time:2017年2月8日下午3:24:41
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private void combineByConcatFull(String srcPath,String dstPath,String format){
		//视频文件路径
		List<String> videoPathList = new ArrayList<String>();
		FileUtil.getFilePathList(srcPath, videoPathList, format);
		//对文件名排序
		videoPathList = FileUtil.sortList(videoPathList);
		//拼接命令
		StringBuilder builder = new StringBuilder();
		//输入文件
		builder.append("ffmpeg -i concat:");
		//文件序列添加双引号
		builder.append("\"");
		for (int i = 0; i <videoPathList.size(); i++) {
			builder.append(videoPathList.get(i)).append("|");
		}
		//删除最后的|
		builder.deleteCharAt(builder.length()-1);
		builder.append("\"");
		//-c copy 音视频编码直接拷贝
		builder.append(" -c copy");
		//文件存在直接覆盖
		builder.append(" -y");
		/**
		 * faststart,边下边播,
		 * 注意bsf:a aac_adtstoasc可能导致无法播放音频;
		 * 而有的文件需要这个选项,和文件编码有
		 */
		builder.append(" -bsf:a aac_adtstoasc -movflags +faststart");
//		builder.append("  -movflags +faststart");
//		builder.append(" -bsf:a aac_adtstoasc");
		builder.append(" ").append(dstPath);
		//临时文件路径
		String bash = builder.toString();
		BashUtil.bashRun(bash);
		//删除冗余文件
		for (String path : videoPathList) {
			FileUtil.delete(path);
		}
		
	}
	/**
	 * 
	 * 功能说明:格式转换
	 * 将mp4等文件,不进行解码，重新封装为ts格式
	 * 以便进行合并。
	 * @param srcPath
	 * 源文件根路径
	 * @time:2017年2月9日上午11:54:15
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private void formatTrans(String srcPath){
		//视频文件路径
		List<String> videoPathList = new ArrayList<String>();
		FileUtil.getFilePathList(srcPath, videoPathList,"");
		videoPathList = FileUtil.sortList(videoPathList);
		//拼接命令
		StringBuilder builder = new StringBuilder();
		//输入文件
		for(String path:videoPathList){
			builder.append("ffmpeg -i ").append(path).append(" -c copy -bsf h264_mp4toannexb -f mpegts -y ").append(path+".ts");
			BashUtil.bashRun(builder.toString());
			builder.delete(0, builder.length());
			//删除文件
			FileUtil.delete(path);
		}
	}
	/**
	 * 
	 * 功能说明:判断芒果tv
	 * @param netLoc
	 * @return boolean
	 * @time:2017年8月25日下午11:03:20
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private boolean isMangGuotv(String netLoc){
		return netLoc.contains("mgtv.com");
	}
	/**
	 * 
	 * 功能说明:判断cntv
	 * @param netLoc
	 * @return boolean
	 * @time:2017年8月25日下午11:03:26
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private boolean isCntv(String netLoc){
		return netLoc.contains("cntv.cn")||netLoc.contains("cntv.com")||netLoc.contains("cctv.com")||netLoc.contains("cctv.cn");
	}
	public IVideoDownLoadManager getCntVideoDownLoadManager() {
		if (cntVideoDownLoadManager==null) {
			cntVideoDownLoadManager=new CntvVideoDownLoadManager();
		}
		return cntVideoDownLoadManager;
	}
	public void setCntVideoDownLoadManager(
			IVideoDownLoadManager cntVideoDownLoadManager) {
		this.cntVideoDownLoadManager = cntVideoDownLoadManager;
	}
	public IVideoDownLoadManager getMgtvVideoDownLoadManager() {
		if (mgtvVideoDownLoadManager==null) {
			mgtvVideoDownLoadManager=new MgtvVideoDownLoadManager();
		}
		return mgtvVideoDownLoadManager;
	}
	public void setMgtvVideoDownLoadManager(
			IVideoDownLoadManager mgtvVideoDownLoadManager) {
		this.mgtvVideoDownLoadManager = mgtvVideoDownLoadManager;
	}
}

/**
* 现实就是实现理想的过程
*/