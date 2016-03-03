package com.example.shakentour;

import com.example.shakentour.network.ServerHandler6_m;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.VoteMakingResultActivity;
import com.example.shakentour.VoteMakingActivity;
import com.example.shakentour.VoteShakingResultActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class VoteResultActivity extends Activity {
	private SocketListner socketListner;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_voteresult);
	    // TODO Auto-generated method stub
	    socketListner = new SocketListner("network-thread");
   		socketListner.start();
	    
   		int ans1=ServerHandler6_m.vc_ans1;
 	    int ans2=ServerHandler6_m.vc_ans2;
 	    int ans3=ServerHandler6_m.vc_ans3;
 	    int ans4=ServerHandler6_m.vc_ans4;
 	    
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
	    
 	    
		final TextView tv6=(TextView) findViewById(R.id.textView10);
 	    final TextView tv7=(TextView) findViewById(R.id.TextView02);
 	    final TextView tv8=(TextView) findViewById(R.id.TextView03);
 	    final TextView tv9=(TextView) findViewById(R.id.TextView04);
 	    
 	    tv6.setText(Integer.toString(ans1));
 	    tv7.setText(Integer.toString(ans2));
 	    tv8.setText(Integer.toString(ans3));
 	    tv9.setText(Integer.toString(ans4));
 	    
 	   final ImageButton imageButton2 = (ImageButton)findViewById(R.id.imageButton2); 
 		imageButton2.setOnClickListener(new View.OnClickListener()
 		{		
 			public void onClick(View v)
 			{		
 							
 		 		Intent intent=new Intent(VoteResultActivity.this,MainActivity.class);
 	        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
 				intent.putExtra("KILL_APP",true);
 	        	startActivity(intent);
 			
 			
 			
 			}
 		});
	}

}
