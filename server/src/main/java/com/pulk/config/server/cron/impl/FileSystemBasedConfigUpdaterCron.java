package com.pulk.config.server.cron.impl;

import java.io.File;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pulk.config.server.cron.CronScheduler;
import com.pulk.config.server.enums.StorageType;
import com.pulk.config.server.service.ConfigStoreService;
import com.pulk.config.server.service.StorageSourceServiceFactory;

@Component
public class FileSystemBasedConfigUpdaterCron implements CronScheduler {

	@Autowired
	private ConfigStoreService configStore;

	@Autowired
	private StorageSourceServiceFactory storageSourceServiceFactory;

	private Date cutoffDate = new Date(0);

	@Scheduled(cron = "${cron.file.system.regex:0/15 * * * * ?}")
	public void read() throws Exception {

		Date toUseDate = cutoffDate;

		for (File file : storageSourceServiceFactory.getConfigStorageService(StorageType.FILESYSTEM.getFileName())
				.getFiles(toUseDate)) {
			configStore.loadorUpdateConfig(file);
		}

	}

}
