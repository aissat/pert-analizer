
import java.util.LinkedList;
import java.util.List;


public class Main {
	
	public static void main(String ... args){
		List<Task> tasks = new LinkedList<>();
		for (int i = 0; i < 5; i++){
			Task t = new Task();
			t.setLabel(i + "");
			t.setDuration(i);
			tasks.add(t);
			if (i != 0){
				t.addPredecessor(tasks.get(i - 1));
			}
				
		}
		tasks.get(4).addPredecessor(tasks.get(1));
		PERTChart chart = new PERTChart();
		chart.setTasks(tasks);
		chart.calculateCriticalPath().generateFile();
	}
}
