package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JustificationDeductionDetailDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JustificationDeductionDetailDTO.class);
        JustificationDeductionDetailDTO justificationDeductionDetailDTO1 = new JustificationDeductionDetailDTO();
        justificationDeductionDetailDTO1.setId(1L);
        JustificationDeductionDetailDTO justificationDeductionDetailDTO2 = new JustificationDeductionDetailDTO();
        assertThat(justificationDeductionDetailDTO1).isNotEqualTo(justificationDeductionDetailDTO2);
        justificationDeductionDetailDTO2.setId(justificationDeductionDetailDTO1.getId());
        assertThat(justificationDeductionDetailDTO1).isEqualTo(justificationDeductionDetailDTO2);
        justificationDeductionDetailDTO2.setId(2L);
        assertThat(justificationDeductionDetailDTO1).isNotEqualTo(justificationDeductionDetailDTO2);
        justificationDeductionDetailDTO1.setId(null);
        assertThat(justificationDeductionDetailDTO1).isNotEqualTo(justificationDeductionDetailDTO2);
    }
}
