package com.example.Expenses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.prototype_phase_1.R;
import com.facebook.Session;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.internal.Utility;
import com.google.analytics.tracking.android.EasyTracker;

public class FacebookIntegrate extends Activity {
	private static String APP_ID = "1485339628346949"; // Replace with your App ID
	private Facebook facebook ;
	 
	 private AsyncFacebookRunner mAsyncRunner;
	 String FILENAME = "AndroidSSO_data";
	 private SharedPreferences mPrefs;
	 private Session currentSession;
	 Button btnFbLogin;
	 Button btnFbGetProfile;
	 Button btnPostToWall;
	 Button btnFbLogout;
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
	        setContentView(R.layout.fb_layout);
	        
	        /*
	        try  {  
	        	  
	        	PackageInfo info = getPackageManager().  
	        	           getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);

	        	      for (Signature signature : info.signatures) {

	        	          MessageDigest md = MessageDigest.getInstance("SHA");
	        	          md.update(signature.toByteArray());
	        	          Log.d("====Hash Key===",Base64.encodeToString(md.digest(), 
	        	                   Base64.DEFAULT));

	        	      }

	        	  } catch (NameNotFoundException e) {

	        	      e.printStackTrace();

	        	  } catch (NoSuchAlgorithmException ex) {

	        	      ex.printStackTrace();

	        	  }
	        
	        */
	        // new
	        
	        facebook = new Facebook(APP_ID);
	        btnFbLogin = (Button) findViewById(R.id.btn_fblogin);
	        btnFbGetProfile = (Button) findViewById(R.id.btn_get_profile);
	        btnPostToWall = (Button) findViewById(R.id.btn_fb_post_to_wall);
	        btnFbLogout = (Button) findViewById(R.id.btn_fblogout);
	        mAsyncRunner = new AsyncFacebookRunner(facebook);
	        
	        btnFbLogin.setOnClickListener(new View.OnClickListener() {
	        	 
	        	   @Override
	        	   public void onClick(View v) {
	        	    Log.d("Image Button", "button Clicked");
	        	    loginToFacebook();
	        	   }
	        	  });
	        	 
	        	  /**
	        	   * Getting facebook Profile info
	        	   * */
	        	  btnFbGetProfile.setOnClickListener(new View.OnClickListener() {
	        	 
	        	   @Override
	        	   public void onClick(View v) {
	        	    getProfileInformation();
	        	   }
	        	  });
	        	 
	        	  /**
	        	   * Posting to Facebook Wall
	        	   * */
	        	  btnPostToWall.setOnClickListener(new View.OnClickListener() {
	        	 
	        	   @Override
	        	   public void onClick(View v) {
	        	    postToWall();
	        	   }
	        	  });
	        	  
	        	  btnFbLogout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (Session.getActiveSession() != null) {
						    Session.getActiveSession().closeAndClearTokenInformation();
						}

						Session.setActiveSession(null);
						 btnFbLogin.setVisibility(View.VISIBLE);
						 
