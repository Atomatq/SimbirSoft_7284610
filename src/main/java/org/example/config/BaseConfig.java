package org.example.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@Sources("classpath:config.properties")
public interface BaseConfig extends Config {
    @Key("base.url")
    String baseURL();

    @Key("user.login")
    String userLogin();
}
