package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.InsuranceCompanyInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceCompanyInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceCompanyInfo.class);
        InsuranceCompanyInfo insuranceCompanyInfo1 = getInsuranceCompanyInfoSample1();
        InsuranceCompanyInfo insuranceCompanyInfo2 = new InsuranceCompanyInfo();
        assertThat(insuranceCompanyInfo1).isNotEqualTo(insuranceCompanyInfo2);

        insuranceCompanyInfo2.setId(insuranceCompanyInfo1.getId());
        assertThat(insuranceCompanyInfo1).isEqualTo(insuranceCompanyInfo2);

        insuranceCompanyInfo2 = getInsuranceCompanyInfoSample2();
        assertThat(insuranceCompanyInfo1).isNotEqualTo(insuranceCompanyInfo2);
    }
}
