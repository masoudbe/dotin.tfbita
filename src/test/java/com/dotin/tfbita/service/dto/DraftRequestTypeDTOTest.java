package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftRequestTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftRequestTypeDTO.class);
        DraftRequestTypeDTO draftRequestTypeDTO1 = new DraftRequestTypeDTO();
        draftRequestTypeDTO1.setId(1L);
        DraftRequestTypeDTO draftRequestTypeDTO2 = new DraftRequestTypeDTO();
        assertThat(draftRequestTypeDTO1).isNotEqualTo(draftRequestTypeDTO2);
        draftRequestTypeDTO2.setId(draftRequestTypeDTO1.getId());
        assertThat(draftRequestTypeDTO1).isEqualTo(draftRequestTypeDTO2);
        draftRequestTypeDTO2.setId(2L);
        assertThat(draftRequestTypeDTO1).isNotEqualTo(draftRequestTypeDTO2);
        draftRequestTypeDTO1.setId(null);
        assertThat(draftRequestTypeDTO1).isNotEqualTo(draftRequestTypeDTO2);
    }
}
