package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JustificationDeductionAmountPartDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JustificationDeductionAmountPartDTO.class);
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO1 = new JustificationDeductionAmountPartDTO();
        justificationDeductionAmountPartDTO1.setId(1L);
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO2 = new JustificationDeductionAmountPartDTO();
        assertThat(justificationDeductionAmountPartDTO1).isNotEqualTo(justificationDeductionAmountPartDTO2);
        justificationDeductionAmountPartDTO2.setId(justificationDeductionAmountPartDTO1.getId());
        assertThat(justificationDeductionAmountPartDTO1).isEqualTo(justificationDeductionAmountPartDTO2);
        justificationDeductionAmountPartDTO2.setId(2L);
        assertThat(justificationDeductionAmountPartDTO1).isNotEqualTo(justificationDeductionAmountPartDTO2);
        justificationDeductionAmountPartDTO1.setId(null);
        assertThat(justificationDeductionAmountPartDTO1).isNotEqualTo(justificationDeductionAmountPartDTO2);
    }
}
