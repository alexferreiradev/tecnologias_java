package pattern.visitor.model;

import pattern.visitor.CarElementVisitor;

public class CarBody implements CarElement {
	@Override
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
}
