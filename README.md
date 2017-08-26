# jyou-get
you-get的java版本
## 已改写:
### cntv
### mgtv
# 测试代码
    package com.linghushaoxia.video.download;
    import com.linghushaoxia.video.dto.DownLoadInfo;
    import com.linghushaoxia.video.manager.IVideoDownLoadManager;
    import com.linghushaoxia.video.manager.impl.VideoDownLoadManager;

    /**功能说明：视频下载基本测试
     * @author:linghushaoxia
     * @time:2017年8月25日下午10:37:21
     * @version:1.0
     * 为中国羸弱的技术撑起一片自立自强的天空
     */
    public class DownLoadTest {
      public static void main(String[] args) {
        IVideoDownLoadManager downLoadManager = new VideoDownLoadManager();
        //视频地址
        String url="http://arts.cctv.com/2017/07/25/VIDEjhWhUmvouacDVMl1ZnoX170725.shtml";
        DownLoadInfo downLoadInfo = new DownLoadInfo();
        //是否合并视频
        downLoadInfo.setMerger(true);
        //保存路径
        downLoadInfo.setDst("苏东坡/第三集");
        downLoadInfo.setUrl(url);
        downLoadManager.downVideo(downLoadInfo);
      }
    }

    /**
    * 现实就是实现理想的过程
    */
