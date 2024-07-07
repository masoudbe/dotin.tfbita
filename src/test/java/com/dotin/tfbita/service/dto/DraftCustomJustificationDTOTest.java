package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftCustomJustificationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftCustomJustificationDTO.class);
        DraftCustomJustificationDTO draftCustomJustificationDTO1 = new DraftCustomJustificationDTO();
        draftCustomJustificationDTO1.setId(1L);
        DraftCustomJustificationDTO draftCustomJustificationDTO2 = new DraftCustomJustificationDTO();
        assertThat(draftCustomJustificationDTO1).isNotEqualTo(draftCustomJustificationDTO2);
        draftCustomJustificationDTO2.setId(draftCustomJustificationDTO1.getId());
        assertThat(draftCustomJustificationDTO1).isEqualTo(draftCustomJustificationDTO2);
        draftCustomJustificationDTO2.setId(2L);
        assertThat(draftCustomJustificationDTO1).isNotEqualTo(draftCustomJustificationDTO2);
        draftCustomJustificationDTO1.setId(null);
        assertThat(draftCustomJustificationDTO1).isNotEqualTo(draftCustomJustificationDTO2);
    }
}
