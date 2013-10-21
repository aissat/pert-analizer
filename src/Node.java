import java.util.LinkedList;

/**
 * @(#) Node.java
 */
public class Node
{
	private String label;
	private String id;
	
	private Color color = Color.BLACK;
	
	private java.util.List<Node> predecessors = new LinkedList<>();
	
	public java.util.List<Node> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(java.util.List<Node> predecessors) {
		this.predecessors = predecessors;
	}
	
	public void addPredecessor(Node predecessor) {
		this.predecessors.add(predecessor);
	}

	public Node(String id){
		this.id = id; 
	}
	
	public String getId() {
		return id;
	}

	public void setColor( Color color )
	{
		this.color=color;
	}
	
	public Color getColor( )
	{
		return color;
	}
	
	
public String getLabel( )
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label=label;
	}
	
	
}
