package com.linghushaoxia.video.manager.impl.mgtv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.linghushaoxia.video.dto.DownLoadInfo;
import com.linghushaoxia.video.manager.IVideoDownLoadManager;
import com.linghushaoxia.video.manager.impl.mgtv.dto.MgTv;
import com.linghushaoxia.video.manager.impl.mgtv.dto.RealUrl;
import com.linghushaoxia.video.manager.impl.mgtv.dto.Stream;
import com.linghushaoxia.video.manager.impl.mgtv.dto.StreamType;
import com.linghushaoxia.video.service.IDownLoaderService;
import com.linghushaoxia.video.service.impl.DownLoaderService;
import com.linghushaoxia.video.util.CommonUtil;
import com.linghushaoxia.video.util.HttpClientUtil;
import com.linghushaoxia.video.util.UrlUtil;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年8月25日下午9:17:05
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class MgtvVideoDownLoadManager implements IVideoDownLoadManager{
	//视频下载服务
	private IDownLoaderService downLoaderService;
	//视频下载的api
    private String videoApi ="http://pcweb.api.mgtv.com/player/video?video_id=videoId";
    /**
     * 播放地址1
     */
    private String regxB = "http://www.mgtv.com/b/\\d+/(\\d+).html";
    /**
     * 播放地址2
     */
    private String regxBd = "http://www.mgtv.com/hz/bdpz/\\d+/(\\d+).html";
    //片名
    private String title;
    /**
     * 流类型
     */
    private List<StreamType> streamTypes = new ArrayList<StreamType>();
    {
    	streamTypes.add(new StreamType("hd","ts","超清"));
    	streamTypes.add(new StreamType("sd","ts","高清"));
    	streamTypes.add(new StreamType("ld","ts","标清"));

    }
    /**
     * 视频清晰度词典
     */
	private Map<String, String> idDict = new HashMap<String, String>();
	{
		for (StreamType streamType : streamTypes) {
			idDict.put(streamType.getVideo_profile(),streamType.getId());
		}
	}
	//视频文件质量和地址列表
	private Map<String, List<String>> videoUrlMap = new HashMap<String, List<String>>();
	@Override
	public void downVideo(DownLoadInfo downLoadInfo) {
		if (downLoadInfo!=null) {
			downVideoByUrl(downLoadInfo.getUrl(),downLoadInfo.getDst());
		}
		
	}
	/**
	 * 
	 * 功能说明:解析url所指向的视频
	 * @param url
	 * 视频url
	 * @param dstPath
	 * 视频保存路径
	 * @time:2017年6月30日下午11:47:40
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private void downVideoByUrl(String url,String dstPath){
		prepare(url);
		//下载文件
		for(String key:videoUrlMap.keySet()){
			List<String> urls = videoUrlMap.get(key);
			for(int i=0;i<urls.size();i++){
				getDownLoaderService().downLoadAndSave(urls.get(i),dstPath+"/"+i+".mp4");
			}
		}
	}
	/**
	 * 
	 * 功能说明:获取视频ID
	 * @param url
	 * 播放的url,浏览器中的地址
	 * @return String
	 * @time:2017年7月2日下午9:28:45
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private String getVideoId(String url){
		 String videoId = CommonUtil.matchs(url, Pattern.compile(regxB));
		 if (StringUtils.isBlank(videoId)) {
			 videoId =CommonUtil.matchs(url, Pattern.compile(regxBd)); 
		}
		 return videoId;
	}
	/**
	 * 
	 * 功能说明:准备url
	 * 对url进行解析,获取视频真实地址等
	 * @param url
	 * @return String
	 * @time:2017年7月2日下午9:29:30
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private String prepare(String url){
		String videoId = getVideoId(url);
		url= videoApi.replace("videoId", videoId);
		try {
			/**
			 * 获取视频信息
			 */
			String videoDataInfo = HttpClientUtil.httpGet(url);
			MgTv mgTv = JSON.parseObject(videoDataInfo, MgTv.class);
			//视频名
			title = mgTv.getData().getInfo().getTitle();
			//视频地址的域名
			String domain = mgTv.getData().getStream_domain().get(0);
			//可以获取的视频流
			Map<String, String> streamAvailable = new HashMap<String, String>();
			for (Stream stream : mgTv.getData().getStream()) {
				streamAvailable.put(stream.getName(), stream.getUrl());
			}
			/**
			 * 解析视频流地址，获取真实地址
			 */
			for (StreamType streamType : streamTypes) {
				if (streamAvailable.containsKey(streamType.getVideo_profile())) {
					//质量
					String quality_id = idDict.get(streamType.getVideo_profile());
					url = streamAvailable.get(streamType.getVideo_profile());
					url= domain+url.replaceAll("(\\&arange\\=\\d+)","");
					List<String> videoUrls =getRealUrl(url);
					videoUrlMap.put(quality_id, videoUrls);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	
	}
	/**
	 * 
	 * 功能说明:获取视频的真实地址
	 * @param url
	 * 视频地址
	 * @return String
	 * @time:2017年7月1日下午4:25:58
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private List<String> getRealUrl(String url){
		//返回结果
		List<String> segmentList = new ArrayList<String>();
		try {
			String content = HttpClientUtil.httpGet(url);
			RealUrl realUrl = JSON.parseObject(content,RealUrl.class);
			String m3uUrl = realUrl.getInfo();
			Map<String, String> urlMap = UrlUtil.urlSplit(m3uUrl);
			String baseUrl =String.format("%s://%s%s/",urlMap.get(UrlUtil.SCHEME),urlMap.get(UrlUtil.NET_LOC),urlMap.get(UrlUtil.PATH).substring(0,urlMap.get("path").lastIndexOf("/")));
			content =HttpClientUtil.httpGet(realUrl.getInfo());
			//所有段的大小
			int segmentsSize=0;
			/**
			 * 获取段地址列表，统计文件大小
			 */
			String[] contentLines = content.split("\n");
			for (String line : contentLines) {
				if (!line.startsWith("#")) {
					segmentList.add(baseUrl+line);
				}else if (line.startsWith("#EXT-MGTV-File-SIZE:")) {
					segmentsSize+=Integer.valueOf(line.split(":")[1]);
				}
			}
			System.out.println("文件大小:"+segmentsSize/1024/1024);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return segmentList;
	}
	public IDownLoaderService getDownLoaderService() {
		if (downLoaderService==null) {
			downLoaderService =new DownLoaderService();
		}
		return downLoaderService;
	}
	public void setDownLoaderService(IDownLoaderService downLoaderService) {
		this.downLoaderService = downLoaderService;
	}


}

/**
* 现实就是实现理想的过程
*/