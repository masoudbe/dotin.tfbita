package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JustificationDeductionAmountDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JustificationDeductionAmountDTO.class);
        JustificationDeductionAmountDTO justificationDeductionAmountDTO1 = new JustificationDeductionAmountDTO();
        justificationDeductionAmountDTO1.setId(1L);
        JustificationDeductionAmountDTO justificationDeductionAmountDTO2 = new JustificationDeductionAmountDTO();
        assertThat(justificationDeductionAmountDTO1).isNotEqualTo(justificationDeductionAmountDTO2);
        justificationDeductionAmountDTO2.setId(justificationDeductionAmountDTO1.getId());
        assertThat(justificationDeductionAmountDTO1).isEqualTo(justificationDeductionAmountDTO2);
        justificationDeductionAmountDTO2.setId(2L);
        assertThat(justificationDeductionAmountDTO1).isNotEqualTo(justificationDeductionAmountDTO2);
        justificationDeductionAmountDTO1.setId(null);
        assertThat(justificationDeductionAmountDTO1).isNotEqualTo(justificationDeductionAmountDTO2);
    }
}
