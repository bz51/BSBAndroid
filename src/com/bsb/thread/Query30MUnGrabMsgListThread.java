package com.bsb.thread;

import java.util.List;
import java.util.TimerTask;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;

public class Query30MUnGrabMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	private ArrayAdapter adapter;
	

	public Query30MUnGrabMsgListThread(SQLiteDatabase db, ArrayAdapter adapter) {
		super();
		this.db = db;
		this.adapter = adapter;
	}


	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.query30MUnGrabMsgList, db,adapter);
	}

}
