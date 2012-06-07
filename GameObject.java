package my.test.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceView;

public class GameObject extends SurfaceView{
	// ����
	public Point position;
	// ������Ų��
	public int zOrder;
	// Resource
	public Bitmap bitmap;
	// �Ƿ񼤻�
	public Boolean inuse = false;
	// ��ͻ��
	public Rect collisionArea;
	public String collisionName = CollisionIdentifiers.NONE;
	
	// �ٶ�
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
	// ��ȷ���д���֤
	protected void setupCollisionArea(){
		collisionArea = new Rect(position.x,position.y,position.x+bitmap.getWidth()/frame,position.y+bitmap.getHeight());	
	}
	
	public Rect getCollisionArea() {
		// y ���껹δ����
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
			// ��������뵽GameObject��
			GameObjectManager.instance.addGameObject(this);
			// manager add Object
			// setupCollision
			setupCollisionArea();
		}
		
	}

	// ֡����
	public void enterFrame() {
		// TODO Auto-generated method stub
		
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		// ���뵽remove���У���֡ɾ��
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
