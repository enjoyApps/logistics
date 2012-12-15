package com.example.logistics_ui.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.logistics_ui.R;

public class NavBarUtil {
	
	public static final int HEADER_BACK_STYLE = 1;
	public static final int HEADER_HOME_STYLE = 2;
	
	private TextView mTitleText;
	private ImageButton mBackButton;
	
	public NavBarUtil(int style, Context context){
		initHeader(style, (Activity) context);		
	}
	
	/**
	 * 设置标题
	 * 
	 * @param resource
	 *            R.string.xxx
	 */
	public void setHeaderTitle(String title) {
		
		if (null != mTitleText) {
			mTitleText.setText(title);
			TextPaint tp = mTitleText.getPaint();
			tp.setFakeBoldText(true); // 中文粗体
		}
	}
	
	/**
	 * 添加[返回]按钮
	 * 
	 * @param activity
	 */
	private void addBackButtonTo(final Activity activity) {
		
		mBackButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Go back to previous activity
				activity.finish();
			}
		});
	}
	
	private void hideBackButton(final Activity activity) {
		
		mBackButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Go back to previous activity
				activity.finish();
			}
		});
	}
	
	public void initHeader(int style, final Activity activity){
		switch(style){
		case HEADER_BACK_STYLE:
			mTitleText = (TextView) activity.findViewById(R.id.top_title);
			mBackButton = (ImageButton) activity.findViewById(R.id.top_back);
			addBackButtonTo(activity);
			break;
		case HEADER_HOME_STYLE:
			mTitleText = (TextView) activity.findViewById(R.id.top_title);
			mBackButton = (ImageButton) activity.findViewById(R.id.top_back);
			hideBackButton(activity);
			break;
		}
	}
	
}
