package com.example.Expenses;

import java.util.ArrayList;
import java.util.List;

import com.example.Expenses.AddExpense.CustomAdExpenseAdapter;
import com.example.Expenses.AddExpense.Expense;
import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddIncome extends Activity {
	List<Income>list=new ArrayList<Income>();
	 DatabaseHandler MYDB=new DatabaseHandler(AddIncome.this);
	 Income income=new Income();//Income[] income=new Income[3];
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_income);
		ListView lv=(ListView) findViewById(R.id.addIncome_ListView);
		
		 lv.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
		            
		    		if(position == 0){
		    			Intent intent = new Intent(AddIncome.this, Calculator.class);
		    			startActivityForResult(intent, 1);
		    			
		    		}
		    		else if(position == 1){
		    			Intent intent = new Intent(AddIncome.this, Calculator.class);
	    			startActivityForResult(intent, 2);
		    		}
		    		else if(position == 2){
		    			Intent intent = new Intent(AddIncome.this, Calculator.class);
		    			startActivityForResult(intent, 3);
		    		}

		        }
		      }); //end of lv.setOnItemClickListener
		 

		Button saveIncome=(Button) findViewById(R.id.SaveIncome);
		saveIncome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Income myarr=new Income();
				String temp;
				temp=income.getSalaryAmount();
				myarr.setSalaryAmount(temp);			
				list.add(0, myarr);
				
				temp=income.getBonusAmount();
				myarr.setBonusAmount(temp);
			
				list.add(1, myarr);
			
				temp=income.getAllowanceAmount();
				myarr.setAllowanceAmount(temp);
				list.add(2, myarr);
				
				
				/*Income []myarr=new Income[3];
				myarr[0]=new Income();
				String temp;
				temp=income[0].getIncomeAmount();
				myarr[0].setIncomeAmount(temp);
				
				temp=income[0].getIncomeType();
				myarr[0].setIncomeType(temp);

				myarr[1]=new Income();
				temp=income[1].getIncomeAmount();
				myarr[1].setIncomeAmount(temp);
				
				temp=income[1].getIncomeType();
				myarr[1].setIncomeType(temp);

				myarr[2]=new Income();
				temp=income[2].getIncomeAmount();
					//Toast.makeText(AddExpense.this, temp, Toast.LENGTH_SHORT).show();
				//myarr.set_Paid_from(temp);
				myarr[2].setIncomeAmount(temp);
				temp=income[2].getIncomeType();
				myarr[2].setIncomeType(temp);
*/
				MYDB.addIncome(myarr);
				if(list.size() > 2){
					for(int i=1; i<list.size() ; i++)
						list.remove(i);
				}
				CustomIncome adapter = new CustomIncome(AddIncome.this, R.layout.add_income_each_row, list);
				ListView lv=(ListView) findViewById(R.id.addIncome_ListView);
				
				lv.setAdapter(adapter);
				
			}
		});
		
		
		income.setSalaryAmount("0");
		list.add(income);
		income.setBonusAmount("0");
		list.add(income);
		income.setAllowanceAmount("0");
		list.add(income);
		/*income[0]=new Income();
		income[0].setIncomeType("Salary");
		income[0].setIncomeAmount("4 ");
		//list.add(income);
		income[1]=new Income();
		income[1].setIncomeType("Bonus");
		income[1].setIncomeAmount(" 5");
		//list.add(income);
		income[2]=new Income();
		income[2].setIncomeType("Allowance");
		income[2].setIncomeAmount(" 6");*/
		//list.add(income);
		//Toast.makeText(AddIncome.this,list.get(0).getIncomeType(), Toast.LENGTH_SHORT).show();
