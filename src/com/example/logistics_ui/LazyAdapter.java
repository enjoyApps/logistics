package com.example.logistics_ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	 
//    private Activity activity;
//    private ArrayList<HashMap<String, String>> data;
//    private static LayoutInflater inflater=null;
// 
//    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
//        activity = a;
//        data=d;
//        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
// 
//    public int getCount() {
//        return data.size();
//    }
// 
//    public Object getItem(int position) {
//        return position;
//    }
// 
//    public long getItemId(int position) {
//        return position;
//    }
// 
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View vi=convertView;
//        if(convertView==null)
//            vi = inflater.inflate(R.layout.list_row, null);
// 
//        TextView title = (TextView)vi.findViewById(R.id.title); // title
//        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
//        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
//        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
// 
//        HashMap<String, String> song = new HashMap<String, String>();
//        song = data.get(position);
// 
//        // Setting all values in listview
//        title.setText(song.get(CustomizedListView.KEY_TITLE));
//        artist.setText(song.get(CustomizedListView.KEY_ARTIST));
//        duration.setText(song.get(CustomizedListView.KEY_DURATION));
//        return vi;
//    }
}