package com.bsb.thread;

import java.util.List;
import java.util.TimerTask;

import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QueryUnPushNeederMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	private ArrayAdapter adapter;


	public QueryUnPushNeederMsgListThread(SQLiteDatabase db,
			ArrayAdapter adapter) {
		super();
		this.db = db;
		this.adapter = adapter;
	}


	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.queryUnPushNeederMsgList, db,adapter);
	}

}
