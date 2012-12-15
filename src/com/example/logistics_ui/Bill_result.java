package com.example.logistics_ui;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logistics_ui.model.LogisticsInfo;
import com.example.logistics_ui.util.LogisticsCompanyUtil;

public class Bill_result extends ListActivity {

	String[] logistics_list = null;

	private TextView com_name = null;

	private TextView to_com_call = null;

	private TextView bill_no = null;
	
	LogisticsInfo logisticsInfo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill_result);

	    logisticsInfo = (LogisticsInfo) getIntent().getExtras()
				.getSerializable("logisticsInfo");

		if (logisticsInfo == null) {
			return;
		}

		com_name = (TextView) findViewById(R.id.com_name);

		com_name.setText(logisticsInfo.getExpTextName());


		bill_no = (TextView) findViewById(R.id.bill_no);

		bill_no.setText(logisticsInfo.getMailNo());

		ImageButton call_com_btn = (ImageButton) findViewById(R.id.to_com_call);
		call_com_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// 打开拨号键盘
				Intent intent = new Intent("android.intent.action.CALL", Uri
						.parse("tel:" + LogisticsCompanyUtil.getPhoneNoByPinyin(logisticsInfo.getExpSpellName())));
				startActivity(intent);
			}
		});

		if (logisticsInfo.getTrackInfoList() == null) {
			return;
		}

		logistics_list = new String[logisticsInfo.getTrackInfoList().size()];

		for (int i = 0; i < logistics_list.length; i++) {
			logistics_list[i] = logisticsInfo.getTrackInfoList().get(i)
					.getTime()
					+ " "
					+ logisticsInfo.getTrackInfoList().get(i).getContext();
		}

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, logistics_list));
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		Toast.makeText(this, "You have selected " + logistics_list[position],
				Toast.LENGTH_SHORT).show();
	}

}
