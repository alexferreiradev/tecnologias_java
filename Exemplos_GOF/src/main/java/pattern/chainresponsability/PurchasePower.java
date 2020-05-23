package pattern.chainresponsability;

public abstract class PurchasePower {

	protected PurchasePower successor;
	protected static double BASE = 200;

	abstract protected Double getAllowable();
	abstract protected String getRole();

	public void setSuccessor(PurchasePower purchasePower) {
		this.successor = purchasePower;
	}

	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < this.getAllowable()) {
			System.out.println(this.getRole() + " irá aprovar $" + request.getAmount());
		} else if (successor != null){
			successor.processRequest(request);
		}
	}
}
