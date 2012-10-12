package com.github.gujiaxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDatabase extends SQLiteOpenHelper {
	private final static  int DATABASE_VERSION = 1;
	private final static  String DATABASE_NAME="betty_db1";
	private final static  String TABLE_NAME="betty_table1";

	public final static  String FIELD_ID="_id";
	public final static  String FIELD_TEXT="betty_text";

	public NoteDatabase(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table "+TABLE_NAME+" ("+FIELD_ID
				+" integer primary key autoincrement,"+
				""+FIELD_TEXT+" text)";

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		String sql ="drop table if exists "+TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public Cursor select() {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}

	public long insert(String text) {
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(FIELD_TEXT,text);
		long row=db.insert(TABLE_NAME, null,cv);
		return row;
	}

	public void update(int _id,String text) {
		SQLiteDatabase db=this.getWritableDatabase();
		String where=FIELD_ID+"=?";
		String[] strwhere={Integer.toString(_id)};
		ContentValues cv=new ContentValues();
		cv.put(FIELD_TEXT, text);
		db.update(TABLE_NAME, cv, where, strwhere);
	}

	public void delete(int _id) {
		SQLiteDatabase db=this.getWritableDatabase();
		String where=FIELD_ID+"=?";
		String[] strwhere={Integer.toString(_id)};
		db.delete(TABLE_NAME, where,strwhere);
	}
}