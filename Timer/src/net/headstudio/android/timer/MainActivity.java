package net.headstudio.android.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	
    private ProgressBar secondsClock, minutesClock, hoursClock;
    private TextView clockText; 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// SETUP CLOCK //
        
        secondsClock = (ProgressBar) findViewById(R.id.secondsClock);
        minutesClock = (ProgressBar) findViewById(R.id.minutesClock);
        hoursClock = (ProgressBar) findViewById(R.id.hoursClock);
        clockText = (TextView) findViewById(R.id.clocktext);

        Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
        an.setFillAfter(true);
        secondsClock.startAnimation(an);
        minutesClock.startAnimation(an);
        hoursClock.startAnimation(an);
        
        // START CLOCK //
        
        if((3230/3600)>=1)
    		hoursClock.setProgress((3230/3600)*3600);
    	else
    		hoursClock.setProgress(0);
    	
    	minutesClock.setProgress((3230-(3230/3600))/60);
    	secondsClock.setProgress((3230-(3230/3600))%60);
    	startTimer(3230);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void startTimer(final int seconds) {
	    CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 500) {
	        // 500 means, onTick function will be called at every 500 milliseconds
	        @Override
	        public void onTick(long leftTimeInMilliseconds) {
	        	int leftTimeInSecs = (int)(leftTimeInMilliseconds/1000);
	        	int hours = (int)(leftTimeInSecs/3600);
	        	leftTimeInSecs = leftTimeInSecs - (hours*3600);
	        	int mins = (int)(leftTimeInSecs/60);
	        	leftTimeInSecs = leftTimeInSecs - (mins*60);
	        	
	            clockText.setText(String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", leftTimeInSecs));
	            clockText.bringToFront();
	            
	            if(hours == 0 && mins == 0){
	            	secondsClock.setProgress(leftTimeInSecs);
	            	
	            }
	            else if(hours == 0){
	            	minutesClock.setProgress(((int)leftTimeInMilliseconds/1000)-60);
	            }
	            else{
	            	while(hours>0)
	            	hoursClock.setProgress(((int)leftTimeInMilliseconds/1000)-3600);
	            }
	            // format the textview to show the easily readable format

	        }
	        @Override
	        public void onFinish() {
	            if(clockText.getText().equals("00:00")){
	            	clockText.setText("STOP");          
	            }
	            else{
	            	clockText.setText("2:00");
	            }
	        }
	    }.start();

	}

}
