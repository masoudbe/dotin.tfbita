package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTypeDTO.class);
        DraftTypeDTO draftTypeDTO1 = new DraftTypeDTO();
        draftTypeDTO1.setId(1L);
        DraftTypeDTO draftTypeDTO2 = new DraftTypeDTO();
        assertThat(draftTypeDTO1).isNotEqualTo(draftTypeDTO2);
        draftTypeDTO2.setId(draftTypeDTO1.getId());
        assertThat(draftTypeDTO1).isEqualTo(draftTypeDTO2);
        draftTypeDTO2.setId(2L);
        assertThat(draftTypeDTO1).isNotEqualTo(draftTypeDTO2);
        draftTypeDTO1.setId(null);
        assertThat(draftTypeDTO1).isNotEqualTo(draftTypeDTO2);
    }
}
