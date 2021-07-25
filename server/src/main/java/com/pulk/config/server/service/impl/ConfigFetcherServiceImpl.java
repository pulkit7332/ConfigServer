package com.pulk.config.server.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pulk.config.server.service.ConfigFetcherService;
import com.pulk.config.server.store.ConfigRepository;

@Service
public class ConfigFetcherServiceImpl implements ConfigFetcherService {

	private ConfigRepository configRepository;
	@Override
	public Optional<String> getConfigKey(String applicationName, Optional<String> profile, Optional<String> configName,
			String key) {
		
		return configRepository.getConfigKey(applicationName, configName, profile, key);
	}
	public Optional<Map<String,String>> getConfigKeys(String applicationName, Optional<String> configName,
			Optional<String> profile){
		// TODO Auto-generated method stub
		
		return configRepository.getConfigKeys(applicationName, configName, profile);
	}

}
