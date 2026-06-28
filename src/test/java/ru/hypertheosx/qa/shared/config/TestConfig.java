package ru.hypertheosx.qa.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.hypertheosx.qa.features.auth.api.AuthApiClient;
import ru.hypertheosx.qa.features.auth.ui.LoginPage;
import ru.hypertheosx.qa.features.auth.workflow.AuthWorkflow;
import ru.hypertheosx.qa.features.cart.ui.CartPage;
import ru.hypertheosx.qa.features.cart.ui.ProductsPage;
import ru.hypertheosx.qa.shared.container.BrowserContainer;
import ru.hypertheosx.qa.shared.wiremock.AuthApiMock;

@Configuration
public class TestConfig {

    @Bean(destroyMethod = "stop")
    public BrowserContainer browserContainer() {
        BrowserContainer container = new BrowserContainer();
        container.startIfEnabled();
        return container;
    }

    @Bean(destroyMethod = "stop")
    public AuthApiMock authApiMock() {
        AuthApiMock mock = new AuthApiMock();
        mock.startIfEnabled();
        return mock;
    }

    @Bean
    public AuthApiClient authApiClient(AuthApiMock authApiMock) {
        AuthApiClient client = new AuthApiClient();
        String mockUrl = authApiMock.getBaseUrl();
        if (mockUrl != null) {
            client.setApiBaseUrl(mockUrl);
        }
        return client;
    }

    @Bean
    public LoginPage loginPage() {
        return new LoginPage();
    }

    @Bean
    public ProductsPage productsPage() {
        return new ProductsPage();
    }

    @Bean
    public CartPage cartPage() {
        return new CartPage();
    }

    @Bean
    public AuthWorkflow authWorkflow(LoginPage loginPage, ProductsPage productsPage) {
        return new AuthWorkflow(loginPage, productsPage);
    }
}
