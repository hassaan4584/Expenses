package com.example.Expenses;

import java.util.ArrayList;
import java.util.List;

import com.example.Expenses.AddExpense.Expense;
import com.example.Expenses.AddIncome.Income;
import com.example.Expenses.Category_AddExpense.category;
import com.example.prototype_phase_1.R;


//import com.example.hw2.MainActivity.Student_calc;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "MYDb";
 
    // Expense table name
    private static final String TABLE_NAME = "Expense";
 
    private static final String Income_table="Income";
    
    private static final String Salary="salary";
     private static final String Bonus="bonus";
    private static final String Allowance="allowance";
    
    
    		
    // Expense Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_PAID_FROM = "paid_from";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMG_ID = "img_id";
    
    
    //Category table name
    
    private static final String TABLE_CATEGORY = "Category";
    //CATEGORY talbe column names
    private static final String KEY_EXPENSE_CATEGORY= "category";
    private static final String KEY_IMAGE_ID = "img_id";
    
    
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void delete()
    {
    	this.deleteDatabase(DATABASE_NAME);
    }
    
 private void deleteDatabase(String string) {
		// TODO Auto-generated method stub
		
	}
	// Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
        		+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_AMOUNT + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_IMG_ID + " INTEGER," + KEY_PAID_FROM +" TEXT," + KEY_DATE + " TEXT " + ")";
        
        
        db.execSQL(CREATE_EXPENSE_TABLE);
        
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
        		+ KEY_EXPENSE_CATEGORY +" TEXT PRIMARY KEY ," + KEY_IMAGE_ID + " INTEGER NOT NULL" + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);
        
        String CREATE_INCOME_TABLE = "CREATE TABLE " + Income_table + "("
        		+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + Salary + " TEXT,"
                + Bonus + " TEXT," + Allowance + " TEXT " + ")";
        db.execSQL(CREATE_INCOME_TABLE);
        
        
    }
    
    
    //Student_calc makeStudent_calc() {
      //  return new Student_calc();
    //}
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addContact(Expense exp) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
       // values.put(KEY_ID,1);
        values.put(KEY_AMOUNT, exp.get_amount()); // Contact Name
        values.put(KEY_CATEGORY, exp.get_Category()); // Contact Phone
        values.put(KEY_PAID_FROM, exp.get_Paid_from());
        values.put(KEY_DATE, exp.get_date());
        values.put(KEY_IMG_ID, exp.get_Img_ID());
 
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    /*
     */
    //ADDING new income
    
    void addIncome(Income inc) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
       // values.put(KEY_ID,1);
        values.put(Salary, inc.getSalaryAmount()); // Contact Name
        values.put(Bonus, inc.getBonusAmount()); // Contact Phone
        values.put(Allowance, inc.getAllowanceAmount());
        
        // Inserting Row
        db.insert(Income_table, null, values);
        db.close(); // Closing database connection
    }
 
   /* // Getting single contact
    Student_calc getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] {
                KEY_COURSE, KEY_ASSESSMENT,KEY_OBT_MARKS,KEY_TOTAL_MARKS },
                null, new String[] { }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Student_calc std_calc = new Student_calc(cursor.getString(0), cursor.getString(1),Double.parseDouble(cursor.getString(2)),Double.parseDouble(cursor.getString(3)));
        // return contact
        return std_calc;
    }
 */
    // Getting All Contacts
    public List<Expense> getAllContacts(int id,String match) {
        List<Expense> contactList = new ArrayList<Expense>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
       
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Expense exp = new Expense();
                // contact.setID(Integer.parseInt(cursor.getString(0)));
             	exp.set_Amount(cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)));
             	exp.set_Category(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
             	exp.set_Paid_from(cursor.getString(cursor.getColumnIndex(KEY_PAID_FROM)));
             	exp.set_Date(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            	exp.set_Img_ID(cursor.getInt(cursor.getColumnIndex(KEY_IMG_ID)));
                // Adding contact to list
                contactList.add(exp);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    //search
    
    public List<Expense> search(String match) {
        List<Expense> contactList = new ArrayList<Expense>();
        // Select All Query
        String selectQuery =null;
        selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_PAID_FROM + " = " + " Cash " ;
/////////////////////////////////////////////////////////////////////////////////////////
        String selectQuery1 = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_PAID_FROM+ " = "+match;

        SQLiteDatabase db = this.getReadableDatabase();

 


        String[] columnNames = {KEY_AMOUNT, KEY_CATEGORY,KEY_IMG_ID, KEY_PAID_FROM,KEY_DATE};

		String[] selectionVals = {match};

		selectionVals[0] = "%" + selectionVals[0] + "%";

		 Cursor cursor = db.query(TABLE_NAME, columnNames, KEY_PAID_FROM+ " LIKE ? ",
		selectionVals, null, null, null);

		
        if (cursor.moveToFirst()) {
            do {
            	Expense exp = new Expense();
               // contact.setID(Integer.parseInt(cursor.getString(0)));
            	exp.set_Amount(cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)));
            	exp.set_Category(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
            	exp.set_Paid_from(cursor.getString(cursor.getColumnIndex(KEY_PAID_FROM)));
            	exp.set_Date(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            	exp.set_Img_ID(cursor.getInt(cursor.getColumnIndex(KEY_IMG_ID)));
                // Adding contact to list
                contactList.add(exp);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

   
 
   /* // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }
 */
    // Deleting single contact
    public void deleteCategory(String cat) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, KEY_EXPENSE_CATEGORY + " = ?",
                new String[] { cat });
        db.close();
    }
 
   //  Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
 
        // return count
        return cursor.getCount();
    }
    //delete all
    public void deleteAll(){
    	SQLiteDatabase db =this.getWritableDatabase();
    	   db.delete(TABLE_NAME, null, null);
    	 }
    
    
    //SEARCH
   /* public Cursor Search(String name) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	  return db.query(true, 
    	    TABLE_NAME, 
    	    new String[] {KEY_COURSE, KEY_ASSESSMENT, KEY_OBT_MARKS, KEY_TOTAL_MARKS}, 
    	    KEY_ASSESSMENT + " = ?",
    	    new String[] {name},
    	    null, null, null, null);
    	}
    */
    
    void addCategory(category c) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
       // values.put(KEY_ID,1);
        values.put(KEY_EXPENSE_CATEGORY, c.get_category()); // Contact Name
        values.put(KEY_IMAGE_ID, c.get_img_id()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_CATEGORY, null, values);
        db.close(); // Closing database connection
    }

    public List<category> getAllCategories() {
        List<category> categoryList = new ArrayList<category>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;
        
        
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	category cat = new category();
                // contact.setID(Integer.parseInt(cursor.getString(0)));
            	cat.set_img_id(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_ID)));
            	cat.set_category(cursor.getString(cursor.getColumnIndex(KEY_EXPENSE_CATEGORY)));
            	
                // Adding contact to list
            	categoryList.add(cat);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return categoryList;
    }
    
    //search

    
 
 
}
  