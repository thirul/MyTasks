package thiru.sudagoni.mytasks;

public class Comment {
	
	private long id;
	private String comment;
	private int completed;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id=id; 
	}
	
	public void setComments(String comment)
	{
		this.comment = comment;
	}
	
	public String getComments()
	{
		return comment;
	}
	
	public int getCompleted()
	{
		return completed;
	}
	public void setCompleted(int completed)
	{
		this.completed= completed;
	}
	
	@Override
	public String toString()
	{
		return comment;
	}

}
