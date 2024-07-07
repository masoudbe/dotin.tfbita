package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftProductInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftProductInfoDTO.class);
        DraftProductInfoDTO draftProductInfoDTO1 = new DraftProductInfoDTO();
        draftProductInfoDTO1.setId(1L);
        DraftProductInfoDTO draftProductInfoDTO2 = new DraftProductInfoDTO();
        assertThat(draftProductInfoDTO1).isNotEqualTo(draftProductInfoDTO2);
        draftProductInfoDTO2.setId(draftProductInfoDTO1.getId());
        assertThat(draftProductInfoDTO1).isEqualTo(draftProductInfoDTO2);
        draftProductInfoDTO2.setId(2L);
        assertThat(draftProductInfoDTO1).isNotEqualTo(draftProductInfoDTO2);
        draftProductInfoDTO1.setId(null);
        assertThat(draftProductInfoDTO1).isNotEqualTo(draftProductInfoDTO2);
    }
}
