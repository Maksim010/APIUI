package demoQaLogin.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:remote.properties",
        "classpath:local.properties",
})
public interface ProjectConfig extends Config {

    @Key("browser")
    String browser();

    @Key("browserSize")
    String browserSize();

    @Key("remote.url")
    @DefaultValue("")
    String remoteDriverUrl();
}
