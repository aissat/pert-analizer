import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @(#) PERTFileParser.java
 */
public class PERTFileParser {

	private String path;
	
	//private List<Task> tasks;

	private int maxEarlyFinish;
	
	//public List<Task> getTasks() {
	//	return tasks;
	//}

	//public void setTasks(List<Task> tasks) {
	//	this.tasks = tasks;
	//}

	public int getMaxEarlyFinish() {
		return maxEarlyFinish;
	}

	public void setMaxEarlyFinish(int maxEarlyFinish) {
		this.maxEarlyFinish = maxEarlyFinish;
	}

	public List<Task> parseFile(String pathToFile) throws IOException {

		List<Task> tasks = new LinkedList<Task>();

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
					throw new IllegalArgumentException(
							"Error in .pert file !!!");
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

			setSuccessors(tasks);
			
			//List<Task> data = getTasks(tasks);
			//setTasks(topologicalSort(tasks));
			tasks = topologicalSort(tasks);
			setStart(tasks);
			setFinish(tasks);

			//System.out.println("Max: " + getMaxEarlyFinish());

		} catch (IllegalArgumentException ilex) {
			System.out.println(ilex.getMessage());
		} catch (IOException iex) {
			System.out.println(iex.getMessage());
		} finally {
			br.close();
		}
		return tasks;
	}

	private Task findTaskByName(String name) {

		/*for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getLabel().equals(name)) {
				return tasks.get(i);
			}
		}*/
		return null;
	}

	private HashSet<Task> getPredecessorsList(String[] lisOfPredecessors) {
		HashSet<Task> predecessors = new HashSet<Task>();
		for (int i = 0; i < lisOfPredecessors.length; i++) {
			Task newPredecessor = findTaskByName(lisOfPredecessors[i]);
			if (newPredecessor != null) {
				predecessors.add(newPredecessor);
			} else {
				throw new IllegalArgumentException("Error in .pert file !!!");
			}
		}

		return predecessors;
	}
	
	private void setSuccessors(List<Task> tasks){
		for(Task t: tasks){
			for(Task predecessor: t.getPredecessors()){
				predecessor.addSuccessor(t);
			}
		}
	}

	public Boolean isValid() {
		return true;
	}

	private List<Task> topologicalSort(List<Task> tasks) {

		HashSet<Task> unsortedList = new HashSet<Task>(tasks);
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
				throw new RuntimeException("Cyclic dependency, algorithm stopped!");

		}

		return new LinkedList<Task>(sortedList);

	}

	private void setStart(List<Task> tasks) {
		for (Task t : tasks) {
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

	private List<Task> setFinish(List<Task> tasks) {

		List<Task> reverseList = new ArrayList<Task>(tasks);

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
