package com.pulk.config.server.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileUtility {
	
	public static Map<String,String> getKeyValues(File file)
	{		
		 Map<String,String> configs=new HashMap<>();

		try {
			
			for (String line : Files.readAllLines(file.toPath())) {
				//TODO: Check if value also contain ":"
				String[] s=line.split(":");
				if(s.length==2)
				{
					configs.put(s[0].strip(), s[1].strip());
				}
				else {

					configs=new HashMap<>();
					break;
				}
					
			}
		} catch (IOException e) {
			return new HashMap<>();
		}
		return configs;
	}

}