CustomIncome adapter = new CustomIncome(AddIncome.this, R.layout.add_income_each_row, list );
		
		lv.setAdapter(adapter);
		
		
	} //end of onCreate()
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         String result=data.getStringExtra("amount_value");
		         income.setSalaryAmount("$"+result);
		         list.add(1,income);
					if(list.size() > 2){
						for(int i=3 ; i<list.size() ; i++)
							list.remove(i);
					}
		    	 Toast.makeText(AddIncome.this, "List Size is "+list.size(), Toast.LENGTH_SHORT).show();
		         CustomIncome adapter = new CustomIncome(this, R.layout.add_expense_each_row, list);
		         ListView lv = (ListView) findViewById(R.id.addIncome_ListView);
	 			 lv.setAdapter(adapter);
		     }
		}
		      if (requestCode == 2) {

			     if(resultCode == RESULT_OK){      
			    	 String result=data.getStringExtra("amount_value");
			         income.setBonusAmount("$"+result);
			         list.add(2,income);
						if(list.size() > 2){
							for(int i=3 ; i<list.size() ; i++)
								list.remove(i);
						}
			         CustomIncome adapter = new CustomIncome(this, R.layout.add_expense_each_row, list);
			         ListView lv = (ListView) findViewById(R.id.addIncome_ListView);
		 			 lv.setAdapter(adapter);                               }
			     }
		     
		      if (requestCode == 3) {

			     if(resultCode == RESULT_OK){      
			    	 String result=data.getStringExtra("amount_value");
			         income.setAllowanceAmount("$"+result);
			         list.add(3,income);
						if(list.size() > 2){
							for(int i=3 ; i<list.size() ; i++)
								list.remove(i);
						}
			         CustomIncome adapter = new CustomIncome(this, R.layout.add_expense_each_row, list);
			         ListView lv = (ListView) findViewById(R.id.addIncome_ListView);
		 			 lv.setAdapter(adapter);                        }
			     }
		     
		     

		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		    	 Toast.makeText(AddIncome.this, "Nothing Inserted", Toast.LENGTH_SHORT).show();
		     }
		}


	public class CustomIncome extends ArrayAdapter<Income>
	{
		Context cntxt;
		int res;
		List<Income> incme;

		public CustomIncome(Context context, int resource, List<Income> list) {
			super(context, resource, list);
			cntxt=context;
			res = resource;
			incme= list;
			// TODO Auto-generated constructor stub
		}
		
		// TODO Auto-generated constructor stub
		@Override
		public View getView(final int position,View convertView,ViewGroup Parent){
			View row=null;
			
			
			
			if(convertView==null){
				
				LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(R.layout.add_income_each_row, Parent, false);
				
				
			}
			else
			{
				row=convertView;
				
			}
			
		
			TextView item=(TextView)row.findViewById(R.id.add_income_textView);
			TextView amount=(TextView) row.findViewById(R.id.add_income_value);
			
			if(position==0)
			{
				item.setText(incme.get(position).getSalaryType());
				amount.setText(incme.get(position).getSalaryAmount());
				
				//item.setText(incme[position].getIncomeType());
				//amount.setText(incme[position].getIncomeAmount());
			}
			if(position==1)
			{
				item.setText(incme.get(position).getBonusType());
				amount.setText(incme.get(position).getBonusAmount());
				
				//item.setText(incme[position].getIncomeType());
				//amount.setText(incme[position].getIncomeAmount());
			}
			if(position==2)
			{
				item.setText(incme.get(position).getAllowanceType());
				amount.setText(incme.get(position).getAllowanceAmount());
				
				//item.setText(incme[position].getIncomeType());
				//amount.setText(incme[position].getIncomeAmount());
			}
					
					
				
					
				 	     				
			return row;
			
			
		} //getView
		
	} //CashAdapter

	public static class Income{
		public  String incomeType;
		public  String incomeAmount;
		public  String SalaryType;
		public  String SalaryAmount;
		public  String BonusType;
		public  String BonusAmount;
		public  String AllowanceType;
		public  String AllowanceAmount;
		Income(){
			SalaryType="Salary";
			BonusType="Bonus";
			AllowanceType="Allowance";
		};

		public void setIncomeType(String _str){
			this.incomeType = _str;
		}
		public void setIncomeAmount(String _str){
			this.incomeAmount = _str;
		}
		public String getIncomeType(){
			return this.incomeType;
		}
		public String getIncomeAmount(){
			return this.incomeAmount;
		}
		
		
		
		public void setSalaryAmount(String _str){
			this.SalaryAmount = _str;
		}
		
		public String getSalaryAmount(){
			return this.SalaryAmount;
		}
		public void setBonusAmount(String _str){
			this.BonusAmount = _str;
		}
		
		public String getBonusAmount(){
			return this.BonusAmount;
		}
		public void setAllowanceAmount(String _str){
			this.AllowanceAmount = _str;
		}
		
		public String getAllowanceAmount(){
			return this.AllowanceAmount;
		}
		
		
		public String getAllowanceType(){
			return this.AllowanceType;
		}
		
		public String getBonusType(){
			return this.BonusType;
		}
		public String getSalaryType(){
			return this.SalaryType;
		}
		
		
	}
} //end of AddIncome Class
