package com.example.logistics_ui;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Send_parcel extends ListActivity {

//	String[] logistics_list = {
//            "申通快递",
//            "德邦物流",
//            "新邦物流",
//            "天天快递",
//            "佳吉快运",
//            "顺丰速运",
//            "EMS",
//            "韵达快运",
//            "中通速递",
//            "中铁物流",
//            "圆通速递"
//    };
	
	String[] logistics_list = {
            "申通快递 13437292435",
            "德邦物流",
            "新邦物流",
            "天天快递",
            "佳吉快运",
            "顺丰速运",
            "EMS",
            "韵达快运",
            "中通速递",
            "中铁物流",
            "圆通速递"
    };
 
    @Override  
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_send_parcel);
 
        setListAdapter(new ArrayAdapter<String>(this,
        	android.R.layout.simple_list_item_1, logistics_list));
    }    
 
    public void onListItemClick(ListView parent, View v,int position, long id) 
    {   
        	Toast.makeText(this, 
            "电话联系" + logistics_list[position] + " 上门取件", 
            Toast.LENGTH_SHORT).show();
        	
        	String num = "13437292435";
        	
        	// 打开拨号键盘
        	Intent intent=new Intent("android.intent.action.CALL",Uri.parse("tel:" + num));
			startActivity(intent);
    } 

}
