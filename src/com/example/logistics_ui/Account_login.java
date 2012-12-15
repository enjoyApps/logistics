package com.example.logistics_ui;

import java.util.Date;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taobao.top.android.TopAndroidClient;
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
