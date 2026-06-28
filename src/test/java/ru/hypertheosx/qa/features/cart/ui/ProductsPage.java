package ru.hypertheosx.qa.features.cart.ui;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import ru.hypertheosx.qa.features.cart.component.ProductCardComponent;

public class ProductsPage {

    public final SelenideElement productPageTitle = $("[data-test='title']");
    public final SelenideElement shoppingCartBadge = $("[data-test='shopping-cart-badge']");
    public final SelenideElement shoppingCartLink = $("[data-test='shopping-cart-link']");

    public ElementsCollection productElements() {
        return $$(".inventory_item");
    }

    public List<ProductCardComponent> getProductCards() {
        return productElements().stream()
                .map(ProductCardComponent::new)
                .collect(Collectors.toList());
    }

    public ProductCardComponent getProductByName(String name) {
        SelenideElement root = productElements()
                .findBy(Condition.text(name));
        return new ProductCardComponent(root);
    }

    public CartPage openCart() {
        shoppingCartLink.click();
        return new CartPage();
    }
}
