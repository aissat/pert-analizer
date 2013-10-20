/**
 * @(#) Task.java
 */
public class Task
{
	private String label;
	
	private int duration;
	
	public void setDuration( int duration )
	{
		this.duration=duration;
	}
	
	public int getDuration( )
	{
		return duration;
	}
	
	public void setLabel( String label )
	{
		this.label=label;
	}
	
	public String getLabel( )
	{
		return label;
	}
}
