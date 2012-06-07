package my.test.activity;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
	// ��Ч
	public SoundPool soundPool;
	public HashMap<Integer, Integer> soundPoolMap;
	// �������֣��������Լ������
	public MediaPlayer mediaPlayerGame;
	// ���ֿ�����
	public AudioManager am;
	// ����
	int maxVol;
	
	public Sound(Context context) {
		am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); 
		mediaPlayerGame = MediaPlayer.create(context, R.raw.sunshine);
		mediaPlayerGame.setLooping(true);
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);  
		soundPoolMap = new HashMap<Integer, Integer>();  
		soundPoolMap.put(1, soundPool.load(context,R.raw.himi_ogg, 1));  
		// �������
	}
	// ��ȡ��ǰ���ֿ�����������
	public int getVol(){
		return am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	// ���õ�ǰ���ֿ���������
	public void setVol(int currentVol){
		am.setStreamVolume(AudioManager.STREAM_MUSIC, currentVol,AudioManager.FLAG_PLAY_SOUND);
	}
	public void stopBgMusic(){
		mediaPlayerGame.stop();
	}
}
