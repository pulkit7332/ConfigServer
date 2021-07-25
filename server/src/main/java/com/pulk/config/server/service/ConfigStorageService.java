package com.pulk.config.server.service;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ConfigStorageService {

	public Map<String, String> getConfig(String applicationName, Optional<String> profile,
			Optional<List<String>> configName);

	public File[] getFiles(Date afterDate);

}
