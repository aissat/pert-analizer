import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @(#) PERTChart.java
 */
public class PERTChart
{
	private String name;
	
	private PERTFileParser parser;
	
	private DotGraphviz graph;
	
	private java.util.List<Task> tasks;
	
	public PERTChart(){
		tasks = new LinkedList<>();
	}
	
	public DotGraphviz calculateCriticalPath( )
	{
		Map<String, Node> nodes = new HashMap<>();
		DotGraphviz graph = new DotGraphviz();
		for (int i = 0; i < tasks.size(); i++){
			Node n = new Node(i);
			String days = (tasks.get(i).getDuration() == 0 || tasks.get(i).getDuration() == 1 ? "day" : "days");
			n.setLabel(tasks.get(i).getLabel() + " (" + tasks.get(i).getDuration() + " " + days + ")");
			nodes.put(tasks.get(i).getLabel(), n);
		}
		
		for (int i = 0; i < tasks.size(); i++){
			for (int j = 0; j < tasks.get(i).getPredecessors().size(); j++){
				Edge e = new Edge();
				e.setPredecessor(nodes.get(tasks.get(i).getPredecessors().get(j).getLabel()));
				e.setSuccessor(nodes.get(tasks.get(i).getLabel()));
				graph.addEdge(e);
			}
		}
		graph.setNodes(nodes);
		
		return graph;
	}
	
	public void setName( String name )
	{
		this.name=name;
	}
	
	public String getName( )
	{
		return name;
	}
	
	public Task getFirstTask( )
	{
		if (tasks.isEmpty())
			return null;
		return tasks.get(0);
	}
	
	
	public void setTasks( java.util.List<Task> tasks )
	{
		this.tasks = tasks;
	}
	
	
	public java.util.List<Task> getTasks( )
	{
		return tasks;
	}
	
	
	public void addTask( Task task )
	{
		tasks.add(task);
	}
	
	
	public Task findTask( String taskLabel )
	{
		for (int i = 0 ; i < tasks.size(); i++){
			if (tasks.get(i).getLabel().equals(taskLabel))
				return tasks.get(i);
		}
		return null;
	}
	
	
}
