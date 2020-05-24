package pattern.state;

public class UpperTextState implements TextState{
	@Override
	public void write(String text, StateContext context) {
		System.out.println("Text: " + text.toUpperCase());
		context.setState(new LowerTextState());
	}
}
