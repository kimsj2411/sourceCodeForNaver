package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.ServerHandler3;
import com.example.shakentour.MainActivity;
import com.example.shakentour.AttendActivity;
import com.example.shakentour.Attend3Activity;
import com.example.shakentour.network.protobuf.PbmUser;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class AttendCheckActivity extends Activity {
	private SocketListner socketListner;
	/** Called when the activity is first created. */
	
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_attendcheck);
	    // TODO Auto-generated method stub
	    socketListner = new SocketListner("network-thread");
	    socketListner.start();
	
	    
		 int Now = ServerHandler3.nowNum;
		 int total=ServerHandler3.totalNum;

		 TextView tv1=(TextView)findViewById(R.id.textView1);
		 TextView tv2=(TextView)findViewById(R.id.textView2);
		 tv1.setText(Integer.toString(Now));
		 tv2.setText(Integer.toString(total));
		 
		 ImageButton image1=(ImageButton)findViewById(R.id.imageButton1);
		 image1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
			Intent intent=new Intent(AttendCheckActivity.this,MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.putExtra("KILL_APP",true);
			startActivity(intent);
			}
		});
		
		 ImageButton image2=(ImageButton)findViewById(R.id.imageButton2);
		 image2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				final String phoneNum = getPhoneNumber();
				Channel channel=SampleClient.channelFuture3_m.getChannel();
				channel.write(PbmUser.A_MasterReq.newBuilder().setPhoneNumber(phoneNum).build() );
			}
		});
		
		
		
	}
	
	
	
	
	

	
}
