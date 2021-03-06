package com.example.server_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Socket socket;
	BufferedReader in;
	PrintWriter out;
	EditText input;
	Button button;
	TextView output;
	String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		input = (EditText) findViewById(R.id.EditText1);
		button = (Button) findViewById(R.id.Button1);
		output = (TextView) findViewById(R.id.textView3);
		
		super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				data = input.getText().toString();
				input.setText("");
				Log.w("send->", data);
				if (data != null) {
					Thread sender = new Thread() {
						public void run() {
							out.println(data);
						}
					};
					sender.start();
				}
			}
		});
		
		Thread worker = new Thread() {
			public void run() {
				try {
					socket = new Socket("bestcomsv.cafe24.com", 3500);
					out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "EUC-KR"), true);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "EUC-KR"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					while (true) {
						data = in.readLine();
						output.post(new Runnable() {
							public void run() {
								Log.w("recv->", data);
								output.setText(data);
							}
						});
					}
				} catch (Exception e) {
				}
			}
		};
		worker.start();
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
