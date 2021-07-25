package com.pulk.config.server.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageSourceServiceFactory {
	
	@Autowired
	Map<String,ConfigStorageService> configStorageServices;

	
	public ConfigStorageService getConfigStorageService(String type)
	{
		return configStorageServices.get(type);
	}

}
