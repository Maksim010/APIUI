package demoQaLogin.config;

import org.aeonbits.owner.ConfigFactory;

public class Project {

    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static boolean isRemoteWebDriver() {
        String url = config.remoteDriverUrl();
        return url != null && !url.isEmpty();
    }

}
