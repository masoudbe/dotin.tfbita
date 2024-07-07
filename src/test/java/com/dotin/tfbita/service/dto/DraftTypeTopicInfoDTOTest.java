package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTypeTopicInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTypeTopicInfoDTO.class);
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO1 = new DraftTypeTopicInfoDTO();
        draftTypeTopicInfoDTO1.setId(1L);
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO2 = new DraftTypeTopicInfoDTO();
        assertThat(draftTypeTopicInfoDTO1).isNotEqualTo(draftTypeTopicInfoDTO2);
        draftTypeTopicInfoDTO2.setId(draftTypeTopicInfoDTO1.getId());
        assertThat(draftTypeTopicInfoDTO1).isEqualTo(draftTypeTopicInfoDTO2);
        draftTypeTopicInfoDTO2.setId(2L);
        assertThat(draftTypeTopicInfoDTO1).isNotEqualTo(draftTypeTopicInfoDTO2);
        draftTypeTopicInfoDTO1.setId(null);
        assertThat(draftTypeTopicInfoDTO1).isNotEqualTo(draftTypeTopicInfoDTO2);
    }
}
