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
		
		TextView tv = (TextView)findViewById(R.id.lblTest);
		tv.setPaintFlags(tv.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);

		reloadItems();
    }
    
   
    
    public void onClick(View view) {
   	
    	
		@SuppressWarnings("unchecked")
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		Comment comment = null;
		switch (view.getId()) {
		case R.id.add:
			final Context context = this;
	    	Intent intent = new Intent(context, AddItemActivity.class);
	    	startActivity(intent);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				/*comment = (Comment) getListAdapter().getItem(0);
				datasource.deleteComment(comment);
				adapter.remove(comment);*/
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
		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
				android.R.layout.simple_list_item_multiple_choice, values);
		setListAdapter(adapter);
		
		final ListView ls = getListView();
		ls.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ls.setOnItemClickListener(new OnItemClickListener(){
			
			 public void onItemClick(AdapterView<?> parent, View view,
				        int position, long id) {
				 
				 ((TextView) view).setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
				      // When clicked, show a toast with the TextView text
				      //Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
				        //  Toast.LENGTH_SHORT).show();
				    }

			
		});
	}
	
	private void deleteItems()
	{
		
		ListView listView = getListView();
		int total = listView.getCount();
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		List<Comment> values = datasource.getAllComments();
		for(int i=0; i<total;i++)
		{
			if(checked.get(i))
			{
				Comment comment = values.get(i);
				datasource.deleteComment(comment);
				
			}
		}
		
		reloadItems();
		
	}	
	
}


































