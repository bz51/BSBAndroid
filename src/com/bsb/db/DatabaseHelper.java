package com.bsb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "bsb.db"; //数据库名称
    private static final int version = 1; //数据库版本
    
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table msg(_id INTEGER PRIMARY KEY AUTOINCREMENT, phone varchar(20) , content varchar(60) , state int);";          
        db.execSQL(sql);
        Log.i("my", "数据库已创建完毕");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
