import java.util.HashSet;


/**
 * @(#) Task.java
 */
public class Task
{
	private String label;
	
	private int duration;
	
	private int criticalCost;
	
	private int earlyStart = 0;
	
	private int lateStart = 0;
	
	private int earlyFinish = 0;
	
	private int lateFinish = 0;

	private java.util.HashSet<Task> predecessors = new HashSet<Task>();
	
	private java.util.HashSet<Task> successors  = new HashSet<Task>();
	
	public int getEarlyStart() {
		return earlyStart;
	}

	public void setEarlyStart(int earlyStart) {
		this.earlyStart = earlyStart;
	}

	public int getLateStart() {
		return lateStart;
	}

	public void setLateStart(int lateStart) {
		this.lateStart = lateStart;
	}

	public int getEarlyFinish() {
		return earlyFinish;
	}

	public void setEarlyFinish(int earlyFinish) {
		this.earlyFinish = earlyFinish;
	}

	public int getLateFinish() {
		return lateFinish;
	}

	public void setLateFinish(int lateFinish) {
		this.lateFinish = lateFinish;
	}
	
	public int getCriticalCost() {
		return criticalCost;
	}

	public void setCriticalCost(int criticalCost) {
		this.criticalCost = criticalCost;
	}
	
	public java.util.HashSet<Task> getSuccessors() {
		return successors;
	}

	public void setSuccessors(java.util.HashSet<Task> successors) {
		this.successors = successors;
	}
	
	public void addSuccessor(Task successor) {
		this.successors.add(successor);
	}

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
	
	
	public void setPredecessors( java.util.HashSet<Task> predecessors )
	{
		this.predecessors=predecessors;
	}
	
	
	public java.util.HashSet<Task> getPredecessors( )
	{
		return predecessors;
	}
	
	
	public void addPredecessor( Task task )
	{
		predecessors.add(task);
	}
	
	
}
