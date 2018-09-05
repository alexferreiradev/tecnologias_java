package arf.examples.stream;

import arf.examples.stream.model.Pessoa;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CollectTest {
	@Test
	public void test_collectors() throws Exception {
		Stream<String> testData = Stream.of("Nome 1", "Nome 2", "Test 1", "Test 2");

		List<String> nameList = testData.collect(Collectors.toList());
		Map<String, List<String>> namesByFirstLetter = testData.collect(Collectors.toMap(o -> o.substring(0, 1), Collectors.toList()));
		String pessoasByFirstName = testData.collect(Collectors.joining(","));

		assertNotNull(pessoasByFirstName);
	}

	@Test
	public void test_map_join() throws Exception {
		List<Pessoa> testData = new ArrayList<>();
		createPessoasToTest(testData);

		String pessoasByFirstName = testData.stream().map(Pessoa::getNome).collect(Collectors.joining(", "));

		assertNotNull(pessoasByFirstName);
		assertEquals("Alex, Teste, Teste, Teste 2", pessoasByFirstName);
	}

	@Test
	public void test_collect_groupingBy() throws Exception {
		List<Pessoa> testData = new ArrayList<>();
		createPessoasToTest(testData);

		Map<String, List<Pessoa>> allPessoaByNome = testData.stream().collect(Collectors.groupingBy(Pessoa::getNome));

		assertNotNull(allPessoaByNome);
		assertEquals("{Alex=[Pessoa{id='null', nome='Alex', sobrenome='null'}], Teste 2=[Pessoa{id='null', nome='Teste 2', sobrenome='null'}], Teste=[Pessoa{id='null', nome='Teste', sobrenome='null'}, Pessoa{id='null', nome='Teste', sobrenome='null'}]}", allPessoaByNome.toString());
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