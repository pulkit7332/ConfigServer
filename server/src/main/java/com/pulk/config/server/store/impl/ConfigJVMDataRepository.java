package com.pulk.config.server.store.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.pulk.config.server.ConfigServerConstant;
import com.pulk.config.server.store.ConfigRepository;

@Component
public class ConfigJVMDataRepository implements ConfigRepository {
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigJVMDataRepository.class);

	private ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>>> applicationConfiguration = new ConcurrentHashMap<>();

	public Optional<String> getConfigKey(String applicationName, Optional<String> configNameOptional,
			Optional<String> profileOptional, String key) {
		String configName = configNameOptional.orElse(ConfigServerConstant.DEFAULT);
		String profile = profileOptional.orElse(ConfigServerConstant.DEFAULT);
		if (ObjectUtils.isEmpty(applicationName)) {
			LOGGER.error("Application Name is mandatory");
			return Optional.empty();
		}

		return Optional.of(applicationConfiguration.get(applicationName))
				.map(appNameMap -> Optional.of(appNameMap.get(configName)).map(configNameMap -> Optional
						.of(configNameMap.get(profile)).map(profileMap -> Optional.of(profileMap.get(key)))))
				.get().get().get();
	}

	public Boolean insertConfigKeys(String applicationName, String configName, String profile,
			Map<String, String> configs) {

		if (applicationConfiguration.contains(applicationName)
				&& applicationConfiguration.get(applicationName).contains(configName)
				&& applicationConfiguration.get(applicationName).get(configName).contains(profile)) {
			applicationConfiguration.get(applicationName).get(configName).put(profile,
					new ConcurrentHashMap<String, String>());
			applicationConfiguration.get(applicationName).get(configName).get(profile).putAll(configs);
		} else if (applicationConfiguration.contains(applicationName)
				&& !applicationConfiguration.get(applicationName).contains(configName)) {
			ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>> a = new ConcurrentHashMap();
			ConcurrentHashMap<String, ConcurrentHashMap<String, String>> b = new ConcurrentHashMap();
			b.put(profile, new ConcurrentHashMap<>(configs));
			a.put(configName, b);
			applicationConfiguration.get(applicationName).putAll(a);
		} else if (applicationConfiguration.contains(applicationName)
				&& applicationConfiguration.get(applicationName).contains(configName)
				&& !applicationConfiguration.get(applicationName).get(configName).contains(profile)) {
			ConcurrentHashMap<String, ConcurrentHashMap<String, String>> b = new ConcurrentHashMap();
			b.put(profile, new ConcurrentHashMap<>(configs));
			applicationConfiguration.get(applicationName).get(configName).putAll(b);
		} else {
			ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>> a = new ConcurrentHashMap();
			ConcurrentHashMap<String, ConcurrentHashMap<String, String>> b = new ConcurrentHashMap();
			b.put(profile, new ConcurrentHashMap<>(configs));
			a.put(configName, b);
			applicationConfiguration.put(applicationName, a);
		}
		return true;
	}

	@Override
	public Optional<Map<String, String>> getConfigKeys(String applicationName, Optional<String> configNameOptional,
			Optional<String> profileOptional) {
		String configName = configNameOptional.orElse(ConfigServerConstant.DEFAULT);
		String profile = profileOptional.orElse(ConfigServerConstant.DEFAULT);
		if (ObjectUtils.isEmpty(applicationName)) {
			LOGGER.error("Application Name is mandatory");
			return Optional.empty();
		}

		return Optional.of(applicationConfiguration.get(applicationName))
				.map(appNameMap -> Optional.of(appNameMap.get(configName)).map(configNameMap -> Optional
						.of(configNameMap.get(profile)).map(profileMap -> (Map<String, String>) profileMap)))
				.get().get();
	}

}
