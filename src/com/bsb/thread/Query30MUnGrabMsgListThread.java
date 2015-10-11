package com.bsb.thread;

import java.util.List;
import java.util.TimerTask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;

public class Query30MUnGrabMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	private ArrayAdapter adapter;
	private Context context;
	

	public Query30MUnGrabMsgListThread(SQLiteDatabase db, ArrayAdapter adapter,Context context) {
		super();
		this.db = db;
		this.adapter = adapter;
		this.context = context;
	}


	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.query30MUnGrabMsgList, db,adapter,context);
	}

}
