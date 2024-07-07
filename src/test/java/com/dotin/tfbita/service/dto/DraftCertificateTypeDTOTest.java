package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftCertificateTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftCertificateTypeDTO.class);
        DraftCertificateTypeDTO draftCertificateTypeDTO1 = new DraftCertificateTypeDTO();
        draftCertificateTypeDTO1.setId(1L);
        DraftCertificateTypeDTO draftCertificateTypeDTO2 = new DraftCertificateTypeDTO();
        assertThat(draftCertificateTypeDTO1).isNotEqualTo(draftCertificateTypeDTO2);
        draftCertificateTypeDTO2.setId(draftCertificateTypeDTO1.getId());
        assertThat(draftCertificateTypeDTO1).isEqualTo(draftCertificateTypeDTO2);
        draftCertificateTypeDTO2.setId(2L);
        assertThat(draftCertificateTypeDTO1).isNotEqualTo(draftCertificateTypeDTO2);
        draftCertificateTypeDTO1.setId(null);
        assertThat(draftCertificateTypeDTO1).isNotEqualTo(draftCertificateTypeDTO2);
    }
}
