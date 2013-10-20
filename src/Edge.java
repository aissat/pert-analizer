/**
 * @(#) Edge.java
 */
public class Edge
{
	private Color color = Color.BLACK;
	
	private Node predecessor;
	
	private Node successor;
	
	public void setColor( Color color )
	{
		this.color=color;
	}
	
	public Color getColor( )
	{
		return color;
	}
	
	public void setPredecessor( Node predecessor )
	{
		this.predecessor=predecessor;
	}
	
	public Node getPredecessor( )
	{
		return predecessor;
	}
	
	public void setSuccessor( Node successor )
	{
		this.successor=successor;
	}
	
	public Node getSuccessor( )
	{
		return successor;
	}
	
	
}
