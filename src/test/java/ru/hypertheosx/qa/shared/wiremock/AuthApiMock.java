package ru.hypertheosx.qa.shared.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tomakehurst.wiremock.WireMockServer;

public class AuthApiMock {

    private static final Logger log = LoggerFactory.getLogger(AuthApiMock.class);
    private static final String WIREMOCK_ENABLED_PROP = "useWireMock";

    private WireMockServer server;

    public void startIfEnabled() {
        if (!Boolean.getBoolean(WIREMOCK_ENABLED_PROP)) {
            log.info("WireMock disabled (use -D{}=true to enable)", WIREMOCK_ENABLED_PROP);
            return;
        }

        log.info("Starting WireMock server...");
        server = new WireMockServer(options().dynamicPort());
        server.start();

        stubAuthEndpoint();

        log.info("WireMock server started on {}", server.baseUrl());
    }

    private void stubAuthEndpoint() {
        server.stubFor(post(urlPathEqualTo("/post"))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "token": "mock-session-token",
                                    "userId": "standard_user",
                                    "expiresIn": 3600
                                }
                                """)
                        .withStatus(200)));
    }

    public void stop() {
        if (server != null) {
            log.info("Stopping WireMock server...");
            server.stop();
            server = null;
        }
    }

    public String getBaseUrl() {
        return server != null ? server.baseUrl() : null;
    }

    public boolean isRunning() {
        return server != null && server.isRunning();
    }
}
