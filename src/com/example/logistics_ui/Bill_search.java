package com.example.logistics_ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logistics_ui.datestore.LogisticInfoDao;
import com.example.logistics_ui.model.LogisticInfo;
import com.example.logistics_ui.model.LogisticsCompany;
import com.example.logistics_ui.util.Actions;
import com.example.logistics_ui.util.DateUtil;
import com.example.logistics_ui.util.HttpListener;
import com.example.logistics_ui.util.LogisticsCompanyUtil;
import com.example.logistics_ui.util.LogisticsInfoUtils;
import com.example.logistics_ui.util.NetUtils;

/**
 * @author zhenggangji
 * 
 */
public class Bill_search extends ListActivity {

	private LogisticsCompany selectedLogisticsCompany = null;

	private EditText logistics_com_edit = null;

	private EditText logistics_num_edit = null;

	private ProgressDialog pd;

	private LogisticInfo logisticsInfo = null;

	private Map<String, Map<String, Set<String>>> dizhi = new LinkedHashMap<String, Map<String, Set<String>>>();

	private Set<String> jingguo;

	private LogisticInfoDao logisticInfoDao = null;

	List<LogisticInfo> logisticInfoHistList = null;

	private TextView history_title = null;

	public static void hideSoftKeyboard(Activity activity, View view) {
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill_search);

		String jasonStr = getAllLocations();

		JSONArray jsonArray = LogisticsInfoUtils
				.getLocationJasonArray(jasonStr);

		setDefaultLocations(jsonArray);

		logistics_num_edit = (EditText) findViewById(R.id.logistics_num_edit);

		logistics_com_edit = (EditText) findViewById(R.id.logistics_com_edit);

