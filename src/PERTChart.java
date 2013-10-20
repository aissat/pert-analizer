/**
 * @(#) PERTChart.java
 */
public class PERTChart
{
	private String name;
	
	private PERTFileParser parser;
	
	private DotGraphviz graph;
	
	private java.util.Collection<Dependency> dependancies;
	
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

	public void setDependancies( java.util.Collection<Dependency> dependancies )
	{
		this.dependancies=dependancies;
	}
	
	
	public java.util.Collection<Dependency> getDependancies( )
	{
		return dependancies;
	}
	
	
}
