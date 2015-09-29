package com.bsb.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baidu.speechsynthesizer.SpeechSynthesizer;
import com.bsb.MySpeechSynthesizerListener;
import com.bsb.core.SMSTool;
import com.bsb.entity.MsgEntity;

/**
 * 专门发送短信
 * @author chibozhou
 *
 */
public class SendMsgThread extends TimerTask {
	private SQLiteDatabase db;
	private Context context;
	private ArrayAdapter adapter;
	
	


	public SendMsgThread(SQLiteDatabase db, Context context,
			ArrayAdapter adapter) {
		super();
		this.db = db;
		this.context = context;
		this.adapter = adapter;
	}




	@Override
	public void run() {

        Log.i("my", "发送短信线程已启动");
//        adapter.add("发送短信线程已启动……");
//		adapter.notifyDataSetChanged();
		
		//从sqlite中读出所有未发送短信
		Cursor c = db.rawQuery("select * from msg where state=?",new String[]{"0"});
		
		List<MsgEntity> list = new ArrayList<MsgEntity>();
		
		if(c.moveToFirst()){//判断游标是否为空
			while(c.moveToNext()){
		    	MsgEntity entity = new MsgEntity();
//		        c.move(i);//移动到指定记录
		        entity.setId( c.getInt(c.getColumnIndex("_id")) );
		        entity.setPhone( c.getString(c.getColumnIndex("phone")) );
		        entity.setContent( c.getString(c.getColumnIndex("content")) );
		        list.add(entity);
		    }
		}
		
		//逐条发送，发完一条就立即更新数据库，将该条标注为已发送
		for(MsgEntity e : list){
			//发送短信
			sendSMS(e.getPhone(),e.getContent());
			
			Log.i("my", "已发送："+e.getPhone()+":"+e.getContent());
			// 注：第二个参数当前请传入任意非空字符串即可
			SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(context, "holder", new MySpeechSynthesizerListener());
			// 注：your-apiKey 和 your-secretKey 需要换成在百度开发者中心注册应用得到的对应值
			speechSynthesizer.setApiKey("QN9uYivTwHgjDCgyVntlUG0q", "7c68f3f2fd3ccdf69ae9b0b70bbc6d3e");
			speechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER,SpeechSynthesizer.SPEAKER_FEMALE);
			speechSynthesizer.speak(e.getContent());
			
			//发送成功的话，更新该短信状态
			String sql = "update [msg] set state = 1 where _id="+e.getId();//修改的SQL语句
			db.execSQL(sql);//执行修改
			
		}
		
	}
	
	/** 
     * 直接调用短信接口发短信 
     * @param phoneNumber 
     * @param message 
     */  
	public void sendSMS(String phoneNumber,String message){  
        //获取短信管理器   
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();  
        //拆分短信内容（手机短信长度限制）    
        List<String> divideContents = smsManager.divideMessage(message);   
        for (String text : divideContents) {    
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);    
        }  
    }  

}
