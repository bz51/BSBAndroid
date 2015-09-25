package com.bsb.core;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bsb.entity.MsgEntity;

public class JsonTools {
	
	public static List<MsgEntity> json2MsgEntity(String json){
		if(json==null || json.equals(""))
			return null;
		
		else{
			try {
				JSONObject jsonObj = new JSONObject(json);
				JSONArray array = jsonObj.getJSONArray("msgList");
				List<MsgEntity> list = new ArrayList<MsgEntity>();
				if(array!=null)
					for(int i=0;i<array.length();i++){
						MsgEntity e = new MsgEntity();
						e.setContent(new JSONObject(array.getString(i)).getString("content"));
						e.setPhone(new JSONObject(array.getString(i)).getString("phone"));
						list.add(e);
					}
				return list;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
