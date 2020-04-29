package pattern.strategy.annotation;

import pattern.strategy.creator.RestricaoCreator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FactoryConfig {
	Class<? extends RestricaoCreator>[] implementations();
}
