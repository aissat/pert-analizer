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
			tasks = parser.getTasks();
		}catch (Exception e){
			System.out.println(e.getMessage());
			
		}
		
	}
	
	public DotGraphviz calculateCriticalPath( )
	{
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
