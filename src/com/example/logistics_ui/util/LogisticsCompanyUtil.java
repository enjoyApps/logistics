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

		logisticsCompanyList.add(new LogisticsCompany("汇通快递","huitong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("顺丰快递","shunfeng","10086"));
		logisticsCompanyList.add(new LogisticsCompany("天天快递","tiantian","10086"));
		logisticsCompanyList.add(new LogisticsCompany("申通快递","shentong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("CCES快递","cces","10086"));
		logisticsCompanyList.add(new LogisticsCompany("韵达快递","yunda","10086"));
		logisticsCompanyList.add(new LogisticsCompany("中通速递","zhongtong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("宅急送快递","zhaijisong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("EMS快递","ems","10086"));
		logisticsCompanyList.add(new LogisticsCompany("圆通快递","yuantong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("中国邮政平邮","pingyou","10086"));
		logisticsCompanyList.add(new LogisticsCompany("星晨急便","xingchen","10086"));
		logisticsCompanyList.add(new LogisticsCompany("中铁快运","zhongtie","10086"));
		logisticsCompanyList.add(new LogisticsCompany("AAE快递","aae","10086"));
		logisticsCompanyList.add(new LogisticsCompany("安捷快递","anjie","10086"));
		logisticsCompanyList.add(new LogisticsCompany("安信达快递","anxinda","10086"));
		logisticsCompanyList.add(new LogisticsCompany("程光快递","chengguang","10086"));
		logisticsCompanyList.add(new LogisticsCompany("大田物流","datian","10086"));
		logisticsCompanyList.add(new LogisticsCompany("德邦物流","debang","10086"));
		logisticsCompanyList.add(new LogisticsCompany("DHL快递","dhl","10086"));
		logisticsCompanyList.add(new LogisticsCompany("DPEX快递","dpex","10086"));
		logisticsCompanyList.add(new LogisticsCompany("D速快递","dsu","10086"));
		logisticsCompanyList.add(new LogisticsCompany("国际Fedex","fedex","10086"));
		logisticsCompanyList.add(new LogisticsCompany("Fedex国内","fedexcn","10086"));
		logisticsCompanyList.add(new LogisticsCompany("原飞航物流","feihang","10086"));

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
