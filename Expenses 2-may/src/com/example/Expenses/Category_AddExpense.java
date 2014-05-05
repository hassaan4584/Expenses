package com.example.Expenses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.Expenses.User_Accounts.CashAdapter;
import com.example.prototype_phase_1.R;
import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Category_AddExpense extends Activity {
	List <category>list=new ArrayList<category>();
	int post=-1;
	boolean ad = false;
	 public Object myActionMode;
	
	 DatabaseHandler MYDB=new DatabaseHandler(Category_AddExpense.this);

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
		setContentView(R.layout.category_add_expense);
		final EditText et=(EditText) findViewById(R.id.enter_category);
		final ListView lv=(ListView) findViewById(R.id.category_list);
		Button add=(Button) findViewById(R.id.add_category);
		
		list=MYDB.getAllCategories();
		CategoryAdapter adpt = new CategoryAdapter(Category_AddExpense.this, R.layout.category_each_row,list);
		lv.setAdapter(adpt);
		 
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				String selectedCategory = (list.get(position).get_category());
				int selectedImg = (list.get(position).get_img_id());
				Toast.makeText(Category_AddExpense.this, selectedCategory, Toast.LENGTH_LONG).show();
				Intent returnIntent = new Intent();
				returnIntent.putExtra("category",selectedCategory);
				returnIntent.putExtra("image",selectedImg);
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		}); //end lv setonitemclicklistener
		
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				
				post=position;
				 myActionMode = Category_AddExpense.this.startActionMode(mActionModeCallback);
	            String message = "Long Click Performed";
	            Toast.makeText(Category_AddExpense.this, message,
	                    Toast.LENGTH_LONG).show();

				return true;
			}
		}); //end of lv.setOnItemLongClickListener

		
add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Category_AddExpense.this, AddNewCategory.class);
				startActivityForResult(intent, 1);
			
			}
		});
		
//		Toast.makeText(this, Integer.toString(MYDB.getAllCategories().size()),Toast.LENGTH_LONG).show();
		//CategoryAdapter myadapter=new CategoryAdapter(this, R.layout.category_each_row,list);
		//lv.setAdapter(myadapter);
		
	} //end of onCreate()
	
	String result;
	int newimg;
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		          result=data.getStringExtra("new_category");
		          newimg = data.getIntExtra("new_img", 1);
		         category cat = new category();
		         cat.set_category(result);
		         cat.set_img_id(newimg);
		         if(list==null){
		        	 list = new ArrayList<category>();
			         list.add(cat);
			         MYDB.addCategory(cat);
		         }
		         else{
		        	 list.add(cat);
		        	 MYDB.addCategory(cat);
		         }
		         //Collections.reverse(list);
		         ListView lv2=(ListView) findViewById(R.id.category_list);
		         CategoryAdapter adapter = new CategoryAdapter(this, R.layout.category_each_row,list);
		         lv2.setAdapter(adapter);
		     }
		}
	}
	
	
	public class CategoryAdapter extends ArrayAdapter<category>
	{
		Context cntxt;
		int res;
		List<category> category;

		public CategoryAdapter(Context context, int resource, List<category> objects) {
			super(context, resource, objects);
			cntxt=context;
			res=resource;
			category=objects
			;
			// TODO Auto-generated constructor stub
		}
		
		// TODO Auto-generated constructor stub
		@Override
		public View getView(final int position,View convertView,ViewGroup Parent){
			View row=null;
			
			
			
			if(convertView==null){
				
				LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(R.layout.category_each_row, Parent, false);
				
				
			}
			else
			{
				row=convertView;
				
			}
			
            ImageView item_pic=(ImageView)row.findViewById(R.id.category_pic);
			
			TextView item=(TextView)row.findViewById(R.id.cat_label);
			
					
//					if(position<category.size()){
					int	temp1=category.get(position).get_img_id();
						//Toast.makeText(MainActivity.this, temp1, Toast.LENGTH_LONG).show();
					String	temp2=category.get(position).get_category();
					
					//item_pic.setImageAlpha(drawable.ic_launcher);
					item_pic.setImageResource(temp1);
					item.setText(temp2);
								
	//				}
				 	     				
			return row;
			
			
		} //getView
		
	} //CashAdapter

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		// Called when the action mode is created; startActionMode() was called
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		// inflate a menu resource providing context menu items
		MenuInflater inflater = mode.getMenuInflater();
		// assumes that you have "contexual.xml" menu resources
		inflater.inflate(R.menu.action_mode, menu);
		return true;
		}

		// called each time the action mode is shown. Always called after
		// onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false; // Return false if nothing is done
		}

		// called when the user selects a contextual menu item
		
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.delete:
		ListView lv1=(ListView) findViewById(R.id.category_list);
		//String temp=(String) lv1.getItemAtPosition(post);
		String temp=list.get(post).get_category();
		MYDB.deleteCategory(temp);
		List<category>list1=new ArrayList<category>();
		list1=MYDB.getAllCategories();
		CategoryAdapter adpt1 = new CategoryAdapter(Category_AddExpense.this, R.layout.category_each_row,list1);
		lv1.setAdapter(adpt1);
		
		//list.get(position).
		Toast.makeText(Category_AddExpense.this, temp,
		Toast.LENGTH_SHORT).show();
		mode.finish();
		return true;
		case R.id.edit:
		Toast.makeText(Category_AddExpense.this, "Filled Star Button Pressed",
		Toast.LENGTH_SHORT).show();
		//mode.finish();
		return true;
		  // Action picked, so close the CAB

		default:
		return false;
		}
		}

		// called when the user exits the action mode
		public void onDestroyActionMode(ActionMode mode) {
		myActionMode = null;
		}
		};	// end of contextual action

	
	public static class category
	{
		public int img_id;
		public String _category;
	
		public category(){
		
		
		}
		public void set_img_id(int id)
		{
			img_id=id;
		}
		public void set_category(String cat)
		{
			_category=cat;
		}
		
		public int get_img_id()
		{
			return img_id;
		}
		public String get_category()
		{
			return _category;
		}
		
	}
}

 //end of Category_AddExpense Class
