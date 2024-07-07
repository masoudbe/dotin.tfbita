package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTypeAccountInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTypeAccountInfoDTO.class);
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO1 = new DraftTypeAccountInfoDTO();
        draftTypeAccountInfoDTO1.setId(1L);
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO2 = new DraftTypeAccountInfoDTO();
        assertThat(draftTypeAccountInfoDTO1).isNotEqualTo(draftTypeAccountInfoDTO2);
        draftTypeAccountInfoDTO2.setId(draftTypeAccountInfoDTO1.getId());
        assertThat(draftTypeAccountInfoDTO1).isEqualTo(draftTypeAccountInfoDTO2);
        draftTypeAccountInfoDTO2.setId(2L);
        assertThat(draftTypeAccountInfoDTO1).isNotEqualTo(draftTypeAccountInfoDTO2);
        draftTypeAccountInfoDTO1.setId(null);
        assertThat(draftTypeAccountInfoDTO1).isNotEqualTo(draftTypeAccountInfoDTO2);
    }
}
