package com.example.ani_dan1_8_2;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class Img_View1 extends View{
	BitmapDrawable ch_Img[] = new BitmapDrawable[3];
	BitmapDrawable Back_Img1 = null;
	//현재 단원에서는 방향그림버튼이 추가된다.
	//왼쪽방향그림이 눌리기 전과 눌린 후의 두개의 그림을 저장하는 그림저장배열객체 생성
	BitmapDrawable btn_left[] = new BitmapDrawable[2];
	//오른쪽방향그림이 눌리기 전과 눌린 후의 두개의 그림을 저장하는 그림저장배열객체 생성
	BitmapDrawable btn_right[] = new BitmapDrawable[2];
	//구름그림 3개를 저장하는 구름저장 객체배열을 만든다.
	BitmapDrawable cloud_ch_img[] = new BitmapDrawable[3];
	//Context는 여러 Activity instance들 간에 리소스를 공유하거나 설정등에 접근하기 위해 사용
	Context _context; // Context객체를 만듦
	
	// 현재 View상태에서 MediaPlayer사용하면 사운드 재생에는 문제가 없지만
	// 배경음악처럼 계속 반복 재생하는 경우 앱을 종료해서 앱 프로세서가 완전히 종료전까지 배경음악이 계속 대생
	// 액티비티에서는 종료시 Destroy에서 mp3를 stop할 수 있는데 현재 View에서는 Destroy가 없기 때문에
	// 종료명령을 지시할 수 없다.
	MediaPlayer backsound; //배경음악용 사운드 객체 만들기
	MediaPlayer btnsound; // 버튼용 사운드 객체 만들기
	boolean stage1_snd; // 스테이지별 사운드 재생 스위치 변수
	
	
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
	// 시간지연 스위치 변수를 만든다.
	boolean delay_time1_sw=false; // 시간지연 스위치
	// 시간지연 변수를 추가한다.
	int delay_time2=0;
	// 시간지연 스위치 변수를 만든다.
	boolean delay_time2_sw=false; // 시간지연 스위치
	// 아래 변수는 주인공의 그림의 폭과 높이를 저장하는 변수이다.
	// 캐릭터들의 폭,높이를 앞으로는 자주사용하게 될 것이다. 충돌검사등에도 사용하기때문
	int ch_width,ch_height;
	//이 방향버튼을 눌렀을 때는 눌린그림으로 변경해야 하는데 눌린상태를 체크하는 변수이다.
	int th_sw_left=0, th_sw_right=0;
	//구름그림의 가로X좌표를 저장하는 배열3개를 만든다.
	int cloud_x[] = new int[3];
	//구름그림의 세로Y좌표를 저장하는 배열3개를 만든다.
	int cloud_y[] = new int[3];
	
	// 초기화 부분, 그림저장 , 변수초기화등을 한다. 시작시 맨처음 실행
	public Img_View1(Context context){
		super(context);
		
		// 주인공 첫번째 나는 모습의 그림을 저장한다.
		ch_Img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch1);
		// 주인공 두번째 나는 모습의 그림을 저장한다.
		ch_Img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch2);
		// 주인공 세번째 나는 모습의 그림을 저장한다.
		ch_Img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch3);
		//배경그림 저장
		Back_Img1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.back1);
		
		// 주인공 그림의 폭과높이를 저장, 이 폭은 내컴퓨터,그림판,훠닐,포토샵에서 보면 나옴
		ch_width=492;
		ch_height=435;
		
		// 왼쪽이동버튼 누르기전 그림을 저장
		btn_left[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_left1);
		// 왼쪽이동버튼 누른후 그림을 저장
		btn_left[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_left2);
		// 오른쪽이동버튼 누르기전 그림을 저장
		btn_right[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_right1);
		// 오른이동버튼 누른후 그림을 저장
		btn_right[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_right2);
		
		//구름그림 제일작은것을 저장한다.
		cloud_ch_img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud1);
		//구름그림 중간크기인것을 저장한다.
		cloud_ch_img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud2);
		//구름그림 제일큰크기인것을 저장한다.
		cloud_ch_img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud3);
		//구름그림을 화면에 출력할 때 최초 위치를 결정하는 함수를 호출
		// 구름의 좌표를 초기화하는 함수를 해당 구름방 번호를 받아 초기화하는 방식으로 변경한다.
		// 이유는 구름의 위치를 매번 다른 곳에서 내려오도록 하는 것이다.
		// 또 구름은 매번 다른 속도로 나오고 구름마다 위치를 결정해야하는 위치를 초기화해서 그 위치부터 다시 내려오도록 해야한다.
		// 매번 다른 연산을 수행하려면 내려온 방번호 순서대로 위치를 초기화해서 그위치부터 다시 내려오도록 해야한다.
		for(int i=0;i<=2;i++)
			cloud_x_y_po(i);
		
		_context=context;
		stage1_snd = true; // 스테이지1 사운드 재생을 true 재생으로 한다.
		// 리소스 폴더res에 raw폴더를 만들고 free_back.mp3를 추가
		// 리소스에 추가한 R.raw.free_back 배경사운드를 재생하도록 설정
		backsound = MediaPlayer.create(_context, R.raw.free_back);
		btnsound = MediaPlayer.create(_context,R.raw.btn1);
		
		thread1.setDaemon(true);
		thread1.start();
	}
	
	@Override
	public void onDraw(Canvas Canvas){
		Scale_X = Canvas.getWidth() / 480f;
		Scale_Y = Canvas.getHeight() / 800f;
		
		Canvas.scale(Scale_X, Scale_Y);
		
		Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null); //배경그림출력
		
		//구름을 cloud_x[0],cloud_y[0]좌표를 출력, 각좌표는 아래쪽 cloud_mov함수에서 변경
		//구름이 위쪽에서 아래방향으로 이동하게 된다.
		//제일작은 구름출력
		Canvas.drawBitmap(cloud_ch_img[0].getBitmap(),cloud_x[0],cloud_y[0],null );
		//중간크기 구름출력
		Canvas.drawBitmap(cloud_ch_img[1].getBitmap(),cloud_x[1],cloud_y[1],null );
		//가장큰 구름출력
		Canvas.drawBitmap(cloud_ch_img[2].getBitmap(),cloud_x[2],cloud_y[2],null );
		
		//주인공크기를 위해서 선언한 변수로 대체했다.
		Rect chh1 = new Rect(X,Y,X+ch_width/7, Y+ch_height/7);
		// 주인공 3개의 그림을 변경해야 마치 나는듯한 모습을 보인다. 주인공 그림 배열중 ch_cnt1번째에 해당하는 그림을 보인다.
		Canvas.drawBitmap(ch_Img[ch_cnt1].getBitmap(),null,chh1,null);
		
		//왼쪽버튼을 가로20, 세로710위치에 출력
		//실수로 버튼 그림을 크게 그렸다. 그래서 폭 137,높이154를 2로 나눈크기로 출력
		// new Rect형식을 알고 있으리라 본다. 뒤쪽 20+137/2는 출력가로좌표+출력할대상폭
		Rect bnn_left = new Rect(20,710,20+137/2,710+154/2);
		//버튼 그림중 th_sw_left번호에 해당하는 왼쪽버튼을 출력한다.
		//초기값이 0이었으므로 누르기전 상태의 버튼을 출력
		Canvas.drawBitmap(btn_left[th_sw_left].getBitmap(),null,bnn_left,null);
		
		//오른쪽버튼을 가로395,세로710위치에 출력한다.
		Rect bnn_right = new Rect(395,710,395+137/2,710+154/2);
		//버튼 그림중 th_sw_right번호에 해당하는 오른쪽버튼을 출력한다.
		// 초기값이 0이었으므로 누르기전 상태의 버튼을 출력
		Canvas.drawBitmap(btn_right[th_sw_right].getBitmap(),null,bnn_right,null);
		
		
		
		Paint p = new Paint();
		Canvas.drawText("XX"+xx+" YY"+yy,10,30,p);
		//병아리 폭을 출력해봤다.
		//그림작업시 폭을 알고 작업하지만 기억하기 싫다면 아래처럼 함수를 사용해서 그림의 크기등 각종 정보를 알 수 있다.
		Canvas.drawText("병아리폭: "+ch_Img[ch_cnt1].getBitmap().getWidth(),10,60,p);
		
		
		// 배경은악을 한번만 재생하며 무한반복 하도록 하였다.
		// stage1_snd변수값이 true이므로 첫번째 스테이지가 시작하면 배경음악이 재생됨
		if(stage1_snd==true){
			stage1_snd=false; //한번만 배경음을 실행하면되므로 다시 현재명령이 실행되지 않도록 stage_snd를 false로 하였다.
			//setVolume의 범위는 0.0f~1.0f이고 1.0은 핸드폰의 MAX 볼륨이 아니라 현재의 볼륨소리이다.
			//배경음악이 다소 소리가 커 불륨을 줄였다. 인수값은 실수값을 주어야한다.
			backsound.setVolume(0.5f,0.5f);
			backsound.start(); // 배경음악이 시작된다.
			backsound.setLooping(true); // 무한반복을 한다.
		}
	}
	
	//뷰가 종료될때 실행되는 이벤트
	//뷰가 종료될때 사운드, 쓰레드를 종료했다.
	@Override
	public void onDetachedFromWindow(){
		// 이제 종료할 때 MediaPlayer도 같이 종료할 수 있게 된다.
		// 아래는 Mediaplayer를 모두 종료하는 내용이다.
		btnsound.stop(); // 사운드 종료
		btnsound.release(); //메모리에서 해체
		btnsound=null;
		backsound.stop();
		backsound.release();
		backsound=null;
		
		//아래처럼 종료시 앱종료 메세지가 보일 수 있다.
		//이를 해결하는 방법은 나중에 별도로 만든 강좌를 참고
		//SurfaceView가 종료될 때 쓰레드를 종료한다.
		//만약 종료시 앱종료 메세지가 보이지 않을 수 있다. 하지만 보이는 경우가 많을듯
		//다음 단원에서는 메세지가 보이지 않도록 다른 thread를 사용하는 것을 학습
		boolean retry1=true;
		thread1.setDaemon(false); // thread1 종료
		while(retry1){
			try{
				thread1.join(); //join:thread1이 즉 쓰레드가 종료될때까지 기달린다.
				retry1=false;
			}catch(InterruptedException e){}
		}
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
				//왼쪽 영역을 누르면 th_sw_left변수값을 1로 변경
				//그럼 위쪽 onDraw부분에서 왼쪽버튼을 누른상태의 그림으로 변경되어 출력
				th_sw_left=1;
				//버튼을 눌렀을 때만 재생하도록 했다. ACTION_MOVE 즉, 터치후 움직일 때도 소리가 계속 들린다.
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					btnsound.seekTo(0); //버튼을 누르면 효과음의 처음부터 소리가 재생되도록한다.
					btnsound.start(); // 소리를 재생한다.
				}
			}
			rt.set(380,700,475,800);
			if(rt.contains(xx,yy)){
				sXR = true;
				//오른쪽 영역을 누르면 th_sw_right변수값을 1로 변경
				//그럼 위쪽 onDraw부분에서 오른쪽버튼을 누른상태의 그림으로 변경되어 출력
				th_sw_right=1;
				//버튼을 눌렀을 때만 재생하도록 했다. ACTION_MOVE 즉, 터치후 움직일 때도 소리가 계속 들린다.
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					btnsound.seekTo(0); //버튼을 누르면 효과음의 처음부터 소리가 재생되도록한다.
					btnsound.start(); // 소리를 재생한다.
				}
			}
		}else{
			sXL=false;
			sXR=false;
			//화면에서 손을 떼면 버튼 그림을 누르기전 상태로 변경하기 위해 값을 0으로 변경
			//0으로 변경하면 0번방의 그림으로 변경
			th_sw_left=0;
			th_sw_right=0;
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
				// 이전에는 이 부분에 시간지연 명령이 있었다.
				// 현재 단원에서는 이 시간지연 내용을 좀더 효율적으로 사용해보자.
				// 어느 부분에서든지 사용가능하도록 변경한다.
				// 시간지연의 의미는 프레임수를 말한다. fps 초당 화면에 몇개의 화면으로 보여질 것인가
				// 모두 같은 속도가 아닌 다른 속도로 보여져야 하기 때문에 이 시간지연 함수가 필요
				// 물론 a+=1, a+=3 등으로 속도 조절할 수 있다고 생각하지만, 한계가 있기 떄문에 계간속도를 조절할 필요가 있다.
				// 어떤 곳에서는 a+=1을 0.3초마다 어떤곳에서는 0.6초마다 증가하는 것을 조정한다는 것
				// 아래는 50delay 호출 0.002(2/1000)초를 50번 반복하고 1번 처리 한다는 것
				// 0.002 * 50 = 0.1초 마다 처리하게된다.
				delay_time50(); //50delay호출
				//0.002 * 3 = 0.006초마다 처리하게 된다.
				delay_time3(); // 3delay호출
				cloud_mov(); // 구름이동
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
		// 이전에는  if(delay_time1==50)조건을 두고 그림변경 속도만을 제어하도록 delay_time1변수를 사용
		// 현재 단원에서는 delay_time1의 시간지연을 어느곳에서든지 사용하도록 변경했다.
		// delay_time1_sw변수가 true일때만 ch_cnt1을 증가시키며 캐릭터모양을 변경
		if(delay_time1_sw==true){
			ch_cnt1++;
			if(ch_cnt1>2){
				ch_cnt1=0;
			}
		}
		
	};
	//구름 이동함수를 만들고 핸들러에서 2/1000초마다 구름이동함수를 호출한다.
	//함수내에서는 좌표값을 증가시켜 onDraw부분에서 구름들이 해당 좌표에 출력하도록 하는 것이다.
	private void cloud_mov(){ // 구름이동 함수
		//이전에는 cloud_mov()가 핸들러이에 호출 될 때마다 구름의 세로(y)좌표를 증가시켜 구름을 이동
		// 문제는 너무 빠르다. 그렇다고 게임의 전체 속도를 늦추면 다른 곳에서 문제가 발생한다.
		// 그래서 세로좌표의 증가를 delay_time2_sw변수가 true일때만 증가하도록 했다.
		// delay_time2_sw변수는 아래 시간지연 함수에서 초기화하도록 했다.
		if(delay_time2_sw==true)cloud_y[0]+=3;
		if(delay_time2_sw==true)cloud_y[1]+=1;
		if(delay_time2_sw==true)cloud_y[2]+=2;
		
		//현재부터는 구름의 위치가 900보다 커지면 해당 방번호의 구름위치를 다시 구한다.
		//cloud_x_y_po()함수에 (0)0번 숫자를 보내 0번방의 구름의 x,y위치를 구한다.
		if(cloud_y[0]>900)cloud_x_y_po(0);
		if(cloud_y[1]>900)cloud_x_y_po(1);
		if(cloud_y[2]>900)cloud_x_y_po(2);
	};
	//구름의 초기 위치를 결정하기 위한 함수이다.
	private void cloud_x_y_po(int cnt){ //구름좌표설정 함수
		//구름이 내려오는 가로 좌표를 매번 다른 곳에서 내려오도록 해야한다.
		// 그래야 구름이지. 그래서 매번 다른 위치의 난수를 발생하는 함수를 선언했다.
		// rnd 난수 객체를 만든다.
		Random rnd = new Random();
		// 아래는 공식이다.
		// 난수객체 rnd에서 1부터 400까지 수 중에서 임의의 값을 구한다.
		// 화면 폭이 480인데 400으로 한 이유는 480으로 하면 구름의 시작위치가 480이 되어 화면에 보이지 않는다.
		// 화면에 보이게 하기위해 400으로 했다.
		// 구름의 폭이 400보다 큰 경우 화면에서 오른쪽 부분이 잘리게 된다.
		int rnd_num_x = rnd.nextInt(400)+1;
		// 구름의 시작위치도 200부터 400사이의 값을 구하도록 했다.
		// 그래야 구름들이 좀 더 자연스럽게 보일 것이다.
		int rnd_num_y = rnd.nextInt(400)+200;
		// 받은 구름 방번호 cnt에 구한 rnd_num_x좌표를 저장
		cloud_x[cnt] = rnd_num_x;
		// 받은 구름 방번호 cnt에 구한 rnd_num_y좌표를 저장
		// rnd_num_y 앞에 음수를 붙였다. 이유는 구름은 화면바깥 위쪽 -위치에서부터 내려오도록 하기 때문이다.
		cloud_y[cnt] = -rnd_num_y;
	};
	// 이전까지의 delay_time1은 캐릭터 모양 변경하는 곳에서만 사용이 가능했다.
	// 현재부터는 시간지연50, 즉 쓰레드속도 0.002 * 처리속도50 = 0.1초마다 delay_time1_sw값이 true가 된다.
	// 그리고 바로 false가 된다. 다른 곳에서는 delay_time1_sw가 true될 때마다 동작하도록 프로그램하면 된다.
	// 그러면 delay_time1_sw변수가 0.1초마다 true가 되니 게임속도보다 50배 느린 속도로 처리하는 곳에서 사용
	// 현재 프로그램에서는 캐릭터 변경 속도를 게임속도보다 50배 느린속도로 변경한다. 
	private void delay_time50(){
		// delay_time1값이 50미만이면 delay_time1_sw변수를 false로 한다.
		if(delay_time1 < 50){
			delay_time1_sw = false;
			delay_time1++;
		// 만약 delay_time1값이 50이 되면 일시적으로 delay_time1_sw변수의 값을 true로 한다.
		// 그리고 바로 delay_time1값을 0으로 하고 delay_time1_sw변수는 위쪽에서 다시 false로 된다.
		// 일시적으로 true로 되었을 때 어떤 명령을 사용하도록 하는 것이다.
		}else if(delay_time1 == 50){
			delay_time1_sw = true;
			delay_time1=0;
		}
	};
	//쓰레드처리속도 0.002 * 처리속도3 = 0.006초마다 delay_time2_sw값을 true로 해서 명령을 처리한다.
	private void delay_time3(){
		if(delay_time2<3){
			delay_time2_sw=false;
			delay_time2++;
		}else if(delay_time2==3){
			delay_time2_sw=true;
			delay_time2=0;
		}
	};
}
