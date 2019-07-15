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
	//���� �ܿ������� ����׸���ư�� �߰��ȴ�.
	//���ʹ���׸��� ������ ���� ���� ���� �ΰ��� �׸��� �����ϴ� �׸�����迭��ü ����
	BitmapDrawable btn_left[] = new BitmapDrawable[2];
	//�����ʹ���׸��� ������ ���� ���� ���� �ΰ��� �׸��� �����ϴ� �׸�����迭��ü ����
	BitmapDrawable btn_right[] = new BitmapDrawable[2];
	//�����׸� 3���� �����ϴ� �������� ��ü�迭�� �����.
	BitmapDrawable cloud_ch_img[] = new BitmapDrawable[3];
	//Context�� ���� Activity instance�� ���� ���ҽ��� �����ϰų� ����� �����ϱ� ���� ���
	Context _context; // Context��ü�� ����
	
	// ���� View���¿��� MediaPlayer����ϸ� ���� ������� ������ ������
	// �������ó�� ��� �ݺ� ����ϴ� ��� ���� �����ؼ� �� ���μ����� ������ ���������� ��������� ��� ���
	// ��Ƽ��Ƽ������ ����� Destroy���� mp3�� stop�� �� �ִµ� ���� View������ Destroy�� ���� ������
	// �������� ������ �� ����.
	MediaPlayer backsound; //������ǿ� ���� ��ü �����
	MediaPlayer btnsound; // ��ư�� ���� ��ü �����
	boolean stage1_snd; // ���������� ���� ��� ����ġ ����
	
	
	int X=200,Y=550;
	boolean sXL=false,sXR=false;
	float Scale_X;
	float Scale_Y;
	int xx, yy;
	BackThread thread1 = new BackThread();
	
	boolean retry = true;
	int ch_cnt1 = 0;
	//���ΰ� �׸��� ����ӵ��� �ʹ� ������ �ӵ��� �����ϱ� ���� ������ �����.
	int delay_time1=0;
	// �ð����� ����ġ ������ �����.
	boolean delay_time1_sw=false; // �ð����� ����ġ
	// �ð����� ������ �߰��Ѵ�.
	int delay_time2=0;
	// �ð����� ����ġ ������ �����.
	boolean delay_time2_sw=false; // �ð����� ����ġ
	// �Ʒ� ������ ���ΰ��� �׸��� ���� ���̸� �����ϴ� �����̴�.
	// ĳ���͵��� ��,���̸� �����δ� ���ֻ���ϰ� �� ���̴�. �浹�˻��� ����ϱ⶧��
	int ch_width,ch_height;
	//�� �����ư�� ������ ���� �����׸����� �����ؾ� �ϴµ� �������¸� üũ�ϴ� �����̴�.
	int th_sw_left=0, th_sw_right=0;
	//�����׸��� ����X��ǥ�� �����ϴ� �迭3���� �����.
	int cloud_x[] = new int[3];
	//�����׸��� ����Y��ǥ�� �����ϴ� �迭3���� �����.
	int cloud_y[] = new int[3];
	
	// �ʱ�ȭ �κ�, �׸����� , �����ʱ�ȭ���� �Ѵ�. ���۽� ��ó�� ����
	public Img_View1(Context context){
		super(context);
		
		// ���ΰ� ù��° ���� ����� �׸��� �����Ѵ�.
		ch_Img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch1);
		// ���ΰ� �ι�° ���� ����� �׸��� �����Ѵ�.
		ch_Img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch2);
		// ���ΰ� ����° ���� ����� �׸��� �����Ѵ�.
		ch_Img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ch3);
		//���׸� ����
		Back_Img1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.back1);
		
		// ���ΰ� �׸��� �������̸� ����, �� ���� ����ǻ��,�׸���,�̴�,���伥���� ���� ����
		ch_width=492;
		ch_height=435;
		
		// �����̵���ư �������� �׸��� ����
		btn_left[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_left1);
		// �����̵���ư ������ �׸��� ����
		btn_left[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_left2);
		// �������̵���ư �������� �׸��� ����
		btn_right[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_right1);
		// �����̵���ư ������ �׸��� ����
		btn_right[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.btn_right2);
		
		//�����׸� ������������ �����Ѵ�.
		cloud_ch_img[0] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud1);
		//�����׸� �߰�ũ���ΰ��� �����Ѵ�.
		cloud_ch_img[1] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud2);
		//�����׸� ����ūũ���ΰ��� �����Ѵ�.
		cloud_ch_img[2] = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud3);
		//�����׸��� ȭ�鿡 ����� �� ���� ��ġ�� �����ϴ� �Լ��� ȣ��
		// ������ ��ǥ�� �ʱ�ȭ�ϴ� �Լ��� �ش� ������ ��ȣ�� �޾� �ʱ�ȭ�ϴ� ������� �����Ѵ�.
		// ������ ������ ��ġ�� �Ź� �ٸ� ������ ���������� �ϴ� ���̴�.
		// �� ������ �Ź� �ٸ� �ӵ��� ������ �������� ��ġ�� �����ؾ��ϴ� ��ġ�� �ʱ�ȭ�ؼ� �� ��ġ���� �ٽ� ���������� �ؾ��Ѵ�.
		// �Ź� �ٸ� ������ �����Ϸ��� ������ ���ȣ ������� ��ġ�� �ʱ�ȭ�ؼ� ����ġ���� �ٽ� ���������� �ؾ��Ѵ�.
		for(int i=0;i<=2;i++)
			cloud_x_y_po(i);
		
		_context=context;
		stage1_snd = true; // ��������1 ���� ����� true ������� �Ѵ�.
		// ���ҽ� ����res�� raw������ ����� free_back.mp3�� �߰�
		// ���ҽ��� �߰��� R.raw.free_back �����带 ����ϵ��� ����
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
		
		Canvas.drawBitmap(Back_Img1.getBitmap(),0,0,null); //���׸����
		
		//������ cloud_x[0],cloud_y[0]��ǥ�� ���, ����ǥ�� �Ʒ��� cloud_mov�Լ����� ����
		//������ ���ʿ��� �Ʒ��������� �̵��ϰ� �ȴ�.
		//�������� �������
		Canvas.drawBitmap(cloud_ch_img[0].getBitmap(),cloud_x[0],cloud_y[0],null );
		//�߰�ũ�� �������
		Canvas.drawBitmap(cloud_ch_img[1].getBitmap(),cloud_x[1],cloud_y[1],null );
		//����ū �������
		Canvas.drawBitmap(cloud_ch_img[2].getBitmap(),cloud_x[2],cloud_y[2],null );
		
		//���ΰ�ũ�⸦ ���ؼ� ������ ������ ��ü�ߴ�.
		Rect chh1 = new Rect(X,Y,X+ch_width/7, Y+ch_height/7);
		// ���ΰ� 3���� �׸��� �����ؾ� ��ġ ���µ��� ����� ���δ�. ���ΰ� �׸� �迭�� ch_cnt1��°�� �ش��ϴ� �׸��� ���δ�.
		Canvas.drawBitmap(ch_Img[ch_cnt1].getBitmap(),null,chh1,null);
		
		//���ʹ�ư�� ����20, ����710��ġ�� ���
		//�Ǽ��� ��ư �׸��� ũ�� �׷ȴ�. �׷��� �� 137,����154�� 2�� ����ũ��� ���
		// new Rect������ �˰� �������� ����. ���� 20+137/2�� ��°�����ǥ+����Ҵ����
		Rect bnn_left = new Rect(20,710,20+137/2,710+154/2);
		//��ư �׸��� th_sw_left��ȣ�� �ش��ϴ� ���ʹ�ư�� ����Ѵ�.
		//�ʱⰪ�� 0�̾����Ƿ� �������� ������ ��ư�� ���
		Canvas.drawBitmap(btn_left[th_sw_left].getBitmap(),null,bnn_left,null);
		
		//�����ʹ�ư�� ����395,����710��ġ�� ����Ѵ�.
		Rect bnn_right = new Rect(395,710,395+137/2,710+154/2);
		//��ư �׸��� th_sw_right��ȣ�� �ش��ϴ� �����ʹ�ư�� ����Ѵ�.
		// �ʱⰪ�� 0�̾����Ƿ� �������� ������ ��ư�� ���
		Canvas.drawBitmap(btn_right[th_sw_right].getBitmap(),null,bnn_right,null);
		
		
		
		Paint p = new Paint();
		Canvas.drawText("XX"+xx+" YY"+yy,10,30,p);
		//���Ƹ� ���� ����غô�.
		//�׸��۾��� ���� �˰� �۾������� ����ϱ� �ȴٸ� �Ʒ�ó�� �Լ��� ����ؼ� �׸��� ũ��� ���� ������ �� �� �ִ�.
		Canvas.drawText("���Ƹ���: "+ch_Img[ch_cnt1].getBitmap().getWidth(),10,60,p);
		
		
		// ��������� �ѹ��� ����ϸ� ���ѹݺ� �ϵ��� �Ͽ���.
		// stage1_snd�������� true�̹Ƿ� ù��° ���������� �����ϸ� ��������� �����
		if(stage1_snd==true){
			stage1_snd=false; //�ѹ��� ������� �����ϸ�ǹǷ� �ٽ� �������� ������� �ʵ��� stage_snd�� false�� �Ͽ���.
			//setVolume�� ������ 0.0f~1.0f�̰� 1.0�� �ڵ����� MAX ������ �ƴ϶� ������ �����Ҹ��̴�.
			//��������� �ټ� �Ҹ��� Ŀ �ҷ��� �ٿ���. �μ����� �Ǽ����� �־���Ѵ�.
			backsound.setVolume(0.5f,0.5f);
			backsound.start(); // ��������� ���۵ȴ�.
			backsound.setLooping(true); // ���ѹݺ��� �Ѵ�.
		}
	}
	
	//�䰡 ����ɶ� ����Ǵ� �̺�Ʈ
	//�䰡 ����ɶ� ����, �����带 �����ߴ�.
	@Override
	public void onDetachedFromWindow(){
		// ���� ������ �� MediaPlayer�� ���� ������ �� �ְ� �ȴ�.
		// �Ʒ��� Mediaplayer�� ��� �����ϴ� �����̴�.
		btnsound.stop(); // ���� ����
		btnsound.release(); //�޸𸮿��� ��ü
		btnsound=null;
		backsound.stop();
		backsound.release();
		backsound=null;
		
		//�Ʒ�ó�� ����� ������ �޼����� ���� �� �ִ�.
		//�̸� �ذ��ϴ� ����� ���߿� ������ ���� ���¸� ����
		//SurfaceView�� ����� �� �����带 �����Ѵ�.
		//���� ����� ������ �޼����� ������ ���� �� �ִ�. ������ ���̴� ��찡 ������
		//���� �ܿ������� �޼����� ������ �ʵ��� �ٸ� thread�� ����ϴ� ���� �н�
		boolean retry1=true;
		thread1.setDaemon(false); // thread1 ����
		while(retry1){
			try{
				thread1.join(); //join:thread1�� �� �����尡 ����ɶ����� ��޸���.
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
				//���� ������ ������ th_sw_left�������� 1�� ����
				//�׷� ���� onDraw�κп��� ���ʹ�ư�� ���������� �׸����� ����Ǿ� ���
				th_sw_left=1;
				//��ư�� ������ ���� ����ϵ��� �ߴ�. ACTION_MOVE ��, ��ġ�� ������ ���� �Ҹ��� ��� �鸰��.
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					btnsound.seekTo(0); //��ư�� ������ ȿ������ ó������ �Ҹ��� ����ǵ����Ѵ�.
					btnsound.start(); // �Ҹ��� ����Ѵ�.
				}
			}
			rt.set(380,700,475,800);
			if(rt.contains(xx,yy)){
				sXR = true;
				//������ ������ ������ th_sw_right�������� 1�� ����
				//�׷� ���� onDraw�κп��� �����ʹ�ư�� ���������� �׸����� ����Ǿ� ���
				th_sw_right=1;
				//��ư�� ������ ���� ����ϵ��� �ߴ�. ACTION_MOVE ��, ��ġ�� ������ ���� �Ҹ��� ��� �鸰��.
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					btnsound.seekTo(0); //��ư�� ������ ȿ������ ó������ �Ҹ��� ����ǵ����Ѵ�.
					btnsound.start(); // �Ҹ��� ����Ѵ�.
				}
			}
		}else{
			sXL=false;
			sXR=false;
			//ȭ�鿡�� ���� ���� ��ư �׸��� �������� ���·� �����ϱ� ���� ���� 0���� ����
			//0���� �����ϸ� 0������ �׸����� ����
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
				// �������� �� �κп� �ð����� ����� �־���.
				// ���� �ܿ������� �� �ð����� ������ ���� ȿ�������� ����غ���.
				// ��� �κп������� ��밡���ϵ��� �����Ѵ�.
				// �ð������� �ǹ̴� �����Ӽ��� ���Ѵ�. fps �ʴ� ȭ�鿡 ��� ȭ������ ������ ���ΰ�
				// ��� ���� �ӵ��� �ƴ� �ٸ� �ӵ��� �������� �ϱ� ������ �� �ð����� �Լ��� �ʿ�
				// ���� a+=1, a+=3 ������ �ӵ� ������ �� �ִٰ� ����������, �Ѱ谡 �ֱ� ������ �谣�ӵ��� ������ �ʿ䰡 �ִ�.
				// � �������� a+=1�� 0.3�ʸ��� ��������� 0.6�ʸ��� �����ϴ� ���� �����Ѵٴ� ��
				// �Ʒ��� 50delay ȣ�� 0.002(2/1000)�ʸ� 50�� �ݺ��ϰ� 1�� ó�� �Ѵٴ� ��
				// 0.002 * 50 = 0.1�� ���� ó���ϰԵȴ�.
				delay_time50(); //50delayȣ��
				//0.002 * 3 = 0.006�ʸ��� ó���ϰ� �ȴ�.
				delay_time3(); // 3delayȣ��
				cloud_mov(); // �����̵�
				ch_img_sub(); // ���Ƹ� ���ȣ ī���� ȣ��
				invalidate(); // onDraw �޼ҵ带 ��ȣ���ϱ� ���� View�� �ʱ�ȭ
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
			if(X<=480-ch_width/7){
				//���ΰ��� x���� 1�� �����ؼ� �������� �̵�
				X+=1;
			}else{
				//���ΰ��� ��ǥ�� ������ ���� �ֵ��� �Ʒ� ��� ����
				X=480-ch_width/7;
			}
		}
		// ��������  if(delay_time1==50)������ �ΰ� �׸����� �ӵ����� �����ϵ��� delay_time1������ ���
		// ���� �ܿ������� delay_time1�� �ð������� ������������� ����ϵ��� �����ߴ�.
		// delay_time1_sw������ true�϶��� ch_cnt1�� ������Ű�� ĳ���͸���� ����
		if(delay_time1_sw==true){
			ch_cnt1++;
			if(ch_cnt1>2){
				ch_cnt1=0;
			}
		}
		
	};
	//���� �̵��Լ��� ����� �ڵ鷯���� 2/1000�ʸ��� �����̵��Լ��� ȣ���Ѵ�.
	//�Լ��������� ��ǥ���� �������� onDraw�κп��� �������� �ش� ��ǥ�� ����ϵ��� �ϴ� ���̴�.
	private void cloud_mov(){ // �����̵� �Լ�
		//�������� cloud_mov()�� �ڵ鷯�̿� ȣ�� �� ������ ������ ����(y)��ǥ�� �������� ������ �̵�
		// ������ �ʹ� ������. �׷��ٰ� ������ ��ü �ӵ��� ���߸� �ٸ� ������ ������ �߻��Ѵ�.
		// �׷��� ������ǥ�� ������ delay_time2_sw������ true�϶��� �����ϵ��� �ߴ�.
		// delay_time2_sw������ �Ʒ� �ð����� �Լ����� �ʱ�ȭ�ϵ��� �ߴ�.
		if(delay_time2_sw==true)cloud_y[0]+=3;
		if(delay_time2_sw==true)cloud_y[1]+=1;
		if(delay_time2_sw==true)cloud_y[2]+=2;
		
		//������ʹ� ������ ��ġ�� 900���� Ŀ���� �ش� ���ȣ�� ������ġ�� �ٽ� ���Ѵ�.
		//cloud_x_y_po()�Լ��� (0)0�� ���ڸ� ���� 0������ ������ x,y��ġ�� ���Ѵ�.
		if(cloud_y[0]>900)cloud_x_y_po(0);
		if(cloud_y[1]>900)cloud_x_y_po(1);
		if(cloud_y[2]>900)cloud_x_y_po(2);
	};
	//������ �ʱ� ��ġ�� �����ϱ� ���� �Լ��̴�.
	private void cloud_x_y_po(int cnt){ //������ǥ���� �Լ�
		//������ �������� ���� ��ǥ�� �Ź� �ٸ� ������ ���������� �ؾ��Ѵ�.
		// �׷��� ��������. �׷��� �Ź� �ٸ� ��ġ�� ������ �߻��ϴ� �Լ��� �����ߴ�.
		// rnd ���� ��ü�� �����.
		Random rnd = new Random();
		// �Ʒ��� �����̴�.
		// ������ü rnd���� 1���� 400���� �� �߿��� ������ ���� ���Ѵ�.
		// ȭ�� ���� 480�ε� 400���� �� ������ 480���� �ϸ� ������ ������ġ�� 480�� �Ǿ� ȭ�鿡 ������ �ʴ´�.
		// ȭ�鿡 ���̰� �ϱ����� 400���� �ߴ�.
		// ������ ���� 400���� ū ��� ȭ�鿡�� ������ �κ��� �߸��� �ȴ�.
		int rnd_num_x = rnd.nextInt(400)+1;
		// ������ ������ġ�� 200���� 400������ ���� ���ϵ��� �ߴ�.
		// �׷��� �������� �� �� �ڿ������� ���� ���̴�.
		int rnd_num_y = rnd.nextInt(400)+200;
		// ���� ���� ���ȣ cnt�� ���� rnd_num_x��ǥ�� ����
		cloud_x[cnt] = rnd_num_x;
		// ���� ���� ���ȣ cnt�� ���� rnd_num_y��ǥ�� ����
		// rnd_num_y �տ� ������ �ٿ���. ������ ������ ȭ��ٱ� ���� -��ġ�������� ���������� �ϱ� �����̴�.
		cloud_y[cnt] = -rnd_num_y;
	};
	// ���������� delay_time1�� ĳ���� ��� �����ϴ� �������� ����� �����ߴ�.
	// ������ʹ� �ð�����50, �� ������ӵ� 0.002 * ó���ӵ�50 = 0.1�ʸ��� delay_time1_sw���� true�� �ȴ�.
	// �׸��� �ٷ� false�� �ȴ�. �ٸ� �������� delay_time1_sw�� true�� ������ �����ϵ��� ���α׷��ϸ� �ȴ�.
	// �׷��� delay_time1_sw������ 0.1�ʸ��� true�� �Ǵ� ���Ӽӵ����� 50�� ���� �ӵ��� ó���ϴ� ������ ���
	// ���� ���α׷������� ĳ���� ���� �ӵ��� ���Ӽӵ����� 50�� �����ӵ��� �����Ѵ�. 
	private void delay_time50(){
		// delay_time1���� 50�̸��̸� delay_time1_sw������ false�� �Ѵ�.
		if(delay_time1 < 50){
			delay_time1_sw = false;
			delay_time1++;
		// ���� delay_time1���� 50�� �Ǹ� �Ͻ������� delay_time1_sw������ ���� true�� �Ѵ�.
		// �׸��� �ٷ� delay_time1���� 0���� �ϰ� delay_time1_sw������ ���ʿ��� �ٽ� false�� �ȴ�.
		// �Ͻ������� true�� �Ǿ��� �� � ����� ����ϵ��� �ϴ� ���̴�.
		}else if(delay_time1 == 50){
			delay_time1_sw = true;
			delay_time1=0;
		}
	};
	//������ó���ӵ� 0.002 * ó���ӵ�3 = 0.006�ʸ��� delay_time2_sw���� true�� �ؼ� ����� ó���Ѵ�.
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
