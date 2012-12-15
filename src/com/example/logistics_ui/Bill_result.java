package com.example.logistics_ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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

import com.example.logistics_ui.model.LogisticInfo;
import com.example.logistics_ui.util.LogisticsCompanyUtil;
import com.example.logistics_ui.util.NavBarUtil;

public class Bill_result extends ListActivity {

	String[] logistics_list = null;

	private TextView com_name = null;

	private TextView to_com_call = null;

	private TextView bill_no = null;

	private TextView bill_lastest = null;

	private LogisticInfo logisticsInfo = null;

	private List<String> jingguo = new ArrayList<String>();
	
	public 	NavBarUtil navbar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill_result);
		com_name = (TextView) findViewById(R.id.com_name);
		bill_no = (TextView) findViewById(R.id.bill_no);
		
		navbar = new NavBarUtil(NavBarUtil.HEADER_BACK_STYLE, this);
		navbar.setHeaderTitle("快递单详情");

		logisticsInfo = (LogisticInfo) getIntent().getExtras()
				.getSerializable("logisticsInfo");

		if (logisticsInfo == null) {
			com_name.setText("");
			bill_no.setText("");
			return;
		}

		com_name.setText(logisticsInfo.getExpTextName());

		bill_no.setText("快递单号: " + logisticsInfo.getMailNo());

		jingguo = getIntent().getStringArrayListExtra("jingguo");

		bill_lastest = (TextView) findViewById(R.id.bill_lastest);

		if (logisticsInfo.getTrackInfoList() != null
				&& logisticsInfo.getTrackInfoList().size() > 0) {
			bill_lastest.setText("最新状态： "
					+ (logisticsInfo.getTrackInfoList().get(logisticsInfo
							.getTrackInfoList().size() - 1)).getContext());
		} else {
			bill_lastest.setText("");
		}

		ImageButton call_com_btn = (ImageButton) findViewById(R.id.to_com_call);

		String phoneNo = LogisticsCompanyUtil.getPhoneNoByPinyin(logisticsInfo
				.getExpSpellName());
		if (StringUtils.isNotBlank(phoneNo))
			;
		call_com_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// 打开拨号键盘
				Intent intent = new Intent("android.intent.action.CALL", Uri
						.parse("tel:"
								+ LogisticsCompanyUtil
										.getPhoneNoByPinyin(logisticsInfo
												.getExpSpellName())));
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
		Intent inmap = new Intent(Bill_result.this, PolylineActivity.class);
		inmap.putStringArrayListExtra("locals", new ArrayList<String>(jingguo));
		startActivity(inmap);
		Toast.makeText(this, "You have selected " + logistics_list[position],
				Toast.LENGTH_SHORT).show();
	}

}
