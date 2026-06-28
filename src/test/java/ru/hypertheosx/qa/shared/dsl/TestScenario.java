package ru.hypertheosx.qa.shared.dsl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import com.codeborne.selenide.Condition;

import io.qameta.allure.Step;
import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.features.auth.workflow.AuthWorkflow;
import ru.hypertheosx.qa.features.cart.assertion.ProductAssert;
import ru.hypertheosx.qa.features.cart.component.ProductCardComponent;
import ru.hypertheosx.qa.features.cart.ui.CartPage;
import ru.hypertheosx.qa.features.cart.ui.ProductsPage;

public class TestScenario {

    private final AuthWorkflow auth;
    private final ProductsPage productsPage;
    private CartPage cartPage;

    public TestScenario(AuthWorkflow auth, ProductsPage productsPage) {
        this.auth = auth;
        this.productsPage = productsPage;
    }

    @Step("Given")
    public GivenActions given() {
        return new GivenActions();
    }

    @Step("When")
    public WhenActions when() {
        return new WhenActions();
    }

    @Step("Then")
    public ThenActions then() {
        return new ThenActions();
    }

    public class GivenActions {
        public TestScenario userIsLoggedInAs(User user) {
            auth.loginAs(user);
            return TestScenario.this;
        }

        public TestScenario and() {
            return TestScenario.this;
        }
    }

    public class WhenActions {
        public TestScenario userAddsProductToCart(String productName) {
            productsPage.getProductByName(productName).addToCart();
            return TestScenario.this;
        }

        public TestScenario userRemovesProductFromCart(String productName) {
            ensureCartPage();
            cartPage.removeProductByName(productName);
            return TestScenario.this;
        }

        public TestScenario userOpensCart() {
            ensureCartPage();
            return TestScenario.this;
        }

        public TestScenario and() {
            return TestScenario.this;
        }

        private void ensureCartPage() {
            if (cartPage == null) {
                cartPage = productsPage.openCart();
            }
        }
    }

    public class ThenActions {
        public TestScenario cartBadgeShouldShow(int expectedCount) {
            if (expectedCount == 0) {
                productsPage.shoppingCartBadge.shouldNot(Condition.exist);
            } else {
                productsPage.shoppingCartBadge.shouldHave(Condition.text(String.valueOf(expectedCount)));
            }
            return TestScenario.this;
        }

        public TestScenario cartShouldHaveItems(int expectedCount) {
            ensureCartPage();
            assertThat(cartPage.getCartItems()).hasSize(expectedCount);
            return TestScenario.this;
        }

        public TestScenario cartShouldBeEmpty() {
            ensureCartPage();
            assertThat(cartPage.getCartItems()).isEmpty();
            return TestScenario.this;
        }

        public TestScenario cartShouldContainProduct(String productName) {
            ensureCartPage();
            List<ProductCardComponent> items = cartPage.getCartItems();
            assertThat(items)
                    .describedAs("Cart should contain product: %s", productName)
                    .anyMatch(item -> item.getName().equals(productName));
            return TestScenario.this;
        }

        public TestScenario productShouldBeAddedToCart(String productName) {
            ProductAssert.assertThat(productsPage.getProductByName(productName)).isAddedToCart();
            return TestScenario.this;
        }

        public TestScenario productShouldNotBeInCart(String productName) {
            ProductAssert.assertThat(productsPage.getProductByName(productName)).isNotInCart();
            return TestScenario.this;
        }

        public TestScenario productShouldHavePrice(String productName, String expectedPrice) {
            ProductAssert.assertThat(productsPage.getProductByName(productName))
                    .hasPrice(new BigDecimal(expectedPrice));
            return TestScenario.this;
        }

        public TestScenario userShouldBeOnProductsPage() {
            productsPage.productPageTitle.shouldBe(Condition.visible);
            return TestScenario.this;
        }

        public TestScenario and() {
            return TestScenario.this;
        }

        private void ensureCartPage() {
            if (cartPage == null) {
                cartPage = productsPage.openCart();
            }
        }
    }
}
