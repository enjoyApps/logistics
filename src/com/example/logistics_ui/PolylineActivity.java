/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.logistics_ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.MapController;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author zhenggang.ji
 *
 */
public class PolylineActivity extends android.support.v4.app.FragmentActivity
        implements OnSeekBarChangeListener {


    private GoogleMap mMap;

    private Geocoder geocoder = null;
    
    
    private List<String> locals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        geocoder = new Geocoder(this,Locale.CHINA);
        
        setContentView(R.layout.activity_polyline);
        
        
        

        locals = getIntent().getStringArrayListExtra("locals");
        Log.d("DEBUG", "locals---"+locals);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
    	
    	List<LatLng> ptList = new ArrayList<LatLng>();
    	
    	for(String locationName:locals){
    		if(StringUtils.isEmpty(locationName)){
    			continue;
    		}
    		try {
				List<Address> addressList = geocoder.getFromLocationName(  
				        locationName, 1);
				
				if (addressList != null && addressList.size() > 0) {
					
					
                    double lat = (addressList.get(0).getLatitude());  
                    double lng = (addressList.get(0).getLongitude());  
                    
                    LatLng pt = new LatLng(lat, lng);
                    ptList.add(pt);
  
                } 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	Log.d("DEBUG", "ptList---"+ptList);
    	
    	LatLng[] latLngArray = ptList.toArray(new LatLng[ptList.size()]);
    	
    	Log.d("DEBUG", "latLngArray---"+latLngArray);

        // A simple polyline with the default options from Melbourne-Adelaide-Perth.
        mMap.addPolyline((new PolylineOptions())
                .add(latLngArray).geodesic(true).color(Color.BLUE).width(5));
        
        mMap.setMyLocationEnabled(true);
        
        
        // Move the map so that it is centered on the mutable polyline.
        if(ptList!=null && ptList.size()>0){
        	mMap.moveCamera(CameraUpdateFactory.newLatLng(ptList.get(0)));
        	mMap.animateCamera(CameraUpdateFactory.zoomTo(7), 3000, null);
        	for (int i = 0; i < latLngArray.length; i++) {
        		mMap.animateCamera(CameraUpdateFactory.newLatLng(ptList.get(i)),2000, null);
			}
        }
        
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
        
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }
}
