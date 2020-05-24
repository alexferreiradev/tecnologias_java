package main;

import pattern.state.StateContext;

public class StateMain {

	public static void main(String[] args) {
		StateContext stateContext = new StateContext();
		stateContext.writeText("Teste");
		stateContext.writeText("Lower");
		stateContext.writeText("Upper");
		stateContext.writeText("lower");
		stateContext.writeText("teste");
	}
}
