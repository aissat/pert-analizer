/**
 * @(#) Dependency.java
 */
public class Dependency
{
	private Task successor;
	
	private Task predecessor;
	
	public void setSuccessor( Task successor )
	{
		this.successor=successor;
	}
	
	public Task getSuccessor( )
	{
		return successor;
	}
	
	public void setPredecessor( Task predecessor )
	{
		this.predecessor=predecessor;
	}
	
	public Task getPredecessor( )
	{
		return predecessor;
	}
	
	
}
