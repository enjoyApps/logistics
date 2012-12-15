package com.example.logistics_ui;

import com.example.logistics_ui.model.LogisticsCompany;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TabHost.TabSpec;

public class Bill_search extends Activity {
	
	private LogisticsCompany selectedLogisticsCompany = null;
	
	private EditText logistics_com_edit = null;
	
	public static void hideSoftKeyboard (Activity activity, View view) {
	  InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//	  imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	  imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill_search);
		
		
		
		ImageButton choose_com_btn = (ImageButton) findViewById(R.id.choose_com_button);
		choose_com_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent i = new Intent(Bill_search.this, Logistics_list.class);
            	Bundle bundle = new Bundle();
            	bundle.putString("ok", "ok");
            	i.putExtras(bundle);
            	startActivityForResult(i,1);
            }
        });
		
		Button search_btn = (Button) findViewById(R.id.search_submit);
		search_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	// TODO: hide the keyboard
//            	View view = v.findViewById(android.R.id.bill_search);
//            	hideSoftKeyboard(this, view);
            	
            	Intent i = new Intent(Bill_search.this, Bill_result.class);
				startActivity(i);
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bill_search, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode)  
	    {  
	      case RESULT_OK: /* 取得数据，并显示于画面上 */
	    	  
	    	Bundle bunde = data.getExtras();  
	    	
	    	Log.d("DEBUG", "selectedName---"+bunde.getString("selectedName"));
	    
	    	  
	    	selectedLogisticsCompany =  (LogisticsCompany)bunde.getSerializable("selected");
	    	
	    	Log.d("DEBUG", "selectedLogisticsCompany---"+selectedLogisticsCompany);
	    	
	    	logistics_com_edit = (EditText)findViewById(R.id.logistics_com_edit);
	    	
	    	logistics_com_edit.setText(selectedLogisticsCompany.getName());

	        break;  
	      default:  
	        break;  
	    }  
		
	}

}
