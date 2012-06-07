package my.test.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback,Runnable{
	//private Button button1, button2;
	public static String button_str = "Himi_在SurfaceView中添加组件练习";
	private int move_x = 2, x = 80;
	private Thread th;
	private SurfaceHolder sfh;
	private Canvas canvas;
	private Paint p;
	int posx=100,posy=100;
	Sound mySound;
	
	int maxVol;
	int loadId;
	Context context;
	
	static boolean inGame = false;
	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		mySound = new Sound(context);
		Resource.LoadResource(context);
		
		p = new Paint();
		p.setAntiAlias(true);
		sfh = this.getHolder();
		sfh.addCallback(this);
		th = new Thread(this);
		this.setKeepScreenOn(true);
		setFocusable(true);
	}	

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		//mySound.mediaPlayerGame.start(); 
		inGame = true;
		
		GameObjectManager.getInstance().startup();
		
		Player testPlayer = new Player(context,new Point(100,100),1,Resource.bigexplosion,8);
		Enemy testEnemy = new Enemy(context,new Point(100,100),1,Resource.enemy1);
		Enemy smallBluePlane = new Enemy(context,new Point(100,0),1,Resource.smallblueplane,3);
		th.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		mySound.stopBgMusic();
		th.stop();
		inGame = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(inGame){
			enterFrame();
			logic();
			try{
				Thread.sleep(100);
			}catch(Exception ex){
				
			}
		}
	}
	private void logic() {
		// TODO Auto-generated method stub
		x += move_x;
		if (x > 200 || x < 80) {
			move_x = -move_x;
		}
	}




	private void enterFrame() {
		// TODO Auto-generated method stub		
		canvas = sfh.lockCanvas();
		//canvas.drawColor(Color.WHITE);
		//canvas.drawText(button_str, x + move_x, 100, p);
		if(inGame){
			GameObjectManager.instance.enterFrame(canvas);
		}
		sfh.unlockCanvasAndPost(canvas);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return GameObjectManager.instance.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return GameObjectManager.instance.onKeyUp(keyCode, event);
	}
	
}
