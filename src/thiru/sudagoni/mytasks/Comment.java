package thiru.sudagoni.mytasks;

public class Comment {
	
	private long id;
	private String comment;
	
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
	
	@Override
	public String toString()
	{
		return comment;
	}

}
