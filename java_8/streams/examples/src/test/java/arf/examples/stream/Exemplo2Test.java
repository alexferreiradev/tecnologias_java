package arf.examples.stream;

import arf.examples.stream.model.Pessoa;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Exemplo2Test {

	@Test
	public void test_filter() throws Exception {
		List<Pessoa> testData = new ArrayList<>();
		createPessoasToTest(testData);

		List<Pessoa> startingWithT = testData.stream().filter(pessoa -> pessoa.getNome().startsWith("T")).collect(Collectors.toList());

		assertEquals(3, startingWithT.size());
	}

	private void createPessoasToTest(List<Pessoa> testData) {
		Pessoa pessoa = new Pessoa();

		pessoa.setNome("Alex");
		testData.add(pessoa);
		pessoa = new Pessoa();
		pessoa.setNome("Teste");
		testData.add(pessoa);
		pessoa = new Pessoa();
		pessoa.setNome("Teste");
		testData.add(pessoa);
		pessoa = new Pessoa();
		pessoa.setNome("Teste 2");
		testData.add(pessoa);
	}

}