package com.example.Expenses;
import java.util.ArrayList;
import java.util.List;

import com.example.Expenses.AddExpense.Expense;
import com.example.prototype_phase_1.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Expense_Status extends Activity {
	List<Expense>list=new ArrayList<Expense>();
	 DatabaseHandler MYDB=new DatabaseHandler(Expense_Status.this);
	Expense exp=new Expense();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_status);
		
		ListView lv=(ListView) findViewById(R.id.listView_Expense_Status);
		
		list=MYDB.getAllContacts(1, "HI");
		
		if(list != null){
			CashAdapter myadapter=new CashAdapter(this, R.layout.expense_status_each_row,list);
			lv.setAdapter(myadapter);
		}
		else {
			CashAdapter myadapter=new CashAdapter(this, R.layout.expense_status_each_row,list);
			lv.setAdapter(myadapter);
			Toast.makeText(Expense_Status.this,"No Match Found ", Toast.LENGTH_SHORT).show();

		}

	}
	
		
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
				row=inflater.inflate(R.layout.expense_status_each_row, Parent, false);
				
				
			}
			else
			{
				row=convertView;
				
			}
			
			ImageView item_pic=(ImageView)row.findViewById(R.id.expense_status_item_img);
			
			TextView item=(TextView)row.findViewById(R.id.expense_status_item);
			TextView amount=(TextView) row.findViewById(R.id.expense_status_item_amount);
			//TextView cash=(TextView)row.findViewById(R.id.cash);
			//TextView date=(TextView) row.findViewById(R.id.date);
			
					
					
					
						//Toast.makeText(MainActivity.this, temp1, Toast.LENGTH_LONG).show();
//					String	temp2=Expense.get(position).get_amount_item();
					
					item_pic.setImageResource(Expense.get(position).get_Img_ID());
					item.setText(Expense.get(position).get_Category());
					amount.setText(Expense.get(position).get_amount());
					//cash.setText(Expense.get(position).get_Paid_from());
					//date.setText(Expense.get(position).get_date());
/*					
*/					
					
				 	     				
			return row;
			
			
		} //getView
		
	} //CashAdapter

	

}
