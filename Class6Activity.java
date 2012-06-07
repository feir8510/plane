package my.test.activity;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Class6Activity extends Activity {
    
	/** Called when the activity is first created. */
	Button button1,button2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //AudioManager audioManager =(AudioManager)Class6Activity.this.getSystemService(Context.AUDIO_SERVICE);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MySurfaceView.button_str="button1 click";
			}
        });
        button2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MySurfaceView.button_str="button2 click";
			}
        });
    }
}