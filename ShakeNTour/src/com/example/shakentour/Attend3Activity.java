package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.AttendCheckActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Attend3Activity extends Activity {
private SocketListner socketListner ;
	/** Called when the activity is first created. */
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_attend3);
	    socketListner = new SocketListner("network-thread");
	    socketListner.start();
	
	    ImageButton image=(ImageButton)findViewById(R.id.imageButton1);
		 image.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
			final String phoneNum=getPhoneNumber();
			
        	Intent intent=new Intent(Attend3Activity.this,AttendCheckActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.putExtra("KILL_APP",true);
			startActivity(intent);  
			Channel channel=SampleClient.channelFuture3.getChannel();
           	channel.write(PbmUser.AttendReq.newBuilder().setPhoneNumber(phoneNum).build());
           	
			}
		});
	    
	      // TODO Auto-generated method stub
	}

}
