package com.example.downdown_first_app_1;

import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.*;
import com.example.downdown_first_app_1.person_data;

public class Img_View1 extends View{
	BitmapDrawable ch_Img[] = new BitmapDrawable[3];
	BitmapDrawable Back_Img1 = null;
	BitmapDrawable btn_left[] = new BitmapDrawable[2];
	BitmapDrawable btn_right[] = new BitmapDrawable[2];
	int rain_num = 5;
	BitmapDrawable rain_img[] = new BitmapDrawable[rain_num];
	int cloud_num = 3;
	BitmapDrawable cloud_ch_img[] = new BitmapDrawable[cloud_num];
	BitmapDrawable stage_top_img = null;
	BitmapDrawable start_btn_img = null;
	BitmapDrawable end_msg_img = null;
	BitmapDrawable coin_img = null;
	BitmapDrawable restart_btn_img = null;
	BitmapDrawable end_game_btn_img = null;
	BitmapDrawable rank_btn_img = null;
	BitmapDrawable rank_top_img = null;
	Context _context;
	SoundPool msoundpool;
	HashMap<Integer,Integer>msoundpoolmap;
	AudioManager maudiomanager;
	int vol=0;
	MediaPlayer backsound;
	
	
	boolean stage1_snd; 
	
	int X=200,Y=600;
	boolean sXL=false,sXR=false;
	float Scale_X;
	float Scale_Y;
	int xx, yy;
	BackThread thread1 = new BackThread();
	boolean thread1_pause=false;
	
	int ch_cnt1 = 0;
	int delay_time1=0;
	boolean delay_time1_sw=false; 
	int delay_time2=0;
	boolean delay_time2_sw=false; 
	int ch_width,ch_height;
	int rain_width, rain_height;
	int th_sw_left=0, th_sw_right=0;
	int cloud_x[] = new int[cloud_num];
	int cloud_y[] = new int[cloud_num];
	int coin_x = 0;
	int coin_y = 0;
	int rain_x[] = new int[rain_num];
	int rain_y[] = new int[rain_num];
	int eaten_coin = 0;
	int start_btn_width, start_btn_height;
	int end_btn_width, end_btn_height;
	int stage = 0;
	int stage_top_width, stage_top_height;
	int score=0;
	int restart_btn_width, restart_btn_height;
	int end_game_btn_width, end_game_btn_height;
	int rank_btn_width, rank_btn_height;
	int rank_top_width, rank_top_height;
	ArrayList<person_data> list_data = new ArrayList<person_data>();
	int count_value;
	
	public final String PREFERENCE = "com.example.downdown_first_app_1";
	
	
	
