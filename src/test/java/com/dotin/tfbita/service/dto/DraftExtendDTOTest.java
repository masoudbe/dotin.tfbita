package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftExtendDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftExtendDTO.class);
        DraftExtendDTO draftExtendDTO1 = new DraftExtendDTO();
        draftExtendDTO1.setId(1L);
        DraftExtendDTO draftExtendDTO2 = new DraftExtendDTO();
        assertThat(draftExtendDTO1).isNotEqualTo(draftExtendDTO2);
        draftExtendDTO2.setId(draftExtendDTO1.getId());
        assertThat(draftExtendDTO1).isEqualTo(draftExtendDTO2);
        draftExtendDTO2.setId(2L);
        assertThat(draftExtendDTO1).isNotEqualTo(draftExtendDTO2);
        draftExtendDTO1.setId(null);
        assertThat(draftExtendDTO1).isNotEqualTo(draftExtendDTO2);
    }
}