		ImageButton choose_com_btn = (ImageButton) findViewById(R.id.choose_com_button);
		choose_com_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(Bill_search.this, Logistics_list.class);
				Bundle bundle = new Bundle();
				i.putExtras(bundle);
				startActivityForResult(i, 1);
			}
		});

		Button search_btn = (Button) findViewById(R.id.search_submit);
		search_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (!NetUtils.isNetworkOk(Bill_search.this)) {
					Toast.makeText(Bill_search.this, "当前网络不可用",
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (StringUtils
						.isBlank(logistics_num_edit.getText().toString())) {
					Toast.makeText(Bill_search.this, "请填写快递单号",
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (StringUtils
						.isBlank(logistics_com_edit.getText().toString())) {
					Toast.makeText(Bill_search.this, "请选择快递公司",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// TODO: hide the keyboard
				// View view = v.findViewById(android.R.id.bill_search);
				// hideSoftKeyboard(this, view);
				fetchlogisticsInfo(
						logistics_num_edit.getText().toString(),
						LogisticsCompanyUtil.getPinyinByName(
								logistics_com_edit.getText().toString())
								.toString());

			}
		});

		ImageButton choose_num_button = (ImageButton) findViewById(R.id.choose_num_button);
		choose_num_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// TODO: hide the keyboard
				// View view = v.findViewById(android.R.id.bill_search);
				// hideSoftKeyboard(this, view);

				Intent i = new Intent(Bill_search.this, CaptureActivity.class);
				startActivityForResult(i, 0);

			}
		});

		logisticInfoDao = new LogisticInfoDao(this);

		logisticInfoHistList = logisticInfoDao.queryAll();

		history_title = (TextView) findViewById(R.id.history_title);
		if (logisticInfoHistList == null || logisticInfoHistList.size() == 0) {
			history_title.setVisibility(4);
		} else {
			setHistoryStringArray(logisticInfoHistList);
		}

		Log.d("DEBUG", "logisticInfoHistList---" + logisticInfoHistList);

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		logisticInfoHistList = logisticInfoDao.queryAll();

		history_title = (TextView) findViewById(R.id.history_title);
		if (logisticInfoHistList == null || logisticInfoHistList.size() == 0) {
			history_title.setVisibility(4);
		} else {
			setHistoryStringArray(logisticInfoHistList);
		}
	}

	/**
	 * @param logisticInfoHistList
	 */
	private void setHistoryStringArray(List<LogisticInfo> logisticInfoHistList) {
		String[] historyStringArray = new String[logisticInfoHistList.size()];
		for (int i = 0; i < historyStringArray.length; i++) {
			if (logisticInfoHistList.get(i) != null)
				historyStringArray[i] = StringUtils.rightPad(logisticInfoHistList.get(i)
						.getExpTextName(), 30)
						+ StringUtils.rightPad(logisticInfoHistList.get(i).getMailNo(), 30)
						+ logisticInfoHistList.get(i).getQueryTime();
		}
		history_title.setVisibility(0);
		setListAdapter(new ArrayAdapter<String>(Bill_search.this,
				android.R.layout.simple_list_item_1, historyStringArray));
	}

	/**
	 * @return
	 */
	private String getAllLocations() {
		InputStream inputStream3 = null;
		try {
			inputStream3 = getAssets().open("sitedata.js");
			return IOUtils.readLines(inputStream3).get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param locationJasonArray
	 */
	private void setDefaultLocations(JSONArray locationJasonArray) {

		for (int i = 0; i < locationJasonArray.length(); i++) {

			try {

				String proviceStr = locationJasonArray.getJSONObject(i)
						.getString("name");

				Map<String, Set<String>> areaMap = new LinkedHashMap<String, Set<String>>();
				try {

					JSONArray areaJsonArray = locationJasonArray.getJSONObject(
							i).getJSONArray("sub");

					if (areaJsonArray != null) {
						for (int j = 0; j < areaJsonArray.length(); j++) {

							String areaStr = areaJsonArray.getJSONObject(j)
									.getString("name");

							Set<String> countySet = new LinkedHashSet<String>();
							try {
								JSONArray countyJsonArray = areaJsonArray
										.getJSONObject(j).getJSONArray("sub");

								if (countyJsonArray != null) {
									for (int k = 0; k < countyJsonArray
											.length(); k++) {
										countySet.add(countyJsonArray
												.getJSONObject(k).getString(
														"name"));
									}
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							areaMap.put(areaStr, countySet);
						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dizhi.put(proviceStr, areaMap);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @param id
	 * @param company
	 */
	protected void fetchlogisticsInfo(String id, String company) {
		// TODO Auto-generated method stub
		pd = new ProgressDialog(this);
		pd.setMessage("正在努力查询中...");
		pd.show();
		HttpTaskListener logisticsInfoListener = new HttpTaskListener(
				HttpListener.GET_WULIUINFO);
		LogisticsInfoUtils.getLogisticsInfo(StringUtils.trim(id),
				StringUtils.trim(company), logisticsInfoListener);

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

		if (data == null)
			return;

		if (StringUtils.equals(data.getAction(),
				Actions.chose_logistics_company))
			switch (resultCode) {
			case RESULT_OK: /* 取得数据，并显示于画面上 */

				Bundle bunde = data.getExtras();

				Log.d("DEBUG",
						"selectedName---" + bunde.getString("selectedName"));

				selectedLogisticsCompany = (LogisticsCompany) bunde
						.getSerializable("selected");
				if (selectedLogisticsCompany != null) {
					Log.d("DEBUG", "selectedLogisticsCompany---"
							+ selectedLogisticsCompany);

					logistics_com_edit = (EditText) findViewById(R.id.logistics_com_edit);

					logistics_com_edit.setText(selectedLogisticsCompany
							.getName());
				}

				break;
			default:
				break;
			}
		if (StringUtils.equals(data.getAction(), Actions.scan))
			if (resultCode == RESULT_OK) {
				// String codeFormat = data.getStringExtra("bracode_format");
				String codeText = data.getStringExtra("bracode_text");
				logistics_num_edit = (EditText) findViewById(R.id.logistics_num_edit);
				if (codeText != null && !"".equals(codeText)) {
					logistics_num_edit.setText(codeText);
					// fetchBookInfo(codeText);
				} else {
					Toast.makeText(this, "没有找到运单号", Toast.LENGTH_SHORT).show();
				}

			}

	}

	/**
	 * Http请求监听器，用于处理HttpAsyncTask中的响应事件
	 * 
	 * @author zhenggangji
	 * 
	 */
	private class HttpTaskListener implements HttpListener {

		int type;

		public HttpTaskListener(int type1) {
			this.type = type1;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onTaskCompleted(Object data) {
			switch (type) {
			case HttpListener.GET_WULIUINFO:
				if (pd != null)
					pd.dismiss();
				logisticsInfo = LogisticsInfoUtils.parseLogisticsInfo(String
						.valueOf(data));
				pd.hide();

				if (logisticsInfo == null) {
					Toast.makeText(Bill_search.this, "当前网络繁忙请重试",
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (logisticsInfo.getTrackInfoList() == null
						|| logisticsInfo.getTrackInfoList().size() == 0) {
					Toast.makeText(Bill_search.this, "没有快递信息，请确认单号和快递公司",
							Toast.LENGTH_SHORT).show();
					return;
				}

				logisticsInfo.setQueryTime(DateUtil.getFormatDateTime(
						new Date(), DateUtil.yyyyMMddFormat));
				LogisticInfo logisticsInfo_test = logisticInfoDao
						.query(logisticsInfo);
				Log.d("DEBUG", "logisticsInfo---" + logisticsInfo);
				Log.d("DEBUG", "logisticsInfo_test---" + logisticsInfo_test);
				if (logisticsInfo_test != null) {
					logisticInfoDao.delete(logisticsInfo_test.getId());
				}

				logisticInfoDao.insert(logisticsInfo);

				logisticInfoHistList = logisticInfoDao.queryAll();

				if (logisticInfoHistList.size() > 20) {
					logisticInfoDao.delete(logisticInfoHistList.get(
							logisticInfoHistList.size() - 1).getId());
					logisticInfoHistList
							.remove(logisticInfoHistList.size() - 1);
				}
				setHistoryStringArray(logisticInfoHistList);

				Log.d("DEBUG", "logisticInfoHistList---" + logisticInfoHistList);

				Intent i = new Intent(Bill_search.this, Bill_result.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("logisticsInfo", logisticsInfo);

				jingguo = LogisticsInfoUtils.getLocations(logisticsInfo, dizhi);
				Log.d("DEBUG", "jingguo---" + jingguo);
				bundle.putSerializable("jingguo",
						new ArrayList<String>(jingguo));
				i.putExtras(bundle);

				startActivity(i);
				break;
			case HttpListener.GOTO_MAP:
				Intent inmap = new Intent(Bill_search.this,
						PolylineActivity.class);
				logisticsInfo = LogisticsInfoUtils.parseLogisticsInfo(String
						.valueOf(data));
				jingguo = LogisticsInfoUtils.getLocations(logisticsInfo, dizhi);
				inmap.putStringArrayListExtra("locals", new ArrayList<String>(
						jingguo));
				startActivity(inmap);
				pd.hide();
				break;
			}
		}

		@Override
		public void onTaskFailed(String data) {
			// TODO Auto-generated method stub
			Toast.makeText(Bill_search.this, "error: " + data,
					Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
		logistics_com_edit.setText(logisticInfoHistList.get(position).getExpTextName());
		logistics_num_edit.setText(logisticInfoHistList.get(position).getMailNo());
	}

}
