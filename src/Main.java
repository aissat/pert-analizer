import java.io.IOException;
import java.net.URL;


public class Main {
	
	public static void main(String ... args){
		
		try{
			String filePath = System.getProperty("user.dir") + "/" + args[0];
			PERTChart chart = new PERTChart(filePath);
			chart.calculateCriticalPath().generateFile(System.getProperty("user.dir"));
		}catch (IllegalArgumentException ilex) {
			System.out.println(ilex.getMessage());
		} catch (IOException iex) {
			System.out.println(iex.getMessage()); 
		} catch (RuntimeException rex) {
			System.out.println(rex.getMessage());
		}
	}
}
