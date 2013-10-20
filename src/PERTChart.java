import java.util.Collection;

/**
 * @(#) PERTChart.java
 */
public class PERTChart
{
	private String name;
	
	private PERTFileParser parser;
	
	private DotGraphviz graph;
	
	private Collection dependancies;
	
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
}
