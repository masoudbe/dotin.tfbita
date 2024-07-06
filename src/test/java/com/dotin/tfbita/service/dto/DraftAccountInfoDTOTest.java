package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftAccountInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftAccountInfoDTO.class);
        DraftAccountInfoDTO draftAccountInfoDTO1 = new DraftAccountInfoDTO();
        draftAccountInfoDTO1.setId(1L);
        DraftAccountInfoDTO draftAccountInfoDTO2 = new DraftAccountInfoDTO();
        assertThat(draftAccountInfoDTO1).isNotEqualTo(draftAccountInfoDTO2);
        draftAccountInfoDTO2.setId(draftAccountInfoDTO1.getId());
        assertThat(draftAccountInfoDTO1).isEqualTo(draftAccountInfoDTO2);
        draftAccountInfoDTO2.setId(2L);
        assertThat(draftAccountInfoDTO1).isNotEqualTo(draftAccountInfoDTO2);
        draftAccountInfoDTO1.setId(null);
        assertThat(draftAccountInfoDTO1).isNotEqualTo(draftAccountInfoDTO2);
    }
}
