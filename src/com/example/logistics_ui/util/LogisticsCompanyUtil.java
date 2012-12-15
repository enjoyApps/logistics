package com.example.logistics_ui.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.example.logistics_ui.model.LogisticsCompany;

/**
 * @author zhenggangji
 *
 */
public class LogisticsCompanyUtil {
	
	private static final List<LogisticsCompany> logisticsCompanyList = new ArrayList<LogisticsCompany>();
	
	static{

		logisticsCompanyList.add(new LogisticsCompany("汇通快运","huitong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("顺丰速递","shunfeng","10086"));
		logisticsCompanyList.add(new LogisticsCompany("汇通快运","huitong","10086"));
	}
	
	/**
	 * @return
	 */
	public static List<LogisticsCompany> getAllLogisticsCompany() {
		return logisticsCompanyList;
	}
	
	/**
	 * @return
	 */
	public static String[] getAllLogisticsCompanyName() {
		
		String[] logisticsCompanyName = new String[logisticsCompanyList.size()];
		
		for (int i = 0; i < logisticsCompanyList.size(); i++) {
			logisticsCompanyName[i] = logisticsCompanyList.get(i).getName();
		}
		
		return logisticsCompanyName;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public static LogisticsCompany getLogisticsCompanyByNanme(String name){
		for(LogisticsCompany entry:logisticsCompanyList){
			if(StringUtils.equalsIgnoreCase(name, entry.getName())){
				return entry;
			}
		}
		return null;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public static String getPhoneNoByNanme(String name){
		for(LogisticsCompany entry:logisticsCompanyList){
			if(StringUtils.equalsIgnoreCase(name, entry.getName())){
				return entry.getPhoneNo();
				
			}
		}
		return "";
	}
	

	/**
	 * @param pinyin
	 * @return
	 */
	public static String getPhoneNoByPinyin(String pinyin){
		for(LogisticsCompany entry:logisticsCompanyList){
			if(StringUtils.equalsIgnoreCase(pinyin, entry.getPinyin())){
				return entry.getPhoneNo();
			}
		}
		return "";
	}
	

	/**
	 * @param pinyin
	 * @return
	 */
	public static String getNameByPinyin(String pinyin){
		for(LogisticsCompany entry:logisticsCompanyList){
			if(StringUtils.equalsIgnoreCase(pinyin, entry.getPinyin())){
				return entry.getName();
			}
		}
		return "";
	}
	

	/**
	 * @param name
	 * @return
	 */
	public static String getPinyinByName(String name){
		for(LogisticsCompany entry:logisticsCompanyList){
			if(StringUtils.equalsIgnoreCase(name, entry.getName())){
				return entry.getPinyin();
			}
		}
		return "";
	}

}
