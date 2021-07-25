package com.pulk.config.server.service;

import java.util.Map;
import java.util.Optional;

public interface ConfigFetcherService {
	
	public Optional<String> getConfigKey(String applicationName,Optional<String> profile,Optional<String> configName,String key);
	public Optional<Map<String,String>> getConfigKeys(String applicationName, Optional<String> configNameOptional,
			Optional<String> profileOptional);
}
