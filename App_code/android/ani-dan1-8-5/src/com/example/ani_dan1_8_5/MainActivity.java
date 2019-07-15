package com.example.ani_dan1_8_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private final long end_tm = 2000; // 종료시간을 2초로 한다.
	private long back_tm = 0; // 종료버튼을 선택한 시간을 저장

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
    
    // 뒤로가기 버튼을 선택하면 아래 명령을 실행한다.
    @Override
    public void onBackPressed(){
    	long now_tm = System.currentTimeMillis(); // 현재시간을 저장
    	long intervalTime = now_tm - back_tm; // 현재시간에서 뒤로가기 선택시간을 빼서 저장
    	// 중간 대기 시간이 0보다 크고 end_tm 즉 종료시간 2초보다 대기시간이 크면 종료
    	// 즉 버튼을 2초안에 두번 누르면 앱이 종료하게 된다.
    	if( 0 <= intervalTime && end_tm >= intervalTime){
    		super.onBackPressed(); // 뒤로가기를 선택한 것으로 처리해서 앱을 종료
    	}else{
    		//처음 뒤로가기 버튼을 선택하면 아래 메세지를 보이고
    		//현재 뒤로가기 버튼을 선택한 시간을 back_tm변수에 저장한다.
    		//버튼이 end_tm(2000:2초)변수값 2초 이내에 한번더 선택하면 앱을 종료한다.
    		back_tm = now_tm; // 뒤로가기 버튼 선택시간 저장변수에 현재시간을 저장
    		//메세지를 보인다.
    		Toast.makeText(getApplicationContext(), "뒤로 버튼을한번더누르시면종료됩니다.", Toast.LENGTH_SHORT).show();
    	}
    }
}
