package com.diploma;

import org.glassfish.jersey.server.ResourceConfig;

public class ResConf extends ResourceConfig {
    public ResConf() {
        packages("com.diploma");

        register(new JerseyBinder());
        register(AuthenticationFilter.class);
    }
}