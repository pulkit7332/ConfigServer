package com.pulk.config.server.service;

import java.io.File;

public interface ConfigStoreService {
	public Boolean loadorUpdateConfig(File file) throws Exception;
}
