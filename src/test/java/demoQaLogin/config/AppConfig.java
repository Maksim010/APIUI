package demoQaLogin.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:app.properties",
})
public interface AppConfig extends Config {

    @Key("webUrl")
    @DefaultValue("https://demoqa.com")
    String webUrl();

    @Key("apiUrl")
    @DefaultValue("https://demoqa.com")
    String apiUrl();

}
