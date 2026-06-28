package ru.hypertheosx.qa.shared.element;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public final class ElementFactory {

    private ElementFactory() {}

    public static SelenideElement byDataTest(String dataTestId) {
        return $("[data-test='" + dataTestId + "']");
    }
}
