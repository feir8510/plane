package my.test.activity;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Resource{
	static Bitmap bt1,enemy1,smallblueplane,bigexplosion;		
	public static void LoadResource(Context context){
		bt1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jay);
		enemy1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		smallblueplane = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallblueplane);
		bigexplosion = BitmapFactory.decodeResource(context.getResources(), R.drawable.bigexplosion);
	}
}
