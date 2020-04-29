package pattern.strategy.creator;

import common.model.Matricula;
import common.model.Restricao;
import common.model.builder.RestricaoBuilder;

import java.util.Objects;


/**
 * Utilize a factory {@link pattern.strategy.MatriculaRestricaoFactory}
 */
public class TurmaInvalidaCreator implements RestricaoCreator {

	@Override
	public Restricao validate(Matricula matricula) {
		RestricaoBuilder builder = new RestricaoBuilder();
		if (Objects.isNull(matricula.getTurma())){
			return builder
					.setNome("Turma inválida")
					.setDescricao("Turma da matricula não é válida")
					.createRestricao();
		}

		return null;
	}
}
