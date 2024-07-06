package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditTypeConditionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditTypeConditionDTO.class);
        CreditTypeConditionDTO creditTypeConditionDTO1 = new CreditTypeConditionDTO();
        creditTypeConditionDTO1.setId(1L);
        CreditTypeConditionDTO creditTypeConditionDTO2 = new CreditTypeConditionDTO();
        assertThat(creditTypeConditionDTO1).isNotEqualTo(creditTypeConditionDTO2);
        creditTypeConditionDTO2.setId(creditTypeConditionDTO1.getId());
        assertThat(creditTypeConditionDTO1).isEqualTo(creditTypeConditionDTO2);
        creditTypeConditionDTO2.setId(2L);
        assertThat(creditTypeConditionDTO1).isNotEqualTo(creditTypeConditionDTO2);
        creditTypeConditionDTO1.setId(null);
        assertThat(creditTypeConditionDTO1).isNotEqualTo(creditTypeConditionDTO2);
    }
}
