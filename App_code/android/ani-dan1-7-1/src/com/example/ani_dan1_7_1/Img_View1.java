package com.example.ani_dan1_7_1;

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
	//���� �ܿ������� ����׸���ư�� �߰��ȴ�.
	//���ʹ���׸��� ������ ���� ���� ���� �ΰ��� �׸��� �����ϴ� �׸�����迭��ü ����
	BitmapDrawable btn_left[] = new BitmapDrawable[2];
	//�����ʹ���׸��� ������ ���� ���� ���� �ΰ��� �׸��� �����ϴ� �׸�����迭��ü ����
	BitmapDrawable btn_right[] = new BitmapDrawable[2];
	//�����׸� 3���� �����ϴ� �������� ��ü�迭�� �����.
	BitmapDrawable cloud_ch_img[] = new BitmapDrawable[3];
	
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
		cloud_x_y_po();
		
		
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
		//�������� ��ư �׸��� ũ�� �׷ȴ�. �׷��� �� 137,����154�� 2�� ����ũ��� ���
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
			}
			rt.set(380,700,475,800);
			if(rt.contains(xx,yy)){
				sXR = true;
				//������ ������ ������ th_sw_right�������� 1�� ����
				//�׷� ���� onDraw�κп��� �����ʹ�ư�� ���������� �׸����� ����Ǿ� ���
				th_sw_right=1;
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
				//���� �ڵ鷯�� �����忡 ���� 2/1000�ʸ��� ȣ��ǰ� �ִ�.
				//��� ó�� �ӵ��� 0.002�ʸ��� ȣ��ǰ� �ִٴ� ���̴�.
				//0.002�ʸ��� �׸��� �����ϸ� �󸶳� ���� ����ǰڴ°�?
				//�� ��û�� ���� �׸����� ȭ�鿡 ����ؾ��Ѵٸ� ���� ����Ʈ���� 0.002�ʸ��� ó������ ���� �������� ���� �߻��� �� �ִ�.
				//�׷��� ����Ʈ������ �ӵ��� ���� ���̵��� ������ ��� �Ѵ�.
				//�������� delay_time1������ 0.002�ʸ��� 1�� ���� ��Ų��. 50�̸��϶������� �ݺ�
				if(delay_time1<50){
					delay_time1++;
				}
				//�����̵� �Լ��� ȣ��
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
		//delay_time1�������� 50�̸��϶��� �׸�����(���� ch_cnt1�� 1 ����)
		//�������� ch_cnt1������ 0.002���� ȣ��Ǿ� ���ΰ��׸��� ��û���� �����
		//�������ʹ� delay_time1�� 50�� �Ǿ�� 1����
		//�� 0.002�� x 50 = 0.1�� -> 0.1�ʸ��� ���ΰ� �׸��� �����Ѵٴ� �ǹ��̴�.
		if(delay_time1==50){
			//���ΰ��� �׸��� 3���� �׸����� ���������. �� 3���� �׸��� ��ü�ϸ� ��ġ ���� �ִ� ���
			//���ΰ� �׸� ���溯�� ch_cnt1�� 1 ����
			ch_cnt1++;
			if(ch_cnt1>2){
				ch_cnt1=0;
			}
			//delay_time1�� 50�� �Ǿ� ch_cnt1�� 1���� ���� ���ΰ� �׸��� �����ߴٸ� �ٷ� �������� ���ϵ���
			//delay_time1�������� �ٽ� 0���� �ʱ�ȭ�ߴ�.���� �ڵ鷯�� if(delay_time1<50)�� ���� �ٽ� ���̹Ƿ�
			//delay_time1������ 1�� ������
			delay_time1=0;
		}
		
	};
	//���� �̵��Լ��� ����� �ڵ鷯���� 2/1000�ʸ��� �����̵��Լ��� ȣ���Ѵ�.
	//�Լ��������� ��ǥ���� �������� onDraw�κп��� �������� �ش� ��ǥ�� ����ϵ��� �ϴ� ���̴�.
	private void cloud_mov(){ // �����̵� �Լ�
		//ù��° ������ ��ǥ�� ���ʿ��� �Ʒ��������� �̵��ϵ��� 1�� �����Ѵ�. ������ �̵�
		cloud_y[0]+=1;
		//�ι�° ������ ��ǥ�� ���ʿ��� �Ʒ��������� �̵��ϵ��� 3�� �����Ѵ�. ������ �̵�
		cloud_y[1]+=3;
		//����° ������ ��ǥ�� ���ʿ��� �Ʒ��������� �̵��ϵ��� 2�� �����Ѵ�. �߰��ӵ��� �̵�
		cloud_y[2]+=2;
		//��ǥ���� 900, �� ȭ���� ����� �ٽ� ���� -200��ġ�� �̵��ϵ��� �Ѵ�.
		//ȭ���� ũ�Ⱑ 800�ε� 900���� �������� ������鼭 �ٷ� ���ʿ��� ������ ���� �ƴ϶� �ð����� �ξ� ���� �ε巴�� ���̱� �����̴�.
		//�ʱ� ��ġ�� 0���� ���� ���� ������ �׸��� ���̰� �ֱ� ������ -200��ġ���� ���̵��� �ߴ�.
		//���� �׸��� ���̰� 200���� ū 500�̶�� -600�Ǵ� -700������ �ؾ��Ѵ�.
		if(cloud_y[0]>900)cloud_y[0]=-200;
		if(cloud_y[1]>900)cloud_y[1]=-200;
		if(cloud_y[2]>900)cloud_y[2]=-200;
	};
	//������ �ʱ� ��ġ�� �����ϱ� ���� �Լ��̴�.
	private void cloud_x_y_po(){ //������ǥ���� �Լ�
		cloud_x[0]=100;
		cloud_x[1]=200;
		cloud_x[2]=300;
		cloud_y[0]=-200;
		cloud_y[1]=-200;
		cloud_y[2]=-200;
	};
}
