import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @(#) PERTFileParser.java
 */
public class PERTFileParser {

	private List<Task> tasks;

	private int maxEarlyFinish;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getMaxEarlyFinish() {
		return maxEarlyFinish;
	}

	public void setMaxEarlyFinish(int maxEarlyFinish) {
		this.maxEarlyFinish = maxEarlyFinish;
	}

	public PERTFileParser(String pathToFile) throws IOException {

		tasks = new ArrayList<Task>();

		// String pertFile = System.getProperty("user.dir") + pathToFile;
		String pertFile = pathToFile;

		BufferedReader br = null;
		String line = "";
		String separator = ",";
		HashMap<Task, String[]> taskAndPredecessors = new HashMap<Task, String[]>();

		try {
			br = new BufferedReader(new FileReader(pertFile));
			while ((line = br.readLine()) != null) {

				String[] taskInfo = line.split(separator);

				if (taskInfo.length < 2) {
					throw new RuntimeException("Error in .pert file.  !");
				}

				Task task = new Task();
				task.setDuration(Integer.parseInt(taskInfo[1]));
				task.setLabel(taskInfo[0]);
				tasks.add(task);

				if (taskInfo.length >= 3) {
					String[] precsesorList = new String[taskInfo.length - 2];
					System.arraycopy(taskInfo, 2, precsesorList, 0,	precsesorList.length);
					taskAndPredecessors.put(task, precsesorList);
				}
			}
			
			for (Map.Entry<Task, String[]> entry : taskAndPredecessors.entrySet()) {
				entry.getKey().addPredecessors(getPredecessorsList(entry.getValue()));
			}

			setSuccessors();
			setTasks(topologicalSort());
			setStart();
			setFinish();
			
			int numberOfStartPoint = 0;
			int numberOfEndPoint = 0;
			for(Task t: getTasks()){
				if(t.getPredecessors().size() == 0){
					numberOfStartPoint++;
				}
				
				if(t.getSuccessors().size() == 0){
					numberOfEndPoint ++;
				}
			}
			
			if(numberOfStartPoint > 1)
				throw new RuntimeException("More than one start point !");
			
			if(numberOfEndPoint > 1)
				throw new RuntimeException("More than one end point !");

		}catch(RuntimeException rex){
			System.out.println(rex.getMessage());
		} finally {
			br.close();
		}
	}
	
	

	private Task findTaskByName(String name) {

		Task task = null;

		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getLabel().equals(name)) {
				task = tasks.get(i);
			}
		}
		return task;
	}

	private HashSet<Task> getPredecessorsList(String[] lisOfPredecessors) {
		HashSet<Task> predecessors = new HashSet<Task>();
		for (int i = 0; i < lisOfPredecessors.length; i++) {
			Task newPredecessor = findTaskByName(lisOfPredecessors[i]);
			if (newPredecessor != null) {
				predecessors.add(newPredecessor);
			} else {
				throw new RuntimeException("Error in .pert file !");
			}
		}

		return predecessors;
	}
	
	private void setSuccessors(){
		for(Task t: getTasks()){
			for(Task predecessor: t.getPredecessors()){
				predecessor.addSuccessor(t);
			}
		}
	}

	public Boolean isValid() {
		return true;
	}

	private List<Task> topologicalSort() {

		HashSet<Task> unsortedList = new HashSet<Task>(getTasks());
		HashSet<Task> sortedList = new HashSet<Task>();

		while (!unsortedList.isEmpty()) {
			boolean progress = false;
			for(Iterator<Task> it = unsortedList.iterator(); it.hasNext();) {
				Task t = it.next();
				if (sortedList.containsAll(t.getSuccessors())) {
					
					sortedList.add(t);
					unsortedList.remove(t);
					progress = true;
					break;
				}

			}
			
			if (!progress)
				throw new RuntimeException("Error. Cycle has been detected !");

		}

		return new ArrayList<Task>(sortedList);

	}

	private void setStart() {
		for (Task t : getTasks()) {
			if (t.getPredecessors() != null) {
				for (Task predcessor : t.getPredecessors()) {
					if (t.getEarlyStart() <= predcessor.getEarlyFinish()) {
						t.setEarlyStart(predcessor.getEarlyFinish());
						t.setEarlyFinish(t.getEarlyStart() + t.getDuration());
						if (t.getEarlyFinish() > getMaxEarlyFinish()) {
							setMaxEarlyFinish(t.getEarlyFinish());
						}
					}
				}
			}
		}
	}

	private List<Task> setFinish() {

		List<Task> reverseList = new ArrayList<Task>(getTasks());

		Collections.reverse(reverseList);

		for (Task t : reverseList) {
			t.setLateFinish(getMaxEarlyFinish());
			t.setLateStart(getMaxEarlyFinish());
		}

		for (Task t : reverseList) {
			if (t.getPredecessors() != null) {
				for (Task predcessor : t.getPredecessors()) {
					if (predcessor.getLateFinish() >= t.getLateStart()) {
						predcessor.setLateFinish(t.getLateStart());
						predcessor.setLateStart(predcessor.getLateFinish()
								- predcessor.getDuration());
					}
				}
			}
		}

		return reverseList;
	}

}
