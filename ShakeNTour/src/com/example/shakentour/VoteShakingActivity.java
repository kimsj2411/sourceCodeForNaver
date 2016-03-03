package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.ServerHandler5;
import com.example.shakentour.VoteMakingActivity;
import com.example.shakentour.MainActivity;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.SampleClient;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class VoteShakingActivity extends Activity implements SensorEventListener {

  private static final int SHAKE_THRESHOLD = 5500;
  private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
  private long mShakeTimestamp;
  private int mShakeCount;
  private long lastUpdate=-1;
  private float speed; 
  private float last_x; 
  private float last_y; 
  private float last_z; 
  private float x, y, z; 
  private int c, a;
  private static final int DATA_X = SensorManager.DATA_X; 
  private static final int DATA_Y = SensorManager.DATA_Y; 
  private static final int DATA_Z = SensorManager.DATA_Z; 
  private static final int MIN_MOVEMENTS = 4;
  private SensorManager sensorManager; 
  private Sensor accelerormeterSensor; 
  public static int moveCount=0;
  TextView vi;
  String str=String.format("%d", moveCount);
  private SocketListner socketListner;
  private static SoundPool sp;
  private static int[] sound;
  
    public void onCreate(Bundle savedInstanceState) {
    	   	
    	
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_voteshaking);
    	vi=(TextView)findViewById(R.id.textView10);
    	vi.setText(str);
    	socketListner = new SocketListner("network-thread");
     	socketListner.start();
    	sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); 
    	accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    sound = new int[4];
    sound[0]=sp.load(this, R.raw.one, 1);
    sound[1]=sp.load(this, R.raw.two, 1);
    sound[2]=sp.load(this, R.raw.three, 1);
    sound[3]=sp.load(this, R.raw.four, 1);
    	
    final String question=ServerHandler5.vc_que;
    final String ans1=ServerHandler5.vc_ans1;
    final String ans2=ServerHandler5.vc_ans2;
    final String ans3=ServerHandler5.vc_ans3;
    final String ans4=ServerHandler5.vc_ans4;
    
    TextView tv1=(TextView)findViewById(R.id.textView1);
    tv1.setText(question);
    TextView tv2=(TextView)findViewById(R.id.textView2);
    tv2.setText(ans1); 
    TextView tv3=(TextView)findViewById(R.id.textView3);
    tv3.setText(ans2);
    TextView tv4=(TextView)findViewById(R.id.textView4);
    tv4.setText(ans3);
    TextView tv5=(TextView)findViewById(R.id.textView5);
    tv5.setText(ans4);
    
    }

    
     @Override 
     public void onStart() { 
         super.onStart(); 
         if (accelerormeterSensor != null) 
             sensorManager.registerListener(this, accelerormeterSensor, SensorManager.SENSOR_DELAY_GAME); 
     } 

     @Override 
       public void onStop() { 
         super.onStop(); 
         if (sensorManager != null) 
             sensorManager.unregisterListener(this); 
     } 

     @Override
 	public void onSensorChanged(SensorEvent event) {
    	if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
    	         long curTime = System.currentTimeMillis();    
    	         // only allow one update every 100ms.
    	         if ((curTime - lastUpdate) > 100) {
    	        	 long diffTime = (curTime - lastUpdate);
    	        	 lastUpdate = curTime;
    	        	 x = event.values[DATA_X];
    	        	 y = event.values[DATA_Y];
    	        	 z = event.values[DATA_Z];
    	        	 float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

    	        	 
    	        	 if (speed > SHAKE_THRESHOLD) {    
    	        		 	moveCount++;	
    	        		 	if(moveCount ==1){
    	        		 		playSound(0);
    	        		 	}else if(moveCount ==2){
    	        		 		playSound(1);
    	        		 	}else if(moveCount == 3){
    	        		 		playSound(2);
    	        		 	}else if(moveCount == 4){
    	        		 		playSound(3);
    	        		 	}
    	        		 	
    	        		 	if (moveCount > MIN_MOVEMENTS) {
    	        		 		moveCount=0;
    	        		 	}
    	        		 	if(c>0){
    	        		 		if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < curTime ) {
    	        		 				a = moveCount; 
    	        	 				
    	        		 				final TextView vi = (TextView) findViewById(R.id.textView10);
    	        		 				    	        		 				
    	        		 				Intent i = new Intent(VoteShakingActivity.this, VoteShakingResultActivity.class);
    	        		 				i.putExtra("a1" , a-1);
    	        		 				i.putExtra("voteResult" , vi.getText().toString());
    	        		 				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
    	        		 				i.putExtra("KILL_APP",true);
    	        		 				startActivity(i);
    	        		 									}
    	        		 								}    	    
    	        		 				mShakeTimestamp = lastUpdate;
    	        		 				c++;
    	        		 				str=String.format("%d", moveCount);
    	        		 				vi.setText(str);
    	        	 									} 
    	         									}
    											}
    									last_x = x;
    									last_y = y;
    									last_z = z;
     									}
     
     		
     	public void resetShakeDetection() {
     		// TODO Auto-generated method stub
     		moveCount = 0;
     	}
     	
     	@Override
     	public void onAccuracyChanged(Sensor arg0, int arg1) {
     		// TODO Auto-generated method stub
     		}


public static void playSound(int i){
	sp.play(sound[i],1,1,0,0,1);
}

}

