package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.ProductTypeTestSamples.*;
import static com.dotin.tfbita.domain.TypeAttributeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProductTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductType.class);
        ProductType productType1 = getProductTypeSample1();
        ProductType productType2 = new ProductType();
        assertThat(productType1).isNotEqualTo(productType2);

        productType2.setId(productType1.getId());
        assertThat(productType1).isEqualTo(productType2);

        productType2 = getProductTypeSample2();
        assertThat(productType1).isNotEqualTo(productType2);
    }

    @Test
    void productTypeAttributesTest() {
        ProductType productType = getProductTypeRandomSampleGenerator();
        TypeAttribute typeAttributeBack = getTypeAttributeRandomSampleGenerator();

        productType.addProductTypeAttributes(typeAttributeBack);
        assertThat(productType.getProductTypeAttributes()).containsOnly(typeAttributeBack);

        productType.removeProductTypeAttributes(typeAttributeBack);
        assertThat(productType.getProductTypeAttributes()).doesNotContain(typeAttributeBack);

        productType.productTypeAttributes(new HashSet<>(Set.of(typeAttributeBack)));
        assertThat(productType.getProductTypeAttributes()).containsOnly(typeAttributeBack);

        productType.setProductTypeAttributes(new HashSet<>());
        assertThat(productType.getProductTypeAttributes()).doesNotContain(typeAttributeBack);
    }
}
