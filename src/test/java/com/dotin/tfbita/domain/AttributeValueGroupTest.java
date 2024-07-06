package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AttributeTestSamples.*;
import static com.dotin.tfbita.domain.AttributeValueGroupTestSamples.*;
import static com.dotin.tfbita.domain.AttributeValueTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AttributeValueGroupTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttributeValueGroup.class);
        AttributeValueGroup attributeValueGroup1 = getAttributeValueGroupSample1();
        AttributeValueGroup attributeValueGroup2 = new AttributeValueGroup();
        assertThat(attributeValueGroup1).isNotEqualTo(attributeValueGroup2);

        attributeValueGroup2.setId(attributeValueGroup1.getId());
        assertThat(attributeValueGroup1).isEqualTo(attributeValueGroup2);

        attributeValueGroup2 = getAttributeValueGroupSample2();
        assertThat(attributeValueGroup1).isNotEqualTo(attributeValueGroup2);
    }

    @Test
    void valuesTest() {
        AttributeValueGroup attributeValueGroup = getAttributeValueGroupRandomSampleGenerator();
        AttributeValue attributeValueBack = getAttributeValueRandomSampleGenerator();

        attributeValueGroup.addValues(attributeValueBack);
        assertThat(attributeValueGroup.getValues()).containsOnly(attributeValueBack);
        assertThat(attributeValueBack.getAttributeValueGroup()).isEqualTo(attributeValueGroup);

        attributeValueGroup.removeValues(attributeValueBack);
        assertThat(attributeValueGroup.getValues()).doesNotContain(attributeValueBack);
        assertThat(attributeValueBack.getAttributeValueGroup()).isNull();

        attributeValueGroup.values(new HashSet<>(Set.of(attributeValueBack)));
        assertThat(attributeValueGroup.getValues()).containsOnly(attributeValueBack);
        assertThat(attributeValueBack.getAttributeValueGroup()).isEqualTo(attributeValueGroup);

        attributeValueGroup.setValues(new HashSet<>());
        assertThat(attributeValueGroup.getValues()).doesNotContain(attributeValueBack);
        assertThat(attributeValueBack.getAttributeValueGroup()).isNull();
    }

    @Test
    void attributeTest() {
        AttributeValueGroup attributeValueGroup = getAttributeValueGroupRandomSampleGenerator();
        Attribute attributeBack = getAttributeRandomSampleGenerator();

        attributeValueGroup.setAttribute(attributeBack);
        assertThat(attributeValueGroup.getAttribute()).isEqualTo(attributeBack);

        attributeValueGroup.attribute(null);
        assertThat(attributeValueGroup.getAttribute()).isNull();
    }
}
