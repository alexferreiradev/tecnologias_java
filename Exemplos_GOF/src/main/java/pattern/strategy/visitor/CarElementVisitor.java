package pattern.strategy.visitor;

import pattern.strategy.visitor.model.Car;
import pattern.strategy.visitor.model.CarBody;
import pattern.strategy.visitor.model.CarEngine;
import pattern.strategy.visitor.model.CarWheel;

public interface CarElementVisitor {

	void visit(Car car);
	void visit(CarBody carBody);
	void visit(CarEngine engine);
	void visit(CarWheel wheel);

}
