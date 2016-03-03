package com.example.shakentour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Attend2Activity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_attend2);
	    // TODO Auto-generated method stub
	
	    ImageButton image1=(ImageButton)findViewById(R.id.imageButton1);
		 image1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
			Intent intent=new Intent(Attend2Activity.this,MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.putExtra("KILL_APP",true);
			startActivity(intent);
			}
		});
		
	}

}
