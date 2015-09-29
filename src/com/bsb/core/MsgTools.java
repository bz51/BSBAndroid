package com.bsb.core;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bsb.entity.MsgEntity;

public class MsgTools {
	
	/**
	 * 发送请求，获取json，转成MsgEntity，并存入DB
	 * @param url
	 */
	public static void getMsgSaveDB(String url,SQLiteDatabase db, ArrayAdapter adapter){
		Log.i("my", "线程"+url+"已启动……");
//		adapter.add("线程"+url+"已启动……");
//		adapter.notifyDataSetChanged();
		
		if(url==null || url.equals("") || db==null)
			return;
		
		//发送请求，获取json
		String json = HttpUtils.getJsonContent(url);
		if(json==null || json.equals("error")){
			//若请求失败，没事，反正过两秒又重新获取
		}
		
		Log.i("my", "获取json="+json);
		
		//json——>List<MsgEntity>
		List<MsgEntity> list = JsonTools.json2MsgEntity(json);
		
		//将List<MsgEntity>存入sqlite
		if(list!=null)
			for(MsgEntity e : list){
				ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据
				cv.put("phone",e.getPhone());
				cv.put("content",e.getContent());
				cv.put("state",0);
				db.insert("msg",null,cv);//执行插入操作
		}
	}
}
