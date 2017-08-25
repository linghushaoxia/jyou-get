package com.linghushaoxia.video.manager.impl.cntv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linghushaoxia.video.dto.DownLoadInfo;
import com.linghushaoxia.video.manager.IVideoDownLoadManager;
import com.linghushaoxia.video.manager.impl.cntv.dto.Chapter;
import com.linghushaoxia.video.manager.impl.cntv.dto.CnTv;
import com.linghushaoxia.video.service.IDownLoaderService;
import com.linghushaoxia.video.service.impl.DownLoaderService;
import com.linghushaoxia.video.util.CommonUtil;
import com.linghushaoxia.video.util.HttpClientUtil;

/**功能说明：cntv下载
 * @author:linghushaoxia
 * @time:2017年8月25日下午9:18:14
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class CntvVideoDownLoadManager implements IVideoDownLoadManager{
	private IDownLoaderService downLoaderService;
	@Override
	public void downVideo(DownLoadInfo downLoadInfo) {
		if (downLoadInfo!=null) {
			//id不空,根据id进行下载
			if (StringUtils.isNotEmpty(downLoadInfo.getVideoId())) {
				downLoadById(downLoadInfo.getVideoId(), downLoadInfo.getDst(),downLoadInfo.isMerger(),downLoadInfo.isOnlyInfo());
			}else {
				downLoadByUrl(downLoadInfo.getUrl(), downLoadInfo.getDst(),downLoadInfo.isMerger(),downLoadInfo.isOnlyInfo());
			}
		}
		
	}

	/**
	 * 
	 * 功能说明:下载cntv视频
	 * @param url
	 * 视频播放地址
	 * @param dstPath
	 * 保存路径
	 * @param infoOnly
	 * 仅仅展示视频信息,不下载
	 * @param merge
	 * 是否对片段进行合并
	 * @time:2017年8月19日下午12:49:32
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private void downLoadByUrl(String url,String dstPath,boolean infoOnly,boolean merge){
		String id="-1";
		try {
			if (CommonUtil.match("http://tv\\.cntv\\.cn/video/(\\w+)/(\\w+)",url)){
				id=CommonUtil.matchs(url, new Pattern[]{Pattern.compile("http://tv\\.cntv\\.cn/video/\\w+/(\\w+)")});
			}else if (CommonUtil.match("http://tv\\.cctv\\.com/\\d+/\\d+/\\d+/\\w+.shtml", url)) {
				id=CommonUtil.match1("var guid = \"(\\w+)\"", HttpClientUtil.httpGet(url));
			}else if(CommonUtil.match("http://\\w+\\.cntv\\.cn/(\\w+/\\w+/(classpage/video/)?)?\\d+/\\d+\\.shtml",url)
					||CommonUtil.match("http://\\w+.cntv.cn/(\\w+/)*VIDE\\d+.shtml", url)
					||CommonUtil.match("http://(\\w+).cntv.cn/(\\w+)/classpage/video/(\\d+)/(\\d+).shtml", url)
					||CommonUtil.match("http://\\w+.cctv.com/\\d+/\\d+/\\d+/\\w+.shtml", url)
					||CommonUtil.match("http://\\w+.cntv.cn/\\d+/\\d+/\\d+/\\w+.shtml", url)) {
				id=CommonUtil.match1("videoCenterId\",\"(\\w+)\"", HttpClientUtil.httpGet(url));
			}else if(CommonUtil.match("http://xiyou.cntv.cn/v-[\\w-]+\\.html", url)){
				id=CommonUtil.match1("http://xiyou.cntv.cn/v-([\\w-]+)\\.html", url);
			}else {
				System.out.println("无法解析url:"+url);
			}
			downLoadById(id, dstPath, merge, infoOnly);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * 功能说明:
	 * @param id
	 * @param dstPath
	 * @param merge
	 * @param infoOnly
	 * @time:2017年8月19日下午12:51:54
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private  void downLoadById(String id,String dstPath,boolean merge,boolean infoOnly){
		if (id==null||"".equals(id)) {
			System.out.println("id为空");
			return;
		}
		try {
			String info=HttpClientUtil.httpGet("http://vdn.apps.cntv.cn/api/getHttpVideoInfo.do?pid="+id);
			JSONObject jsonObject= (JSONObject) JSON.parse(info);
			CnTv cnTv = JSON.parseObject(info, CnTv.class);
			/**
			 * 对video单独解析
			 */
			Map<String, List<Chapter>> videoMap = new HashMap<String, List<Chapter>>();
			//video
			JSONObject videoJsonObject= (JSONObject) jsonObject.get("video");
			Set<String> keySet= videoJsonObject.keySet();
			for (String key : keySet) {
				Object value =videoJsonObject.get(key);
				if (value instanceof JSONArray) {
					JSONArray jsonArray =(JSONArray) value;
					videoMap.put(key, jsonArray.toJavaList(Chapter.class));
				}
			}
			cnTv.getVideo().setMap(videoMap);
			/**
			 * 获取最清晰的视频地址
			 */
			List<String> keyChapters = new ArrayList<String>();
			for(String key:videoMap.keySet()){
				if (key.startsWith("chapters")) {
					keyChapters.add(key);
				}
			}
			Collections.sort(keyChapters);
			final List<Chapter> videoChapters = videoMap.get(keyChapters.get(keyChapters.size()-1));
			for(int i=0;i<videoChapters.size();i++){
				final int j=i;
				final String temp =dstPath;
				Thread thread =new Thread(){
					public void run(){
						getDownLoaderService().downLoadAndSave(videoChapters.get(j).getUrl(),temp+"/"+j+".mp4");
					}
				};
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IDownLoaderService getDownLoaderService() {
		if (downLoaderService==null) {
			downLoaderService = new DownLoaderService();
		}
		return downLoaderService;
	}

	public void setDownLoaderService(IDownLoaderService downLoaderService) {
		this.downLoaderService = downLoaderService;
	}

//	public static void main(String[] args) {
//		//http://tv.cctv.com/2014/08/14/VIDE1408003051109651.shtml
//		String url="http://tv.cctv.com/2014/08/15/VIDE1408113188467747.shtml";
//		String dstPath="data";
//		CNTV.cntvDownload(url, dstPath, false, true);
//	}


}

/**
* 现实就是实现理想的过程
*/