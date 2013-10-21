import java.net.URL;


public class Main {
	
	public static void main(String ... args){
		
		try{
			String filePath = "D:\\tartuUniversity\\tasks.txt";//args[0];
			PERTChart chart = new PERTChart(filePath);
			chart.calculateCriticalPath().generateFile(filePath.substring(0, filePath.lastIndexOf("\\")));
		}catch (Exception e){
			System.out.println(e.getMessage());
			
		}
	}
}
