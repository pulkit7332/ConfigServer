package com.pulk.config.server.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.filefilter.AgeFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pulk.config.server.service.ConfigStorageService;
import com.pulk.config.server.service.FileNameService;

@Service("FileSystemConfigStorageService")
public class FileSystemConfigStorageService implements ConfigStorageService {

	@Autowired
	private FileNameService fileNameService;

	@Value(value = "${file.system.config.file.path:/Users/user/Downloads/server}")
	private String fileSystemPath;

	@Override
	public Map<String, String> getConfig(String applicationName, Optional<String> profile,
			Optional<List<String>> configNames) {

		return fileNameService.getFileNames(applicationName, profile, configNames).stream()
				.collect(Collectors.toMap(name -> name, name -> {
					try {
						return new String(Files.readString(Paths.get(name)));
					} catch (IOException e) {
						return null;
					}

				}));

	}

	@Override
	public File[] getFiles(Date afterDate) {
		File directory = new File(fileSystemPath);
		FileFilter fileFilter = new AgeFileFilter(afterDate, false);
		return directory.listFiles(fileFilter);

	}

}
