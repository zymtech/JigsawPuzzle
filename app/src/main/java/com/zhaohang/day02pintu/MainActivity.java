package com.zhaohang.day02pintu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity
{

	ImageButton img00_00, img00_01, img00_02;
	ImageButton img01_00, img01_01, img01_02;
	ImageButton img02_00, img02_01, img02_02;
	TextView textView;

	// 建一个图片数组
	int[] img = { R.drawable.img_xiaoxiong_00x00, R.drawable.img_xiaoxiong_00x01, R.drawable.img_xiaoxiong_00x02, R.drawable.img_xiaoxiong_01x00, R.drawable.img_xiaoxiong_01x01, R.drawable.img_xiaoxiong_01x02, R.drawable.img_xiaoxiong_02x00, R.drawable.img_xiaoxiong_02x01, R.drawable.img_xiaoxiong_02x02 };
	int[] imgIndex = new int[img.length];
	private int whiteID = R.id.img_02x02;
	private int whiteSite = 8;
	// 3*3格式
	int imgX = 3;
	int imgY = 3;
	int time = 0;
	boolean timeswich = true;
	// 服务
	Intent intent = new Intent("com.angel.Android.MUSIC");
	Handler handler = new Handler()
	{
		// 接收子线程的消息
		public void handleMessage(android.os.Message msg)
		{
			// 判断信号
			if (msg.what == 1)
			{
				textView.setText("时间：" + msg.obj);
			}

		};
	};

	protected void onPause()
	{
		stopService(intent);
	};

	protected void onDestroy()
	{
		stopService(intent);
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitView();
		InitImg();
		// 开启服务
		startService(intent);

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					while (timeswich)
					{
						Message message = Message.obtain();
						// obj 一般情况下，数据发送时都是利用obj传递
						message.obj = time;
						// 信号
						message.what = 1;
						// 发送数据
						handler.sendMessage(message);
						try
						{
							Thread.sleep(1000);
							time++;
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}

					}
				}
			}
		}).start();
	}

	// 初始化控件
	private void InitView()
	{
		img00_00 = (ImageButton) findViewById(R.id.img_00x00);
		img00_01 = (ImageButton) findViewById(R.id.img_00x01);
		img00_02 = (ImageButton) findViewById(R.id.img_00x02);
		img01_00 = (ImageButton) findViewById(R.id.img_01x00);
		img01_01 = (ImageButton) findViewById(R.id.img_01x01);
		img01_02 = (ImageButton) findViewById(R.id.img_01x02);
		img02_00 = (ImageButton) findViewById(R.id.img_02x00);
		img02_01 = (ImageButton) findViewById(R.id.img_02x01);
		img02_02 = (ImageButton) findViewById(R.id.img_02x02);
		textView = (TextView) findViewById(R.id.time_main);

	}

	// 图片排列
	public void InitImg()
	{

		for (int i = 0; i < imgIndex.length; i++)
		{
			imgIndex[i] = i;
		}
		// 进行随机排列这个数组里面的值·
		int r1, r2;
		for (int i = 0; i < 20; i++)
		{
			r1 = (int) (Math.random() * (img.length - 1));
			do
			{
				r2 = (int) (Math.random() * (img.length - 1));
				if (r1 != r2)
				{
					break;
				}

			} while (true);
			// 交换方法
			Swop(r1, r2);
		}
		// img00_00.setImageDrawable(drawable)
		// img00_00.setImageDrawable(drawable)
		img00_00.setImageDrawable(getResources().getDrawable(img[imgIndex[0]]));
		img00_01.setImageDrawable(getResources().getDrawable(img[imgIndex[1]]));
		img00_02.setImageDrawable(getResources().getDrawable(img[imgIndex[2]]));
		img01_00.setImageDrawable(getResources().getDrawable(img[imgIndex[3]]));
		img01_01.setImageDrawable(getResources().getDrawable(img[imgIndex[4]]));
		img01_02.setImageDrawable(getResources().getDrawable(img[imgIndex[5]]));
		img02_00.setImageDrawable(getResources().getDrawable(img[imgIndex[6]]));
		img02_01.setImageDrawable(getResources().getDrawable(img[imgIndex[7]]));
		img02_02.setImageDrawable(getResources().getDrawable(img[imgIndex[8]]));

		// img00_00.setImageDrawable(getResources().getDrawable(img[imgIndex[0]]));
		// img00_01.setImageDrawable(getResources().getDrawable(img[imgIndex[1]]));
		// img00_02.setImageDrawable(getResources().getDrawable(img[imgIndex[2]]));
		//
		// img01_00.setImageDrawable(getResources().getDrawable(img[imgIndex[3]]));
		// img01_01.setImageDrawable(getResources().getDrawable(img[imgIndex[4]]));
		// img01_02.setImageDrawable(getResources().getDrawable(img[imgIndex[5]]));
		// // 设置图片，从资源，图片里
		// img02_00.setImageDrawable(getResources().getDrawable(img[imgIndex[6]]));
		// img02_01.setImageDrawable(getResources().getDrawable(img[imgIndex[7]]));
		// img02_02.setImageDrawable(getResources().getDrawable(img[imgIndex[8]]));

	}

	public void Swop(int r1, int r2)
	{
		int temp = 0;
		temp = imgIndex[r1];
		imgIndex[r1] = imgIndex[r2];
		imgIndex[r2] = temp;

	}

	// 监听方法
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.img_00x00:
				Move(R.id.img_00x00, 0);
				break;
			case R.id.img_00x01:
				Move(R.id.img_00x01, 1);
				break;
			case R.id.img_00x02:
				Move(R.id.img_00x02, 2);
				break;
			case R.id.img_01x00:
				Move(R.id.img_01x00, 3);
				break;
			case R.id.img_01x01:
				Move(R.id.img_01x01, 4);
				break;
			case R.id.img_01x02:
				Move(R.id.img_01x02, 5);
				break;
			case R.id.img_02x00:
				Move(R.id.img_02x00, 6);
				break;
			case R.id.img_02x01:
				Move(R.id.img_02x01, 7);
				break;
			case R.id.img_02x02:
				Move(R.id.img_02x02, 8);
				break;
			default:
				break;
		}
	}

	// 图片移动方法
	public void Move(int clickId, int site)
	{
		int clickX = site % imgX;
		int clickY = site / imgY;

		int whiteX = whiteSite % imgX;
		int whiteY = whiteSite / imgY;

		// 进行判断是否相邻
		int x = Math.abs(whiteX - clickX);
		int y = Math.abs(whiteY - clickY);
		// 如果是 进行移动了
		if ( (x == 0 && y == 1) || (x == 1 && y == 0))
		{
			// 将被点击图片隐藏
			ImageButton clickbtn = (ImageButton) findViewById(clickId);
			clickbtn.setVisibility(View.INVISIBLE);
			// 将原空白区域图片设置为被点击的图片，然后显示。
			ImageButton whitebtn = (ImageButton) findViewById(whiteID);
			whitebtn.setImageDrawable(getResources().getDrawable(img[imgIndex[site]]));
			whitebtn.setVisibility(View.VISIBLE);

			for (int i = 0; i < imgIndex.length; i++)
			{
				Log.i("交换前---", "---" + imgIndex[i]);

			}

			// 表面控件上图片变化，后面的数组里的图片也要跟随
			Swop(site, whiteSite);

			for (int i = 0; i < imgIndex.length; i++)
			{
				Log.i("交换后---", "+++" + imgIndex[i]);

			}
			// 空白区域随时变化
			whiteID = clickId;
			whiteSite = site;

		}

		GameOver();
	}

	// 判断成功的方法
	public void GameOver()
	{
		boolean loop = true;

		// 判断数据0--8
		for (int i = 0; i < imgIndex.length; i++)
		{
			if (imgIndex[i] != i)
			{
				loop = false;
				break;
			}
		}
		if (loop)
		{
			timeswich = false;

			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setMessage("我的天哪");
			builder.setPositiveButton("确定", new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface arg0, int arg1)
				{
					// TODO Auto-generated method stub

				}
			});
			builder.create().show();

		}
	}

	// 还原
	public void Restore()
	{
		ImageButton clickButton = (ImageButton) findViewById(whiteID);
		clickButton.setVisibility(View.VISIBLE);

		ImageButton whitebtn = (ImageButton) findViewById(R.id.img_02x02);
		whitebtn.setVisibility(View.INVISIBLE);
		// 还原默认空白
		whiteSite = 8;
		whiteID = R.id.img_02x02;
	}

	// 重新开始
	public void Restart(View view)
	{
		Restore();
		InitImg();

		time = 0;
		timeswich = true;
		// textView.setText("时间：");

	}
}
