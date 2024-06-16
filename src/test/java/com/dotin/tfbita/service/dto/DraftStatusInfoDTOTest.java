package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftStatusInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftStatusInfoDTO.class);
        DraftStatusInfoDTO draftStatusInfoDTO1 = new DraftStatusInfoDTO();
        draftStatusInfoDTO1.setId(1L);
        DraftStatusInfoDTO draftStatusInfoDTO2 = new DraftStatusInfoDTO();
        assertThat(draftStatusInfoDTO1).isNotEqualTo(draftStatusInfoDTO2);
        draftStatusInfoDTO2.setId(draftStatusInfoDTO1.getId());
        assertThat(draftStatusInfoDTO1).isEqualTo(draftStatusInfoDTO2);
        draftStatusInfoDTO2.setId(2L);
        assertThat(draftStatusInfoDTO1).isNotEqualTo(draftStatusInfoDTO2);
        draftStatusInfoDTO1.setId(null);
        assertThat(draftStatusInfoDTO1).isNotEqualTo(draftStatusInfoDTO2);
    }
}
