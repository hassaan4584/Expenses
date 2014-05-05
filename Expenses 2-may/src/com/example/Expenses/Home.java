package com.example.Expenses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;


import com.example.prototype_phase_1.R;
import com.google.ads.*;
import com.google.analytics.tracking.android.EasyTracker;
import com.bugsense.trace.BugSenseHandler;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	
     
    protected TextView retrieveLocationButton;
    
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
		BugSenseHandler.initAndStartSession(Home.this, "b211c145");
		setContentView(R.layout.home);
		
		BugSenseHandler.startSession(Home.this);
		
		retrieveLocationButton = (TextView) findViewById(R.id.Retrieve_location);
        
       
        retrieveLocationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Home.this,ExpensesMap.class);
				startActivity(intent);
				
			}
		});
    
        
   
 
		ImageButton add_expense = (ImageButton) findViewById(R.id.add_expense_home);
		ImageButton add_income = (ImageButton) findViewById(R.id.add_income_home);
		ImageButton accounts = (ImageButton) findViewById(R.id.accounts_home);
		TextView tvExp=(TextView) findViewById(R.id.expense_status);
		TextView tvInc=(TextView) findViewById(R.id.income_status);
		TextView tvAcn=(TextView) findViewById(R.id.net_worth);
		
		String value="450.50";
		String msg=String.format("         Expense Status\n\n            $%1$s \n ",value );
		tvExp.setText(msg);
		value="675.50";
		 msg=String.format("         Income Status\n\n            $%1$s \n ",value );
		 tvInc.setText(msg);
		value="1125.00";
		 msg=String.format("          Net Worth \n\n            $%1$s \n ",value );
		 tvAcn.setText(msg);
		/* onClick Functions of the Buttons */
		add_expense.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this, AddExpense.class);
				intent.putExtra("key", "value");

				startActivity(intent);
			}
		}); // end of add_expense.setOnClickListener
		
		tvExp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Home.this,Expense_Status.class);
				startActivity(i);
				
			}
		});
		
		add_income.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this, AddIncome.class);
				intent.putExtra("key", "value");

				startActivity(intent);
			}
		}); //end of add_income.setOnClickListener
		accounts.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this, Accounts.class);
				intent.putExtra("key", "value");

				startActivity(intent);
			}
		}); //end of accounts.setOnClickListener
		
		add_expense.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
		        String message = "Long Click!";
		        Toast.makeText(Home.this, message,
		                Toast.LENGTH_LONG).show();
				
				return true;
			}
		}); //end of add_expense.setOnLongClickListener()
		
	}//end of onCreate Function
	
	 	 
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.home, menu);
	       // String message = "OptionsMenu Selected";
	        //Toast.makeText(Home.this, message,
	          //      Toast.LENGTH_LONG).show();

			return true;
		}
	 
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		
		switch (item.getItemId()) 
		{ 
			case R.id.fbID:
				Intent in = new Intent(Home.this, FacebookIntegrate.class);
				startActivity(in);
				break;
				
			case R.id.inID:
				startActivity(new Intent(Home.this, LinkedInSampleActivity.class));
				break;
				
			case R.id.gmailID:	
				Intent intent = new Intent(Intent.ACTION_SENDTO);
				intent.setType("text/html");
				intent.putExtra(Intent.EXTRA_EMAIL, "ali.hassan.tariq@gmail.com");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Expenses");
				intent.putExtra(Intent.EXTRA_TEXT, "Sent from Expenses");
				startActivity(Intent.createChooser(intent, "Send Email"));
				break;
				default:
					break;
		}
		return false; 
		
	 
	}

} //end of home class
