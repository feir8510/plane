package my.test.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class Enemy extends GameObject{

	int type;
	
	
	public Enemy(Context context, Point position, int zOrder, Bitmap bitmap) {
		super(context, position, zOrder, bitmap);
		// TODO Auto-generated constructor stub
		this.collisionName=CollisionIdentifiers.ENEMY;
	}
	public Enemy(Context context, Point position, int zOrder, Bitmap bitmap,
			int frame) {
		// TODO Auto-generated constructor stub
		this(context, position, zOrder, bitmap);
		this.frame = frame;
	}
	@Override
	public void enterFrame() {
		// TODO Auto-generated method stub
		//System.out.println("player enterFrame");
		super.enterFrame();
		// TODO 
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
		//bitmap.creat
		prepareToDraw=Bitmap.createBitmap(bitmap, (frame-1)*bitmap.getWidth()/frame, 0, bitmap.getWidth()/frame,bitmap.getHeight());
		canvas.drawBitmap(prepareToDraw,position.x,position.y,new Paint());
	}
	@Override
	public void collision(GameObject go) {
		// TODO Auto-generated method stub
		super.collision(go);
		// TODO our job
		if(go instanceof Player){
			System.out.println("collision with Player");
			Log.v("Enemy::collision", "collision with Player");
		}
	}
}
