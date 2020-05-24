package pattern.visitor.model;

import pattern.visitor.CarElementVisitor;

public class CarWheel implements CarElement {

	private Integer num;

	public CarWheel(Integer num) {
		this.num = num;
	}

	@Override
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
