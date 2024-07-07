package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AttributeValueGroupTestSamples.*;
import static com.dotin.tfbita.domain.AttributeValueTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.TypeAttributeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AttributeValueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttributeValue.class);
        AttributeValue attributeValue1 = getAttributeValueSample1();
        AttributeValue attributeValue2 = new AttributeValue();
        assertThat(attributeValue1).isNotEqualTo(attributeValue2);

        attributeValue2.setId(attributeValue1.getId());
        assertThat(attributeValue1).isEqualTo(attributeValue2);

        attributeValue2 = getAttributeValueSample2();
        assertThat(attributeValue1).isNotEqualTo(attributeValue2);
    }

    @Test
    void typeAttributeTest() {
        AttributeValue attributeValue = getAttributeValueRandomSampleGenerator();
        TypeAttribute typeAttributeBack = getTypeAttributeRandomSampleGenerator();

        attributeValue.setTypeAttribute(typeAttributeBack);
        assertThat(attributeValue.getTypeAttribute()).isEqualTo(typeAttributeBack);

        attributeValue.typeAttribute(null);
        assertThat(attributeValue.getTypeAttribute()).isNull();
    }

    @Test
    void attributeValueGroupTest() {
        AttributeValue attributeValue = getAttributeValueRandomSampleGenerator();
        AttributeValueGroup attributeValueGroupBack = getAttributeValueGroupRandomSampleGenerator();

        attributeValue.setAttributeValueGroup(attributeValueGroupBack);
        assertThat(attributeValue.getAttributeValueGroup()).isEqualTo(attributeValueGroupBack);

        attributeValue.attributeValueGroup(null);
        assertThat(attributeValue.getAttributeValueGroup()).isNull();
    }

    @Test
    void productTest() {
        AttributeValue attributeValue = getAttributeValueRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        attributeValue.setProduct(productBack);
        assertThat(attributeValue.getProduct()).isEqualTo(productBack);

        attributeValue.product(null);
        assertThat(attributeValue.getProduct()).isNull();
    }
}
