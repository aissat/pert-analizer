import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @(#) PERTChart.java
 */
public class PERTChart
{
	private String name;
	
	private static PERTFileParser parser;
	
	private DotGraphviz graph;
	
	private static List<Task> tasks;
	
	public static void main(String[] args) {
		
		try{
			
			parser = new PERTFileParser(args[0]);
			//tasks = parser.getTasks();
		}catch (IOException e){
			System.out.println(e.getMessage());
			
		}
		
	}
	
	public DotGraphviz calculateCriticalPath( )
	{
		
		List<Task> sortedElements = new ArrayList<Task>();
		// Not sure !!!!!
		List<Task> unsortedElements = parser.getTasks();
		
		
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
	
	public void addDependancy( Dependency dependancy )
	{
		
	}


	public Task getFirstTask( )
	{
		return null;
	}
	
	
	public void setTasks( Task tasks )
	{
		
	}
	
	
	public Task getTasks( )
	{
		return null;
	}
	
	
	public void addTask( Task task )
	{
		
	}
	
	
	public Task findTask( String taskLabel )
	{
		return null;
	}
	
	
}
