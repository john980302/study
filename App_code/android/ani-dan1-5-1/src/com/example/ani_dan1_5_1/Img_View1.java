package com.example.ani_dan1_5_1;

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
	//주인공 그림의 변경속도가 너무 빠르다 속도를 조절하기 위한 변수를 만든다.
	int delay_time1=0;
	// 아래 변수는 주인공의 그림의 폭과 높이를 저장하는 변수이다.
	// 캐릭터들의 폭,높이를 앞으로는 자주사용하게 될 것이다. 충돌검사등에도 사용하기때문
	int ch_width,ch_height;

	public Img_View1(Context context){
		super(context);
		
		// 주인공 첫번째 나는 모습의 그림을 저장한다.
		ch_Img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch1);
		// 주인공 두번째 나는 모습의 그림을 저장한다.
		ch_Img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch2);
		// 주인공 세번째 나는 모습의 그림을 저장한다.
		ch_Img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch3);
		Back_Img1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.back1);
		
		// 주인공 그림의 폭과높이를 저장, 이 폭은 내컴퓨터,그림판,훠닐,포토샵에서 보면 나옴
		ch_width=492;
		ch_height=435;
		
		thread1.setDaemon(true);
		thread1.start();
	}
	
	@Override
	public void onDraw(Canvas Canvas){
		Scale_X = Canvas.getWidth() / 480f;
		Scale_Y = Canvas.getHeight() / 800f;
		
		Canvas.scale(Scale_X, Scale_Y);
		
		Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null);
		
		//주인공크기를 위해서 선언한 변수로 대체했다.
		Rect chh1 = new Rect(X,Y,X+ch_width/7, Y+ch_height/7);
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
				//현재 핸들러는 쓰레드에 의해 2/1000초마다 호출되고 있다.
				//모든 처리 속도가 0.002초마다 호출되고 있다는 것이다.
				//0.002초마다 그림을 변경하면 얼마나 빨리 변경되겠는가?
				//단 엄청난 양의 그림등을 화면에 출력해야한다면 느린 스마트폰은 0.002초마다 처리하지 못해 느려지는 현상도 발생할 수 있다.
				//그래서 스마트폰마다 속도를 같게 보이도록 프레임 제어를 한다.
				//본론으로 delay_time1변수를 0.002초마다 1씩 증가 시킨다. 50미만일때까지만 반복
				if(delay_time1<50){
					delay_time1++;
				}
				ch_img_sub(); // 병아리 방번호 카운터 호출
				invalidate(); // onDraw 메소드를 재호출하기 위해 View를 초기화
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
			if(X<=480-ch_width/7){
				//주인공의 x값을 1씩 증가해서 왼쪽으로 이동
				X+=1;
			}else{
				//주인공의 좌표를 오른쪽 끝에 있도록 아래 명령 실행
				X=480-ch_width/7;
			}
		}
		//delay_time1변수값이 50미만일때만 그림변경(변수 ch_cnt1을 1 증가)
		//이전에는 ch_cnt1변수가 0.002마다 호출되어 주인공그림이 엄청빨리 변경됨
		//이제부터는 delay_time1이 50이 되어야 1증가
		//즉 0.002초 x 50 = 0.1초 -> 0.1초마다 주인공 그림을 변경한다는 의미이다.
		if(delay_time1==50){
			//주인공의 그림은 3개의 그림으로 만들어졌다. 이 3개의 그림을 교체하면 마치 날고 있는 모습
			//주인공 그림 변경변수 ch_cnt1을 1 증가
			ch_cnt1++;
			if(ch_cnt1>2){
				ch_cnt1=0;
			}
			//delay_time1이 50이 되어 ch_cnt1을 1증가 시켜 주인공 그림을 변경했다면 바로 변경하지 못하도록
			//delay_time1변수값을 다시 0으로 초기화했다.위쪽 핸들러의 if(delay_time1<50)가 이제 다시 참이므로
			//delay_time1변수가 1씩 증가됨
			delay_time1=0;
		}
		
	};
}
