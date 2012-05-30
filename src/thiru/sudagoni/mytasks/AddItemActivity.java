package thiru.sudagoni.mytasks;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;




public class AddItemActivity extends Activity  {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);
    }
        
	public void onClickAddNew(View view)
	{
		EditText txtNewItem = (EditText) findViewById(R.id.txtItem);
		String item = txtNewItem.getText().toString();
		
		if(item.length()==0)
		{
			Toast.makeText(this, "Please enter item", Toast.LENGTH_LONG).show();
			return;
		}
		
		
		
		CommentsDataSource  datasource = new CommentsDataSource(this);
		datasource.open();

		Comment comment = datasource.createComment(item);
		
		datasource.close();
		
		goToMainActivity();
	}
	public void onClickCancel(View view)
	{
		goToMainActivity();
	}
	
	private void goToMainActivity()
	{
		final Context context = this;
		Intent intent = new Intent(context,MyTasksActivity.class);
		startActivity(intent);
	}
}