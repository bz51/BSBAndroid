package com.bsb.thread;

import java.util.List;
import java.util.TimerTask;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bsb.core.HttpUtils;
import com.bsb.core.JsonTools;
import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;
import com.bsb.entity.MsgEntity;


/**
 * 获取验证码短信，并发送
 * @author chibozhou
 *
 */
public class GetAuthCodeThread extends TimerTask{
	private SQLiteDatabase db;
	private ArrayAdapter adapter;
	

	public GetAuthCodeThread(SQLiteDatabase db, ArrayAdapter adapter) {
		super();
		this.db = db;
		this.adapter = adapter;
	}


	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.getUnSendCodeList, db,adapter);
	}

}
