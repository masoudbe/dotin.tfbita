package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftDTO.class);
        DraftDTO draftDTO1 = new DraftDTO();
        draftDTO1.setId(1L);
        DraftDTO draftDTO2 = new DraftDTO();
        assertThat(draftDTO1).isNotEqualTo(draftDTO2);
        draftDTO2.setId(draftDTO1.getId());
        assertThat(draftDTO1).isEqualTo(draftDTO2);
        draftDTO2.setId(2L);
        assertThat(draftDTO1).isNotEqualTo(draftDTO2);
        draftDTO1.setId(null);
        assertThat(draftDTO1).isNotEqualTo(draftDTO2);
    }
}
