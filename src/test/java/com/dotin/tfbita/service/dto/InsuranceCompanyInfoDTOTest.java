package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceCompanyInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceCompanyInfoDTO.class);
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO1 = new InsuranceCompanyInfoDTO();
        insuranceCompanyInfoDTO1.setId(1L);
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO2 = new InsuranceCompanyInfoDTO();
        assertThat(insuranceCompanyInfoDTO1).isNotEqualTo(insuranceCompanyInfoDTO2);
        insuranceCompanyInfoDTO2.setId(insuranceCompanyInfoDTO1.getId());
        assertThat(insuranceCompanyInfoDTO1).isEqualTo(insuranceCompanyInfoDTO2);
        insuranceCompanyInfoDTO2.setId(2L);
        assertThat(insuranceCompanyInfoDTO1).isNotEqualTo(insuranceCompanyInfoDTO2);
        insuranceCompanyInfoDTO1.setId(null);
        assertThat(insuranceCompanyInfoDTO1).isNotEqualTo(insuranceCompanyInfoDTO2);
    }
}
