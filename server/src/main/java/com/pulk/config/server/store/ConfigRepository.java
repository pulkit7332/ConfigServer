package com.pulk.config.server.store;

import java.util.Map;
import java.util.Optional;

public interface ConfigRepository {

	public Optional<String> getConfigKey(String applicationName, Optional<String> configNameOptional,
			Optional<String> profileOptional, String key);
	public Optional<Map<String,String>> getConfigKeys(String applicationName, Optional<String> configNameOptional,
			Optional<String> profileOptional);

	public Boolean insertConfigKeys(String applicationName, String configName, String profile,
			Map<String, String> configs);

}
