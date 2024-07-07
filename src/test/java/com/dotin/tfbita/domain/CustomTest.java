package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Custom.class);
        Custom custom1 = getCustomSample1();
        Custom custom2 = new Custom();
        assertThat(custom1).isNotEqualTo(custom2);

        custom2.setId(custom1.getId());
        assertThat(custom1).isEqualTo(custom2);

        custom2 = getCustomSample2();
        assertThat(custom1).isNotEqualTo(custom2);
    }
}
