package demoQaLogin.helpers.extensions;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoginExtensions implements BeforeEachCallback {

    private static final String DEMO_QA_SESSION = "demoQaSession";

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {

    }
}
