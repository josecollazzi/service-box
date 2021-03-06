package com.manywho.services.box;

import com.manywho.sdk.services.BaseApplication;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends BaseApplication {

    public Application() {
        registerSdk()
                .packages("com.manywho.services.box")
                .register(new ApplicationBinder());
    }

    public static void main(String[] args) {
        startServer(new Application(), "api/box/3");
    }
}
