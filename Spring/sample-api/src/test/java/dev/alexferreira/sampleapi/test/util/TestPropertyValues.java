package dev.alexferreira.sampleapi.test.util;

/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Test utilities for adding properties. Properties can be applied to a Spring
 * {@link Environment} or to the {@link System#getProperties() system environment}.
 *
 * @author Madhura Bhave
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 2.0.0
 */
public final class TestPropertyValues {

	private static final TestPropertyValues EMPTY = new TestPropertyValues(Collections.emptyMap());

	private final Map<String, Object> properties;

	private TestPropertyValues(Map<String, Object> properties) {
		this.properties = Collections.unmodifiableMap(properties);
	}

	/**
	 * Return a new {@link TestPropertyValues} with the underlying map populated with the
	 * given map entries.
	 * @param map the map of properties that need to be added to the environment
	 * @return the new instance
	 */
	public static TestPropertyValues of(Map<String, String> map) {
		return (map != null) ? of(map.entrySet().stream(), Pair::fromMapEntry) : empty();
	}

	/**
	 * Return a new {@link TestPropertyValues} with the underlying map populated with the
	 * given stream.
	 * @param <T> the stream element type
	 * @param stream the elements that need to be added to the environment
	 * @param mapper a mapper function to convert an element from the stream into a
	 * {@link Pair}
	 * @return the new instance
	 */
	public static <T> TestPropertyValues of(Stream<T> stream, Function<T, Pair> mapper) {
		return (stream != null) ? empty().and(stream, mapper) : empty();
	}

	/**
	 * Return a new {@link TestPropertyValues} instance with additional entries.
	 * @param <T> the stream element type
	 * @param stream the elements that need to be added to the environment
	 * @param mapper a mapper function to convert an element from the stream into a
	 * {@link Pair}
	 * @return a new {@link TestPropertyValues} instance
	 * @since 2.4.0
	 */
	public <T> TestPropertyValues and(Stream<T> stream, Function<T, Pair> mapper) {
		if (stream == null) {
			return this;
		}
		Map<String, Object> properties = new LinkedHashMap<>(this.properties);
		stream.map(mapper).filter(Objects::nonNull).forEach((pair) -> pair.addTo(properties));
		return new TestPropertyValues(properties);
	}

	/**
	 * Return an empty {@link TestPropertyValues} instance.
	 * @return an empty instance
	 */
	public static TestPropertyValues empty() {
		return EMPTY;
	}

	/**
	 * Add the properties from the underlying map to the environment owned by an
	 * {@link ApplicationContext}.
	 *
	 * @param context the context with an environment to modify
	 */
	public void applyTo(ConfigurableApplicationContext context) {
		applyTo(context.getEnvironment());
	}

	/**
	 * Add the properties from the underlying map to the environment. The default property
	 * source used is {@link MapPropertySource}.
	 *
	 * @param environment the environment that needs to be modified
	 */
	public void applyTo(ConfigurableEnvironment environment) {
		applyTo(environment, Type.MAP);
	}

	/**
	 * Add the properties from the underlying map to the environment using the specified
	 * property source type.
	 *
	 * @param environment the environment that needs to be modified
	 * @param type        the type of {@link PropertySource} to be added. See {@link Type}
	 */
	public void applyTo(ConfigurableEnvironment environment, Type type) {
		applyTo(environment, type, type.applySuffix("test"));
	}

	/**
	 * Add the properties from the underlying map to the environment using the specified
	 * property source type and name.
	 *
	 * @param environment the environment that needs to be modified
	 * @param type        the type of {@link PropertySource} to be added. See {@link Type}
	 * @param name        the name for the property source
	 */
	public void applyTo(ConfigurableEnvironment environment, Type type, String name) {
		Assert.notNull(environment, "'environment' must not be null");
		Assert.notNull(type, "'type' must not be null");
		Assert.notNull(name, "'name' must not be null");
		MutablePropertySources sources = environment.getPropertySources();
		addToSources(sources, type, name);
		ConfigurationPropertySources.attach(environment);
	}

	/**
	 * Add the properties to the {@link System#getProperties() system properties} for the
	 * duration of the {@code call}, restoring previous values when it completes.
	 *
	 * @param <T>  the result type
	 * @param call the call to make
	 * @return the result of the call
	 */
	public <T> T applyToSystemProperties(Callable<T> call) {
		try {
			return call.call();
		} catch (Exception ex) {
			rethrow(ex);
			throw new IllegalStateException("Original cause not rethrown", ex);
		}
	}

	@SuppressWarnings("unchecked")
	private <E extends Throwable> void rethrow(Throwable e) throws E {
		throw (E) e;
	}

	@SuppressWarnings("unchecked")
	private void addToSources(MutablePropertySources sources, Type type, String name) {
		if (sources.contains(name)) {
			PropertySource<?> propertySource = sources.get(name);
			if (propertySource.getClass() == type.getSourceClass()) {
				((Map<String, Object>) propertySource.getSource()).putAll(this.properties);
				return;
			}
		}
		Map<String, Object> source = new LinkedHashMap<>(this.properties);
		sources.addFirst((type.equals(Type.MAP) ? new MapPropertySource(name, source)
				: new SystemEnvironmentPropertySource(name, source)));
	}

	/**
	 * The type of property source.
	 */
	public enum Type {

		/**
		 * Used for {@link SystemEnvironmentPropertySource}.
		 */
		SYSTEM_ENVIRONMENT(SystemEnvironmentPropertySource.class,
				StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME),

		/**
		 * Used for {@link MapPropertySource}.
		 */
		MAP(MapPropertySource.class, null);

		private final Class<? extends MapPropertySource> sourceClass;

		private final String suffix;

		Type(Class<? extends MapPropertySource> sourceClass, String suffix) {
			this.sourceClass = sourceClass;
			this.suffix = suffix;
		}

		public Class<? extends MapPropertySource> getSourceClass() {
			return this.sourceClass;
		}

		protected String applySuffix(String name) {
			return (this.suffix != null) ? name + "-" + this.suffix : name;
		}

	}

	/**
	 * A single name value pair.
	 */
	public static final class Pair {

		private final String name;

		private final String value;

		private Pair(String name, String value) {
			Assert.hasLength(name, "'name' must not be empty");
			this.name = name;
			this.value = value;
		}

		public void addTo(Map<String, Object> properties) {
			properties.put(this.name, this.value);
		}

		/**
		 * Factory method to create a {@link Pair} from a {@code Map.Entry}.
		 * @param entry the map entry
		 * @return the {@link Pair} instance or {@code null}
		 * @since 2.4.0
		 */
		public static Pair fromMapEntry(Map.Entry<String, String> entry) {
			return (entry != null) ? of(entry.getKey(), entry.getValue()) : null;
		}

		/**
		 * Factory method to create a {@link Pair} from a name and value.
		 *
		 * @param name  the name
		 * @param value the value
		 * @return the {@link Pair} instance or {@code null}
		 * @since 2.4.0
		 */
		public static Pair of(String name, String value) {
			if (StringUtils.hasLength(name) || StringUtils.hasLength(value)) {
				return new Pair(name, value);
			}
			return null;
		}
	}
}
