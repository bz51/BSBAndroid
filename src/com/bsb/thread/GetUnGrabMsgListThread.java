package com.bsb.thread;

import java.util.List;
import java.util.TimerTask;

import com.bsb.core.HttpUtils;
import com.bsb.core.JsonTools;
import com.bsb.core.MsgTools;
import com.bsb.core.Parameters;
import com.bsb.entity.MsgEntity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 获取未抢单短信
 * @author chibozhou
 *
 */
public class GetUnGrabMsgListThread extends TimerTask {
	private SQLiteDatabase db;
	
	public GetUnGrabMsgListThread(SQLiteDatabase db) {
		this.db = db;
	}
	
	@Override
	public void run() {
		MsgTools.getMsgSaveDB(Parameters.getUnGrabMsgList, db);
	}

}
