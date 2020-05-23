package pattern.chainresponsability;

public class PresidentPPower extends PurchasePower {
	@Override
	protected Double getAllowable() {
		return BASE * 60;
	}

	@Override
	protected String getRole() {
		return "Presidente";
	}
}
