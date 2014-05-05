package com.example.Expenses;


import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;

public class Amount_AddExpense extends Activity {
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
		setContentView(R.layout.amount_add_expense);
		final EditText amount=(EditText) findViewById(R.id.enterAmount_editText_amount_add_expense);
		Button enter_amount=(Button) findViewById(R.id.enterAmountButton_amount_add_expense);
		enter_amount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String entered_amount = amount.getText().toString();
				//Toast.makeText(Amount_AddExpense.this, entered_amount, Toast.LENGTH_LONG).show();
				Intent returnIntent = new Intent();
				returnIntent.putExtra("amount_value",entered_amount);
				setResult(RESULT_OK,returnIntent);
				finish();
				
			}
		});
		
		
	} //end of onCreate()

} //end of Amount_AddExpense Class
