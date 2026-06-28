package ru.hypertheosx.qa.features.cart.ui;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import ru.hypertheosx.qa.features.cart.component.ProductCardComponent;

public class CartPage {

    public final SelenideElement cartPageTitle = $("[data-test='title']");
    public final SelenideElement checkoutButton = $("[data-test='checkout']");
    public final SelenideElement continueShoppingButton = $("[data-test='continue-shopping']");

    public ElementsCollection cartItems() {
        return $$(".cart_item");
    }

    public List<ProductCardComponent> getCartItems() {
        return cartItems().stream()
                .map(ProductCardComponent::new)
                .collect(Collectors.toList());
    }

    public ProductCardComponent getCartItemByName(String name) {
        SelenideElement root = cartItems()
                .findBy(Condition.text(name));
        return new ProductCardComponent(root);
    }

    public CartPage removeProductByName(String name) {
        getCartItemByName(name).removeFromCart();
        return this;
    }
}
