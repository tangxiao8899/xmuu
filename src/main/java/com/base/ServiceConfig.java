package com.base;

import org.springframework.beans.factory.annotation.Value;


public class ServiceConfig {
	/**
	 * debug配置<br/>
	 **/
	@Value("${debug}")
	public static boolean DEBUG = true;
    public enum Env {
        DEV, TEST, STAGING, PROD
    }

    private static class InstanceHolder {
        public static ServiceConfig INSTANCE = new ServiceConfig();
    }

    public static ServiceConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

//    private Config config;
//
//    ServiceConfig(Config config) {
//        this.config = config;
//    }

//    ServiceConfig() {
//        this(ConfigFactory.load());
//    }
//
//    public Env getEnv() {
//        return Env.valueOf(config.getString("env").toUpperCase());
//    }
//
//    public boolean isProd() {
//        return getEnv() == Env.PROD;
//    }
}
