package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LicenceInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LicenceInfoDTO.class);
        LicenceInfoDTO licenceInfoDTO1 = new LicenceInfoDTO();
        licenceInfoDTO1.setId(1L);
        LicenceInfoDTO licenceInfoDTO2 = new LicenceInfoDTO();
        assertThat(licenceInfoDTO1).isNotEqualTo(licenceInfoDTO2);
        licenceInfoDTO2.setId(licenceInfoDTO1.getId());
        assertThat(licenceInfoDTO1).isEqualTo(licenceInfoDTO2);
        licenceInfoDTO2.setId(2L);
        assertThat(licenceInfoDTO1).isNotEqualTo(licenceInfoDTO2);
        licenceInfoDTO1.setId(null);
        assertThat(licenceInfoDTO1).isNotEqualTo(licenceInfoDTO2);
    }
}
