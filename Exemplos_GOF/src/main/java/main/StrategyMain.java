package main;

import common.model.Matricula;
import common.model.Restricao;
import pattern.strategy.MatriculaRestricaoFactory;
import pattern.strategy.creator.RestricaoCreator;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StrategyMain {

	public static void main(String[] args) throws URISyntaxException {
		List<? extends RestricaoCreator> list = MatriculaRestricaoFactory.createList();

		Matricula matricula = new Matricula();
		matricula.setStatus("Excluida");
		matricula.setTurma("null");
		List<Restricao> restricaoList = list.stream().map(restricaoCommand -> restricaoCommand.validate(matricula))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		System.out.println(
				restricaoList.toString()
		);
	}
}
