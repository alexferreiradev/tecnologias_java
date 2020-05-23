package pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{

	private List<Component> componentList = new ArrayList<>();

	@Override
	public void print() {
		for (Component component : componentList) {
			component.print();
		}
	}

	public void add(Component component) {
		componentList.add(component);
	}

	public void remove(Component component) {
		componentList.remove(component);
	}
}
