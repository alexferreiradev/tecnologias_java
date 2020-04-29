package common.model.builder;

import common.model.Restricao;
import common.model.RestricaoData;

import java.util.Objects;

public class RestricaoBuilder {
	private String nome;
	private String descricao;
	private RestricaoData details;

	public Restricao createRestricao() {
		Restricao restricao = new Restricao();
		if (!Objects.isNull(nome)) restricao.setNome(nome);
		if (!Objects.isNull(descricao)) restricao.setDescricao(descricao);
		if (!Objects.isNull(details)) restricao.setDetails(details);

		return restricao;
	}

	public RestricaoBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public RestricaoBuilder setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public RestricaoBuilder setDetails(RestricaoData details) {
		this.details = details;
		return this;
	}
}