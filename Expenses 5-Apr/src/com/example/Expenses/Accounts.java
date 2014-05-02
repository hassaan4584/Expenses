package com.example.Expenses;



import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;

public class Accounts extends Activity{
	 @Override
	  public void onStart() {
	    super.onStart();
	    // The rest of your onStart() code.
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    // The rest of your onStop() code.
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }

@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accounts);
		ListView lv = (ListView) findViewById(R.id.listView1_accounts);
		Button home=(Button) findViewById(R.id.accounts_to_home);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Accounts.this,Home.class);
				startActivity(i);
				
			}
		});

		final String[] myArr = new String[4];
		myArr[0] = "Credit Card"; 
		myArr[1] = "Cash";
		myArr[2] = "Checking"; 
		myArr[3]=  "Savings";

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArr);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Accounts.this, User_Accounts.class);
				
				intent.putExtra("position", position);
				startActivity(intent);
				
			}
		});
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
	            String message = "Long Click Performed";
	            Toast.makeText(Accounts.this, message,
	                    Toast.LENGTH_LONG).show();
				
				return true;
			}
		});
		
	} //end of onCreate
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
        //String message = "OptionsMenu Selected";
        //Toast.makeText(Accounts.this, message,
          //      Toast.LENGTH_LONG).show();

		return true;
	} //end of onCreateOptonsMenu


	
} //end of Accounts Class
