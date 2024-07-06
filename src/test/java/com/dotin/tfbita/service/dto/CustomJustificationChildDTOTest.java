package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomJustificationChildDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomJustificationChildDTO.class);
        CustomJustificationChildDTO customJustificationChildDTO1 = new CustomJustificationChildDTO();
        customJustificationChildDTO1.setId(1L);
        CustomJustificationChildDTO customJustificationChildDTO2 = new CustomJustificationChildDTO();
        assertThat(customJustificationChildDTO1).isNotEqualTo(customJustificationChildDTO2);
        customJustificationChildDTO2.setId(customJustificationChildDTO1.getId());
        assertThat(customJustificationChildDTO1).isEqualTo(customJustificationChildDTO2);
        customJustificationChildDTO2.setId(2L);
        assertThat(customJustificationChildDTO1).isNotEqualTo(customJustificationChildDTO2);
        customJustificationChildDTO1.setId(null);
        assertThat(customJustificationChildDTO1).isNotEqualTo(customJustificationChildDTO2);
    }
}
