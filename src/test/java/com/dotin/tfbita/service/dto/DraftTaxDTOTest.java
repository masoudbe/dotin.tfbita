package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTaxDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTaxDTO.class);
        DraftTaxDTO draftTaxDTO1 = new DraftTaxDTO();
        draftTaxDTO1.setId(1L);
        DraftTaxDTO draftTaxDTO2 = new DraftTaxDTO();
        assertThat(draftTaxDTO1).isNotEqualTo(draftTaxDTO2);
        draftTaxDTO2.setId(draftTaxDTO1.getId());
        assertThat(draftTaxDTO1).isEqualTo(draftTaxDTO2);
        draftTaxDTO2.setId(2L);
        assertThat(draftTaxDTO1).isNotEqualTo(draftTaxDTO2);
        draftTaxDTO1.setId(null);
        assertThat(draftTaxDTO1).isNotEqualTo(draftTaxDTO2);
    }
}
