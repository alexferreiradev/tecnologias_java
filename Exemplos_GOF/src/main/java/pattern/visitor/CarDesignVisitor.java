package pattern.visitor;

import pattern.visitor.model.Car;
import pattern.visitor.model.CarBody;
import pattern.visitor.model.CarEngine;
import pattern.visitor.model.CarWheel;

public class CarDesignVisitor implements CarElementVisitor {
	@Override
	public void visit(CarBody carBody) {
		System.out.println("Montando corpo");
	}

	@Override
	public void visit(Car car) {
		System.out.println("Carro montado");
	}

	@Override
	public void visit(CarEngine engine) {
		System.out.println("Montando motor");
	}

	@Override
	public void visit(CarWheel wheel) {
		System.out.println("Montando roda: " + wheel.getNum());
	}
}
