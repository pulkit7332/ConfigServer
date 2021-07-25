package com.pulk.config.server.enums;

import com.pulk.config.server.service.impl.FileSystemConfigStorageService;

public enum StorageType {
	FILESYSTEM(FileSystemConfigStorageService.class.getSimpleName());
	//GIT()
	private String fileName;
	private StorageType(String fileName)
	{
		this.fileName=fileName;
	}
	public String getFileName()
	{
		return this.fileName;
	}

}
