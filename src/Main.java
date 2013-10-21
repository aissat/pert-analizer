
import java.util.LinkedList;
import java.util.List;


public class Main {
	
	public static void main(String ... args){
		
		try{
			String filePath = /*args[0]*/"D:\\tartuUniversity\\tasks.txt";
			PERTChart chart = new PERTChart(filePath);
			String [] pathParts = filePath.split("\\");
			chart.calculateCriticalPath().generateFile(pathParts[pathParts.length - 1]);
		}catch (Exception e){
			System.out.println(e.getMessage());
			
		}
	}
}
