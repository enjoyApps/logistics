package com.example.logistics_ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.logistics_ui.model.LogisticsCompany;
import com.example.logistics_ui.util.Actions;
import com.example.logistics_ui.util.LogisticsCompanyUtil;
import com.example.logistics_ui.util.NavBarUtil;

public class Logistics_list extends ListActivity {
	
	public 	NavBarUtil navbar;
	
	//Spinner s1;
	
    @Override  
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_logistics_list);
        
        navbar = new NavBarUtil(NavBarUtil.HEADER_BACK_STYLE, this);
		navbar.setHeaderTitle("选择物流公司");
 
        setListAdapter(new ArrayAdapter<String>(this,
        	android.R.layout.simple_list_item_1, LogisticsCompanyUtil.getAllLogisticsCompanyName()));

    }    
 
    public void onListItemClick(ListView parent, View v,int position, long id) 
    {   
    	
    	
    	LogisticsCompany selected = LogisticsCompanyUtil.getLogisticsCompanyByNanme(LogisticsCompanyUtil.getAllLogisticsCompanyName()[position]);
    	Log.d("DEBUG", "selected---"+selected);

    	Intent intent = new Intent();
    	intent.setAction(Actions.chose_logistics_company);
    	Bundle bundle = new Bundle();
    	bundle.putSerializable("selected", selected);
    	
    	intent.putExtras(bundle);
    	
    	this.setResult(RESULT_OK, intent); /* 关闭activity */
    	
    	Toast.makeText(this, 
                "You have selected " + selected.getName(), 
                Toast.LENGTH_SHORT).show();
    	
    	this.finish();
    	
        
    } 

}
