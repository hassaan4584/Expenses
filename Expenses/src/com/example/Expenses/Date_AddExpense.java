package com.example.Expenses;

import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class Date_AddExpense extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date_add_expense);
		final DatePicker dp=(DatePicker) findViewById(R.id.datePicker1);
		Button confrm=(Button) findViewById(R.id.set_date);
		confrm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String selectedDate = Integer.toString(dp.getDayOfMonth()) + "/" + Integer.toString(dp.getMonth()+1)+"/"+Integer.toString(dp.getYear()); ;
				
				Toast.makeText(Date_AddExpense.this, selectedDate, Toast.LENGTH_LONG).show();
				Intent returnIntent = new Intent();
				returnIntent.putExtra("date",selectedDate);
				setResult(RESULT_OK,returnIntent);
				finish();
				
			}
		});
		String selectedDate = Integer.toString(dp.getDayOfMonth()) + "/" + Integer.toString(dp.getMonth())+"/"+Integer.toString(dp.getYear()); ;
		
		/*Toast.makeText(Date_AddExpense.this, selectedDate, Toast.LENGTH_LONG).show();
		Intent returnIntent = new Intent();
		returnIntent.putExtra("date",selectedDate);
		setResult(RESULT_OK,returnIntent);
		finish();*/
		
	} //end of onCreate()

} //end of Date_AddExpense Class
