package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomJustificationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomJustificationDTO.class);
        CustomJustificationDTO customJustificationDTO1 = new CustomJustificationDTO();
        customJustificationDTO1.setId(1L);
        CustomJustificationDTO customJustificationDTO2 = new CustomJustificationDTO();
        assertThat(customJustificationDTO1).isNotEqualTo(customJustificationDTO2);
        customJustificationDTO2.setId(customJustificationDTO1.getId());
        assertThat(customJustificationDTO1).isEqualTo(customJustificationDTO2);
        customJustificationDTO2.setId(2L);
        assertThat(customJustificationDTO1).isNotEqualTo(customJustificationDTO2);
        customJustificationDTO1.setId(null);
        assertThat(customJustificationDTO1).isNotEqualTo(customJustificationDTO2);
    }
}
