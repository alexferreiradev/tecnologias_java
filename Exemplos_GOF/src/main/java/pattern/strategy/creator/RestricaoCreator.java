package pattern.strategy.creator;

import common.model.Matricula;
import common.model.Restricao;


/**
 * Utilize a factory para obter as implementações.
 *
 * Sempre tenha um <b>construtor vazio</b> nas implementações,
 * pois a factory {@link pattern.strategy.MatriculaRestricaoFactory} utiliza newInstance() para gerar
 * as instâncias configuradas.
 */
public interface RestricaoCreator {

	Restricao validate(Matricula matricula);

}
