package com.example.logistics_ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.logistics_ui.model.LogisticsCompany;
import com.example.logistics_ui.util.LogisticsCompanyUtil;

public class CustomizedListView extends Activity {
 
    ListView list;
    LazyAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customizedlistview);
        
        List<LogisticsCompany> logisticsCompanyList = LogisticsCompanyUtil.getAllLogisticsCompany();
 
        final ArrayList<HashMap<String, String>> comList = new ArrayList<HashMap<String, String>>();
        
        
        for(LogisticsCompany LogisticsCompany : logisticsCompanyList){
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("com_name", LogisticsCompany.getName());
        	map.put("com_tel", LogisticsCompany.getPhoneNo());
        	comList.add(map);
        }
 
        list = (ListView) findViewById(R.id.list);
 
        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, comList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(CustomizedListView.this,
						"电话联系" + comList.get(position).get("com_name") + " 上门取件",
						Toast.LENGTH_SHORT).show();

				String num = comList.get(position).get("com_tel");

				// 打开拨号键盘
				Intent intent = new Intent("android.intent.action.CALL", Uri
						.parse("tel:" + num));
				startActivity(intent);

			}
        });
    }
}