	public Img_View1(Context context){
		super(context);

		ch_Img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch1);
		ch_Img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch2);
		ch_Img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch3);
		Back_Img1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.back_img);
		
		ch_width=492;
		ch_height=435;
		
		btn_left[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_left1);
		btn_left[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_left2);
		btn_right[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_right1);
		btn_right[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_right2);	
		
		cloud_ch_img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud1);
		cloud_ch_img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud2);
		cloud_ch_img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud3);
		
		for(int i=0;i<5;i++)
			rain_img[i] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.rain);
		
		rain_width = 127;
		rain_height = 230;
		
		start_btn_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.start_btn);
		start_btn_width = 245;
		start_btn_height = 96;
		
		end_msg_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.end_msg);
		//end_btn_width = 399;
		//end_btn_height = 139;
		
		stage_top_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.stage_top);
		stage_top_width = 441;
		stage_top_height = 134;
		
		restart_btn_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.restart_btn);
		restart_btn_width = 260;
		restart_btn_height = 85;
		
		end_game_btn_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.end_game_btn);
		end_game_btn_width = 260;
		end_game_btn_height = 85;
		
		rank_btn_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.rank_btn);
		rank_btn_width = 260;
		rank_btn_height = 85;
		
		rank_top_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.top_4_rank);
		rank_top_width = 645;
		rank_top_height = 182;
		
		
		//동전 넣기
		coin_img = (BitmapDrawable) context.getResources().getDrawable(R.drawable.coin);
		
		
		count_value = 0;
		
		
		// 사진위치 함수 실행
		for(int i=0;i<cloud_num;i++)
			cloud_x_y_po(i);
		for(int i=0;i<rain_num;i++)
			rain_x_y_po(i);
		coin_x_y_po();
			
		
		_context=context;
		stage1_snd = true;
		setEffects(_context,2);
		addSound(_context,0,R.raw.left); 
		addSound(_context,1,R.raw.right);
		
		backsound = MediaPlayer.create(_context, R.raw.free_back);
		
		
		thread1.setRunning(true);
		thread1.start();
		
		
		
	}
	//사운드효과
	public void setEffects(Context cont, int number){
		msoundpool = new SoundPool(number, AudioManager.STREAM_MUSIC,0);
		msoundpoolmap = new HashMap<Integer, Integer>();
		maudiomanager = (AudioManager)cont.getSystemService(cont.AUDIO_SERVICE);
		vol = maudiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	//사운드추가
	public void addSound(Context cont, int ID, int name){
		msoundpoolmap.put(ID, msoundpool.load(cont, name, 1));
	}
	//사운드재생
	public int playSound(int ID, int count, float speed){
		//현재 볼륨크기에 따라 재생함
		int vol = maudiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);
		return msoundpool.play(msoundpoolmap.get(ID), vol, vol, 1, (count-1), speed);
	}
	//사운드멈춤
	public void stopSound(int ID){
		msoundpool.stop(ID);
	}
	
	
	@Override
	public void onDraw(Canvas Canvas){
		Scale_X = Canvas.getWidth() / 480f;
		Scale_Y = Canvas.getHeight() / 800f;
		
		Canvas.scale(Scale_X, Scale_Y);
		
		if (stage == 0){
			Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null);
			Canvas.drawBitmap(stage_top_img.getBitmap(),0,0,null);
			Rect start_btn = new Rect(120, 500, 120+start_btn_width, 500+start_btn_height);
			Canvas.drawBitmap(start_btn_img.getBitmap(),null,start_btn, null);
		}
		else if (stage == 1){
			Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null); 
			
			for (int i=0;i<cloud_num;i++){		
				Rect cloud_size = new Rect(cloud_x[i],cloud_y[i],cloud_x[i]+ch_width/7,cloud_y[i]+ch_height/7);
				Canvas.drawBitmap(cloud_ch_img[i].getBitmap(), null, cloud_size, null);
			}
			
				
			Rect chh1 = new Rect(X,Y,X+ch_width/7, Y+ch_height/7);
			Canvas.drawBitmap(ch_Img[ch_cnt1].getBitmap(),null,chh1,null);
			
		
			Rect bnn_left = new Rect(20,710,20+137/2,710+154/2);
			Canvas.drawBitmap(btn_left[th_sw_left].getBitmap(),null,bnn_left,null);
				
				
			Rect bnn_right = new Rect(395,710,395+137/2,710+154/2);
			Canvas.drawBitmap(btn_right[th_sw_right].getBitmap(),null,bnn_right,null);
		

			Paint p = new Paint();
			p.setColor(Color.YELLOW);
			p.setTextSize(10);
			Canvas.drawText("XX"+xx+" YY"+yy,10,30,p);
			Canvas.drawText("병아리폭: "+ch_Img[ch_cnt1].getBitmap().getWidth(),10,60,p);
				
			int coin_size_x = 158;
			int coin_size_y = 149;
				
			Rect coin = new Rect(coin_x, coin_y, coin_x+coin_size_x/4, coin_y+coin_size_y/4);
			Canvas.drawBitmap(coin_img.getBitmap(), null, coin, null);
				
			Rect eaten_coin_img = new Rect(330,30,330+coin_size_x/4, 30+coin_size_y/4);
			Canvas.drawBitmap(coin_img.getBitmap(), null, eaten_coin_img, null);
			p.setTextSize(30);
			p.setColor(Color.BLACK);
			Canvas.drawText(""+eaten_coin, 420, 60, p);
			Canvas.drawText("점수:   "+score, 300, 100, p);
				
			for(int i=0;i<rain_num;i++){
				Rect rain = new Rect(rain_x[i], rain_y[i], rain_x[i]+rain_width/7, rain_y[i]+rain_height/7);
				Canvas.drawBitmap(rain_img[i].getBitmap(), null, rain, null);
			}
					
			if(stage1_snd==true){
				stage1_snd=false; 
				backsound.setVolume(0.5f,0.5f);
				backsound.start();
				backsound.setLooping(true); 
			}
			
		}
		else if(stage == 3){
			count_value++;
			Rect end_top = new Rect(0, 80, 480, 200);
			Canvas.drawBitmap(end_msg_img.getBitmap(),null,end_top,null);
			
			Paint p = new Paint();
			p.setColor(Color.DKGRAY);
			p.setTextSize(25);
			Canvas.drawText("동전 수: "+eaten_coin, 100, 300, p);
			Canvas.drawText("점    수: "+score, 100, 340, p);
			
			if(count_value == 1)
				list_data.add(new person_data(score, eaten_coin));
			
			Rect restart_btn = new Rect(100, 400, 100+restart_btn_width, 400+restart_btn_height);
			Canvas.drawBitmap(restart_btn_img.getBitmap(), null, restart_btn, null);
			
			Rect end_game_btn = new Rect(100, 520, 100+end_game_btn_width, 520+end_game_btn_height);
			Canvas.drawBitmap(end_game_btn_img.getBitmap(), null, end_game_btn, null);
			
			Rect rank_btn = new Rect(100, 640, 100+rank_btn_width, 640+rank_btn_height);
			Canvas.drawBitmap(rank_btn_img.getBitmap(), null, rank_btn, null);
		}
		else if(stage == 4){
			//바탕
			Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null); 
			
			//맨 위 전체 순위 표
			Rect rank_top = new Rect(0, 80, 480, 200);
			Canvas.drawBitmap(rank_top_img.getBitmap(), null, rank_top, null);
			
			Paint p = new Paint();
			p.setTextSize(30);
			p.setColor(Color.BLACK);
			Canvas.drawText("등수", 100, 250, p);
			Canvas.drawText("점수", 200, 250, p);
			Canvas.drawText("동전수", 300, 250, p);
			Canvas.drawText(String.valueOf(list_data.size()),200,50,p);
			
			
			Collections.sort(list_data);
			for(int i=0; i<list_data.size(); i++){
				if(i==10)
					break;
				person_data person = list_data.get(i);
				String eaten_coin_value = String.valueOf(person.getEaten_coin());
				String score_value = String.valueOf(person.getScore());
				Canvas.drawText(String.valueOf(i+1), 110, 300+40*i, p);
				Canvas.drawText(score_value, 200, 300+40*i, p);
				Canvas.drawText(eaten_coin_value, 330, 300+40*i, p);
			}
			
			Rect end_game_btn = new Rect(0, 640+end_game_btn_height, 100 , 640+(end_game_btn_height*2));
			Canvas.drawBitmap(end_game_btn_img.getBitmap(), null, end_game_btn, null);
			
			Rect restart_btn = new Rect(120+restart_btn_width, 640+restart_btn_height, 220+restart_btn_width, 640+(restart_btn_height*2));
			Canvas.drawBitmap(restart_btn_img.getBitmap(), null, restart_btn, null);
			
			
			
			
		}
	}
	
	@Override
	public void onDetachedFromWindow(){
		stopSound(0);
		stopSound(1);
		backsound.stop();
		backsound.release();
		backsound=null;
		
		
		boolean retry1=true;
		thread1.setRunning(false); 
		while(retry1){
			try{
				thread1.join(); 
				retry1=false;
			}catch(InterruptedException e){}
		}
	}
	
	
	@Override
	public void onWindowVisibilityChanged(int visibility){
		super.onWindowVisibilityChanged(visibility);
		if(visibility==View.VISIBLE){ 
			backsound.start(); 
			thread1_pause=false;
		}else{ 
			backsound.pause();
			thread1_pause=true;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		//int action = event.getAction();
		
		xx = (int)(event.getX()/Scale_X);
		yy = (int)(event.getY()/Scale_Y);
		Rect rt = new Rect();
		
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
			if(stage==0){
				rt.set(120, 500, 120+start_btn_width, 500+start_btn_height);
				if(rt.contains(xx,yy)){
					stage=1;
				}
			}
			else if(stage==1){
				rt.set(5,700,100,800);
				if(rt.contains(xx,yy)){
					sXL = true;
					th_sw_left=1;
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						playSound(0,1,2f);
					}
				}
				rt.set(380,700,475,800);
				if(rt.contains(xx,yy)){
					sXR = true;
					th_sw_right=1;
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						playSound(1,1,2f);
					}
				}
			}
			else if(stage==3){ // 비를 맞았을 이후
				rt.set(100, 400, 100+restart_btn_width, 400+restart_btn_height);
				if(rt.contains(xx,yy)){
					thread1.setRunning(true);
					init_val();
					thread1.start();
				}
				rt.set(100, 520, 100+end_game_btn_width, 520+end_game_btn_height);
				if(rt.contains(xx,yy)){
					System.exit(0);
				}
				rt.set(100, 640, 100+rank_btn_width, 640+rank_btn_height);
				if(rt.contains(xx,yy)){
					stage=4;
				}	
			}
			else if(stage==4){ //전체 등수표
				rt.set(0, 640+end_btn_height, 100 , 640+(end_game_btn_height*2));
				if(rt.contains(xx,yy)){
					System.exit(0);
				}
				rt.set(100+restart_btn_width, 640+restart_btn_height, 200+restart_btn_width, 640+(restart_btn_height*2));
				if(rt.contains(xx,yy)){
					thread1.setRunning(true);
					init_val();
					thread1.start();
				}
			}
		}else{
			sXL=false;
			sXR=false;
			th_sw_left=0;
			th_sw_right=0;
		}
		return true;
	}

	class BackThread extends Thread{
		private boolean m_run=false;
		public void setRunning(boolean run){
			m_run=run;
		}
		public void run(){
			try{
				if(Thread.interrupted())
					thread1.interrupt();
				while(m_run){
					try{
						if(thread1_pause==false){
							Handler1.sendEmptyMessage(0);
							Thread.sleep(2);
						}
					}catch
						(InterruptedException e){;}
				}
			}catch(Exception exTot){}
		}
	}
	
	Handler Handler1 = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0){
				if(stage==0){
					invalidate();
				}
				else if(stage==1){
					delay_time50();
					delay_time3(); 
					cloud_mov();
					coin_mov();
					rain_mov();
					ch_img_sub();
					compute_score();
					invalidate();
				}
				else if(stage==3){
					invalidate();
				}
				else if(stage==4){
					invalidate();
				}
			}
		}
	};
	
	private void ch_img_sub(){
		if(sXL==true){
			if(X>=0){
				X-=1;
			}else{
				X=0;
			}
		}else if(sXR==true){
			if(X<=480-ch_width/7){
				X+=1;
			}else{
				X=480-ch_width/7;
			}
		}
		if(delay_time1_sw==true){
			ch_cnt1++;
			if(ch_cnt1>2){
				ch_cnt1=0;
			}
		}
		
	};
	private void cloud_mov(){ 
		
		if(delay_time2_sw==true)cloud_y[0]+=3;
		if(delay_time2_sw==true)cloud_y[1]+=1;
		if(delay_time2_sw==true)cloud_y[2]+=2;
		
		
		for (int i=0;i<cloud_num;i++){
			if(cloud_y[i]>900)cloud_x_y_po(i);
		}
		
	};
	
	private void coin_mov(){
		
		if(coin_eat())coin_y=900;
		coin_y += 1;
		if(coin_y>900)coin_x_y_po();
	};
	
	private void rain_mov(){
		
		
		if(delay_time2_sw==true)rain_y[0]+=2;
		if(delay_time2_sw==true)rain_y[1]+=3;
		if(delay_time2_sw==true)rain_y[2]+=1;
		
		for(int i=0;i<rain_num;i++){
			if(rain_attacked(i)){
				stage=3;
			}
			if(rain_y[i]>900)rain_x_y_po(i);
		}
	};
	
	private void rain_x_y_po(int cnt){
		Random rnd = new Random();
		
		int rnd_num_x = rnd.nextInt(400) + 1;
		int rnd_num_y = rnd.nextInt(400) + 200;
		
		rain_x[cnt] = rnd_num_x;
		rain_y[cnt] = -rnd_num_y;
	};

	private void cloud_x_y_po(int cnt){ 
		Random rnd = new Random();

		int rnd_num_x = rnd.nextInt(400) + 1;
		int rnd_num_y = rnd.nextInt(400) + 200;

		cloud_x[cnt] = rnd_num_x;
		cloud_y[cnt] = -rnd_num_y;
	};
	
	private void coin_x_y_po(){
		Random rnd = new Random();
		
		int rnd_num_x = rnd.nextInt(400) + 1;
		int rnd_num_y = rnd.nextInt(400) + 200;
		
		coin_x = rnd_num_x;
		coin_y = -rnd_num_y;
		
		
	};
	
	private void compute_score(){
		if(delay_time2_sw==true)score++;
	};
	
	private boolean coin_eat(){
		boolean eat=false;
		
		if (X <= coin_x && coin_x <= X+ch_width/7 
				&& Y <= coin_y && coin_y <= Y+ch_height/7){
			eaten_coin++;
			eat = true;
		}
		return eat;
	};
	
	private boolean rain_attacked(int cnt){
		boolean attacked=false;
		
		if (X <= rain_x[cnt] && rain_x[cnt] <= X+ch_width/7 
				&& Y <= rain_y[cnt] && rain_y[cnt] <= Y+ch_height/7){
			attacked = true;
		}
		return attacked;
	};
	
	private void init_val(){
		vol=0;
		X=200;
		Y=600;
		sXL=false;
		sXR=false;
		thread1_pause=false;
		thread1 = new BackThread();
		
		//list_data = new ArrayList();
		count_value = 0;
		
		ch_cnt1 = 0;
		delay_time1=0;
		delay_time1_sw=false; 
		delay_time2=0;
		delay_time2_sw=false; 
		th_sw_left=0;
		th_sw_right=0;
		for(int i=0;i<cloud_num;i++){
			cloud_x[i] = 0;
			cloud_y[i] = 0;
		}
		for(int i=0;i<rain_num;i++){
			rain_x[i] = 0;
			rain_y[i] = 0;
		}
		coin_x = 0;
		coin_y = 0;
		eaten_coin = 0;
		stage = 0;
		score = 0;
		
		for(int i=0;i<cloud_num;i++)
			cloud_x_y_po(i);
		for(int i=0;i<rain_num;i++)
			rain_x_y_po(i);
		coin_x_y_po();
	}
	
	private void delay_time50(){

		if(delay_time1 < 50){
			delay_time1_sw = false;
			delay_time1++;
		}else if(delay_time1 == 50){
			delay_time1_sw = true;
			delay_time1=0;
		}
	};
	private void delay_time3(){
		if(delay_time2<3){
			delay_time2_sw=false;
			delay_time2++;
		}else if(delay_time2==3){
			delay_time2_sw=true;
			delay_time2=0;
		}
	};
	
	private ArrayList<person_data> sort_arraylist(ArrayList<person_data> data_list){
		
		ArrayList<Integer> person_int_data = new ArrayList<Integer>();
		ArrayList<person_data> data_list_new = new ArrayList<person_data>();
		
		for(int i=0;i<data_list.size();i++){
			person_int_data.add(data_list.get(i).getScore());
		}
		//Collections.sort(person_int_data);
		
		for(int i=0;i<data_list.size();i++){
			for(int j=0;j<data_list.size();j++){
				if(person_int_data.get(i) == data_list.get(j).getScore())
					data_list_new.add(data_list.get(j));
			}
		}
		
		return data_list_new;
		
	}
	
	
}


