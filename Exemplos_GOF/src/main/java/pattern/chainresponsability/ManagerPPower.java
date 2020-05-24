package pattern.chainresponsability;

public class ManagerPPower  extends PurchasePower {
	@Override
	protected Double getAllowable() {
		return BASE * 10;
	}

	@Override
	protected String getRole() {
		return "Gerente";
	}
}
