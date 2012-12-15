package com.example.logistics_ui.util;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.logistics_ui.model.LogisticsInfo;
import com.example.logistics_ui.model.TrackInfo;

import android.util.Log;

/**
 * @author zhenggangji
 *
 */
public class LogisticsInfoUtils {
	
	public static void getLogisticsInfo(String id,String com, HttpListener listener){
		String url = "http://api.ickd.cn/?com="+com+"&nu="+id+"&id=D2BCB3066D4D77EC5567712ECA489FC8&type=json&encode=utf8";
		Log.d("DEBUG", "url: " + url);
		new HttpDownloadAsyncTask(listener).execute(url);
	}
	
	
	/**
	 * @param str
	 * @return
	 */
	public static JSONArray getLocationJasonArray(String str){
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	
	/**
	 * 从json解析，获取Book对象
	 * @param jsonStr
	 * @return
	 */
	public static LogisticsInfo parseLogisticsInfo(String jsonStr){
		
		JSONObject json = null;
		Log.d("DEBUG", "jsonStr: " + jsonStr);
		LogisticsInfo wuliuInfo = new LogisticsInfo();
		try {
			json = new JSONObject(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(json!=null) {
			wuliuInfo.init(json);
			return wuliuInfo;
		}else{
			return null;
		}
		
	}
	
	/**
	 * @param info
	 * @return
	 */
	public static Set<String> getLocations(LogisticsInfo info,
			Map<String, Map<String, Set<String>>> dizhi) {
		Set<String> locations = new LinkedHashSet<String>();
		if(info==null){
			return locations;
		}
		
		if (info.getTrackInfoList() != null)
			for (TrackInfo trackInfo : info.getTrackInfoList()) {
				String context = trackInfo.getContext();
				if (StringUtils.isNotBlank(context)) {
					context = StringUtils.substringBeforeLast(context, "正发往");
					context = StringUtils.substringBeforeLast(context, "分拨中心");
					String local = "";
					for (Map.Entry<String, Map<String, Set<String>>> entry : dizhi
							.entrySet()) {
						if (context.contains(entry.getKey())
								|| context.contains(remove(entry.getKey()))) {
							local = local + entry.getKey();
							for (Map.Entry<String, Set<String>> subEntry : entry
									.getValue().entrySet()) {
								if (StringUtils
										.equals(subEntry.getKey(), "市辖区")
										|| StringUtils.equals(
												subEntry.getKey(), "县")
										|| StringUtils.equals(
												subEntry.getKey(),
												"自治区直辖县级行政区划")) {
									for (String str : subEntry.getValue()) {
										if (context.contains(str)
												|| context
														.contains(remove(str))) {
											local = local + str;
											break;
										}
									}

								} else if (context.contains(subEntry.getKey())
										|| context.contains(remove(subEntry
												.getKey()))) {
									local = local + subEntry.getKey();
									for (String str : subEntry.getValue()) {
										if (context.contains(str)
												|| context
														.contains(remove(str))) {
											local = local + str;
											break;
										}
									}
								}
							}
							break;
						} else {
							for (Map.Entry<String, Set<String>> subEntry : entry
									.getValue().entrySet()) {
								if (StringUtils
										.equals(subEntry.getKey(), "市辖区")
										|| StringUtils.equals(
												subEntry.getKey(), "县")
										|| StringUtils.equals(
												subEntry.getKey(),
												"自治区直辖县级行政区划")) {
									for (String str : subEntry.getValue()) {
										if (context.contains(str)
												|| context
														.contains(remove(str))) {
											local = local + str;
											break;
										}
									}

								} else if (context.contains(subEntry.getKey())
										|| context.contains(remove(subEntry
												.getKey()))) {
									local = local + subEntry.getKey();
									for (String str : subEntry.getValue()) {
										if (context.contains(str)
												|| context
														.contains(remove(str))) {
											local = local + str;
											break;
										}
									}
								}
							}
						}
					}
					locations.add(local);
				}
			}
		return locations;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String remove(String str){
		String temp =new String(str);
		if(!StringUtils.equals("市辖区", temp)){
			return temp.replace("市", "").replace("省", "").replace("区", "").replace("县", "").replace("自治区", "").replace("自治州", "");
		} else {
			return temp;
		}
		
	}

	
}
