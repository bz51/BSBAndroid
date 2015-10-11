package com.bsb.thread;

import java.util.TimerTask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;

public class QueryUnPushNeederMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	private ArrayAdapter adapter;
	private Context context;


	public QueryUnPushNeederMsgListThread(SQLiteDatabase db,ArrayAdapter adapter,Context context) {
		super();
		this.db = db;
		this.adapter = adapter;
		this.context = context;
	}


	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.queryUnPushNeederMsgList, db,adapter,context);
	}

}
