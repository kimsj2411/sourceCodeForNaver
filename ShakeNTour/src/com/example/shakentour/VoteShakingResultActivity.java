package com.example.shakentour;

import org.jboss.netty.channel.Channel;

import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.VoteResultActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VoteShakingResultActivity extends Activity {
	 private SocketListner socketListner;
		/** Called when the activity is first created. */
	
	 public String getPhoneNumber(){
			TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
			return telManager.getLine1Number();
		}
	 @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_voteshakingresult);  
	
	}
	
	@Override
	protected void onStart()
	{
	    super.onStart();

	    final Dialog dialog = new Dialog(this);
	    dialog.setContentView(R.layout.activity_voteshakingresult);  
	    socketListner = new SocketListner("network-thread");
	   	socketListner.start();
	    final String phoneNum=getPhoneNumber();
	
	    TextView tv1=(TextView) dialog.findViewById(R.id.textView2);
        tv1.setText(getIntent().getExtras().getString("voteResult")+"¿‘¥œ±Ó?");
      final int result=getIntent().getExtras().getInt("a1");
	 System.out.println(result);
	    Button button1 = (Button) dialog.findViewById(R.id.Button01);
	    button1.setOnClickListener(new View.OnClickListener() {

	    	   public void onClick(View view) {
	    		   
	    		   Intent i = new Intent(VoteShakingResultActivity.this, MainActivity.class);
	 				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
	 				i.putExtra("KILL_APP",true);
	 				startActivity(i);       
	 				dialog.dismiss();
	 				
 				
	    	 Channel channel=SampleClient.channelFuture6.getChannel();
	 		channel.write(PbmUser.VoteResultReq.newBuilder().setPhoneNumber(phoneNum).setVote(result).build());
	 		
   	   }
	    	   });
	    	   
	    	   Button button2 = (Button) dialog.findViewById(R.id.Button02);
	   	    button2.setOnClickListener(new View.OnClickListener() {

	   	    	   public void onClick(View view) {
	   	    		Intent i = new Intent(VoteShakingResultActivity.this, VoteShakingActivity.class);
	 				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
	 				i.putExtra("KILL_APP",true);
	 				startActivity(i);       
	 				dialog.dismiss();
	      	   }
	    	});
	    dialog.show();
	
}

	}
	
