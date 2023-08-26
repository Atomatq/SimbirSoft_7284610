package org.example.config;

import org.aeonbits.owner.ConfigCache;


public class ConfigManager {
    public ConfigManager() {
    }

    public static BaseConfig getConfig() {
        return ConfigCache.getOrCreate(BaseConfig.class);
    }
}
