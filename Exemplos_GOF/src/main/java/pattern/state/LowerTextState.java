package pattern.state;

public class LowerTextState implements TextState {
	@Override
	public void write(String text, StateContext context) {
		System.out.println("Texto: " + text.toLowerCase());
		context.setState(new UpperTextState());
	}
}
