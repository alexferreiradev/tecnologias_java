package pattern.command;

import java.util.ArrayList;
import java.util.List;

public class Switch {

	private List<Command> history = new ArrayList<>();

	public void storeAndExecute(Command command) {
		history.add(command);
		command.execute();
	}

	public void undo() {
		List<Command> invertedHistory = createInvertedList();
		for (Command command : invertedHistory) {
			command.undo();
		}
	}

	private List<Command> createInvertedList() {
		List<Command> invertedList = new ArrayList<>();
		int size = history.size();
		for (int i = size - 1; i >= 0; i--) {
			invertedList.add(history.get(i));
		}

		return invertedList;
	}

}
