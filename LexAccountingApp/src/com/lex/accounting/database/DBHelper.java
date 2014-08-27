package com.lex.accounting.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.lex.accounting.entity.Account;
import com.lex.accounting.entity.BaseEntity;

/**
 * 
 * @author lex
 *
 */
public class DBHelper extends SQLiteOpenHelper{

	protected SQLiteDatabase db;
	private static final String DBNAME = "LexAccount";
	private static final int VERSION = 1;
	protected static final String COLUMN_USER_ID = "user_id";
	protected static final String COLUMN_ID = "id";
	
	/**账户*/
	private static final String TABLE_ACCOUNT = "table_account"; 
	/**账户管理*/
	private static final String TABLE_ACCOUNT_MANAGEMENT = "table_account_management"; 
	/**借入借出*/
	private static final String TABLE_BORROWING_LENDING_MANAGEMENT = "table_borrowing_lending_management"; 
	/**收入\支出类别*/
	private static final String TABLE_INCOME_EXPENDITURE_CATAGORY = "table_income_expenditure_catagory"; 
	/**流水报表*/
	private static final String TABLE_FLOW_STATEMENT = "table_flow_statement"; 
	/**分类报表*/
	private static final String TABLE_CLASSIFICATION_STATEMENT = "table_classification_statement";
	/**人员报表*/ 
	private static final String TABLE_PERSONAL_STATEMENT = "table_personal_statement"; 
	/**资金管理*/
	private static final String TABLE_BUGDIT_MANAGEMENT = "table_bugdit_management"; 
	
	private String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE " + TABLE_ACCOUNT + 
			" (USER_ID INTEGER, ACCOUNT_ID INTEGER, NAME TEXT, INITIAL_MONEY DOUBLE" +
			", BALANCE DOUBLE, COMMENTS TEXT);";
	private String SQL_CREATE_TABLE_ACCOUNT_MANAGEMENT = "CREATE TABLE " + TABLE_ACCOUNT_MANAGEMENT + 
			" (USER_ID INTEGER, MANAGER_ID, TIME LONG, ACCOUNT_IN_ID INTEGER, ACCOUNT_OUT_ID INTEGER" +
			", MONEY DOUBLE, COMMENTS TEXT);";
	private String SQL_CREATE_TABLE_BORROWING_LENDING_MANAGEMENT = "CREATE TABLE " + TABLE_BORROWING_LENDING_MANAGEMENT + 
			" (USER_ID INTEGER, BORROW_ID INTEGER, TIME LONG, ACCOUNT_ID INTEGER, BORROW_MONEY DOUBLE" +
			", PAID_MONEY DOUBLE, BALANCE DOUBLE, EXPECT_TIME LONG, BORROW_FROM TEXT, COMMENTS TEXT);";
	private String SQL_CREATE_TABLE_INCOME_EXPENDITURE_CATAGORY = "CREATE TABLE " + TABLE_INCOME_EXPENDITURE_CATAGORY + 
			" (USER_ID INTEGER, INCOME_CATEGORY_ID INTEGER, NAME TEXT);";
//	private String SQL_CREATE_TABLE_FLOW_STATEMENT = "CREATE TABLE " + TABLE_FLOW_STATEMENT + 
//			" (USER_ID INTEGER, ACCOUNT_ID INTEGER, NAME TEXT, INITIAL_MONEY DOUBLE" +
//			", BALANCE DOUBLE, COMMENTS TEXT);";
//	private String SQL_CREATE_TABLE_CLASSIFICATION_STATEMENT = "CREATE TABLE " + TABLE_CLASSIFICATION_STATEMENT + 
//			" (USER_ID INTEGER, ACCOUNT_ID INTEGER, NAME TEXT, INITIAL_MONEY DOUBLE" +
//			", BALANCE DOUBLE, COMMENTS TEXT);";
//	private String SQL_CREATE_TABLE_PERSONAL_STATEMENT = "CREATE TABLE " + TABLE_PERSONAL_STATEMENT + 
//			" (USER_ID INTEGER, ACCOUNT_ID INTEGER, NAME TEXT, INITIAL_MONEY DOUBLE" +
//			", BALANCE DOUBLE, COMMENTS TEXT);";
//	private String SQL_CREATE_TABLE_BUGDIT_MANAGEMENT = "CREATE TABLE " + TABLE_BUGDIT_MANAGEMENT + 
//			" (USER_ID INTEGER, ACCOUNT_ID INTEGER, NAME TEXT, INITIAL_MONEY DOUBLE" +
//			", BALANCE DOUBLE, COMMENTS TEXT);";
	public DBHelper(Context context){
		super(context, DBNAME, null, VERSION);
	}
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE_ACCOUNT);
		db.execSQL(SQL_CREATE_TABLE_ACCOUNT_MANAGEMENT);
		db.execSQL(SQL_CREATE_TABLE_INCOME_EXPENDITURE_CATAGORY);
		db.execSQL(SQL_CREATE_TABLE_BORROWING_LENDING_MANAGEMENT);
	}
	
//	public List<Account> queryAccountInfo(int userid){
//		db = this.getWritableDatabase();
//		List<Account> mList = new ArrayList<Account>();
//		String sql = "select * from " + TABLE_ACCOUNT + " where USER_ID=?";
//		Cursor c = db.rawQuery(sql, new String[]{userid+""});
//		String sql_query_income = "select sum(TABLE_INCOME.MONEY) from " + TABLE_INCOME_EXPENDITURE + " where USER_ID=?";
//		Cursor cursor2 = db.rawQuery(sql_query_income, new String[]{userid+""});
//		try{
//			while (c.moveToNext()) {
//				Account account = new Account();
//				account.setName(c.getString(c.getColumnIndex("NAME")));
//				account.setInitialMoney(c.getDouble(c.getColumnIndex("INITIAL_MONEY")));
//				account.setIncomeMoney(c.getDouble(c.getColumnIndex("INCOME_MONEY")));
//				account.setExpenditureMoney(c.getDouble(c.getColumnIndex("EXPENDITURE_MONEY")));
//				account.setBorrowMoney(c.getDouble(c.getColumnIndex("BORROW_MONEY")));
//				account.setLoanMondy(c.getDouble(c.getColumnIndex("LOAN_MONEY")));
//				account.setInMoney(c.getDouble(c.getColumnIndex("")));
//				mList.add(account);
//			}
//		}catch(Exception e) {
//			
//		}finally{
//			if(c != null){
//				c.close();
//				c = null;
//			}
//			if(db != null){
//				db.close();
//				db = null;
//			}
//		}
//		return mList;
//	};


	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
