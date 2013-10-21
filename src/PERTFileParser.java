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

	public List<Task> parseFile(String pathToFile) throws IOException, IllegalArgumentException, RuntimeException {

		String pertFile = pathToFile;
		
		Map<String, Task> tasksMap = new HashMap<>();
		List<Task> tasks = null;
		int maxEarlyFinish = 0;

		BufferedReader br = null;
		String line = "";
		String separator = ",";

			br = new BufferedReader(new FileReader(pertFile));
			LinkedList<String[]> lines = new LinkedList<>();
			while ((line = br.readLine()) != null) {
				String[] taskInfo = line.split(separator);
				if (taskInfo.length < 2) {
					throw new RuntimeException("Incorrect task info!");
				}
				lines.add(taskInfo);
			}
			for (String[] t : lines) {
				Task task = new Task();
				task.setDuration(Integer.parseInt(t[1]));
				task.setLabel(t[0]);
				tasksMap.put(task.getLabel(), task);
			}
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).length >= 3) {
					for (int j = 2; j < lines.get(i).length; j++) {
						if (tasksMap.get(lines.get(i)[j]) == null)
							throw new RuntimeException(
									"Invalid task name! Line " + (i + 1));
						tasksMap.get(lines.get(i)[0]).addPredecessor(
								tasksMap.get(lines.get(i)[j]));
					}
				}
			}

			tasks = new LinkedList<>(tasksMap.values());
			for (Task t : tasks) {
				for (Task predecessor : t.getPredecessors()) {
					predecessor.addSuccessor(t);
				}
			}

			
			List<Task> reverseList = checkCyclic(tasks);
			Collections.reverse(reverseList);
			tasks = reverseList;

			// ====== set start
			for (Task t : tasks) {
				if (t.getPredecessors() != null) {
					for (Task predcessor : t.getPredecessors()) {
						if (t.getEarlyStart() <= predcessor.getEarlyFinish()) {
							t.setEarlyStart(predcessor.getEarlyFinish());
							t.setEarlyFinish(t.getEarlyStart()
									+ t.getDuration());
							if (t.getEarlyFinish() > maxEarlyFinish) {
								maxEarlyFinish = t.getEarlyFinish();
							}
						}
					}
				}
			}

			// ===== set finish
			reverseList = new ArrayList<Task>(tasks);
			Collections.reverse(reverseList);
			tasks = reverseList;

			for (Task t : reverseList) {
				t.setLateFinish(maxEarlyFinish);
				t.setLateStart(maxEarlyFinish);
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

			tasks = reverseList;

			// === check there is one task with no successors and one task with
			// no predecessors
			int numberOfStartPoint = 0;
			int numberOfEndPoint = 0;
			for (Task t : tasks) {
				boolean hasPredecessors = true, hasSuccessors = true;
				if (t.getPredecessors().size() == 0) {
					numberOfStartPoint++;
					hasPredecessors = false;
				}

				if (t.getSuccessors().size() == 0) {
					numberOfEndPoint++;
					hasSuccessors = false;
				}
				if (!hasSuccessors && !hasPredecessors)
					throw new RuntimeException("Task has neither predecessors nor successors !");
			}

			if (numberOfStartPoint > 1)
				throw new RuntimeException("More than one start point !");

			if (numberOfEndPoint > 1)
				throw new RuntimeException("More than one end point !");
			
		
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return tasks;
	}

	private List<Task> checkCyclic(List<Task> tasks) {

		List<Task> unsortedList = new ArrayList<Task>(tasks);
		List<Task> sortedList = new ArrayList<Task>();

		while (!unsortedList.isEmpty()) {
			boolean progress = false;
			for (Iterator<Task> it = unsortedList.iterator(); it.hasNext();) {
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

		return sortedList;

	}

}
