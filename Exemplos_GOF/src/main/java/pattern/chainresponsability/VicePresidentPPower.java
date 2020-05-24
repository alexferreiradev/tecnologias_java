package pattern.chainresponsability;

public class VicePresidentPPower extends PurchasePower {
	@Override
	protected Double getAllowable() {
		return BASE * 40;
	}

	@Override
	protected String getRole() {
		return "Vice Presidente";
	}
}
