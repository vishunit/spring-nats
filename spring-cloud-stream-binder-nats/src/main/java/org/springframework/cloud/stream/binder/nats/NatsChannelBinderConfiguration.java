/*
 * Copyright 2017-2019 the original author or authors.
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

package org.springframework.cloud.stream.binder.nats;

import java.io.IOException;
import java.util.Collections;

import org.springframework.boot.autoconfigure.nats.NatsAutoConfiguration;
import org.springframework.boot.autoconfigure.nats.NatsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.cloud.stream.config.BindingHandlerAdvise.MappingsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ NatsAutoConfiguration.class })
@EnableConfigurationProperties({ NatsProperties.class })
public class NatsChannelBinderConfiguration {
	@Bean
	public NatsChannelProvisioner natsChannelProvisioner() {
		return new NatsChannelProvisioner();
	}

	@Bean
	public NatsChannelBinder natsBinder(NatsChannelProvisioner natsProvisioner, NatsProperties properties) throws IOException, InterruptedException {
		NatsChannelBinder binder = new NatsChannelBinder(properties, natsProvisioner);
		return binder;
	}

	@Bean
	public MappingsProvider natsExtendedPropertiesDefaultMappingsProvider() {
		return () -> Collections.singletonMap(
				ConfigurationPropertyName.of("spring.cloud.stream.nats.bindings"),
				ConfigurationPropertyName.of("spring.cloud.stream.nats.default"));
	}
}