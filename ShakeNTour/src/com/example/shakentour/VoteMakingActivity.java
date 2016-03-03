package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.ServerHandler4;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.protobuf.PbmUser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class VoteMakingActivity extends Activity {
	private SocketListner socketListner;
	
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_votemaking);
		socketListner = new SocketListner("network-thread");
		socketListner.start();
		
		final ImageButton imageButton2 = (ImageButton)findViewById(R.id.imageButton2); 
		imageButton2.setOnClickListener(new View.OnClickListener()
		{		
			public void onClick(View v)
			{		
				save();
			}
		});
	}
	
public void save() {
   
    final EditText v1 = (EditText) findViewById(R.id.editText1);
    final EditText v2 = (EditText) findViewById(R.id.editText2);
    final EditText v3 = (EditText) findViewById(R.id.editText3);
    final EditText v4 = (EditText) findViewById(R.id.editText4);
    final EditText v5 = (EditText) findViewById(R.id.editText5);
    
    String q=v1.getText().toString();
    String a1=v2.getText().toString();
    String a2=v3.getText().toString();
    String a3=v4.getText().toString();
    String a4=v5.getText().toString();
    final String phoneNum = getPhoneNumber();
   
    Intent i = new Intent(VoteMakingActivity.this, VoteMakingResultActivity.class);
    i.putExtra("text1" , v1.getText().toString());
    i.putExtra("text2" , v2.getText().toString());
    i.putExtra("text3" , v3.getText().toString());
    i.putExtra("text4" , v4.getText().toString());
    i.putExtra("text5" , v5.getText().toString());
    startActivity(i);
    
    Channel channel=SampleClient.channelFuture4.getChannel();
	channel.write(PbmUser.VoteMakeReq.newBuilder().setPhoneNumber(phoneNum).setQuestion(q).setAns1(a1).setAns2(a2).setAns3(a3).setAns4(a4).build());;
    
   

	}
	

}



