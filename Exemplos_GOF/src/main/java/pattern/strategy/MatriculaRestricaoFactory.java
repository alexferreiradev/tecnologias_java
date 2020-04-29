package pattern.strategy;

import pattern.strategy.annotation.FactoryConfig;
import pattern.strategy.creator.RestricaoCreator;
import pattern.strategy.creator.StatusCreator;
import pattern.strategy.creator.TurmaInvalidaCreator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilize esta factory para obter todas implementações disponíveis de
 * {@link RestricaoCreator}.
 */
@FactoryConfig(implementations = {
		StatusCreator.class,
		TurmaInvalidaCreator.class
})
public final class MatriculaRestricaoFactory {

	public static List<? extends RestricaoCreator> createList() {
		FactoryConfig factoryConfig = MatriculaRestricaoFactory.class.getDeclaredAnnotation(FactoryConfig.class);
		Class<? extends RestricaoCreator>[] implementations = factoryConfig.implementations();

		return Arrays.stream(implementations)
				.map(aClass -> {
					try {
						return aClass.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
	}

}
