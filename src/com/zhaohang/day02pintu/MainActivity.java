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

	// ��һ��ͼƬ����
	int[] img = { R.drawable.img_xiaoxiong_00x00, R.drawable.img_xiaoxiong_00x01, R.drawable.img_xiaoxiong_00x02, R.drawable.img_xiaoxiong_01x00, R.drawable.img_xiaoxiong_01x01, R.drawable.img_xiaoxiong_01x02, R.drawable.img_xiaoxiong_02x00, R.drawable.img_xiaoxiong_02x01, R.drawable.img_xiaoxiong_02x02 };
	int[] imgIndex = new int[img.length];
	private int whiteID = R.id.img_02x02;
	private int whiteSite = 8;
	// 3*3��ʽ
	int imgX = 3;
	int imgY = 3;
	int time = 0;
	boolean timeswich = true;
	// ����
	Intent intent = new Intent("com.angel.Android.MUSIC");
	Handler handler = new Handler()
	{
		// �������̵߳���Ϣ
		public void handleMessage(android.os.Message msg)
		{
			// �ж��ź�
			if (msg.what == 1)
			{
				textView.setText("ʱ�䣺" + msg.obj);
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
		// ��������
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
						// obj һ������£����ݷ���ʱ��������obj����
						message.obj = time;
						// �ź�
						message.what = 1;
						// ��������
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

	// ��ʼ���ؼ�
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

	// ͼƬ����
	public void InitImg()
	{

		for (int i = 0; i < imgIndex.length; i++)
		{
			imgIndex[i] = i;
		}
		// �����������������������ֵ��
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
			// ��������
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
		// // ����ͼƬ������Դ��ͼƬ��
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

	// ��������
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

	// ͼƬ�ƶ�����
	public void Move(int clickId, int site)
	{
		int clickX = site % imgX;
		int clickY = site / imgY;

		int whiteX = whiteSite % imgX;
		int whiteY = whiteSite / imgY;

		// �����ж��Ƿ�����
		int x = Math.abs(whiteX - clickX);
		int y = Math.abs(whiteY - clickY);
		// ����� �����ƶ���
		if ( (x == 0 && y == 1) || (x == 1 && y == 0))
		{
			// �������ͼƬ����
			ImageButton clickbtn = (ImageButton) findViewById(clickId);
			clickbtn.setVisibility(View.INVISIBLE);
			// ��ԭ�հ�����ͼƬ����Ϊ�������ͼƬ��Ȼ����ʾ��
			ImageButton whitebtn = (ImageButton) findViewById(whiteID);
			whitebtn.setImageDrawable(getResources().getDrawable(img[imgIndex[site]]));
			whitebtn.setVisibility(View.VISIBLE);

			for (int i = 0; i < imgIndex.length; i++)
			{
				Log.i("����ǰ---", "---" + imgIndex[i]);

			}

			// ����ؼ���ͼƬ�仯��������������ͼƬҲҪ����
			Swop(site, whiteSite);

			for (int i = 0; i < imgIndex.length; i++)
			{
				Log.i("������---", "+++" + imgIndex[i]);

			}
			// �հ�������ʱ�仯
			whiteID = clickId;
			whiteSite = site;

		}

		GameOver();
	}

	// �жϳɹ��ķ���
	public void GameOver()
	{
		boolean loop = true;

		// �ж�����0--8
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
			builder.setMessage("�ҵ�����");
			builder.setPositiveButton("ȷ��", new OnClickListener()
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

	// ��ԭ
	public void Restore()
	{
		ImageButton clickButton = (ImageButton) findViewById(whiteID);
		clickButton.setVisibility(View.VISIBLE);

		ImageButton whitebtn = (ImageButton) findViewById(R.id.img_02x02);
		whitebtn.setVisibility(View.INVISIBLE);
		// ��ԭĬ�Ͽհ�
		whiteSite = 8;
		whiteID = R.id.img_02x02;
	}

	// ���¿�ʼ
	public void Restart(View view)
	{
		Restore();
		InitImg();

		time = 0;
		timeswich = true;
		// textView.setText("ʱ�䣺");

	}
}
