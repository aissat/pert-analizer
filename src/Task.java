import java.util.List;

/**
 * @(#) Task.java
 */
public class Task
{
	private String label;
	
	private int duration;
	
	
	private java.util.List<Task> predecessors;
	
	
	
	public void setDuration( int duration )
	{
		this.duration=duration;
	}
	
	public int getDuration( )
	{
		return duration;
	}
	
	
public String getLabel( )
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label=label;
	}
	
	
	public void setPredecessors( java.util.List<Task> predecessors )
	{
		this.predecessors=predecessors;
	}
	
	
	public java.util.List<Task> getPredecessors( )
	{
		return predecessors;
	}
	
	
	public void addPredecessor( Task task )
	{
		
	}
	
	public void addPredecessors(List<Task> tasks){
		predecessors = tasks;
	}
	
	
}
