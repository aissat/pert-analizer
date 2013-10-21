import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @(#) PERTChart.java
 */
public class PERTChart
{
	private String name;
	
	private PERTFileParser parser;
	
	private DotGraphviz graph;
	
	private java.util.List<Task> tasks = new LinkedList<>();
	
	public PERTChart(String filePath){
		parser = new PERTFileParser();
		try {
			tasks = parser.parseFile(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DotGraphviz calculateCriticalPath( )
	{
		List<Task> sortedElements = new ArrayList<Task>();
		// Not sure !!!!!
		List<Task> unsortedElements = tasks;
		
		while(unsortedElements.size() != 0 ){
			
			Iterator<Task> i = unsortedElements.iterator();
			
			while(i.hasNext()){
				Task task = i.next();
				int critical = 0;
				if(sortedElements.containsAll(task.getPredecessors())){
					for (Task t : task.getPredecessors()) {
                        if (t.getCriticalCost() > critical) {
                            critical = t.getCriticalCost();
                        }
                    }
                    task.setCriticalCost(critical + task.getDuration());
                    // set task as calculated an remove
                    sortedElements.add(task);
                    unsortedElements.remove(task);
				}
				
			}
		}
		
		return null;
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
