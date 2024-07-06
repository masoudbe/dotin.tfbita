package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CustomJustificationChildTestSamples.*;
import static com.dotin.tfbita.domain.CustomJustificationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomJustificationChildTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomJustificationChild.class);
        CustomJustificationChild customJustificationChild1 = getCustomJustificationChildSample1();
        CustomJustificationChild customJustificationChild2 = new CustomJustificationChild();
        assertThat(customJustificationChild1).isNotEqualTo(customJustificationChild2);

        customJustificationChild2.setId(customJustificationChild1.getId());
        assertThat(customJustificationChild1).isEqualTo(customJustificationChild2);

        customJustificationChild2 = getCustomJustificationChildSample2();
        assertThat(customJustificationChild1).isNotEqualTo(customJustificationChild2);
    }

    @Test
    void customJustificationTest() {
        CustomJustificationChild customJustificationChild = getCustomJustificationChildRandomSampleGenerator();
        CustomJustification customJustificationBack = getCustomJustificationRandomSampleGenerator();

        customJustificationChild.setCustomJustification(customJustificationBack);
        assertThat(customJustificationChild.getCustomJustification()).isEqualTo(customJustificationBack);

        customJustificationChild.customJustification(null);
        assertThat(customJustificationChild.getCustomJustification()).isNull();
    }
}
