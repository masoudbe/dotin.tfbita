package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditTypeConditionInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditTypeConditionInfoDTO.class);
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO1 = new CreditTypeConditionInfoDTO();
        creditTypeConditionInfoDTO1.setId(1L);
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO2 = new CreditTypeConditionInfoDTO();
        assertThat(creditTypeConditionInfoDTO1).isNotEqualTo(creditTypeConditionInfoDTO2);
        creditTypeConditionInfoDTO2.setId(creditTypeConditionInfoDTO1.getId());
        assertThat(creditTypeConditionInfoDTO1).isEqualTo(creditTypeConditionInfoDTO2);
        creditTypeConditionInfoDTO2.setId(2L);
        assertThat(creditTypeConditionInfoDTO1).isNotEqualTo(creditTypeConditionInfoDTO2);
        creditTypeConditionInfoDTO1.setId(null);
        assertThat(creditTypeConditionInfoDTO1).isNotEqualTo(creditTypeConditionInfoDTO2);
    }
}
