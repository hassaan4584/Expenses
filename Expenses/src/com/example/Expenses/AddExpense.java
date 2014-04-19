package com.example.Expenses;


import java.util.ArrayList;
import java.util.List;

import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddExpense extends Activity {
	Expense[] myArr = new Expense[5];
	//List<Expense>list=new ArrayList<Expense>();
	public final DatabaseHandler MYDB=new DatabaseHandler(AddExpense.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense);
		ListView lv = (ListView) findViewById(R.id.listView1_add_expense);
		
		//list=MYDB.getAllContacts(1, "2");
		Button save=(Button) findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Expense myarr=new Expense();
				String temp;int img;
				temp=myArr[0].get_amount();
//				Toast.makeText(AddExpense.this, temp, Toast.LENGTH_SHORT).show();
				myarr.set_Amount("$"+temp);
				temp=myArr[1].get_Category();
				img=myArr[1].get_Img_ID();
//				Toast.makeText(AddExpense.this, Integer.toString(img), Toast.LENGTH_SHORT).show();
				
				myarr.set_Img_ID(img);
//				Toast.makeText(AddExpense.this, temp, Toast.LENGTH_SHORT).show();
				myarr.set_Category(temp);
				temp=myArr[2].get_Paid_from();
//				Toast.makeText(AddExpense.this, temp, Toast.LENGTH_SHORT).show();
				myarr.set_Paid_from(temp);
				temp=myArr[3].get_date();
//				Toast.makeText(AddExpense.this, temp, Toast.LENGTH_SHORT).show();
				myarr.set_Date(temp);
				MYDB.addContact(myarr);
				Toast.makeText(AddExpense.this, "Expense Successfully Added", Toast.LENGTH_LONG).show();
						
			}
		});
		myArr[0]=new Expense();
		myArr[0].set_Amount_label("Amount");
		myArr[0].set_Amount("25");
		myArr[1]=new Expense();
		myArr[1].set_Img_ID(R.drawable.ic_launcher);
		myArr[1].set_Category_label("Category");
		myArr[1].set_Category("Food");
		myArr[2]=new Expense();
		myArr[2].set_Paid_from_label("Paid From");
		myArr[2].set_Paid_from("Cash");
		myArr[3]=new Expense();
		myArr[3].set_Date_label("Date"); 
		myArr[3].set_Date("01/04/14");
		myArr[4]=new Expense();
		myArr[4].set_Notes_label("Notes"); 
		myArr[4].set_Notes("");
	

 CustomAdExpenseAdapter adapter = new CustomAdExpenseAdapter(AddExpense.this, R.layout.add_expense_each_row, myArr);
		
		lv.setAdapter(adapter);
		//ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArr);
		//lv.setAdapter(adapter);
		
		  lv.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
