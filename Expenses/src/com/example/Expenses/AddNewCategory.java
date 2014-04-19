package com.example.Expenses;

import com.example.Expenses.Category_AddExpense.category;
import com.example.prototype_phase_1.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewCategory extends Activity {
	category returnNewCategory = new category();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_category);
		
		final EditText et = (EditText) findViewById(R.id.editText1CategoryNew);
		final ImageView iv = (ImageView) findViewById(R.id.imageView1Category);
		final GridView gv = (GridView) findViewById(R.id.gridView1);
		
		returnNewCategory.set_img_id(R.drawable.ic_launcher);
		
		// HARD-CODED IMAGES
		int[] img_arr = new int[4];
		img_arr[0] = R.drawable.food;
		img_arr[1] = R.drawable.transport;
		img_arr[2] = R.drawable.utility;
		img_arr[3] = R.drawable.ic_launcher;
		
		MyGridImagesAdapter adpt = new MyGridImagesAdapter(AddNewCategory.this, img_arr);
		gv.setAdapter(adpt);
		
		et.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keycode, KeyEvent event) {
				
				// TODO Auto-generated method stub
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keycode == KeyEvent.KEYCODE_ENTER)) {
					String s = et.getText().toString();
					if(s.length()<1)
						Toast.makeText(AddNewCategory.this, "Category Name Not Entered", Toast.LENGTH_SHORT).show();
					else{
						returnNewCategory.set_category(s);
						Intent returnIntent = new Intent();
						returnIntent.putExtra("new_category",s);
						returnIntent.putExtra("new_img", returnNewCategory.get_img_id());
						setResult(RESULT_OK,returnIntent);
						finish();
						return true;
					}
				}
				return false;
			}
		});
		
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, 
                    int position, long id) {
				// TODO Auto-generated method stub
				String s = (gv.getItemAtPosition(position).toString());
				int i = Integer.parseInt(s);
				iv.setImageResource(i);
				returnNewCategory.set_img_id(i);
			}
		});
		/*
		Intent intent = new Intent();
		intent.put
		*/
	}
	
	public class MyGridImagesAdapter extends BaseAdapter{

		Context c;
		int[] arr;
		
		public MyGridImagesAdapter(Context context, int[] a){
			c = context;
			arr = a;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arr.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arr[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView iview;
			if (view == null) {
				iview = new ImageView(c);
				iview.setLayoutParams(new GridView.LayoutParams(72,72));
				iview.setScaleType(ImageView.ScaleType.CENTER);
				iview.setPadding(5, 5, 5, 5);
			} 
			else {
				iview = (ImageView) view;	
			}
			iview.setImageResource(arr[position]);
			return iview;
		}
	}
}

