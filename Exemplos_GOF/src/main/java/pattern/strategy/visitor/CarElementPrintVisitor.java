package pattern.strategy.visitor;

import pattern.strategy.visitor.model.Car;
import pattern.strategy.visitor.model.CarBody;
import pattern.strategy.visitor.model.CarEngine;
import pattern.strategy.visitor.model.CarWheel;

public class CarElementPrintVisitor implements CarElementVisitor {

	@Override
	public void visit(CarBody carBody) {
		System.out.println("Visitando corpo");
	}

	@Override
	public void visit(Car car) {
		System.out.println("Visitando carro");
	}

	@Override
	public void visit(CarEngine engine) {
		System.out.println("Visitando motor");
	}

	@Override
	public void visit(CarWheel wheel) {
		System.out.println("Visitando roda: " + wheel.getNum());
	}
}
