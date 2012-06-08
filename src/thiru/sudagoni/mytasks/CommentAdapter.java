package thiru.sudagoni.mytasks;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CommentAdapter extends ArrayAdapter<Comment> {

	
	private final Context context;
	private final List<Comment> comments;
	
	public CommentAdapter(Context context,List<Comment> comments){
		super( context, R.layout.items, comments);
		this.context = context;
		this.comments= comments;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.items, parent,false);
		
		TextView tvId = (TextView) rowView.findViewById(R.id.hdCommentId);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		CheckBox chk = (CheckBox) rowView.findViewById(R.id.chkItem);
		Comment comment = comments.get(position);
		if(comment!=null)
		{
			tvId.setText(String.format("%s", comment.getId()));
			textView.setText( String.format(" %s",comment.getComments()));
			
			int check = comment.getCompleted();
			
			if(check==1)
				chk.setChecked(true);
			else
				chk.setChecked(false);
		}
		
		return rowView;
	}
}
