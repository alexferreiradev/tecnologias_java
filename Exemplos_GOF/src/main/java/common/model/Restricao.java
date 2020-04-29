package common.model;

public class Restricao {

	private String nome;
	private String descricao;

	private RestricaoData details;

	public Restricao() {
	}

	public Restricao(String nome, String descricao, RestricaoData details) {
		this.nome = nome;
		this.descricao = descricao;
		this.details = details;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public RestricaoData getDetails() {
		return details;
	}

	public void setDetails(RestricaoData details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Restricao{" +
				"nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", details=" + details +
				'}';
	}
}
