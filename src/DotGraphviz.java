import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @(#) DotGraphviz.java
 */
public class DotGraphviz
{
	private java.util.List<Edge> edges;
	private java.util.Map<String, Node> nodes;
	
	public java.util.Collection<Edge> getEdges() {
		return edges;
	}

	public void setEdges(java.util.List<Edge> edges) {
		this.edges = edges;
	}

	public java.util.Map<String, Node> getNodes() {
		return nodes;
	}

	public void setNodes(java.util.Map<String, Node> nodes) {
		this.nodes = nodes;
	}

	public DotGraphviz(){
		edges = new LinkedList<>();
		nodes = new HashMap<>();
	}
	
	public void generateFile( )
	{
		StringBuilder graph = new StringBuilder("digraph myPERT {\n");
		int j = 0;
		for (Map.Entry<String, Node> node: nodes.entrySet()){
			
				String color;
				switch (node.getValue().getColor()){
					case GREY: color = "grey"; break;
					default: color = "white";
				}
				String fontStyle;
				switch (node.getValue().getFontStyle()){
					case BOLD: fontStyle = "bold"; break;
					case ITALIC: fontStyle = "italic"; break;
					default: fontStyle = "normal";
				}
				graph.append("n"+ node.getValue().getId() + "[color=\"" + color + "\", style=\"" + fontStyle + "\", label=\"" + node.getKey() + "\"]\n");
			
		}
		for (int i = 0; i < edges.size(); i++){
			if (edges.get(i).getPredecessor() == null) continue;
			if (edges.get(i).getSuccessor() == null) continue;
			String color;
			switch (edges.get(i).getColor()){
				case RED: color = "red"; break;
				default: color = "black";
			}
			graph.append(edges.get(i).getPredecessor().getId() + " -> " + edges.get(i).getSuccessor().getId() + "[color=" + color + "]\n");
		}
		graph.append("}");
		
		try {
			System.out.println(graph.toString());
	        File file = new File("D:/tartuUniversity/critical_path.dot");
	        BufferedWriter output = new BufferedWriter(new FileWriter(file));
	        output.write(graph.toString());
	        output.close();
		} catch ( IOException e ) {
	         e.printStackTrace();
	    }
	}
	
	public void addEdge( Edge edge )
	{
		
	}
	
	
}
