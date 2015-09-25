package com.bsb.thread;

import java.util.TimerTask;

import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;

import android.database.sqlite.SQLiteDatabase;

public class QueryUnPushNeederMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	
	public QueryUnPushNeederMsgListThread(SQLiteDatabase db) {
		this.db = db;
	}
	
	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.queryUnPushNeederMsgList, db);
	}

}
