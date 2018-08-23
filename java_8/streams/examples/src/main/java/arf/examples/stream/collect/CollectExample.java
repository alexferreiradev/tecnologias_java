package arf.examples.stream.collect;

import arf.examples.stream.model.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectExample {

	private List<Pessoa> pessoaList;

	public CollectExample() {
		pessoaList = new ArrayList<>();
	}

	public String getAllNomePessoas() {
		return pessoaList.stream().map(Pessoa::getNome).collect(Collectors.joining(", "));
	}

	public void setPessoaList(List<Pessoa> pessoaList) {
		this.pessoaList = pessoaList;
	}
}
