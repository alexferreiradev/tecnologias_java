package main;

import pattern.command.FlipDownCommand;
import pattern.command.FlipUpCommand;
import pattern.command.Light;
import pattern.command.Switch;

public class CommandMain {

	public static void main(String[] args) {
		Light light = new Light();
		FlipUpCommand flipUpCommand = new FlipUpCommand(light);
		FlipDownCommand flipDownCommand = new FlipDownCommand(light);

		Switch mySwitch = new Switch();
		mySwitch.storeAndExecute(flipUpCommand);
		mySwitch.storeAndExecute(flipUpCommand);
		mySwitch.storeAndExecute(flipDownCommand);
		mySwitch.storeAndExecute(flipDownCommand);
		mySwitch.storeAndExecute(flipDownCommand);
		mySwitch.storeAndExecute(flipUpCommand);
		System.out.println("Desfazendo todos comandos armazenados");
		mySwitch.undo();
	}
}
