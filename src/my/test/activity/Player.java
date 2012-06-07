package my.test.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.KeyEvent;

public class Player extends GameObject{
	
	public Player(Context context, Point position, int zOrder, Bitmap bitmap) {
		super(context, position, zOrder, bitmap);
		// TODO Auto-generated constructor stub		
		this.collisionName=CollisionIdentifiers.PLAYER;
	}

	public Player(Context context, Point position, int zOrder, Bitmap bitmap,
			int frame) {
		// TODO Auto-generated constructor stub
		this(context, position, zOrder, bitmap);
		this.frame = frame;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//return GameObjectManager.instance.onKeyDown(keyCode, event);
		//return super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
			position.y-=5;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			position.y+=5;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			position.x-=5;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			position.x+=5;
		}
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//return GameObjectManager.instance.onKeyUp(keyCode, event);
		//position.y++;
		return true;
	}

	@Override
	public void enterFrame() {
		// TODO Auto-generated method stub
		//System.out.println("player enterFrame");
		super.enterFrame();
		// TODO MyJob
		currentFrame+=1;
		if(currentFrame>frame){
			currentFrame = 1;
		}
	}

	@Override
	public void copyToBackBuffer(Bitmap db) {
		// TODO Auto-generated method stub
		super.copyToBackBuffer(db);
		//Bitmap.createBitmap(bitmap,position.x, position.y, bitmap.getWidth(), bitmap.getHeight());
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		
		prepareToDraw=Bitmap.createBitmap(bitmap, (frame-1)*bitmap.getWidth()/frame, 0, bitmap.getWidth()/frame,bitmap.getHeight());
		canvas.drawBitmap(prepareToDraw,position.x,position.y,new Paint());
	}

	@Override
	public void collision(GameObject go) {
		// TODO Auto-generated method stub
		super.collision(go);
		// TODO our job
		if(go instanceof Enemy){
			System.out.println("collision with Enemy");
		}
	}
	
}
