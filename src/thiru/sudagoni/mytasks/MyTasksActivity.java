package thiru.sudagoni.mytasks;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyTasksActivity extends ListActivity  {
	private CommentsDataSource datasource;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        datasource = new CommentsDataSource(this);
		datasource.open();
		
		reloadItems();
    }
    
   
    
    public void onClick(View view) {
   	
    	
		@SuppressWarnings("unchecked")
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		Comment comment = null;
		final Context context = this;
		switch (view.getId()) {
		case R.id.add:
			
	    	Intent intent = new Intent(context, AddItemActivity.class);
	    	startActivity(intent);
			break;
		/*case R.id.btnNew:
			
	    	Intent intent2 = new Intent(context, MyList.class);
	    	startActivity(intent2);
			break;*/
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {				
				deleteItems();
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}
    @Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	private void reloadItems()
	{
		List<Comment> values = datasource.getAllComments();
		
		
		

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		CommentAdapter adapter = new CommentAdapter(this, values);
		setListAdapter(adapter);
		
		final Context context = this;
		final ListView ls = getListView();
	//	ls.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ls.setOnItemClickListener(new OnItemClickListener(){
			
			 public void onItemClick(AdapterView<?> parent, View view,
				        int position, long id) {
				 
				 Toast.makeText(context, "selected", Toast.LENGTH_LONG);
				 
				 Comment comment = new Comment();
				 comment.setId(position);
				 
				 
				 if(isItemChecked(position))
				 {
					 ((TextView) view).setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
					 comment.setCompleted(1);
				 }
				 else
				 {
					 comment.setCompleted(0);
				 }
				 
				 datasource.updateComment(comment);			 

			  }

			
		});
	}
	
	 public void onClickCheck(View view)
	 {
		 ListView listView = getListView();
		 final int position = listView.getPositionForView((View)view.getParent());
		 if(position>-1)
		 {
			 View child = listView.getChildAt(position);
			 CheckBox chk = (CheckBox) child.findViewById(R.id.chkItem);
			 int checkStatus = 0;
				if(chk.isChecked())
				{				
					checkStatus = 1;
				}
				TextView tvId = (TextView) child.findViewById(R.id.hdCommentId);
				long id = Long.parseLong(tvId.getText().toString());
				
				if(id>0)
				{
					Comment comment = new Comment();
					comment.setId(id);
					comment.setCompleted(checkStatus);
					datasource .updateComment(comment);
				}				
		 }
	 }
	private void deleteItems()
	{
		
		
		ListView listView = getListView();
		
		for(int i=0; i< listView.getChildCount(); i++)
		{
			View v = listView.getChildAt(i);
			CheckBox chk = (CheckBox) v.findViewById(R.id.chkItem);
			if(chk.isChecked())
			{
				TextView tvId = (TextView) v.findViewById(R.id.hdCommentId);
				long id = Long.parseLong(tvId.getText().toString());
				
				if(id>0)
				{
					Comment comment = new Comment();
					comment.setId(id);
					datasource .deleteComment(comment);
				}
				
			}
		}
		
		reloadItems();
		
	}
	
	private Boolean isItemChecked(int position)
	{
		Boolean result = false;
		
		ListView listView = getListView();
		
		SparseBooleanArray checked = listView.getCheckedItemPositions();		
		
		if(checked.get(position))
		{
			result = true;
		}
		
		
		
		return result;
	}
	
}


































