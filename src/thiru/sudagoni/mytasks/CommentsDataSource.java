package thiru.sudagoni.mytasks;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

	
		private SQLiteDatabase database;
		private MySQLiteHelper dbHelper;
		private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT};
		
		public CommentsDataSource(Context context)
		{
			dbHelper = new MySQLiteHelper(context);
		}
		
		public void open()
		{
			database = dbHelper.getWritableDatabase();
		}
			
		public void close()
		{
			database.close();
		}
		
		public Comment createComment(String comment)
		{
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
			
			long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);
			Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, MySQLiteHelper.COLUMN_ID+" = "+ insertId,null,null,null,null);
			cursor.moveToNext();
			Comment commentNew = cursorToComment(cursor);
			cursor.close();
			return commentNew;
		}
		
		public void deleteComment(Comment comment) {
			long id = comment.getId();
			System.out.println("Comment deleted with id: " + id);
			database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
					+ " = " + id, null);
		}

		public List<Comment> getAllComments() {
			List<Comment> comments = new ArrayList<Comment>();

			Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
					allColumns, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Comment comment = cursorToComment(cursor);
				comments.add(comment);
				cursor.moveToNext();
			}
			// Make sure to close the cursor
			cursor.close();
			return comments;
		}
		
		
		
		private Comment cursorToComment(Cursor cursor) {
			Comment comment = new Comment();
			comment.setId(cursor.getLong(0));
			comment.setComments(cursor.getString(1));
			return comment;
		}
			
	
	
	
}