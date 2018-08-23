package arf.examples.stream.collect;

import arf.examples.stream.model.Pessoa;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CollectExampleTest {

	@Test
	public void getPessoasByFirstName() throws Exception {
		List<Pessoa> testData = new ArrayList<>();
		createPessoasToTest(testData);
		CollectExample collectExample = new CollectExample();
		collectExample.setPessoaList(testData);

		String pessoasByFirstName = collectExample.getAllNomePessoas();

		assertNotNull(pessoasByFirstName);
		assertEquals("Alex, Teste, Teste 2", pessoasByFirstName);
	}

	private void createPessoasToTest(List<Pessoa> testData) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Alex");
		testData.add(pessoa);
		pessoa = new Pessoa();
		pessoa.setNome("Teste");
		testData.add(pessoa);
		pessoa = new Pessoa();
		pessoa.setNome("Teste 2");
		testData.add(pessoa);
	}

}