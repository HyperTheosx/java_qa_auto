package ru.hypertheosx.qa.shared.config;

import java.util.Map;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config-${env}.properties",
        "classpath:config.properties"
})
public interface AppConfig extends Config {

    AppConfig config = ConfigFactory.create(
            AppConfig.class,
            Map.of("env", System.getProperty("env", "dev"))
    );

    @Key("env")
    @DefaultValue("dev")
    String env();

    @Key("base.url")
    @DefaultValue("https://www.saucedemo.com")
    String baseUrl();

    @Key("password")
    @DefaultValue("secret_sauce")
    String password();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("headless")
    @DefaultValue("true")
    boolean headless();

    @Key("timeout")
    @DefaultValue("10000")
    long timeout();

    @Key("page.load.timeout")
    @DefaultValue("20000")
    long pageLoadTimeout();

    @Key("remote.url")
    @DefaultValue("")
    String remoteUrl();

    @Key("remote.video.enabled")
    @DefaultValue("false")
    boolean remoteVideoEnabled();
}
