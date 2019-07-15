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
		
		// 주인공 첫번째 나는 모습의 그림을 저장한다.
		ch_Img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch1);
		// 주인공 두번째 나는 모습의 그림을 저장한다.
		ch_Img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch2);
		// 주인공 세번째 나는 모습의 그림을 저장한다.
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
		// 주인공 3개의 그림을 변경해야 마치 나는듯한 모습을 보인다. 주인공 그림 배열중 ch_cnt1번째에 해당하는 그림을 보인다.
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
			// 아래 조건문은 주인공이 왼쪽화면으로 사라지지 않도록 하는 내용이다.
			// x값이 0이상이면 즉 주인공이 화면에 위치하고 있다면 아래 명령을 실행
			if(X>=0){
				//주인공의 x값을 1씩 감소해서 왼쪽으로 이동
				X-=1;
			//만약 X값이 0보다 작아지면 즉 주인공이 화면 왼쪽으로 사라지려 하면 아래 명령 실행
			}else{
				X=0;
			}
		}else if(sXR==true){
			//아래 조건문은 주인공이 오른쪽 화면으로 사라지지 않도록 하는 내용
			//X값이 480-492/7 이하이면 즉 주인공이 화면에 위치하고 있다면 아래 명령 실행
			//480은 화면의 폭이고 492/7은 주인공의 폭이다. 즉 주인공의 오른쪽이 화면밖으로 나가지 않는 좌표값이다.
			if(X<=480-492/7){
				//주인공의 x값을 1씩 증가해서 왼쪽으로 이동
				X+=1;
			}else{
				//주인공의 좌표를 오른쪽 끝에 있도록 아래 명령 실행
				X=480-492/7;
			}
		}
		
		//주인공의 그림은 3개의 그림으로 만들어졌다. 이 3개의 그림을 교체하면 마치 날고 있는 모습
		//주인공 그림 변경변수 ch_cnt1을 1 증가
		ch_cnt1++;
		if(ch_cnt1>2){
			ch_cnt1=0;
		}
	};
}
