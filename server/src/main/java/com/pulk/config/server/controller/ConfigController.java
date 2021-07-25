package com.pulk.config.server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pulk.config.server.service.ConfigFetcherService;

@RestController
public class ConfigController {
	@Autowired
	private ConfigFetcherService configFetcherService;

	@GetMapping(value = "/getConfig/{applicationName}")
	public ResponseEntity getConfig(@PathVariable(value = "applicationName") String applicationName,
			@RequestHeader(name = "profile", required = false) Optional<String> profile,
			@RequestHeader(name = "configName", required = false) Optional<String> configName) {

		;
		return ResponseEntity.ok().body(configFetcherService.getConfigKeys(applicationName, profile, configName));

	}

	@GetMapping(value = "/getConfig/{applicationName}/key/{keyName}")
	public ResponseEntity getConfig(@PathVariable(value = "applicationName") String applicationName,
			@RequestHeader(name = "profile", required = false) Optional<String> profile,
			@RequestHeader(name = "configName", required = false) Optional<String> configName,
			@PathVariable(value = "key") String configKey) {

		;
		return ResponseEntity.ok()
				.body(configFetcherService.getConfigKey(applicationName, profile, configName, configKey));

	}

}
