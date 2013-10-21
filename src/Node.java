/**
 * @(#) Node.java
 */
public class Node
{
	private String label;
	private int id;
	
	private Color color = Color.WHITE;
	
	private FontStyle fontStyle = FontStyle.NORMAL;
	
	public Node(int id){
		this.id = id; 
	}
	
	public int getId() {
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
	
	public void setFontStyle( FontStyle fontStyle )
	{
		this.fontStyle=fontStyle;
	}
	
	public FontStyle getFontStyle( )
	{
		return fontStyle;
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
