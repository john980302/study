package com.example.ani_dan1_8_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private final long end_tm = 2000; // ����ð��� 2�ʷ� �Ѵ�.
	private long back_tm = 0; // �����ư�� ������ �ð��� ����

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Img_View1(this));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    }
    
    // �ڷΰ��� ��ư�� �����ϸ� �Ʒ� ����� �����Ѵ�.
    @Override
    public void onBackPressed(){
    	long now_tm = System.currentTimeMillis(); // ����ð��� ����
    	long intervalTime = now_tm - back_tm; // ����ð����� �ڷΰ��� ���ýð��� ���� ����
    	// �߰� ��� �ð��� 0���� ũ�� end_tm �� ����ð� 2�ʺ��� ���ð��� ũ�� ����
    	// �� ��ư�� 2�ʾȿ� �ι� ������ ���� �����ϰ� �ȴ�.
    	if( 0 <= intervalTime && end_tm >= intervalTime){
    		super.onBackPressed(); // �ڷΰ��⸦ ������ ������ ó���ؼ� ���� ����
    	}else{
    		//ó�� �ڷΰ��� ��ư�� �����ϸ� �Ʒ� �޼����� ���̰�
    		//���� �ڷΰ��� ��ư�� ������ �ð��� back_tm������ �����Ѵ�.
    		//��ư�� end_tm(2000:2��)������ 2�� �̳��� �ѹ��� �����ϸ� ���� �����Ѵ�.
    		back_tm = now_tm; // �ڷΰ��� ��ư ���ýð� ���庯���� ����ð��� ����
    		//�޼����� ���δ�.
    		Toast.makeText(getApplicationContext(), "�ڷ� ��ư���ѹ��������ø�����˴ϴ�.", Toast.LENGTH_SHORT).show();
    	}
    }
}
