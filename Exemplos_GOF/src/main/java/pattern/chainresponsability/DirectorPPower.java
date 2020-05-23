package pattern.chainresponsability;

public class DirectorPPower  extends PurchasePower {
	@Override
	protected Double getAllowable() {
		return BASE * 20;
	}

	@Override
	protected String getRole() {
		return "Diretor";
	}
}
