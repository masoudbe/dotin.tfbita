package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AttributeTestSamples.*;
import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AttributeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attribute.class);
        Attribute attribute1 = getAttributeSample1();
        Attribute attribute2 = new Attribute();
        assertThat(attribute1).isNotEqualTo(attribute2);

        attribute2.setId(attribute1.getId());
        assertThat(attribute1).isEqualTo(attribute2);

        attribute2 = getAttributeSample2();
        assertThat(attribute1).isNotEqualTo(attribute2);
    }

    @Test
    void formatTest() throws Exception {
        Attribute attribute = getAttributeRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        attribute.setFormat(categoryElementBack);
        assertThat(attribute.getFormat()).isEqualTo(categoryElementBack);

        attribute.format(null);
        assertThat(attribute.getFormat()).isNull();
    }

    @Test
    void typeTest() throws Exception {
        Attribute attribute = getAttributeRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        attribute.setType(categoryElementBack);
        assertThat(attribute.getType()).isEqualTo(categoryElementBack);

        attribute.type(null);
        assertThat(attribute.getType()).isNull();
    }
}
