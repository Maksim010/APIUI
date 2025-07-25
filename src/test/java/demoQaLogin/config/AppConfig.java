package demoQaLogin.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:app.properties",
})
public interface AppConfig extends Config {

    @Key("webUrl")
    String webUrl();

    @Key("apiUrl")
    String apiUrl();

}
