package com.example.logistics_ui;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Bill_result extends ListActivity {

	String[] logistics_list = {
            "已到达北京",
            "已到达上海",
            "已到达南京",
    };
 
    @Override  
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_bill_result);
 
        setListAdapter(new ArrayAdapter<String>(this,
        	android.R.layout.simple_list_item_1, logistics_list));
    }    
 
    public void onListItemClick(ListView parent, View v,int position, long id) 
    {   
        Toast.makeText(this, 
            "You have selected " + logistics_list[position], 
            Toast.LENGTH_SHORT).show();
    } 

}
