package com.example.logistics_ui.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author zhenggangji
 *
 */
public class LogisticsInfo {

	private String status, message, errCode, html, mailNo, expTextName, expSpellName, update, cache, ord;
	
	private List<TrackInfo>  trackInfoList = new ArrayList<TrackInfo>();

	public void init(JSONObject json) {
		
		try {
			status = json.getString("status");
			message = json.getString("message");
			errCode = json.getString("errCode");
			html = json.getString("html");
			mailNo = json.getString("mailNo");
			expTextName = json.getString("expTextName");
			expSpellName = json.getString("expSpellName");
			update = json.getString("update");
			cache = json.getString("cache");
			ord = json.getString("ord");
			// get trackInfoList ...

			JSONArray authorArray = json.getJSONArray("data");
			for (int i = 0; i < authorArray.length(); i++) {
				TrackInfo trackInfo = new TrackInfo();
				trackInfo.setTime(authorArray.getJSONObject(i).getString("time"));
				trackInfo.setContext(authorArray.getJSONObject(i).getString("context"));
				trackInfoList.add(trackInfo);
			}

		} catch (JSONException jsone) {
			Log.e("EXCEPTION", "JSON FAIL --- " + jsone.getMessage());
		}

	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public List<TrackInfo> getTrackInfoList() {
		return trackInfoList;
	}

	public void setTrackInfoList(List<TrackInfo> trackInfoList) {
		this.trackInfoList = trackInfoList;
	}
	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public String getExpTextName() {
		return expTextName;
	}

	public void setExpTextName(String expTextName) {
		this.expTextName = expTextName;
	}

	public String getExpSpellName() {
		return expSpellName;
	}

	public void setExpSpellName(String expSpellName) {
		this.expSpellName = expSpellName;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}
	

	public String getCache() {
		return cache;
	}


	public void setCache(String cache) {
		this.cache = cache;
	}
	
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}

}
