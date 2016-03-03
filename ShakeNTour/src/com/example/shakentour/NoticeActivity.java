package com.example.shakentour;

import com.example.shakentour.MainActivity;
import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.protobuf.PbmUser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class NoticeActivity extends Activity {
	private SocketListner socketListner;
	
	  
	    public String getPhoneNumber(){
			TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
			return telManager.getLine1Number();
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		
		socketListner = new SocketListner("network-thread");
		socketListner.start();
		
	
	
    final EditText editText = (EditText) findViewById(R.id.editText); 
    final TextView tv=(TextView)findViewById(R.id.textView1);
    
    ImageButton image1=(ImageButton)findViewById(R.id.imageButton1);
	image1.setOnClickListener(new OnClickListener()
	{
		public void onClick(View v)
		{
		Intent intent=new Intent(NoticeActivity.this,MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra("KILL_APP",true);
		startActivity(intent);
		}
	});
    ImageButton send = (ImageButton)findViewById(R.id.send_button); 

    final String phoneNum = getPhoneNumber();
    	    send.setOnClickListener(new View.OnClickListener() { 
               
    	    	@Override
                public void onClick(View view) { 
 
                    String message = editText.getText().toString(); 
                    tv.setText(message);
                    Channel channel=SampleClient.channelFuture2.getChannel();
    				channel.write(PbmUser.NoticeReq.newBuilder().setPhoneNumber(phoneNum).setNotice(message).build());
                   
                } 
            }); 
  
    

	}


}
