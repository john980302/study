package com.example.ani_dan1_8_8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    	//�ڷΰ��� ��ư�� �����ϸ� AlertDialog�� ���δ�.
    	new AlertDialog.Builder(this)
    	.setIcon(android.R.drawable.ic_dialog_alert) // ���������� ���δ�.
    	.setTitle("����") // �޼��� ������ ����
    	.setMessage("���� �����Ͻðڽ��ϱ�?") //�޼����� ���
    	.setNegativeButton("Yes",new DialogInterface.OnClickListener() // yes�� �����ϸ� ����
    	{
    		@Override
    		public void onClick(DialogInterface dialog, int which){
    			finish(); // yes�� �����ϸ� ���� �����Ѵ�.
    		}
    	})
    	.setPositiveButton("No",null) // no�� �����ϸ� �ƹ��� ���� ����
    	.show();
    }
}
