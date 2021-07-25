package com.pulk.config.server.service.impl;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pulk.config.server.service.ConfigStoreService;
import com.pulk.config.server.service.FileNameService;
import com.pulk.config.server.store.ConfigRepository;
import com.pulk.config.server.utils.FileUtility;

@Component
public class ConfigStoreServiceImpl implements ConfigStoreService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigStoreServiceImpl.class);

	@Autowired
	private FileNameService fileNameService;

	@Autowired
	private ConfigRepository configRepository;

	public Boolean loadorUpdateConfig(File file) throws Exception

	{
		String fileName = file.getName();

		// TODO Can use optional to decrease complexity of code
		String applicationName = fileNameService.getApplicationName(fileName);
		String environment = fileNameService.getEnviornment(fileName);
		String configName = fileNameService.getConfigName(fileName);

		if (applicationName == null || environment == null) {
			LOGGER.error("Incorrect File Name");

			// can add monitoring metrics as well
			return false;
		}
		Map<String, String> configs = FileUtility.getKeyValues(file);

		if (configs == null || configs.size() == 0) {
			LOGGER.error("Incorrect File  content");

			// can add monitoring metrics as well
			return false;
		}
		return configRepository.insertConfigKeys(applicationName, configName, configName, configs);

	}

}
