package pattern.strategy.creator;

import common.model.Matricula;
import common.model.Restricao;
import common.model.RestricaoData;
import common.model.builder.RestricaoBuilder;
import pattern.strategy.MatriculaRestricaoFactory;

/**
 * Utilize a factory {@link MatriculaRestricaoFactory}
 */
public final class StatusCreator implements RestricaoCreator {

	@Override
	public Restricao validate(Matricula matricula) {
		RestricaoBuilder restricaoBuilder = new RestricaoBuilder();


		if (matricula.getStatus() == "Excluida") {
			restricaoBuilder.setNome("Matricula Excluída");
			restricaoBuilder.setDescricao("Matricula não pode ser utilizada por estar excluída");
			RestricaoData details = new RestricaoData();
			details.cause = "Matricula com status excluida";
			restricaoBuilder.setDetails(details);
		}

		return restricaoBuilder.createRestricao();
	}
}
