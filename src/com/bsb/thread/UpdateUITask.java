package com.bsb.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

public class UpdateUITask extends AsyncTask<String, Integer, String>{
	private Context context;
//	private ListView listview;
	private String json;
	
//	public UpdateUITask(Context context, ListView listview,String json) {
//		super();
//		this.context = context;
//		this.listview = listview;
//		this.json = json;
//	}
	
	public UpdateUITask(Context context, String json) {
		super();
		this.context = context;
		this.json = json;
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
	}
}
