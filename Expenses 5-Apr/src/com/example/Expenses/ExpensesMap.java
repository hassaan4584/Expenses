package com.example.Expenses;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.example.prototype_phase_1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ExpensesMap extends Activity {
 
	//protected LocationManager locationManager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.expenses_map);
      
      /*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      
      Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

      // Get a handle to the Map Fragment
      GoogleMap map = ((MapFragment) getFragmentManager()
              .findFragmentById(R.id.map)).getMap();

      LatLng sydney = new LatLng(location.getLatitude(),location.getLongitude());

      map.setMyLocationEnabled(true);
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

      map.addMarker(new MarkerOptions()
              .title("Current Location")
              .snippet("This is your current location")
              .position(sydney));
  */
      }
  
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

}
