package main;

import pattern.composite.Circle;
import pattern.composite.Composite;
import pattern.composite.Square;
import pattern.composite.Triangle;

public class CompositeMain {

	public static void main(String[] args) {
		Composite composite = new Composite();
		composite.add(new Circle());
		composite.add(new Square());
		composite.add(new Triangle());

		composite.print();
	}
}
