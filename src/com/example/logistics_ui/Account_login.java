package com.example.logistics_ui;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taobao.top.android.TopAndroidClient;
import com.taobao.top.android.api.TopTqlListener;
import com.taobao.top.android.auth.AccessToken;
import com.taobao.top.android.auth.AuthActivity;
import com.taobao.top.android.auth.AuthError;
import com.taobao.top.android.auth.AuthException;
import com.taobao.top.android.auth.AuthorizeListener;

public class Account_login extends AuthActivity {
	
	private static final String TAG = "MainActivity";
	private TextView topResult;
	private ImageButton to_login_taobao = null;
	private TopAndroidClient client=TopAndroidClient.getAndroidClientByAppKey("21323956");
	
	private String nick;
	private Long userId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_login);
		
		to_login_taobao=(ImageButton)this.findViewById(R.id.to_login_taobao);
        //topResult = (TextView)this.findViewById(R.id.topResult);
        if(nick!=null){
        	
        	String ql="{select tid,order_code,seller_nick,buyer_nick,delivery_start,delivery_end,out_sid,item_title,receiver_name, created,modified,status,type,freight_payer,seller_confirm,company_name from logistics.trace where buyer_nick="+nick+" and start_created=2012-12-01 00:00:00}";
        	Log.d("DEBUG",
					"TOQ ql---" + ql);
        	client.tql(ql, userId, new TopTqlListener(){

				@Override
				public void onComplete(String result) {
					Log.d("DEBUG",
							"TOQ result---" + result);
					
					if (TextUtils.isEmpty(result)) {
						return;
					}
//					String[] array = result.split("\r\n");
//					String title = "";
//					for (int i = 0; i < array.length; i++) {
//						JSONObject json;
//						try {
//							json = new JSONObject(array[i]);
//							JSONObject j = json
//									.optJSONObject("item_get_response");
//							if (j != null) {
//								j = j.optJSONObject("item");
//								if (j != null) {
//									String t = j.optString("title");
//									title += t;
//									title += "\n";
//								}
//							}
//						} catch (JSONException e) {
//							Log.e(TAG, e.getMessage(), e);
//						}
//
//					}

					//setTopResultText(title);

				}

				@Override
				public void onException(Exception e) {
					// TODO Auto-generated method stub
					
				}}, true);


        	
        	//this.setTopResultText(nick);
        }
        to_login_taobao.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
//				Uri uri = Uri.parse("https://oauth.taobao.com/authorize?response_type=token&view=wap&redirect_uri=sample://authresult&client_id="+TopConfig.APPKEY);  
//				Intent it = new Intent(Intent.ACTION_VIEW, uri);
//				startActivity(it);
				client.authorize(Account_login.this);
				Account_login.this.finish();
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_account_login, menu);
		return true;
	}

	@Override
	protected TopAndroidClient getTopAndroidClient() {
		return client;
	}

	@Override
	protected AuthorizeListener getAuthorizeListener() {
		return new AuthorizeListener(){

			@Override
			public void onComplete(AccessToken accessToken) {
				Log.d(TAG, "callback");
				String id=accessToken.getAdditionalInformation().get(AccessToken.KEY_SUB_TAOBAO_USER_ID);
				if(id==null){
					id=accessToken.getAdditionalInformation().get(AccessToken.KEY_TAOBAO_USER_ID);
				}
				Account_login.this.userId=Long.parseLong(id);
				nick=accessToken.getAdditionalInformation().get(AccessToken.KEY_SUB_TAOBAO_USER_NICK);
				if(nick==null){
					nick=accessToken.getAdditionalInformation().get(AccessToken.KEY_TAOBAO_USER_NICK);
				}
				String r2_expires = accessToken.getAdditionalInformation().get(
						AccessToken.KEY_R2_EXPIRES_IN);
				Date start = accessToken.getStartDate();
				Date end = new Date(start.getTime()
						+ Long.parseLong(r2_expires) * 1000L);
			}

			@Override
			public void onError(AuthError e) {
				Log.e(TAG, e.getErrorDescription());
				
			}

			@Override
			public void onAuthException(AuthException e) {
				Log.e(TAG, e.getMessage());
				
			}};
	}

}
