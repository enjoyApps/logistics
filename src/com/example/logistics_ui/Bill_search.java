package com.example.logistics_ui;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.logistics_ui.model.LogisticsCompany;
import com.example.logistics_ui.util.Actions;

public class Bill_search extends Activity {
	
	private LogisticsCompany selectedLogisticsCompany = null;
	
	private EditText logistics_com_edit = null;
	
	private EditText logistics_num_edit = null;
	
	
	
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
		
		ImageButton choose_num_button = (ImageButton) findViewById(R.id.choose_num_button);
		choose_num_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	// TODO: hide the keyboard
//            	View view = v.findViewById(android.R.id.bill_search);
//            	hideSoftKeyboard(this, view);

				Intent i = new Intent(Bill_search.this, CaptureActivity.class);
				startActivityForResult(i, 0);

        }});
		
		
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
		
		if(data == null) return;
		
		if(StringUtils.equals(data.getAction(), Actions.chose_logistics_company))
		switch (resultCode)  
	    {  
	      case RESULT_OK: /* 取得数据，并显示于画面上 */
	    	  
	    	Bundle bunde = data.getExtras();  
	    	
	    	Log.d("DEBUG", "selectedName---"+bunde.getString("selectedName"));
	    
	    	  
	    	selectedLogisticsCompany =  (LogisticsCompany)bunde.getSerializable("selected");
	    	if(selectedLogisticsCompany != null) {
	    		Log.d("DEBUG", "selectedLogisticsCompany---"+selectedLogisticsCompany);
		    	
		    	logistics_com_edit = (EditText)findViewById(R.id.logistics_com_edit);
		    	
		    	logistics_com_edit.setText(selectedLogisticsCompany.getName());
	    	}

	        break;  
	      default:  
	        break;  
	    }
		if(StringUtils.equals(data.getAction(), Actions.scan))
		if (resultCode == RESULT_OK) {
            //String codeFormat = data.getStringExtra("bracode_format");
            String codeText = data.getStringExtra("bracode_text");
            logistics_num_edit  = (EditText)findViewById(R.id.logistics_num_edit);
            if (codeText!=null && !"".equals(codeText)) {
            	logistics_num_edit.setText(codeText);
                //fetchBookInfo(codeText);
            } else {
                Toast.makeText(this, "没有找到运单号", Toast.LENGTH_SHORT).show();
            }

        }

	}

}
