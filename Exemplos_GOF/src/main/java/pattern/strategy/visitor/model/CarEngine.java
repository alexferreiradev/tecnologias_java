package pattern.strategy.visitor.model;

import pattern.strategy.visitor.CarElementVisitor;

public class CarEngine implements CarElement {
	@Override
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
}
