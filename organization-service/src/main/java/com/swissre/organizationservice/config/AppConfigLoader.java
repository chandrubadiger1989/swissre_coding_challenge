package com.swissre.organizationservice.config;

import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class AppConfigLoader {

    public static AppConfig loadConfig() {
    	
    	LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);
        
        Yaml yaml = new Yaml(new Constructor(AppConfig.class, options));
        InputStream inputStream = AppConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.yaml");

        if (inputStream == null) {
            throw new RuntimeException("Could not find config.yaml in resources.");
        }

        return yaml.load(inputStream);
    }
}