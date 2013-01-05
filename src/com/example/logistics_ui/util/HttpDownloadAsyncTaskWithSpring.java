package com.example.logistics_ui.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.util.Log;

import com.example.logistics_ui.model.LogisticInfo;

public class HttpDownloadAsyncTaskWithSpring extends AsyncTask<Void, Void, List<LogisticInfo>> {
	
	protected static final String TAG = HttpDownloadAsyncTaskWithSpring.class.getSimpleName();

	@Override
	protected void onPreExecute() {
		//showLoadingProgressDialog();
	}
	
	private String getUrl(String... params) {
		String url = "http://api.ickd.cn/?com="+params[0]+"&nu="+params[1]+"&id=D2BCB3066D4D77EC5567712ECA489FC8&type=json&encode=utf8";
		return url;
	}
	
	

	@Override
	protected List<LogisticInfo> doInBackground(Void... params) {
		try {
			// The URL for making the GET request
			final String url = getUrl() + "/states";

			// Set the Accept header for "application/json"
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);

			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			// Perform the HTTP GET request
			ResponseEntity<LogisticInfo[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					LogisticInfo[].class);

			// convert the array to a list and return it
			return Arrays.asList(responseEntity.getBody());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}

		return Collections.emptyList();
	}

	@Override
	protected void onPostExecute(List<LogisticInfo> result) {
		//dismissProgressDialog();
		//refreshStates(result);
	}

}
