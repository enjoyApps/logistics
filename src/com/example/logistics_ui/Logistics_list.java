package com.example.logistics_ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.logistics_ui.util.LogisticsCompanyUtil;

public class Logistics_list extends ListActivity {
	
	
	Spinner s1;
	
    @Override  
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_logistics_list);
 
        setListAdapter(new ArrayAdapter<String>(this,
        	android.R.layout.simple_list_item_1, LogisticsCompanyUtil.getAllLogisticsCompanyName()));
        
//        s1 = (Spinner) findViewById(R.id.spinner1);
//        
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//        		android.R.layout.simple_spinner_dropdown_item, logistics_list);
// 
//        s1.setAdapter(adapter);
//        OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener()
//        {
//            public void onItemSelected(AdapterView<?> arg0, 
//            View arg1, int arg2, long arg3) 
//            {
//                int index = s1.getSelectedItemPosition();
//                Toast.makeText(getBaseContext(), 
//                    "You have selected item : " + logistics_list[index], 
//                    Toast.LENGTH_SHORT).show();                
//            }
// 
//            public void onNothingSelected(AdapterView<?> arg0) {}
//
//        };
//        s1.setOnItemSelectedListener(onItemSelectedListener);
        
    }    
 
    public void onListItemClick(ListView parent, View v,int position, long id) 
    {   
        Toast.makeText(this, 
            "You have selected " + LogisticsCompanyUtil.getAllLogisticsCompanyName()[position], 
            Toast.LENGTH_SHORT).show();
    } 

}
