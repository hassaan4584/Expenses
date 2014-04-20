package com.example.Expenses;
import java.util.ArrayList;
import java.util.List;


import com.example.Expenses.AddExpense.Expense;
import com.example.prototype_phase_1.R;
import com.example.prototype_phase_1.R.drawable;
import com.google.ads.am;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class User_Accounts extends Activity {
	
	int post=6;// row on which item has been clicked in the previous listView
	 DatabaseHandler MYDB=new DatabaseHandler(User_Accounts.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_accounts);
		Button Linkedin = (Button) findViewById(R.id.Export_accounts);
		Linkedin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(User_Accounts.this, LinkedInSampleActivity.class));
			}
		});
		List<Expense> mylist1=new ArrayList<Expense>();
		Intent i=this.getIntent();
		post=i.getIntExtra("position", post);
		//Toast.makeText(User_Accounts.this, post, Toast.LENGTH_LONG).show();
		Button accounts=(Button) findViewById(R.id.Cash_to_accounts);
		TextView heading=(TextView) findViewById(R.id.heading_user_accounts);
		if(post==0)
		{
			heading.setText("Credit Card");
			mylist1 = MYDB.search("Credit Card");
			//will set adapter here for credit card items
		}
		else if(post==1)
		{
			heading.setText("Cash");
			mylist1 = MYDB.search("Cash");
			
			//will set adapter here for cash items
		}
		else if(post==2)
		{
			heading.setText("Checkings");
			mylist1 = MYDB.search("Checkings");
			
			//will set adapter here for checkings items
		}
		else if(post==3)
		{
			heading.setText("Savings");
			mylist1 = MYDB.search("Savings");
			
			//will set adapter here for savings items
		}
		accounts.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(User_Accounts.this,Accounts.class);
				startActivity(i);
				
			}
		});
		
		
		
		//TextView tv = (TextView) findViewById(R.id.heading_user_accounts);
		ListView lv=(ListView) findViewById(R.id.cash_view);
		//Cash cash=new Cash();
		
		//mylist1 = MYDB.search("Credit Card");
		
		if(mylist1 != null){
			CashAdapter myadapter=new CashAdapter(this, R.layout.cash,mylist1);
			lv.setAdapter(myadapter);
		}
		else {
			CashAdapter myadapter=new CashAdapter(this, R.layout.cash,mylist1);
			lv.setAdapter(myadapter);
			Toast.makeText(User_Accounts.this,"No Match Found ", Toast.LENGTH_SHORT).show();

		}
	}//on create
	
	public class CashAdapter extends ArrayAdapter<Expense>
	{
		Context cntxt;
		int res;
		List<Expense> Expense;

		public CashAdapter(Context context, int resource, List<Expense> mylist1) {
			super(context, resource, mylist1);
			cntxt=context;
			res = resource;
			Expense = mylist1;
			// TODO Auto-generated constructor stub
		}
		
		// TODO Auto-generated constructor stub
		@Override
		public View getView(final int position,View convertView,ViewGroup Parent){
			View row=null;
			
			
			
			if(convertView==null){
				
				LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(R.layout.cash, Parent, false);
				
				
			}
			else
			{
				row=convertView;
				
			}
			
			ImageView item_pic=(ImageView)row.findViewById(R.id.item_pic);
			
			TextView item=(TextView)row.findViewById(R.id.cash_items);
			TextView amount=(TextView) row.findViewById(R.id.amount);
			TextView cash=(TextView)row.findViewById(R.id.cash);
			TextView date=(TextView) row.findViewById(R.id.date);
			
					
					
					
						//Toast.makeText(MainActivity.this, temp1, Toast.LENGTH_LONG).show();
//					String	temp2=Expense.get(position).get_amount_item();
					
					item_pic.setImageResource(Expense.get(position).get_Img_ID());
					item.setText(Expense.get(position).get_Category());
					amount.setText(Expense.get(position).get_amount());
					cash.setText(Expense.get(position).get_Paid_from());
					date.setText(Expense.get(position).get_date());
/*					if(post==0)

					{
						cash.setText("Credit Card");
					}
					else if(post==0)
					{
						cash.setText("Cash");
					}
					else if(post==0)
					{
						cash.setText("Checkings");
					}
					else if(post==0)
					{
						cash.setText("Savings");
					}
*/					
					
				 	     				
			return row;
			
			
		} //getView
		
	} //CashAdapter
	
	
	
	
	
} //end of activity
