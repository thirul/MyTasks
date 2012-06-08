package thiru.sudagoni.mytasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";
	public static final String COLUMN_COMPLETED = "completed";
	public static final String COLUMN_NOTES = "notes";
	
	
	private static final String DATABASE_NAME = "comments.db";
	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_COMMENTS + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_COMMENT
			+ " text not null," + COLUMN_COMPLETED			+ " integer not null );"; 
	
	public MySQLiteHelper(Context context)
	{
		super(context,DATABASE_NAME,null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS  "+TABLE_COMMENTS);
		onCreate(db);
	}

}