					       // making all remaining buttons invisible
					       btnFbGetProfile.setVisibility(View.INVISIBLE);
					       btnPostToWall.setVisibility(View.INVISIBLE);
					       btnFbLogout.setVisibility(View.INVISIBLE);
						/*
						Session session = Session.getActiveSession();
						session.closeAndClearTokenInformation();
						Utility.clearFacebookCookies(FacebookIntegrate.this);
						Intent intent = new Intent(FacebookIntegrate.this, Home.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						((Activity)FacebookIntegrate.this).startActivity(intent);
						*/
					}
				});
	 }
	 @SuppressWarnings("deprecation")
	 public void loginToFacebook() {
	 
	  mPrefs = getPreferences(MODE_PRIVATE);
	  String access_token = mPrefs.getString("access_token", null);
	  long expires = mPrefs.getLong("access_expires", 0);
	 
	  if (access_token != null) {
	   facebook.setAccessToken(access_token);
	    
	   btnFbLogin.setVisibility(View.INVISIBLE);
	    
	   // Making get profile button visible
	   btnFbGetProfile.setVisibility(View.VISIBLE);
	 
	   // Making post to wall visible
	   btnPostToWall.setVisibility(View.VISIBLE);
	   btnFbLogout.setVisibility(View.VISIBLE);
	 
	   Log.d("FB Sessions", "" + facebook.isSessionValid());
	  }
	 
	  if (expires != 0) {
	   facebook.setAccessExpires(expires);
	  }
	 
	  if (!facebook.isSessionValid()) {
	   facebook.authorize(this,
	     new String[] { "email", "publish_stream" },
	     new DialogListener() {
	 
	      @Override
	      public void onCancel() {
	       // Function to handle cancel event
	      }
	 
	      @Override
	      public void onComplete(Bundle values) {
	       // Function to handle complete event
	       // Edit Preferences and update facebook acess_token
	       SharedPreferences.Editor editor = mPrefs.edit();
	       editor.putString("access_token",
	         facebook.getAccessToken());
	       editor.putLong("access_expires",
	         facebook.getAccessExpires());
	       editor.commit();
	 
	       // Making Login button invisible
	       btnFbLogin.setVisibility(View.INVISIBLE);
	 
	       // Making logout Button visible
	       btnFbGetProfile.setVisibility(View.VISIBLE);
	 
	       // Making post to wall visible
	       btnPostToWall.setVisibility(View.VISIBLE);
	       btnFbLogout.setVisibility(View.VISIBLE);
	      }
	 
	      @Override
	      public void onError(DialogError error) {
	       // Function to handle error
	 
	      }
	 
	      @Override
	      public void onFacebookError(FacebookError fberror) {
	       // Function to handle Facebook errors
	 
	      }
	 
	     });
	  }
	 }
	 
	 @Override
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  facebook.authorizeCallback(requestCode, resultCode, data);
	 }
	 
	 
	 /**
	  * Get Profile information by making request to Facebook Graph API
	  * */
	 @SuppressWarnings("deprecation")
	 public void getProfileInformation() {
	  mAsyncRunner.request("me", new RequestListener() {
	   @Override
	   public void onComplete(String response, Object state) {
	    Log.d("Profile", response);
	    String json = response;
	    try {
	     // Facebook Profile JSON data
	     JSONObject profile = new JSONObject(json);
	      
	     // getting name of the user
	     final String name = profile.getString("name");
	      
	     // getting email of the user
	     final String email = profile.getString("email");
	      
	     runOnUiThread(new Runnable() {
	 
	      @Override
	      public void run() {
	       Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();
	      }
	 
	     });
	 
	      
	    } catch (JSONException e) {
	     e.printStackTrace();
	    }
	   }
	 
	   @Override
	   public void onIOException(IOException e, Object state) {
	   }
	 
	   @Override
	   public void onFileNotFoundException(FileNotFoundException e,
	     Object state) {
	   }
	 
	   @Override
	   public void onMalformedURLException(MalformedURLException e,
	     Object state) {
	   }
	 
	   @Override
	   public void onFacebookError(FacebookError e, Object state) {
	   }
	  });
	 }
	 
	 /**
	  * Function to post to facebook wall
	  * */
	 @SuppressWarnings("deprecation")
	 public void postToWall() {
	  // post on user's wall.
	  facebook.dialog(this, "feed", new DialogListener() {
	 
	   @Override
	   public void onFacebookError(FacebookError e) {
	   }
	 
	   @Override
	   public void onError(DialogError e) {
	   }
	 
	   @Override
	   public void onComplete(Bundle values) {
	   }
	 
	   @Override
	   public void onCancel() {
	   }
	  });
	 
	 }
	 
	 /**
	  * Function to show Access Tokens
	  * */
	 public void showAccessTokens() {
	  String access_token = facebook.getAccessToken();
	 
	  Toast.makeText(getApplicationContext(),
	    "Access Token: " + access_token, Toast.LENGTH_LONG).show();
	 }
	  
	 /**
	  * Function to Logout user from Facebook
	  * */
	 
	 public void logout()
	 {
	     Session session = Session.getActiveSession();
	     if (session != null) {
	         session.closeAndClearTokenInformation();
	     }
	     else
	     {
	         Session session2 = Session.openActiveSession((Activity)FacebookIntegrate.this, false, null);
	         if(session2 != null)
	             session2.closeAndClearTokenInformation();
	     }
	     Session.setActiveSession(null);

	 }
	 @SuppressWarnings("deprecation")
	 public void logoutFromFacebook() {
		 
	  mAsyncRunner.logout(this, new RequestListener() {
	   @Override
	   public void onComplete(String response, Object state) {
	    Log.d("Logout from Facebook", response);
	    if (Boolean.parseBoolean(response) == true) {
	     runOnUiThread(new Runnable() {
	 
	      @Override
	      public void run() {
	       // make Login button visible
	       btnFbLogin.setVisibility(View.VISIBLE);
	 
	       // making all remaining buttons invisible
	       btnFbGetProfile.setVisibility(View.INVISIBLE);
	       btnPostToWall.setVisibility(View.INVISIBLE);
	       btnFbLogout.setVisibility(View.INVISIBLE);
	      }
	 
	     });
	 
	    }
	   }
	   
	   
	 
	   @Override
	   public void onIOException(IOException e, Object state) {
	   }
	 
	   @Override
	   public void onFileNotFoundException(FileNotFoundException e,
	     Object state) {
	   }
	 
	   @Override
	   public void onMalformedURLException(MalformedURLException e,
	     Object state) {
	   }
	 
	   @Override
	   public void onFacebookError(FacebookError e, Object state) {
	   }
	  });
	 }
}
