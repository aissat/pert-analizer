import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * @(#) PERTChart.java
 */
public class PERTChart {

	private PERTFileParser parser;
	
	private DotGraphviz graph;
	
	private java.util.List<Task> tasks = new LinkedList<>();
	

	public PERTChart(String filePath) throws IllegalArgumentException, IOException, RuntimeException{
		parser = new PERTFileParser();
		tasks = parser.parseFile(filePath);
	}
	
	public DotGraphviz calculateCriticalPath( )
	{
		graph = new DotGraphviz();
		int i = 0;
		Map<String, Node> nodes = new HashMap<>();
		for (Task t: tasks){
			Node n = new Node("n" + i++);
			String days = (t.getDuration() < 2 ? "day" : "days");
			n.setLabel(t.getLabel() + " (" + t.getDuration() + " " + days + ")");
			if ( t.getEarlyFinish() == t.getLateFinish()){
				n.setColor(Color.RED);
			}
			nodes.put(t.getLabel(), n);
		}
		for (Task t: tasks){
			for (Task pred: t.getPredecessors()){
				nodes.get(t.getLabel()).addPredecessor(nodes.get(pred.getLabel()));
			}
			
		}
		graph.setNodes(new LinkedList<Node>(nodes.values()));
		return graph;
	}
	
	public void setTasks( java.util.List<Task> tasks )
	{
		this.tasks = tasks;
	}
	
	
	public java.util.List<Task> getTasks( )
	{
		return tasks;
	}	

}
