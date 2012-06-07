package my.test.activity;

import java.util.HashMap;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;

public class GameObjectManager extends SurfaceView{
	//double buffer
	public Bitmap backBuffer;
	// clear backbuffer
	public int clearColor = 0xFF808080;
	private HashMap<String, String> collisionMap = new HashMap<String, String>();
	// single Instance , 单例类
	protected static GameObjectManager instance = null;
	// gameObjects
	protected Vector<GameObject> gameObjects ;
	protected Vector<GameObject> removedGameObjects;
	protected Vector<GameObject> newGameObjects;
	//private boolean inuse = false;
	static Context context;
	public static GameObjectManager getInstance(){
		if(instance == null)
			instance = new GameObjectManager(context);
		return instance;
	}
	
	public GameObjectManager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		//backBuffer 
		gameObjects = new Vector<GameObject>();
		removedGameObjects = new Vector<GameObject>();
		newGameObjects = new Vector<GameObject>();
	}

	
	public void startup(){
		addCollidingPair(CollisionIdentifiers.PLAYER,CollisionIdentifiers.ENEMY);
		// more Collision
	}
	private void addCollidingPair(String collider1, String collider2) {
		// TODO Auto-generated method stub									
		collisionMap.put(collider1, collider2);
		collisionMap.put(collider2, collider1);
	}
	public void shutdown(){
		shutdownAll();
	}
	private void shutdownAll() {
		// TODO Auto-generated method stub
		// don't dispose objects twice
		GameObject go,rgo;
		for(int i=0;i<gameObjects.size();i++)
		{
			go = gameObjects.elementAt(i);
			Boolean found = false;
			for(int j=0;j<removedGameObjects.size();j++)
			{
				rgo = removedGameObjects.elementAt(j);
				if (rgo == go)
				{
					found = true;
					break;
				}
			}
			
			if (!found)
				go.shutdown();
		}
	}

	public void enterFrame(Canvas canvas){
		removeDeletedGameObjects();
		insertNewGameObjects();
		//Level.Instance.enterFrame(seconds);
		for(int i=0;i<gameObjects.size();i++){
			if(gameObjects.elementAt(i).inuse){
				gameObjects.elementAt(i).enterFrame();
			}
		}
		checkCollisions();
		drawObjects(canvas);
	}
	private void drawObjects(Canvas canvas) {
		// TODO Auto-generated method stub
		GameObject goI;
		for(int i=0;i<gameObjects.size();i++)
		{
			
			goI=gameObjects.elementAt(i);
			if (MySurfaceView.inGame && goI.inuse) 
				//backBuffer 未用
				//System.out.println("tryDraw"+i);
				goI.tryDraw(canvas);
		}
	}
	private void checkCollisions() {
		// TODO Auto-generated method stub
		GameObject goI,goJ;
		for(int i=0;i<gameObjects.size();i++){
			goI = gameObjects.elementAt(i);
			for(int j=i+1;j<gameObjects.size();j++){
				goJ = gameObjects.elementAt(j);
				Boolean collisionNameNotNothing = false;
				if(goI.collisionName != CollisionIdentifiers.NONE)
					collisionNameNotNothing = true;
				Boolean bothInUse = goI.inuse && goJ.inuse;
				Boolean collisionMapEntryExists=collisionMap.containsKey(goI.collisionName);
				Boolean testForCollision=collisionMapEntryExists && (collisionMap.get(goI.collisionName)==goJ.collisionName);
				if(collisionNameNotNothing && bothInUse && testForCollision){
					if(goI.getCollisionArea().intersect(goJ.getCollisionArea())){
						goI.collision(goJ);
						goJ.collision(goI);
					}
				}
			}
		}
	}
	private void insertNewGameObjects() {
		// TODO Auto-generated method stub
		GameObject go,ngo;
		for(int i=0;i<newGameObjects.size();i++){
			ngo = (GameObject)newGameObjects.elementAt(i);
			for(int j=0;j<gameObjects.size();j++){
				go = (GameObject)gameObjects.elementAt(j);
				if(go.zOrder > ngo.zOrder || go.zOrder == -1)
					break;
			}
			//System.out.println("new add complete!");
			gameObjects.add(i, ngo);
		}
		
		newGameObjects.removeAllElements();
	}
	private void removeDeletedGameObjects() {
		// TODO Auto-generated method stub	
		//System.out.print(removedGameObjects.size());
		//Log.v("output",String.valueOf(removedGameObjects.size()));
		for(int i=0;i<removedGameObjects.size();i++){
			GameObject rgo = removedGameObjects.elementAt(i);
			for(int j=0;j<gameObjects.size();j++){
				GameObject go = gameObjects.elementAt(j);
				if(go == rgo){
					gameObjects.remove(j);
					break;
				}
			}
		}
		try{
			removedGameObjects.removeAllElements();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void addGameObject(GameObject go){
		newGameObjects.add(go);
	}
	public void removeGameObject(GameObject go)
	{
		removedGameObjects.add(go);
	}
	//将按键响应到各个object
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//super(keyCode,event);
		GameObject go;
		for(int i=0;i<gameObjects.size();i++){
			go = (GameObject)gameObjects.elementAt(i);
			if(go.inuse ){//&& //(gameObjects[i] instanceof ))
				return go.onKeyDown(keyCode, event);
			}
		}
		return true;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		GameObject go;
		for(int i=0;i<gameObjects.size();i++){
			go = (GameObject)gameObjects.elementAt(i);
			if(go.inuse ){
				return go.onKeyUp(keyCode, event);
			}
		}
		return true;
	}
	
}
