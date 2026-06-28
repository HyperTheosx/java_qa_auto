package ru.hypertheosx.qa.features.cart.assertion;

import java.math.BigDecimal;
import java.util.Objects;

import org.assertj.core.api.AbstractAssert;

import ru.hypertheosx.qa.features.cart.component.ProductCardComponent;

public class ProductAssert extends AbstractAssert<ProductAssert, ProductCardComponent> {

    public static ProductAssert assertThat(ProductCardComponent actual) {
        return new ProductAssert(actual);
    }

    public ProductAssert(ProductCardComponent actual) {
        super(actual, ProductAssert.class);
    }

    public ProductAssert hasName(String expected) {
        isNotNull();
        String actualName = actual.getName();
        if (!Objects.equals(actualName, expected)) {
            failWithMessage("Expected product name <%s> but was <%s>", expected, actualName);
        }
        return this;
    }

    public ProductAssert hasPrice(BigDecimal expected) {
        isNotNull();
        BigDecimal actualPrice = actual.getPrice();
        if (actualPrice.compareTo(expected) != 0) {
            failWithMessage("Expected product price <%s> but was <%s>", expected, actualPrice);
        }
        return this;
    }

    public ProductAssert isAddedToCart() {
        isNotNull();
        if (!actual.isAddedToCart()) {
            failWithMessage("Expected product <%s> to be added to cart but it was not", actual.getName());
        }
        return this;
    }

    public ProductAssert isNotInCart() {
        isNotNull();
        if (actual.isAddedToCart()) {
            failWithMessage("Expected product <%s> to NOT be in cart but it was", actual.getName());
        }
        return this;
    }
}
