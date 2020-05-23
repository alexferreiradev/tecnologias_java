package pattern.strategy.visitor.model;

import pattern.strategy.visitor.CarElementVisitor;

public interface CarElement {
	void accept(CarElementVisitor visitor);
}
