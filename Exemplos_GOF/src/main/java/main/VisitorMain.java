package main;

import pattern.visitor.CarDesignVisitor;
import pattern.visitor.CarElementPrintVisitor;
import pattern.visitor.model.Car;

public class VisitorMain {

	public static void main(String[] args) {
		Car car = new Car();

		car.accept(new CarDesignVisitor());
		car.accept(new CarElementPrintVisitor());
	}
}
