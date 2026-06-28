package ru.hypertheosx.qa.features.cart.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import io.qameta.allure.*;
import ru.hypertheosx.qa.features.auth.data.TestUsers;
import ru.hypertheosx.qa.shared.base.BaseTest;
import ru.hypertheosx.qa.shared.extension.RetryTest;

@Tags({
        @Tag("smoke"),
        @Tag("regression")
})
@Owner("hypertheosx")
@Epic("Cart")
@Feature("Cart Management")
@Story("Add and remove products")
@DisplayName("Cart tests")
public class CartTest extends BaseTest {

    @RetryTest(3)
    @Severity(SeverityLevel.CRITICAL)
    @Issue("JIRA-003")
    @Description("Verify full add-to-cart and remove-from-cart flow")
    @DisplayName("Add and remove product from cart")
    public void addAndRemoveProductFromCartTest() {
        String targetProduct = "Sauce Labs Backpack";

        scenario()
                .given().userIsLoggedInAs(TestUsers.standardUser())
                .then().userShouldBeOnProductsPage()
                .then().productShouldHavePrice(targetProduct, "29.99")

                .when().userAddsProductToCart(targetProduct)
                .then().cartBadgeShouldShow(1)
                .then().productShouldBeAddedToCart(targetProduct)

                .when().userOpensCart()
                .then().cartShouldHaveItems(1)
                .then().cartShouldContainProduct(targetProduct)

                .when().userRemovesProductFromCart(targetProduct)
                .then().cartShouldBeEmpty()
                .then().cartBadgeShouldShow(0);
    }

    @RetryTest(2)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Hybrid login bypassing UI with token injection")
    @Description("Verify login via session token injection bypassing UI login form")
    public void hybridLoginTest() {
        var authResponse = authApiClient.authenticateViaApi(TestUsers.standardUser());
        authApiClient.loginByBypassingUi(authResponse);

        scenario()
                .then().userShouldBeOnProductsPage();
    }
}
