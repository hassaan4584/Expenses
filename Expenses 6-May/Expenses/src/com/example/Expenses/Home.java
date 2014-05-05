package com.example.Expenses;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bugsense.trace.BugSenseHandler;
import com.example.prototype_phase_1.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.drive.Drive;

public class Home extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
	
     
    private static final int RESOLVE_CONNECTION_REQUEST_CODE = 1;
	protected TextView retrieveLocationButton;
    GoogleApiClient mGoogleApiClient;
	 @Override
	  public void onStart() {
	    super.onStart();
	    // The rest of your onStart() code.
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
//	    mGoogleApiClient.connect();
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
		
		
/*		mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addApi(Drive.API)
        .addScope(Drive.SCOPE_FILE)
        .addConnectionCallbacks( this)
        .addOnConnectionFailedListener( this)
        .build();
*/		
		BugSenseHandler.startSession(Home.this);
		
		retrieveLocationButton = (TextView) findViewById(R.id.Retrieve_location);
		
		AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
       
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
		
		String value="0.00";
		String msg=String.format("Expenses \n\n              " );
		tvExp.setText(msg);
		value="0.00";
		 msg=String.format("Income \n\n            ");
		 tvInc.setText(msg);
		value="0.00";
		 msg=String.format("Net Worth \n\n            ");
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
	
	
	public void onConnectionFailed(ConnectionResult connectionResult) {
	    if (connectionResult.hasResolution()) {
	        try {
	            connectionResult.startResolutionForResult(this, RESOLVE_CONNECTION_REQUEST_CODE);
	        } catch (IntentSender.SendIntentException e) {
	            // Unable to resolve, message user appopriately
	        }
	    } else {
	        GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
	    }
	}
	
	
	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
	    switch (requestCode) {
	        
	        case RESOLVE_CONNECTION_REQUEST_CODE:
	            if (resultCode == RESULT_OK) {
	                mGoogleApiClient.connect();
	            }
	            break;
	    }
	}
	
	 	 
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.home, menu);
	        String message = "OptionsMenu Selected";
	       // Toast.makeText(Home.this, message,Toast.LENGTH_LONG).show();

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
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/html");
				intent.putExtra(Intent.EXTRA_EMAIL, "ali.hassan.tariq@gmail.com");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Expenses");
				intent.putExtra(Intent.EXTRA_TEXT, "Sent from Expenses");
				startActivity(Intent.createChooser(intent, "Send Email"));
				break;
			case R.id.callID:
				
				Intent i = new Intent(Intent.ACTION_CALL,Uri.parse("tel:0332-6555418"));
//		    	i.setData(Uri.parse("tel:0332-6555418"));
		    	startActivity(i);
				break;
				
			case R.id.messageID:
		//		String feedbackBody = body.getText().toString() + ". Message from " + name.getText().toString();
				try{
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage("+923326555418", null, "From Expenses", null, null);
					Toast.makeText(getApplicationContext(), "Message From Expenses!", Toast.LENGTH_LONG).show();
				} 
				catch (Exception e){
					e.printStackTrace();
				}
				
				break;
			case R.id.driveID:
				startActivity(new Intent(Home.this, DriveActivity.class));
				
				break;
				
				default:
					break;
		}
		return false; 
		
	 
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		
	}

} //end of home class
