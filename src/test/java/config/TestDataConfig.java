package config;

import org.aeonbits.owner.Config;

@Config.Sources({ "classpath:config/testdata.properties" })
public interface TestDataConfig extends Config {

    @Key("project.url")
    String getProjectUrl();

    @Key("user.login")
    String getLogin();

    @Key("user.password")
    String getPassword();

}
