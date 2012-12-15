package com.example.logistics_ui.util;

import java.util.ArrayList;
import java.util.List;

import com.example.logistics_ui.model.LogisticsCompany;

public class LogisticsCompanyUtil {
	
	private static final List<LogisticsCompany> logisticsCompanyList = new ArrayList<LogisticsCompany>();
	
	static{

		logisticsCompanyList.add(new LogisticsCompany("汇通快运","huitong","10086"));
		logisticsCompanyList.add(new LogisticsCompany("顺丰速递","shunfeng","10086"));
		logisticsCompanyList.add(new LogisticsCompany("汇通快运","huitong","10086"));
	}

}
