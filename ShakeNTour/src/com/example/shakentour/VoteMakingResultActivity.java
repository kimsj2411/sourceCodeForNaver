package com.example.shakentour;

import org.jboss.netty.channel.Channel;

import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.ServerHandler4;
import com.example.shakentour.network.ServerHandler6_m;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.protobuf.PbmUser.VoteMResultReq;
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

public class VoteMakingResultActivity extends Activity {
	private SocketListner socketListner;

	
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_votemakingresult);
 	socketListner = new SocketListner("network-thread");
		socketListner.start();

    final TextView tv1=(TextView) findViewById(R.id.textView1);
    tv1.setText(getIntent().getExtras().getString("text1"));
   
    final TextView tv2=(TextView) findViewById(R.id.textView2);
    tv2.setText(getIntent().getExtras().getString("text2"));
    
    final TextView tv3=(TextView) findViewById(R.id.textView3);
    tv3.setText(getIntent().getExtras().getString("text3"));
    

    final TextView tv4=(TextView) findViewById(R.id.textView4);
    tv4.setText(getIntent().getExtras().getString("text4"));
    
    final TextView tv5=(TextView) findViewById(R.id.textView5);
    tv5.setText(getIntent().getExtras().getString("text5"));
    
   
    
    final ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton1); 
	imageButton.setOnClickListener(new View.OnClickListener()
	{		
		public void onClick(View v)
		{		
		
		    final TextView tv1=(TextView) findViewById(R.id.textView1);
		    tv1.setText(getIntent().getExtras().getString("question"));
		   
		    final TextView tv2=(TextView) findViewById(R.id.textView2);
		   tv2.setText(getIntent().getExtras().getString("ans1"));
		    
		    final TextView tv3=(TextView) findViewById(R.id.textView3);
		  tv3.setText(getIntent().getExtras().getString("ans2"));
		    

		    final TextView tv4=(TextView) findViewById(R.id.textView4);
		   tv4.setText(getIntent().getExtras().getString("ans3"));
		    
		    final TextView tv5=(TextView) findViewById(R.id.textView5);
		  tv5.setText(getIntent().getExtras().getString("ans4"));


			final String phoneNum=getPhoneNumber();
			Channel channel=SampleClient.channelFuture6_m.getChannel();
			System.out.println("¿¬°á");
	 		channel.write(PbmUser.VoteMResultReq.newBuilder().setPhoneNumber(phoneNum).build());
	 		System.out.println("º¸³¿");
	 		
			Intent intent=new Intent(VoteMakingResultActivity.this,VoteResultActivity.class);
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.putExtra("KILL_APP",true);
		    intent.putExtra("question" , tv1.getText().toString());
		    intent.putExtra("ans1" , tv2.getText().toString());
		    intent.putExtra("ans2" , tv3.getText().toString());
		    intent.putExtra("ans3" , tv4.getText().toString());
		    intent.putExtra("ans4" , tv5.getText().toString());
        	startActivity(intent);
        		
		}
	});
    
	final ImageButton imageButton2 = (ImageButton)findViewById(R.id.imageButton2); 
	imageButton2.setOnClickListener(new View.OnClickListener()
	{		
		public void onClick(View v)
		{		
						
	 		Intent intent=new Intent(VoteMakingResultActivity.this,MainActivity.class);
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.putExtra("KILL_APP",true);
        	startActivity(intent);
		
		
		
		}
	});
}
 

}



