package ru.hypertheosx.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {

    public SelenideElement productPageTitle = $("[data-test='title']");

    public SelenideElement inventoryItem;
    public SelenideElement inventoryItemName;
    public SelenideElement inventoryItemDescription;
    public SelenideElement inventoryItemPrice;

    public SelenideElement addToCartBtn;
}
