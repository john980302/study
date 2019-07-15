package com.example.ani_dan1_8_8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    	//뒤로가기 버튼을 선택하면 AlertDialog가 보인다.
    	new AlertDialog.Builder(this)
    	.setIcon(android.R.drawable.ic_dialog_alert) // 경고아이콘이 보인다.
    	.setTitle("종료") // 메세지 제목은 종료
    	.setMessage("앱을 종료하시겠습니까?") //메세지를 출력
    	.setNegativeButton("Yes",new DialogInterface.OnClickListener() // yes를 선택하면 종료
    	{
    		@Override
    		public void onClick(DialogInterface dialog, int which){
    			finish(); // yes를 선택하면 앱을 종료한다.
    		}
    	})
    	.setPositiveButton("No",null) // no를 선택하면 아무런 동작 없음
    	.show();
    }
}