/*		            Toast toast1=  Toast.makeText(getApplicationContext(),
		                    "Save button was Clicked", Toast.LENGTH_LONG);
		            toast1.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
		            toast1.show();
*/
		    		if(position == 0){
//		    			Intent intent = new Intent(AddExpense.this, Amount_AddExpense.class);
		    			Intent intent = new Intent(AddExpense.this, Calculator.class);
		    			startActivityForResult(intent, 1);
		    			//startActivity(intent);
		    		}
		    		else if(position == 1){
		    			Intent intent = new Intent(AddExpense.this, Category_AddExpense.class);
		    			startActivityForResult(intent, 2);
		    			//startActivity(intent);
		    		}
		    		else if(position == 2){
		    			Intent intent = new Intent(AddExpense.this, Add_pay_method.class);
		    			startActivityForResult(intent, 3);
		    			//startActivity(intent);
		    		}
		    		else if(position == 3){
		    			Intent intent = new Intent(AddExpense.this, Date_AddExpense.class);
		    			startActivityForResult(intent, 4);
		    			//startActivity(intent);
		    		}

		        }
		      }); //end of lv.setOnItemClickListener
		  
		  

			lv.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
		            String message = "Long Click Performed";
		            Toast.makeText(AddExpense.this, message,
		                    Toast.LENGTH_LONG).show();

					return true;
				}
			}); //end of lv.setOnItemLongClickListener
		
	} //end of onCreate()
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
        //String message = "OptionsMenu Selected";
        //Toast.makeText(AddExpense.this, message,
          //      Toast.LENGTH_LONG).show();

		return true;
	} //end of onCreateOptonsMenu
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         String result=data.getStringExtra("amount_value");
		         myArr[0].set_Amount(result);
		         CustomAdExpenseAdapter adapter = new CustomAdExpenseAdapter(this, R.layout.add_expense_each_row, myArr);
		         ListView lv = (ListView) findViewById(R.id.listView1_add_expense);
	 			 lv.setAdapter(adapter);
		     }
		}
		      if (requestCode == 2) {

			     if(resultCode == RESULT_OK){      
			         String result=data.getStringExtra("category");
			       int Pic =data.getIntExtra("image", 1);
			         myArr[1].set_Category(result);
			         myArr[1].set_Img_ID(Pic);
			         CustomAdExpenseAdapter adapter = new CustomAdExpenseAdapter(this, R.layout.add_expense_each_row, myArr);
			         ListView lv = (ListView) findViewById(R.id.listView1_add_expense);
		 			 lv.setAdapter(adapter);
			                                }
			     }
		     
		      if (requestCode == 3) {

			     if(resultCode == RESULT_OK){      
			         String result=data.getStringExtra("paid_from");
			         myArr[2].set_Paid_from(result);
			         CustomAdExpenseAdapter adapter = new CustomAdExpenseAdapter(this, R.layout.add_expense_each_row, myArr);
			         ListView lv = (ListView) findViewById(R.id.listView1_add_expense);
		 			 lv.setAdapter(adapter);
			                                }
			     }
		     
		      if (requestCode == 4) {

			     if(resultCode == RESULT_OK){      
			         String result=data.getStringExtra("date");
			         myArr[3].set_Date(result);
			         CustomAdExpenseAdapter adapter = new CustomAdExpenseAdapter(this, R.layout.add_expense_each_row, myArr);
			         ListView lv = (ListView) findViewById(R.id.listView1_add_expense);
		 			 lv.setAdapter(adapter);
			                                }
			     }

		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		}
	
	//end onActiityResult
	
	
	public class CustomAdExpenseAdapter extends ArrayAdapter<Expense>{

		Context c;
		int id;
		Expense[] array;
		
		public CustomAdExpenseAdapter(Context context, int resource,
				Expense[] objects) {
			super(context, resource, objects);
			// TODO Auto-generated constructor stub
			c = context;
			id = resource;
			array = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View row;
			
			if(convertView==null){
				LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(id, parent, false);
			}
			else
			{
				row = convertView;
			}
			
			TextView t1 = (TextView) row.findViewById(R.id.add_expense_textView);
			TextView t2 = (TextView) row.findViewById(R.id.add_expense_value);
			ImageView Img=(ImageView) row.findViewById(R.id.categoryImage_addExpense);
			Img.setVisibility(View.GONE);
			if(position==0)
			{
			t1.setText(array[position].get_amount_label());
			t2.setText("$"+array[position].get_amount());
			}
			if(position==1)
			{
			t1.setText(array[position].get_Category_label());
			Img.setVisibility(View.VISIBLE);
			Img.setImageResource(array[position].get_Img_ID());
			t2.setText(array[position].get_Category());
			}
			if(position==2)
			{
			t1.setText(array[position].get_Paid_from_label());
			t2.setText(array[position].get_Paid_from());
			}
			if(position==3)
			{
			t1.setText(array[position].get_date_label());
			t2.setText(array[position].get_date());
			}
			if(position==4)
			{
			t1.setText(array[position].get_notes_label());
			t2.setText(array[position].get_notes());
			}
			
			return row;
			
		}		
	}

	
	public static class Expense
	{
		public String amount_label;
		public String category_label;
		public String Paid_from_label;
		public String Date_label;
		public String Notes_label;
		public String amount_value;
		public String category_value;
		public String Paid_from_value;
		public String Date_value;
		public String Notes_value;
		public int img_id;
		
		Expense()
		{
		 
		 
		}
		public void set_Amount(String amnt)
		{
			amount_value=amnt;
		}
		public void set_Img_ID(int id)
		{
			img_id=id;
		}
		
		public int get_Img_ID()
		{
			return img_id;
		}
		public void set_Category(String Catg)
		{
			category_value=Catg;
		}
		
		public void set_Paid_from(String sub_cat)
		{
			Paid_from_value=sub_cat;
		}
		public void set_Date(String date)
		{
			Date_value=date;
		}
		public void set_Notes(String Note )
		{
			Notes_value=Note;
		}
		
		//////////
		public void set_Amount_label(String amnt)
		{
			amount_label=amnt;
		}
		
		public void set_Category_label(String Catg)
		{
			category_label=Catg;
		}
		
		public void set_Paid_from_label(String sub_cat)
		{
			Paid_from_label=sub_cat;
		}
		public void set_Date_label(String date)
		{
			Date_label=date;
		}
		public void set_Notes_label(String Note )
		{
			Notes_label=Note;
		}
		public String get_amount()
		{
			return amount_value;
		}
		public String get_Category()
		{
			return category_value;
		}
		public String get_Paid_from()
		{
			return Paid_from_value;
		}
		public String get_date()
		{
			return Date_value;
		}
		public String get_notes ()
		{
			return Notes_value;
		}
		
		//////////////////
		public String get_amount_label()
		{
			return amount_label;
		}
		public String get_Category_label()
		{
			return category_label;
		}
		public String get_Paid_from_label()
		{
			return Paid_from_label;
		}
		public String get_date_label()
		{
			return Date_label;
		}
		public String get_notes_label ()
		{
			return Notes_label;
		}
		
		
		
		
	} //end of cash class



} //end of AddExpense Class


