package com.bsb.thread;

import java.util.List;
import java.util.TimerTask;

import com.bsb.core.HttpUtils;
import com.bsb.core.JsonTools;
import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;
import com.bsb.entity.MsgEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 获取未抢单短信
 * @author chibozhou
 *
 */
public class GetUnGrabMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	private ArrayAdapter adapter;
	private Context context;
	
	public GetUnGrabMsgListThread(SQLiteDatabase db, ArrayAdapter adapter,Context context) {
		super();
		this.db = db;
		this.adapter = adapter;
		this.context = context;
	}

	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.getUnGrabMsgList, db,adapter,context);
	}

}
