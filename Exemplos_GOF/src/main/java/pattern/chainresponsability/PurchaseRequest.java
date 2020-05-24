package pattern.chainresponsability;

public class PurchaseRequest {

	private Double amount;
	private String propose;

	public PurchaseRequest(Double amount, String propose) {
		this.amount = amount;
		this.propose = propose;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPropose() {
		return propose;
	}

	public void setPropose(String propose) {
		this.propose = propose;
	}
}
