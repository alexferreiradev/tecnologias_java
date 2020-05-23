package pattern.visitor;

import pattern.visitor.model.Car;
import pattern.visitor.model.CarBody;
import pattern.visitor.model.CarEngine;
import pattern.visitor.model.CarWheel;

public interface CarElementVisitor {

	void visit(Car car);
	void visit(CarBody carBody);
	void visit(CarEngine engine);
	void visit(CarWheel wheel);

}
