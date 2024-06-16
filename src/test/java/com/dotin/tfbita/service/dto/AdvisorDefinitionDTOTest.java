package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdvisorDefinitionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdvisorDefinitionDTO.class);
        AdvisorDefinitionDTO advisorDefinitionDTO1 = new AdvisorDefinitionDTO();
        advisorDefinitionDTO1.setId(1L);
        AdvisorDefinitionDTO advisorDefinitionDTO2 = new AdvisorDefinitionDTO();
        assertThat(advisorDefinitionDTO1).isNotEqualTo(advisorDefinitionDTO2);
        advisorDefinitionDTO2.setId(advisorDefinitionDTO1.getId());
        assertThat(advisorDefinitionDTO1).isEqualTo(advisorDefinitionDTO2);
        advisorDefinitionDTO2.setId(2L);
        assertThat(advisorDefinitionDTO1).isNotEqualTo(advisorDefinitionDTO2);
        advisorDefinitionDTO1.setId(null);
        assertThat(advisorDefinitionDTO1).isNotEqualTo(advisorDefinitionDTO2);
    }
}
