package pattern.command;

import pattern.composite.Component;

public class FlipUpCommand implements Command {

	private Light light;

	public FlipUpCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.turnOn();
	}

	@Override
	public void undo() {
		light.turnOff();
	}
}
