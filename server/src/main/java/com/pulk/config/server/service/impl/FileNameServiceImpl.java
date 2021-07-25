package com.pulk.config.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pulk.config.server.ConfigServerConstant;
import com.pulk.config.server.service.FileNameService;

@Service
public class FileNameServiceImpl implements FileNameService {

	@Override
	public String getFileName(String applicationName, Optional<String> profileOptional,
			Optional<String> configNameOptional) {

		StringBuilder fileName = new StringBuilder();

		if (!ObjectUtils.isEmpty(applicationName))
			fileName.append(applicationName);
		else
			//throw new Exception("Application Name is mandatory");

		configNameOptional.ifPresent(configName -> {
			fileName.append("-");
			fileName.append(configName);
		});
		profileOptional.ifPresent(profile -> {
			fileName.append("-");
			fileName.append(profile);
		});

		return fileName.toString();
	}

	@Override
	public List<String> getFileNames(String applicationName, Optional<String> profile,
			Optional<List<String>> configNamesOptional)  {
		return configNamesOptional.map(
				configNames->configNames.stream().map(configName -> getFileName(applicationName, profile, Optional.of(configName)))
				.collect(Collectors.toList())).orElse(List.of(getFileName(applicationName, profile)));
	}

	@Override
	public String getFileName(String applicationName, Optional<String> profile)  {

		return getFileName(applicationName, profile, null);
	}

	@Override
	public String getFileName(String applicationName) throws Exception {
		return getFileName(applicationName, null, null);
	}

	@Override
	public List<String> getFileNames(String applicationName, Optional<List<String>> configNames){
		return getFileNames(applicationName, null, configNames);
	}

	@Override
	public String getApplicationName(String fileName) {
		if(!isFileNameCorrect(fileName))
			return null;
		return fileName.split("-")[0];
	}

	@Override
	public String getEnviornment(String fileName) {
		if(!isFileNameCorrect(fileName))
			return null;
		String[] fileNameSplit=fileName.split("-");
		if(fileNameSplit.length==1)
		{
			return ConfigServerConstant.DEFAULT;
		}
		else 
		{
			return fileNameSplit[fileNameSplit.length-1];
		}
	}

	@Override
	public Boolean isFileNameCorrect(String fileName) {
		String[] fileNameSplit=fileName.split("-");
		if(fileNameSplit.length>3||fileNameSplit.length<1)
			return false;
		return true;
	}

	@Override
	public String getConfigName(String fileName) {
		if(!isFileNameCorrect(fileName))
			return null;
		String[] fileNameSplit=fileName.split("-");
		if(fileNameSplit.length==3)
		{
			return fileNameSplit[1];
		}
		else if(fileNameSplit.length==1)
		{
			return ConfigServerConstant.DEFAULT;
		}
		else 
		{
			//TODO need to code for length  2
			return ConfigServerConstant.DEFAULT;
		}
	}

}
