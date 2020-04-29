package common.model;

public class Matricula {

	private Long id;
	private Long pf;
	private String turma;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPf() {
		return pf;
	}

	public void setPf(Long pf) {
		this.pf = pf;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
