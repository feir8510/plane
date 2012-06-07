package my.test.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceView;

public class GameObject extends SurfaceView{
	// 坐标
	public Point position;
	// 对象叠放层次
	public int zOrder;
	// Resource
	public Bitmap bitmap;
	// 是否激活
	public Boolean inuse = false;
	// 冲突域
	public Rect collisionArea;
	public String collisionName = CollisionIdentifiers.NONE;
	
	// 速度
	public int speed;

	// frame
	int frame=1;
	int currentFrame=0;
	Bitmap prepareToDraw;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}
	// 正确性有待考证
	protected void setupCollisionArea(){
		collisionArea = new Rect(position.x,position.y,position.x+bitmap.getWidth()/frame,position.y+bitmap.getHeight());	
	}
	
	public Rect getCollisionArea() {
		// y 坐标还未处理
		collisionArea = new Rect(position.x,position.y,position.x+bitmap.getWidth()/frame,position.y+bitmap.getHeight()); 	
		return collisionArea;
	}

	

	public GameObject(Context context, Point position, int zOrder,
			Bitmap bitmap) {
		super(context);
		if(!inuse){
			inuse = true;
			this.position = position;
			this.zOrder = zOrder;
			this.bitmap = bitmap;
			// 将对象加入到GameObject中
			GameObjectManager.instance.addGameObject(this);
			// manager add Object
			// setupCollision
			setupCollisionArea();
		}
		
	}

	// 帧处理
	public void enterFrame() {
		// TODO Auto-generated method stub
		
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		// 加入到remove队列，下帧删除
		if (inuse)
		{				
			//graphics = null;
			inuse = false;
			
			GameObjectManager.instance.removeGameObject(this);
		}
	}

	public void collision(GameObject go) {
		// TODO Auto-generated method stub
		
	}
	public void copyToBackBuffer(Bitmap db)
	{			
		//db.(bitmap, graphics.bitmap.rect, position, graphics.bitmapAlpha, new Point(0, 0), true);
	}
	//void startGameObject(Bitmap []bitmap,int mapnum,Point position,ZOder)

	public void tryDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		draw(canvas);
	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		//canvas.drawBitmap(
	}
}
