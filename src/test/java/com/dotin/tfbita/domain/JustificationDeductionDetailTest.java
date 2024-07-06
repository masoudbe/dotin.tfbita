package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.JustificationDeductionAmountTestSamples.*;
import static com.dotin.tfbita.domain.JustificationDeductionDetailTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JustificationDeductionDetailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JustificationDeductionDetail.class);
        JustificationDeductionDetail justificationDeductionDetail1 = getJustificationDeductionDetailSample1();
        JustificationDeductionDetail justificationDeductionDetail2 = new JustificationDeductionDetail();
        assertThat(justificationDeductionDetail1).isNotEqualTo(justificationDeductionDetail2);

        justificationDeductionDetail2.setId(justificationDeductionDetail1.getId());
        assertThat(justificationDeductionDetail1).isEqualTo(justificationDeductionDetail2);

        justificationDeductionDetail2 = getJustificationDeductionDetailSample2();
        assertThat(justificationDeductionDetail1).isNotEqualTo(justificationDeductionDetail2);
    }

    @Test
    void deductionReasonTest() {
        JustificationDeductionDetail justificationDeductionDetail = getJustificationDeductionDetailRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        justificationDeductionDetail.setDeductionReason(categoryElementBack);
        assertThat(justificationDeductionDetail.getDeductionReason()).isEqualTo(categoryElementBack);

        justificationDeductionDetail.deductionReason(null);
        assertThat(justificationDeductionDetail.getDeductionReason()).isNull();
    }

    @Test
    void justificationDeductionAmountTest() {
        JustificationDeductionDetail justificationDeductionDetail = getJustificationDeductionDetailRandomSampleGenerator();
        JustificationDeductionAmount justificationDeductionAmountBack = getJustificationDeductionAmountRandomSampleGenerator();

        justificationDeductionDetail.setJustificationDeductionAmount(justificationDeductionAmountBack);
        assertThat(justificationDeductionDetail.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmountBack);

        justificationDeductionDetail.justificationDeductionAmount(null);
        assertThat(justificationDeductionDetail.getJustificationDeductionAmount()).isNull();
    }
}
