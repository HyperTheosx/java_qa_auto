package ru.hypertheosx.qa.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;

public class ProductCardComponent {

    private final SelenideElement root;

    public ProductCardComponent(SelenideElement root) {
        this.root = root;
    }

    private SelenideElement image() {
        return root.$(".inventory_item_img img");
    }

    private SelenideElement name() {
        return root.$("[data-test='inventory_item_name']");
    }

    private SelenideElement description() {
        return root.$("[data-test='inventory_item_desc']");
    }

    private SelenideElement price() {
        return root.$("[data-test='inventory_item_price']");
    }

    private SelenideElement actionButton() {
        return root.$("button");
    }

    public String getName() {
        return name().getText();
    }

    public String getDescription() {
        return description().getText();
    }

    public String getImageUrl() {
        return image().getAttribute("src");
    }

    public BigDecimal getPrice() {
        return new BigDecimal(
                price()
                        .getText()
                        .replace("$", "")
        );
    }

    public ProductCardComponent addToCart() {

        if (!isAddedToCart()) {
            actionButton().click();
        }

        return this;
    }

    public ProductCardComponent removeFromCart() {

        if (isAddedToCart()) {
            actionButton().click();
        }

        return this;
    }

    public boolean isAddedToCart() {
        return actionButton()
                .has(Condition.text("Remove"));
    }
}