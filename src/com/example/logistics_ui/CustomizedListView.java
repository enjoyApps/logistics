package com.example.logistics_ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.example.logistics_ui.model.LogisticsCompany;
import com.example.logistics_ui.util.LogisticsCompanyUtil;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Element;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CustomizedListView extends Activity {
    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "com_name";
    static final String KEY_TITLE = "com_tel";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";
 
    ListView list;
    LazyAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customizedlistview);
        
        List<LogisticsCompany> logisticsCompanyList = LogisticsCompanyUtil.getAllLogisticsCompany();
 
        ArrayList<HashMap<String, String>> comList = new ArrayList<HashMap<String, String>>();
        
        
        for(LogisticsCompany LogisticsCompany : logisticsCompanyList){
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put(LogisticsCompany.getName(), LogisticsCompany.getPhoneNo());
        	comList.add(map);
        }
 
        ListView list = (ListView) findViewById(R.id.list);
 
        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, comList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
 
            }
        });
    }
}