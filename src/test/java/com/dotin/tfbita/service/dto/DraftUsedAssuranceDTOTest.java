package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftUsedAssuranceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftUsedAssuranceDTO.class);
        DraftUsedAssuranceDTO draftUsedAssuranceDTO1 = new DraftUsedAssuranceDTO();
        draftUsedAssuranceDTO1.setId(1L);
        DraftUsedAssuranceDTO draftUsedAssuranceDTO2 = new DraftUsedAssuranceDTO();
        assertThat(draftUsedAssuranceDTO1).isNotEqualTo(draftUsedAssuranceDTO2);
        draftUsedAssuranceDTO2.setId(draftUsedAssuranceDTO1.getId());
        assertThat(draftUsedAssuranceDTO1).isEqualTo(draftUsedAssuranceDTO2);
        draftUsedAssuranceDTO2.setId(2L);
        assertThat(draftUsedAssuranceDTO1).isNotEqualTo(draftUsedAssuranceDTO2);
        draftUsedAssuranceDTO1.setId(null);
        assertThat(draftUsedAssuranceDTO1).isNotEqualTo(draftUsedAssuranceDTO2);
    }
}
