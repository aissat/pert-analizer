import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @(#) PERTFileParser.java
 */
public class PERTFileParser {

	private List<Task> tasks;

	public List<Task> getTasks() {
		return tasks;
	}

	public PERTFileParser(String pathToFile) throws IOException {

		tasks = new ArrayList<Task>();

		// String pertFile = System.getProperty("user.dir") + pathToFile;
		String pertFile = pathToFile;

		BufferedReader br = null;
		String line = "";
		String separator = ",";

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

				if (taskInfo.length >= 3) {
					String[] precsesorList = new String[taskInfo.length - 2];
					System.arraycopy(taskInfo, 2, precsesorList, 0,
							precsesorList.length);
					System.out.println("Name: " + precsesorList[0] + "\n");
					task.setPredecessors(getPredecessorsList(precsesorList));
				}

				tasks.add(task);
			}
			

		} catch (IllegalArgumentException ilex) {
			System.out.println(ilex.getMessage());
		} catch (IOException iex) {
			System.out.println(iex.getMessage());
		}finally {
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

	private List<Task> getPredecessorsList(String[] lisOfPredecessors) {
		List<Task> predecessors = new ArrayList<Task>();
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

	public Boolean isValid() {
		return true;
	}

}
