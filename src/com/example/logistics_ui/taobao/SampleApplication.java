/**
 * 
 */
package com.example.logistics_ui.taobao;

import com.taobao.top.android.TopAndroidClient;
//import com.taobao.top.android.TopAndroidClient.Env;

import android.app.Application;


/**
 * @author zhenggangji
 *
 */
public class SampleApplication extends Application {
	@Override  
	public void onCreate() {  
		super.onCreate();
		TopAndroidClient.registerAndroidClient(getApplicationContext(), "21323956", "32c7ebdb893efe5e0000e0130ffa280b", "com.taobao.top.android.sample://authresule");
		//TopAndroidClient.registerAndroidClient(getApplicationContext(), "519255", "988d57871c1fb8767a9b0875b28e5c17", "callback://authresult",Env.DAILY);
		
	}
}
