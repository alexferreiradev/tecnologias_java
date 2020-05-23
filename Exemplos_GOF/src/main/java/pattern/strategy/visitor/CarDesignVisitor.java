package pattern.strategy.visitor;

import pattern.strategy.visitor.model.Car;
import pattern.strategy.visitor.model.CarBody;
import pattern.strategy.visitor.model.CarEngine;
import pattern.strategy.visitor.model.CarWheel;

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
