package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.JustificationDeductionAmountPartTestSamples.*;
import static com.dotin.tfbita.domain.JustificationDeductionAmountTestSamples.*;
import static com.dotin.tfbita.domain.JustificationDeductionDetailTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class JustificationDeductionAmountTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JustificationDeductionAmount.class);
        JustificationDeductionAmount justificationDeductionAmount1 = getJustificationDeductionAmountSample1();
        JustificationDeductionAmount justificationDeductionAmount2 = new JustificationDeductionAmount();
        assertThat(justificationDeductionAmount1).isNotEqualTo(justificationDeductionAmount2);

        justificationDeductionAmount2.setId(justificationDeductionAmount1.getId());
        assertThat(justificationDeductionAmount1).isEqualTo(justificationDeductionAmount2);

        justificationDeductionAmount2 = getJustificationDeductionAmountSample2();
        assertThat(justificationDeductionAmount1).isNotEqualTo(justificationDeductionAmount2);
    }

    @Test
    void justificationDeductionAmountPartsTest() {
        JustificationDeductionAmount justificationDeductionAmount = getJustificationDeductionAmountRandomSampleGenerator();
        JustificationDeductionAmountPart justificationDeductionAmountPartBack = getJustificationDeductionAmountPartRandomSampleGenerator();

        justificationDeductionAmount.addJustificationDeductionAmountParts(justificationDeductionAmountPartBack);
        assertThat(justificationDeductionAmount.getJustificationDeductionAmountParts()).containsOnly(justificationDeductionAmountPartBack);
        assertThat(justificationDeductionAmountPartBack.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmount);

        justificationDeductionAmount.removeJustificationDeductionAmountParts(justificationDeductionAmountPartBack);
        assertThat(justificationDeductionAmount.getJustificationDeductionAmountParts()).doesNotContain(
            justificationDeductionAmountPartBack
        );
        assertThat(justificationDeductionAmountPartBack.getJustificationDeductionAmount()).isNull();

        justificationDeductionAmount.justificationDeductionAmountParts(new HashSet<>(Set.of(justificationDeductionAmountPartBack)));
        assertThat(justificationDeductionAmount.getJustificationDeductionAmountParts()).containsOnly(justificationDeductionAmountPartBack);
        assertThat(justificationDeductionAmountPartBack.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmount);

        justificationDeductionAmount.setJustificationDeductionAmountParts(new HashSet<>());
        assertThat(justificationDeductionAmount.getJustificationDeductionAmountParts()).doesNotContain(
            justificationDeductionAmountPartBack
        );
        assertThat(justificationDeductionAmountPartBack.getJustificationDeductionAmount()).isNull();
    }

    @Test
    void justificationDeductionDetailsTest() {
        JustificationDeductionAmount justificationDeductionAmount = getJustificationDeductionAmountRandomSampleGenerator();
        JustificationDeductionDetail justificationDeductionDetailBack = getJustificationDeductionDetailRandomSampleGenerator();

        justificationDeductionAmount.addJustificationDeductionDetails(justificationDeductionDetailBack);
        assertThat(justificationDeductionAmount.getJustificationDeductionDetails()).containsOnly(justificationDeductionDetailBack);
        assertThat(justificationDeductionDetailBack.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmount);

        justificationDeductionAmount.removeJustificationDeductionDetails(justificationDeductionDetailBack);
        assertThat(justificationDeductionAmount.getJustificationDeductionDetails()).doesNotContain(justificationDeductionDetailBack);
        assertThat(justificationDeductionDetailBack.getJustificationDeductionAmount()).isNull();

        justificationDeductionAmount.justificationDeductionDetails(new HashSet<>(Set.of(justificationDeductionDetailBack)));
        assertThat(justificationDeductionAmount.getJustificationDeductionDetails()).containsOnly(justificationDeductionDetailBack);
        assertThat(justificationDeductionDetailBack.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmount);

        justificationDeductionAmount.setJustificationDeductionDetails(new HashSet<>());
        assertThat(justificationDeductionAmount.getJustificationDeductionDetails()).doesNotContain(justificationDeductionDetailBack);
        assertThat(justificationDeductionDetailBack.getJustificationDeductionAmount()).isNull();
    }
}
