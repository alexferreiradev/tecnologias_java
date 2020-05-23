package pattern.visitor.model;

import pattern.visitor.CarElementVisitor;

public interface CarElement {
	void accept(CarElementVisitor visitor);
}
