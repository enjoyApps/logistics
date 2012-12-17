package com.example.logistics_ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.logistics_ui.util.HttpListener;
import com.example.logistics_ui.util.LogisticsInfoUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.GeoPoint;

/**
 * @author zhenggang.ji
 * 
 */
public class PolylineActivity extends android.support.v4.app.FragmentActivity
		implements OnSeekBarChangeListener {

	private GoogleMap mMap;

	private Geocoder geocoder = null;

	private List<String> locals;

	List<LatLng> ptList = null;
	
	public static int size = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		size = 0;
		size1=0;

		geocoder = new Geocoder(this, Locale.CHINA);

		setContentView(R.layout.activity_polyline);

		ptList = new ArrayList<LatLng>();

		locals = getIntent().getStringArrayListExtra("locals");
		Log.d("DEBUG", "locals---" + locals);
		setUpMapIfNeeded();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {

		for (String locationName : locals) {
			if (StringUtils.isEmpty(locationName)) {
				continue;
			}
			size = size + 1;
		}
		
		for (String locationName : locals) {
			if (StringUtils.isEmpty(locationName)) {
				continue;
			}
			LogisticsInfoUtils.getLogisticsInfoNoMap(locationName,
					new HttpTaskListener(HttpListener.MAP_INI));
		}

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// Don't do anything here.
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// Don't do anything here.
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

	}

	public static JSONObject getLocationInfo(String address) {
		StringBuilder stringBuilder = new StringBuilder();
		try {

			address = address.replaceAll(" ", "%20");

			HttpPost httppost = new HttpPost(
					"http://maps.google.com/maps/api/geocode/json?address="
							+ address + "&sensor=false&region=cn");
			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			stringBuilder = new StringBuilder();

			response = client.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = new JSONObject(stringBuilder.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	public static GeoPoint getLatLong(JSONObject jsonObject) {

		Double lon = new Double(0);
		Double lat = new Double(0);

		try {

			lon = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location")
					.getDouble("lng");

			lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location")
					.getDouble("lat");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
	}
	
	private static int size1 = 0;

	/**
	 * Http请求监听器，用于处理HttpAsyncTask中的响应事件
	 * 
	 * @author zhenggangji
	 * 
	 */
	private class HttpTaskListener implements HttpListener {
		
		
		private synchronized void add(){
			size1 = size1 + 1;
		}

		int type;

		public HttpTaskListener(int type1) {
			this.type = type1;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onTaskCompleted(Object data) {
			add();
			if (data == null)
				return;

			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject = new JSONObject(String.valueOf(data));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			GeoPoint p = PolylineActivity.getLatLong(jsonObject);
			double lat = p.getLatitudeE6() / 1E6;
			double lng = p.getLongitudeE6() / 1E6;

			LatLng pt = new LatLng(lat, lng);
			ptList.add(pt);
			if(size1 == PolylineActivity.size) {
				Log.d("DEBUG", "ptList---" + ptList);

				LatLng[] latLngArray = ptList.toArray(new LatLng[ptList.size()]);

				Log.d("DEBUG", "latLngArray---" + latLngArray);

				// A simple polyline with the default options from
				// Melbourne-Adelaide-Perth.
				mMap.addPolyline((new PolylineOptions()).add(latLngArray)
						.geodesic(true).color(Color.BLUE).width(5));

				mMap.setMyLocationEnabled(true);

				// Move the map so that it is centered on the mutable polyline.
				if (ptList != null && ptList.size() > 0) {
					mMap.moveCamera(CameraUpdateFactory.newLatLng(ptList.get(0)));
					mMap.animateCamera(CameraUpdateFactory.zoomTo(10.9F), 300, null);
					for (int i = 0; i < latLngArray.length; i++) {
						mMap.animateCamera(
								CameraUpdateFactory.newLatLng(ptList.get(i)), 200,
								null);
					}
				}
			}
		}

		@Override
		public void onTaskFailed(String data) {
			// TODO Auto-generated method stub
			add();
		}

		@Override
		public int getType() {
			// TODO Auto-generated method stub
			return this.type;
		}
	}

}
