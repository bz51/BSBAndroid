package com.bsb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.baidu.speechsynthesizer.SpeechSynthesizer;
import com.bsb.core.HttpUtils;
import com.bsb.core.JsonTools;
import com.bsb.core.Parameters;
import com.bsb.db.DatabaseHelper;
import com.bsb.entity.MsgEntity;
import com.bsb.thread.GetAuthCodeThread;
import com.bsb.thread.GetUnGrabMsgListThread;
import com.bsb.thread.Query30MUnGrabMsgListThread;
import com.bsb.thread.QueryUnPushNeederMsgListThread;
import com.bsb.thread.SendMsgThread;

public class MainActivity extends Activity {
	private SQLiteDatabase db;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //创建数据库bsb
        DatabaseHelper database = new DatabaseHelper(this);//这段代码放到Activity类中才用this
        db = database.getReadableDatabase();
        
        
        //开启接收短信验证码定时器
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new GetAuthCodeThread(db), new Date(),2000);//当前时间开始起动 每次间隔2秒再启动
        
        //开启发送短信定时器
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new SendMsgThread(db,this), new Date(),2000);//当前时间开始起动 每次间隔2秒再启动
        
        //开启获取大神的抢单信息定时器
        Timer timer3 = new Timer();
        timer3.scheduleAtFixedRate(new GetUnGrabMsgListThread(db), new Date(),2000);//当前时间开始起动 每次间隔2秒再启动
        
        //开启获取抢单成功短信定时器
        Timer timer4 = new Timer();
        timer4.scheduleAtFixedRate(new QueryUnPushNeederMsgListThread(db), new Date(),2000);//当前时间开始起动 每次间隔2秒再启动
        
        //开启30分钟未抢单短信计时器
        Timer timer5 = new Timer();
        timer5.scheduleAtFixedRate(new Query30MUnGrabMsgListThread(db), new Date(),1800000);//当前时间开始起动 每次间隔30秒再启动
        
    }
}
