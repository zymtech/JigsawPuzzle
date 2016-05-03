package com.zhaohang.day02pintu;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service
{
	MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
		mediaPlayer = new MediaPlayer();
		mediaPlayer = MediaPlayer.create(MusicService.this, R.raw.kldy);
		mediaPlayer.start();

	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mediaPlayer.stop();
	}

}
