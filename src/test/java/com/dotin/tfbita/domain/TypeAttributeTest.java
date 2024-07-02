package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AttributeTestSamples.*;
import static com.dotin.tfbita.domain.ProductTypeTestSamples.*;
import static com.dotin.tfbita.domain.TypeAttributeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TypeAttributeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAttribute.class);
        TypeAttribute typeAttribute1 = getTypeAttributeSample1();
        TypeAttribute typeAttribute2 = new TypeAttribute();
        assertThat(typeAttribute1).isNotEqualTo(typeAttribute2);

        typeAttribute2.setId(typeAttribute1.getId());
        assertThat(typeAttribute1).isEqualTo(typeAttribute2);

        typeAttribute2 = getTypeAttributeSample2();
        assertThat(typeAttribute1).isNotEqualTo(typeAttribute2);
    }

    @Test
    void attributeTest() throws Exception {
        TypeAttribute typeAttribute = getTypeAttributeRandomSampleGenerator();
        Attribute attributeBack = getAttributeRandomSampleGenerator();

        typeAttribute.setAttribute(attributeBack);
        assertThat(typeAttribute.getAttribute()).isEqualTo(attributeBack);

        typeAttribute.attribute(null);
        assertThat(typeAttribute.getAttribute()).isNull();
    }

    @Test
    void productTypeTest() throws Exception {
        TypeAttribute typeAttribute = getTypeAttributeRandomSampleGenerator();
        ProductType productTypeBack = getProductTypeRandomSampleGenerator();

        typeAttribute.addProductType(productTypeBack);
        assertThat(typeAttribute.getProductTypes()).containsOnly(productTypeBack);
        assertThat(productTypeBack.getProductTypeAttributes()).containsOnly(typeAttribute);

        typeAttribute.removeProductType(productTypeBack);
        assertThat(typeAttribute.getProductTypes()).doesNotContain(productTypeBack);
        assertThat(productTypeBack.getProductTypeAttributes()).doesNotContain(typeAttribute);

        typeAttribute.productTypes(new HashSet<>(Set.of(productTypeBack)));
        assertThat(typeAttribute.getProductTypes()).containsOnly(productTypeBack);
        assertThat(productTypeBack.getProductTypeAttributes()).containsOnly(typeAttribute);

        typeAttribute.setProductTypes(new HashSet<>());
        assertThat(typeAttribute.getProductTypes()).doesNotContain(productTypeBack);
        assertThat(productTypeBack.getProductTypeAttributes()).doesNotContain(typeAttribute);
    }
}
