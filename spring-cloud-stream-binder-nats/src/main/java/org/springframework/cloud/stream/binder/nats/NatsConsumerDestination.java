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

import org.springframework.cloud.stream.provisioning.ConsumerDestination;

public class NatsConsumerDestination implements ConsumerDestination {
	private String name;

	public NatsConsumerDestination(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getSubject() {
		String[] parts = this.name.split("#");

		if (parts.length > 2) {
			return parts[1];
		}
		else {
			return parts[0];
		}
	}

	public String getQueueGroup() {
		String[] parts = this.name.split("#");

		if (parts.length > 2) {
			return parts[2];
		}
		else if (parts.length > 1) {
			return parts[1];
		}

		return "";
	}
}