package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftFactorDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftFactorDTO.class);
        DraftFactorDTO draftFactorDTO1 = new DraftFactorDTO();
        draftFactorDTO1.setId(1L);
        DraftFactorDTO draftFactorDTO2 = new DraftFactorDTO();
        assertThat(draftFactorDTO1).isNotEqualTo(draftFactorDTO2);
        draftFactorDTO2.setId(draftFactorDTO1.getId());
        assertThat(draftFactorDTO1).isEqualTo(draftFactorDTO2);
        draftFactorDTO2.setId(2L);
        assertThat(draftFactorDTO1).isNotEqualTo(draftFactorDTO2);
        draftFactorDTO1.setId(null);
        assertThat(draftFactorDTO1).isNotEqualTo(draftFactorDTO2);
    }
}
