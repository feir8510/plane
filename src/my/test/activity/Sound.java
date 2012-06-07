package my.test.activity;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
	// 音效
	public SoundPool soundPool;
	public HashMap<Integer, Integer> soundPoolMap;
	// 背景音乐，将来可以继续添加
	public MediaPlayer mediaPlayerGame;
	// 音乐控制器
	public AudioManager am;
	// 音量
	int maxVol;
	
	public Sound(Context context) {
		am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); 
		mediaPlayerGame = MediaPlayer.create(context, R.raw.sunshine);
		mediaPlayerGame.setLooping(true);
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);  
		soundPoolMap = new HashMap<Integer, Integer>();  
		soundPoolMap.put(1, soundPool.load(context,R.raw.himi_ogg, 1));  
		// 后续添加
	}
	// 获取当前音乐控制器的音量
	public int getVol(){
		return am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	// 设置当前音乐控制器音量
	public void setVol(int currentVol){
		am.setStreamVolume(AudioManager.STREAM_MUSIC, currentVol,AudioManager.FLAG_PLAY_SOUND);
	}
	public void stopBgMusic(){
		mediaPlayerGame.stop();
	}
}
