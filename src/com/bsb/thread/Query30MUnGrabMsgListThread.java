package com.bsb.thread;

import java.util.TimerTask;

import android.database.sqlite.SQLiteDatabase;

import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;

public class Query30MUnGrabMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	
	public Query30MUnGrabMsgListThread(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.query30MUnGrabMsgList, db);
	}

}
