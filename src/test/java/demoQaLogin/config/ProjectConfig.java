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
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("remote.url")
    @DefaultValue("")
    String remoteDriverUrl();
}
