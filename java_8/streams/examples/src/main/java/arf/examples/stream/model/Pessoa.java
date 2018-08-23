package arf.examples.stream.model;

public class Pessoa {

	private String id;
	private String nome;
	private String sobrenome;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pessoa pessoa = (Pessoa) o;

		if (id != null ? !id.equals(pessoa.id) : pessoa.id != null) return false;
		if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
		return sobrenome != null ? sobrenome.equals(pessoa.sobrenome) : pessoa.sobrenome == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (nome != null ? nome.hashCode() : 0);
		result = 31 * result + (sobrenome != null ? sobrenome.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Pessoa{" +
				"id='" + id + '\'' +
				", nome='" + nome + '\'' +
				", sobrenome='" + sobrenome + '\'' +
				'}';
	}
}
