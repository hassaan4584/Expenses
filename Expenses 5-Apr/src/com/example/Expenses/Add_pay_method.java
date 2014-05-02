package com.example.Expenses;


import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.google.analytics.tracking.android.EasyTracker;

public class Add_pay_method extends Activity {
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_pay_method);
		final ListView lv=(ListView) findViewById(R.id.pay_method);
		String[] myArr = new String[4];
		myArr[0] = "Cash";
		myArr[1] = "Credit Card";
		myArr[2] = "Checkings";
		myArr[3] = "Savings";
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArr);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				String selectedCategory = (lv.getItemAtPosition(position).toString());
				//Toast.makeText(Category_AddExpense.this, selectedCategory, Toast.LENGTH_LONG).show();
				Intent returnIntent = new Intent();
				returnIntent.putExtra("paid_from",selectedCategory);
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		}); //end lv setonitemclicklistener
	}

}
