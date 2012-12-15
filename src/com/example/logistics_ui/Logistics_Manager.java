package com.example.logistics_ui;

import com.example.logistics_ui.model.LogisticsCompany;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Logistics_Manager extends TabActivity {
	
	/**
	 * 
	 */
	private LogisticsCompany selectedLogisticsCompany = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logistics_manager);
		
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Logistics List tab
		Intent intentParcel_list = new Intent().setClass(this, Account_login.class);
		TabSpec tabSpecParcel_list = tabHost
		  .newTabSpec("我的快递")
		  .setIndicator("我的快递", null)
		  .setContent(intentParcel_list);
 
		// Send Parcel tab
		//Intent intentSendParcel = new Intent().setClass(this, Send_parcel.class);
		Intent intentSendParcel = new Intent().setClass(this, CustomizedListView.class);
		TabSpec tabSpecSendParcel = tabHost
		  .newTabSpec("寄快递")
		  .setIndicator("寄快递", null)
		  .setContent(intentSendParcel);
 
		// Bill Search tab
		Intent intentBillSearch = new Intent().setClass(this, Bill_search.class);
		TabSpec tabSpecBillSearch = tabHost
		  .newTabSpec("快递单查询")
		  .setIndicator("快递单查询", null)
		  .setContent(intentBillSearch);
 
		// add all tabs 
		tabHost.addTab(tabSpecBillSearch);
		tabHost.addTab(tabSpecParcel_list);
		tabHost.addTab(tabSpecSendParcel);
 
		//set Parcel List tab as default (zero based)
		tabHost.setCurrentTab(0);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_logistics_manager, menu);
		return true;
	}

}
