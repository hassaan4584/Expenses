package com.example.Expenses;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.prototype_phase_1.R;
import com.google.ads.*;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
     
    protected LocationManager locationManager;
     
    protected TextView retrieveLocationButton;
     

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		retrieveLocationButton = (TextView) findViewById(R.id.Retrieve_location);
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, 
                new MyLocationListener()
        );
         
        retrieveLocationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					showCurrentLocation();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	
	 protected void showCurrentLocation() throws IOException {
		 
	        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	 
	       // if (location != null) {
	        	List<Address> addresses=null;
	        	
	        	
	        	Geocoder geocoder = new Geocoder(this, Locale.getDefault());
	       	 addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
	         Toast.makeText(Home.this,Double.toString(location.getLatitude()) , Toast.LENGTH_SHORT).show();
	         Toast.makeText(Home.this,Double.toString(location.getLongitude()) , Toast.LENGTH_SHORT).show();
	            
             
	        /*	 try {
	                 /*
	                  * Return 1 address.
	                  */
	        		 // location.getLatitude(),location.getLongitude()
	        	/*	 addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
	             } catch (IOException e1) {
	             Log.e("LocationSampleActivity",
	                     "IO Exception in getFromLocation()");
	             e1.printStackTrace();
	            // return ("IO Exception trying to get address");
	             } catch (IllegalArgumentException e2) {
	             // Error message to post in the log
	             String errorString = "Illegal arguments " +
	                     Double.toString(location.getLatitude()) +
	                     " , " +
	                     Double.toString(location.getLongitude()) +
	                     " passed to address service";
	             Log.e("LocationSampleActivity", errorString);
	             e2.printStackTrace();
	             }
	        	 
	        	 if (addresses != null && addresses.size() > 0) {
	                 // Get the first address
	                 Address address = addresses.get(0);
	                 /*
	                  * Format the first line of address (if available),
	                  * city, and country name.
	                  */
	                 //String Country=address.getCountryName();
	                 //retrieveLocationButton.setText(Country);
	                 //Toast.makeText(Home.this,Country , Toast.LENGTH_LONG).show();
	           /*      String addressText = String.format(
	                         "%s, %s, %s",
	                         // If there's a street address, add it
	                         address.getMaxAddressLineIndex() > 0 ?
	                                 address.getAddressLine(0) : "",
	                         // Locality is usually a city
	                         address.getLocality(),
	                         // The country of the address
	                         address.getCountryName());
	                 retrieveLocationButton.setText(addressText);
	  	           
	                 Toast.makeText(Home.this,addressText , Toast.LENGTH_SHORT).show();
	                 // Return the text
	                
	             } else {
	            	 retrieveLocationButton.setText("No address found");
	                  Toast.makeText(Home.this,"No address found" , Toast.LENGTH_SHORT).show();
	             }
	        	 
	        	*/
	        	//double latitude=31.475111666666667;
	        	//double longitude=74.30231166666667;
	        	//addresses = geocoder.getFromLocation(31.475111666666667, 74.30231166666667, 1);

	        	//String address = addresses.get(0).getAddressLine(0);
	        	//String city = addresses.get(0).getAddressLine(1);
	        	//String country = addresses.get(0).getAddressLine(2);
	           
	            //retrieveLocationButton.setText(message);
	            
	           
	       // }
	 
	    }  

	
	
	
	/////////////////////////////////////////////////////////////
	 class MyLocationListener implements LocationListener {
		 
	        public void onLocationChanged(Location location) {
	            String message = String.format(
	                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
	                    location.getLongitude(), location.getLatitude()
	            );
	            
	          
	            retrieveLocationButton.setText(message);
	            
	            
	        }
	 
	        public void onStatusChanged(String s, int i, Bundle b) {
	           
	        	retrieveLocationButton.setText("Provider status changed");
	        	retrieveLocationButton.setText("Retrieve Location");
     }
	 
	        public void onProviderDisabled(String s) {
	           
	        	retrieveLocationButton.setText( "Provider disabled by the user. GPS turned off");
	                    
	        }
	 
	        public void onProviderEnabled(String s) {
	            
	        	retrieveLocationButton.setText( "Provider enabled by the user. GPS turned on");
	        }
	 
	    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
       // String message = "OptionsMenu Selected";
        //Toast.makeText(Home.this, message,
          //      Toast.LENGTH_LONG).show();

		return true;
	}


} //end of home class
