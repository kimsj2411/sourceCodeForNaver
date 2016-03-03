package com.example.shakentour;

import org.jboss.netty.channel.Channel;
import com.example.shakentour.network.SocketListner;
import com.example.shakentour.network.SampleClient;
import com.example.shakentour.network.protobuf.PbmUser;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AttendActivity extends Activity implements SensorEventListener {
	private static final int SHAKE_THRESHOLD = 3000;
	private long lastUpdate=-1;
	private float speed; 
	private float last_x; 
	private float last_y; 
	private float last_z; 
	private float x, y, z; 
	private static final int DATA_X = SensorManager.DATA_X; 
	private static final int DATA_Y = SensorManager.DATA_Y; 
	private static final int DATA_Z = SensorManager.DATA_Z; 
	private SensorManager sensorManager; 
	private Sensor accelerormeterSensor; 
	public static int moveCount=0;
	private SocketListner socketListner;
	
	public String getPhoneNumber(){
		TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		return telManager.getLine1Number();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_attend);
	    	socketListner = new SocketListner("network-thread");
	   		socketListner.start();
	   		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); 
	    	accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
	    	    final String phoneNum = getPhoneNumber();
	            float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;
	            if (speed > SHAKE_THRESHOLD) {    
	            		//	int p=0;
	            	moveCount++;
	            
	            	if(moveCount>3){
	            	
	            		Intent intent=new Intent(AttendActivity.this,Attend2Activity.class);
		            	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
						intent.putExtra("KILL_APP",true);
		            	startActivity(intent);
	            	onStop();
	            	Channel channel=SampleClient.channelFuture3.getChannel();
	            	channel.write(PbmUser.AttendReq.newBuilder().setPhoneNumber(phoneNum).build());
	            	
	            	}
	    	    }    	    
	      }
	         last_x = x;
	         last_y = y;
	         last_z = z;
	    	}   
	}
	     public void resetShakeDetection() {
	     		// TODO Auto-generated method stub
	     		onStop();
	     	}
	     	

	     @Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
	     

	
}
