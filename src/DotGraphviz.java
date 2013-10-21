import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @(#) DotGraphviz.java
 */
public class DotGraphviz
{
	private java.util.List<Node> nodes = new LinkedList<>();

	public java.util.List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(java.util.List<Node> nodes) {
		this.nodes = nodes;
	}

	public void generateFile( String filePath)
	{
		StringBuilder graph = new StringBuilder("digraph myPERT {\n");
		for (Node node: nodes){
			String color;
			switch (node.getColor()){
				case RED: color = "red"; break;
				default: color = "black";
			}
			graph.append(node.getId() + "[color=\"" + color + "\", label=\"" + node.getLabel() + "\"]\n");
			for (Node pred: node.getPredecessors()){
				color = "black";
				if (node.getColor() == Color.RED && pred.getColor() == Color.RED){ // change condition
					color = "red";
				}
				graph.append(pred.getId() + " -> " + node.getId() + "[color=\"" + color + "\"]\n");
			}
		}
		
		graph.append("}");
		
		try {
			System.out.println(graph.toString());
	        File file = new File(filePath + "/critical_path.dot");
	        BufferedWriter output = new BufferedWriter(new FileWriter(file));
	        output.write(graph.toString());
	        output.close();
		} catch ( IOException e ) {
	         e.printStackTrace();
	    }
	}	
	
}
