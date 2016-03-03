package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.protobuf.PbmUser;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class RoomActivity extends Activity {
	private SocketListner socketListner;
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room);
		socketListner = new SocketListner("network-thread");
    	socketListner.start();
    	
		final String phoneNum = getPhoneNumber();
		TextView t1 = (TextView)findViewById(R.id.TextView01);
		t1.setText(phoneNum);
		
		
		ImageButton imageButton1 = (ImageButton)findViewById(R.id.imageButton2); 
		imageButton1.setOnClickListener(new View.OnClickListener()
				{		
				public void onClick(View v)
					{		
					EditText et=(EditText)findViewById(R.id.editText1);	  
					final String rn=et.getText().toString();
					
					Intent intent = new Intent(RoomActivity.this,MainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
		
					startActivity(intent);
	
					Channel channel=SampleClient.channelFuture.getChannel();
					channel.write(PbmUser.LoginReq.newBuilder().setPhoneNumber(phoneNum).setRoomNumber(rn).build() );
			
					
				
					}
				});
	
	}
}
