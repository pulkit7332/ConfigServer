package com.pulk.config.server.service;

import java.util.List;
import java.util.Optional;

public interface FileNameService {

	public String getFileName(String applicationName, Optional<String> profile,  Optional<String> configName) throws Exception;

	public String getFileName(String applicationName,  Optional<String> profile) throws Exception;

	public String getFileName(String applicationName) throws Exception;

	public List<String> getFileNames(String applicationName, Optional<List<String>> configNames) ;

	public List<String> getFileNames(String applicationName,  Optional<String> profile, Optional<List<String>> configNames) ;
	
	public String getApplicationName(String fileName);
	
	public String getEnviornment(String fileName);

	public String getConfigName(String fileName);

	public Boolean isFileNameCorrect(String fileName);

}
