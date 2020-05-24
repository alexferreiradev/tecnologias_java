package pattern.state;

public class StateContext {
	private TextState state = new LowerTextState();

	public void setState(TextState textState) {
		this.state = textState;
	}

	public void writeText(String teste) {
		state.write(teste, this);
	}
}
