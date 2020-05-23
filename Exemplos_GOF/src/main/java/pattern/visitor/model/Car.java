package pattern.visitor.model;

import pattern.visitor.CarElementVisitor;

import java.util.ArrayList;
import java.util.List;

public class Car implements CarElement {

	private List<CarElement> carPartList = new ArrayList<>();

	public Car() {
		CarElement carBody = new CarBody();
		CarWheel carWheel = new CarWheel(1);
		CarWheel carWheel2 = new CarWheel(2);
		CarWheel carWheel3 = new CarWheel(3);
		CarWheel carWheel4 = new CarWheel(4);
		CarEngine carEngine = new CarEngine();

		carPartList.add(carBody);
		carPartList.add(carEngine);
		carPartList.add(carWheel);
		carPartList.add(carWheel2);
		carPartList.add(carWheel3);
		carPartList.add(carWheel4);
	}

	@Override
	public void accept(CarElementVisitor visitor) {
		for (CarElement carElement : carPartList) {
			carElement.accept(visitor);
		}
		visitor.visit(this);
	}
}
