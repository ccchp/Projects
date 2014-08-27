package com.lex.accounting.database;

import com.lex.accounting.entity.BaseEntity;
import com.lex.accounting.entity.IncomeExpenditureEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class IncomeExpendidureDB extends DBHelper {

	/** 收入支出 */
	private static final String TABLE_INCOME_EXPENDITURE = "table_income_expenditure";
	private static final String COLUMN_TIME = "time";
	private static final String COLUMN_CATEGORY_ID = "category_id";
	private static final String COLUMN_MONEY = "money";
	private static final String COLUMN_ACCOUNT_ID = "account_id";
	private static final String COLUMN_COMMENTS = "comments";

	private String SQL_CREATE_TABLE_INCOME_EXPENDITURE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INCOME_EXPENDITURE
			+ "("
			+ COLUMN_USER_ID
			+ " INTEGER, "
			+ COLUMN_ID
			+ " INTEGER, "
			+ COLUMN_TIME
			+ " LONG, "
			+ COLUMN_CATEGORY_ID
			+ " INTEGER, "
			+ COLUMN_ACCOUNT_ID
			+ " INTEGER, "
			+ COLUMN_MONEY
			+ " DOUBLE, " + COLUMN_COMMENTS + " TEXT" + ");";

	public IncomeExpendidureDB(Context context) {
		super(context);
	}
	
	/**
	 * 
	 * @param flag
	 * @param entity
	 * @return
	 */
	public long insertIncomeExpenditureInfo(int flag, BaseEntity entity){
		long id = 0;
		db = getWritableDatabase();
		db.beginTransaction();
		try{
			IncomeExpenditureEntity newEntity = (IncomeExpenditureEntity) entity;
			ContentValues values = new ContentValues();
			values.put(COLUMN_USER_ID, newEntity.getUserID());
			values.put(COLUMN_ID, newEntity.getId());
			values.put(COLUMN_TIME, newEntity.getTime());
			values.put(COLUMN_CATEGORY_ID, newEntity.getCategory_id());
			values.put(COLUMN_MONEY, flag * newEntity.getMoney());
			values.put(COLUMN_COMMENTS, newEntity.getComments());
			
			id = db.insert(TABLE_INCOME_EXPENDITURE, null, values);
			db.setTransactionSuccessful();
		}catch(Exception e){
		}finally{
			db.endTransaction();
		}
		return id;
	}
	
	public Cursor queryIncomeExpenditureInfo(String time){
		Cursor cursor = null;
		db = getReadableDatabase();
		db.beginTransaction();
		try{
			cursor = db.query(TABLE_INCOME_EXPENDITURE, null, "time=?", new String[]{time}, null, null, null);
		}catch(Exception e){
		}finally{
			if(cursor != null){
				cursor.close();
				cursor = null;
			}
		}
		
		return cursor;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_TABLE_INCOME_EXPENDITURE);
		super.onCreate(db);
	}
}
