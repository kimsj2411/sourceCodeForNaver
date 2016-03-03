package com.example.shakentour;

import com.example.shakentour.MainActivity;
import com.example.shakentour.network.ServerHandler2;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.NoticeActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Notice2Activity extends Activity {
	private SocketListner socketListner;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_notice2);
	   	    // TODO Auto-generated method stub
		   socketListner = new SocketListner("network-thread");
		   socketListner.start(); 
		 	
	final String Notice=ServerHandler2.notice;
	TextView tv=(TextView)findViewById(R.id.textView1);
	tv.setText(Notice);
    
	ImageButton image1=(ImageButton)findViewById(R.id.imageButton1);
	 image1.setOnClickListener(new OnClickListener()
	{
		public void onClick(View v)
		{
		Intent intent=new Intent(Notice2Activity.this,MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra("KILL_APP",true);
		startActivity(intent);
		}
	});
		    
	}
	   
}

