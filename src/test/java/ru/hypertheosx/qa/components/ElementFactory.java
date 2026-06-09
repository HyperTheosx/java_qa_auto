package ru.hypertheosx.qa.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ElementFactory {

    public static SelenideElement inputField(String data){
        return $("input[data-test='"+data+"']");
    };

    public static SelenideElement button(String data){
        return $("button[data-test='"+data+"']");
    }

}
