
import java.util.LinkedList;
import java.util.List;


public class Main {
	
	public static void main(String ... args){
		
		try{
			PERTChart chart = new PERTChart(args[0]);
			chart.calculateCriticalPath().generateFile();
		}catch (Exception e){
			System.out.println(e.getMessage());
			
		}
	}
}
