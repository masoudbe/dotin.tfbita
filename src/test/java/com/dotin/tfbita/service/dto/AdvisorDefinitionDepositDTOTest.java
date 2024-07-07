package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdvisorDefinitionDepositDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdvisorDefinitionDepositDTO.class);
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO1 = new AdvisorDefinitionDepositDTO();
        advisorDefinitionDepositDTO1.setId(1L);
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO2 = new AdvisorDefinitionDepositDTO();
        assertThat(advisorDefinitionDepositDTO1).isNotEqualTo(advisorDefinitionDepositDTO2);
        advisorDefinitionDepositDTO2.setId(advisorDefinitionDepositDTO1.getId());
        assertThat(advisorDefinitionDepositDTO1).isEqualTo(advisorDefinitionDepositDTO2);
        advisorDefinitionDepositDTO2.setId(2L);
        assertThat(advisorDefinitionDepositDTO1).isNotEqualTo(advisorDefinitionDepositDTO2);
        advisorDefinitionDepositDTO1.setId(null);
        assertThat(advisorDefinitionDepositDTO1).isNotEqualTo(advisorDefinitionDepositDTO2);
    }
}
