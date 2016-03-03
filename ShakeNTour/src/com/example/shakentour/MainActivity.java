package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.example.shakentour.network.ServerHandler2;
import com.example.shakentour.Attend3Activity;
import com.example.shakentour.network.ServerHandler2_m;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.ServerHandler5_m;
import com.example.shakentour.network.protobuf.PbmUser;

public class MainActivity extends Activity {
	private SocketListner socketListner;
	
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		socketListner = new SocketListner("network-thread");
		socketListner.start();
		
		final String phoneNum = getPhoneNumber();
		
		ImageButton image1=(ImageButton)findViewById(R.id.imageButton1);
		image1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
			Intent intent=new Intent(MainActivity.this,RoomActivity.class);
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
			 Channel channel=SampleClient.channelFuture2.getChannel();
			 channel.write(PbmUser.NoticeReq.newBuilder().setPhoneNumber(phoneNum).build());
				
				final String Master=ServerHandler2.master;
				
				if(Master=="MASTER_SUCCESS"){
					Intent intent=new Intent(MainActivity.this,NoticeActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
					startActivity(intent);
				} else if(Master=="MASTER_FAIL"){
					Intent intent=new Intent(MainActivity.this,Notice2Activity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
					startActivity(intent);
				}
			}
		});
		
		ImageButton image3=(ImageButton)findViewById(R.id.imageButton3);
		image3.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Channel channel=SampleClient.channelFuture2_m.getChannel();
				channel.write(PbmUser.N_MasterReq.newBuilder().setPhoneNumber(phoneNum).build());	
				
				final String master=ServerHandler2_m.master;
				
				
				if(master=="MASTER_SUCCESS"){
					Intent intent=new Intent(MainActivity.this,Attend3Activity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
					startActivity(intent);
				} else if(master=="MASTER_FAIL"){
					Intent intent=new Intent(MainActivity.this,AttendActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
					startActivity(intent);
			}
			}
		});
		
		
		
		
		ImageButton image4=(ImageButton)findViewById(R.id.imageButton4);
		image4.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				
				Channel channel=SampleClient.channelFuture5_m.getChannel();
				channel.write(PbmUser.V_MasterReq.newBuilder().setPhoneNumber(phoneNum).build());	
				
				final String master=ServerHandler5_m.MasterCheck;
				//final String master1=ServerHandler4.voteMake;
				if(master=="MASTER_SUCCESS"){
					Intent intent=new Intent(MainActivity.this,VoteMakingActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
					startActivity(intent);
				} else if(master=="MASTER_FAIL"){
					Channel channel2=SampleClient.channelFuture5.getChannel();
					channel2.write(PbmUser.VoteReq.newBuilder().setPhoneNumber(phoneNum).build());	
					//if(master1=="VOTEMAKE_SUCCESS"){
					Intent intent=new Intent(MainActivity.this,VoteShakingActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.putExtra("KILL_APP",true);
					startActivity(intent);
					//} else if(master1=="VOTEMAKE_FAIL"){
					//Toast toast = Toast.makeText(MainActivity.this, "투표 용지가 아직 생성되지 않았습니다.", Toast.LENGTH_SHORT); 
					//toast.show(); 					
										
					}
				}
			
		});
		
		
	}
	
	@Override
	public void onBackPressed() {

		// TODO Auto-generated method stub

		// super.onBackPressed(); //지워야 실행됨



		Builder d = new AlertDialog.Builder(this);
		d.setMessage("정말 종료하시겠습니까?");
		d.setPositiveButton("예", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// process전체 종료
				finish();
			}
		});

		d.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		d.show();
	} 
}
