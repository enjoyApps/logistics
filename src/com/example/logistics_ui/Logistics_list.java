package com.example.logistics_ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.logistics_ui.model.LogisticsCompany;
import com.example.logistics_ui.util.Actions;
import com.example.logistics_ui.util.LogisticsCompanyUtil;

public class Logistics_list extends ListActivity {
	
	
	//Spinner s1;
	
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
    	
    	
    	LogisticsCompany selected = LogisticsCompanyUtil.getLogisticsCompanyByNanme(LogisticsCompanyUtil.getAllLogisticsCompanyName()[position]);
    	Log.d("DEBUG", "selected---"+selected);

    	Intent intent = new Intent();
    	intent.setAction(Actions.chose_logistics_company);
    	Bundle bundle = new Bundle();
    	bundle.putString("selectedName", selected.getName());
    	bundle.putSerializable("selected", selected);
    	
    	intent.putExtras(bundle);
    	
        intent.putExtras(bundle);
    	
    	this.setResult(RESULT_OK, intent); /* 关闭activity */
    	
    	Toast.makeText(this, 
                "You have selected " + selected.getName(), 
                Toast.LENGTH_SHORT).show();
    	
    	this.finish();
    	
        
    } 

}
