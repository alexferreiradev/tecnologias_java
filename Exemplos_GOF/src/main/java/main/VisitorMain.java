package main;

import pattern.strategy.visitor.CarDesignVisitor;
import pattern.strategy.visitor.CarElementPrintVisitor;
import pattern.strategy.visitor.model.Car;

public class VisitorMain {

	public static void main(String[] args) {
		Car car = new Car();

		car.accept(new CarDesignVisitor());
		car.accept(new CarElementPrintVisitor());
	}
}
