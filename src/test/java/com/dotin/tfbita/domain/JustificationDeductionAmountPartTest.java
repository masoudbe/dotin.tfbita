package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.JustificationDeductionAmountPartTestSamples.*;
import static com.dotin.tfbita.domain.JustificationDeductionAmountTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JustificationDeductionAmountPartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JustificationDeductionAmountPart.class);
        JustificationDeductionAmountPart justificationDeductionAmountPart1 = getJustificationDeductionAmountPartSample1();
        JustificationDeductionAmountPart justificationDeductionAmountPart2 = new JustificationDeductionAmountPart();
        assertThat(justificationDeductionAmountPart1).isNotEqualTo(justificationDeductionAmountPart2);

        justificationDeductionAmountPart2.setId(justificationDeductionAmountPart1.getId());
        assertThat(justificationDeductionAmountPart1).isEqualTo(justificationDeductionAmountPart2);

        justificationDeductionAmountPart2 = getJustificationDeductionAmountPartSample2();
        assertThat(justificationDeductionAmountPart1).isNotEqualTo(justificationDeductionAmountPart2);
    }

    @Test
    void justificationDeductionAmountTest() {
        JustificationDeductionAmountPart justificationDeductionAmountPart = getJustificationDeductionAmountPartRandomSampleGenerator();
        JustificationDeductionAmount justificationDeductionAmountBack = getJustificationDeductionAmountRandomSampleGenerator();

        justificationDeductionAmountPart.setJustificationDeductionAmount(justificationDeductionAmountBack);
        assertThat(justificationDeductionAmountPart.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmountBack);

        justificationDeductionAmountPart.justificationDeductionAmount(null);
        assertThat(justificationDeductionAmountPart.getJustificationDeductionAmount()).isNull();
    }
}
