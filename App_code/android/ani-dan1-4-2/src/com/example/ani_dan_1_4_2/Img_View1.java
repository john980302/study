package com.example.ani_dan_1_4_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class Img_View1 extends View{
	BitmapDrawable ch_Img[] = new BitmapDrawable[3];
	BitmapDrawable Back_Img1 = null;
	int X=200,Y=550;
	boolean sXL=false,sXR=false;
	float Scale_X;
	float Scale_Y;
	int xx, yy;
	BackThread thread1 = new BackThread();
	boolean retry = true;
	int ch_cnt1 = 0;

	public Img_View1(Context context){
		super(context);
		
		// ���ΰ� ù��° ���� ����� �׸��� �����Ѵ�.
		ch_Img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch1);
		// ���ΰ� �ι�° ���� ����� �׸��� �����Ѵ�.
		ch_Img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch2);
		// ���ΰ� ����° ���� ����� �׸��� �����Ѵ�.
		ch_Img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch3);
		Back_Img1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.back1);
		
		thread1.setDaemon(true);
		thread1.start();
	}
	
	@Override
	public void onDraw(Canvas Canvas){
		Scale_X = Canvas.getWidth() / 480f;
		Scale_Y = Canvas.getHeight() / 800f;
		
		Canvas.scale(Scale_X, Scale_Y);
		
		Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null);
		
		Rect chh1 = new Rect(X,Y,X+492/7, Y+435/7);
		// ���ΰ� 3���� �׸��� �����ؾ� ��ġ ���µ��� ����� ���δ�. ���ΰ� �׸� �迭�� ch_cnt1��°�� �ش��ϴ� �׸��� ���δ�.
		Canvas.drawBitmap(ch_Img[ch_cnt1].getBitmap(),null,chh1,null);
		
		
		Paint p = new Paint();
		Canvas.drawText("XX"+xx+" YY"+yy,10,30,p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int action = event.getAction();
		
		xx = (int)(event.getX()/Scale_X);
		yy = (int)(event.getY()/Scale_Y);
		
		Rect rt = new Rect();
		
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
			
			rt.set(5,700,100,800);
			if(rt.contains(xx,yy)){
				sXL = true;
			}
			rt.set(380,700,475,800);
			if(rt.contains(xx,yy)){
				sXR = true;
			}
		}else{
			sXL=false;
			sXR=false;
		}
		return true;
	}
	
	class BackThread extends Thread{
		public void run(){
			while(retry){
				try{
					Handler1.sendEmptyMessage(0);
					Thread.sleep(2);
				}catch
						(InterruptedException e){;}
			}
		}
	}
	
	Handler Handler1 = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0){
				ch_img_sub();
				invalidate();
			}
		}
	};
	
	private void ch_img_sub(){
		if(sXL==true){
			// �Ʒ� ���ǹ��� ���ΰ��� ����ȭ������ ������� �ʵ��� �ϴ� �����̴�.
			// x���� 0�̻��̸� �� ���ΰ��� ȭ�鿡 ��ġ�ϰ� �ִٸ� �Ʒ� ����� ����
			if(X>=0){
				//���ΰ��� x���� 1�� �����ؼ� �������� �̵�
				X-=1;
			//���� X���� 0���� �۾����� �� ���ΰ��� ȭ�� �������� ������� �ϸ� �Ʒ� ��� ����
			}else{
				X=0;
			}
		}else if(sXR==true){
			//�Ʒ� ���ǹ��� ���ΰ��� ������ ȭ������ ������� �ʵ��� �ϴ� ����
			//X���� 480-492/7 �����̸� �� ���ΰ��� ȭ�鿡 ��ġ�ϰ� �ִٸ� �Ʒ� ��� ����
			//480�� ȭ���� ���̰� 492/7�� ���ΰ��� ���̴�. �� ���ΰ��� �������� ȭ������� ������ �ʴ� ��ǥ���̴�.
			if(X<=480-492/7){
				//���ΰ��� x���� 1�� �����ؼ� �������� �̵�
				X+=1;
			}else{
				//���ΰ��� ��ǥ�� ������ ���� �ֵ��� �Ʒ� ��� ����
				X=480-492/7;
			}
		}
		
		//���ΰ��� �׸��� 3���� �׸����� ���������. �� 3���� �׸��� ��ü�ϸ� ��ġ ���� �ִ� ���
		//���ΰ� �׸� ���溯�� ch_cnt1�� 1 ����
		ch_cnt1++;
		if(ch_cnt1>2){
			ch_cnt1=0;
		}
	};
}
