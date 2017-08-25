package com.linghushaoxia.video.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**功能说明：工具类
 * @see https://github.com/soimort/you-get/blob/develop/src/you_get/common.py
 * @author:linghushaoxia
 * @time:2017年8月19日下午12:16:00
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class CommonUtil {
	/**
	 * 
	 * 功能说明:匹配第一个分组
	 * @param text
	 * @param patterns
	 * @return String
	 * @time:2017年8月19日下午12:25:37
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static String matchs(String text,Pattern... patterns){
		if (patterns.length==1) {
			Pattern pattern=patterns[0];
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}else {
			StringBuilder builder = new StringBuilder();
			for (Pattern pattern : patterns) {
				Matcher matcher = pattern.matcher(text);
				builder.append(matcher.group(1));
			}
			return builder.toString();
		}
		return null;
	}
	/**
	 * 
	 * 功能说明:匹配第一个分组
	 * @param regex
	 * @param text
	 * @return String
	 * @time:2017年8月19日下午12:35:21
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static String match1(String regex,String text){
		Matcher matcher = Pattern.compile(regex).matcher(text);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}public CommonUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * 功能说明:
	 * @param regex
	 * @param text void
	 * @time:2017年8月25日下午9:27:30
	 * @author:linghushaoxia
	 * @return 
	 * @exception:
	 *
	 */
	public static boolean match(String regex,String text){
		return Pattern.compile(regex).matcher(text).find();
	}
}

/**
* 现实就是实现理想的过程
*/