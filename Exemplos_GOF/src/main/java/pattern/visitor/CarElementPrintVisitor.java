package pattern.visitor;

import pattern.visitor.model.Car;
import pattern.visitor.model.CarBody;
import pattern.visitor.model.CarEngine;
import pattern.visitor.model.CarWheel;

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
