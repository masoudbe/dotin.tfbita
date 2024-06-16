package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.LicenceInfoTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegServTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LicenceInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LicenceInfo.class);
        LicenceInfo licenceInfo1 = getLicenceInfoSample1();
        LicenceInfo licenceInfo2 = new LicenceInfo();
        assertThat(licenceInfo1).isNotEqualTo(licenceInfo2);

        licenceInfo2.setId(licenceInfo1.getId());
        assertThat(licenceInfo1).isEqualTo(licenceInfo2);

        licenceInfo2 = getLicenceInfoSample2();
        assertThat(licenceInfo1).isNotEqualTo(licenceInfo2);
    }

    @Test
    void productTest() throws Exception {
        LicenceInfo licenceInfo = getLicenceInfoRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        licenceInfo.setProduct(productBack);
        assertThat(licenceInfo.getProduct()).isEqualTo(productBack);

        licenceInfo.product(null);
        assertThat(licenceInfo.getProduct()).isNull();
    }

    @Test
    void orderRegServTest() throws Exception {
        LicenceInfo licenceInfo = getLicenceInfoRandomSampleGenerator();
        OrderRegServ orderRegServBack = getOrderRegServRandomSampleGenerator();

        licenceInfo.setOrderRegServ(orderRegServBack);
        assertThat(licenceInfo.getOrderRegServ()).isEqualTo(orderRegServBack);

        licenceInfo.orderRegServ(null);
        assertThat(licenceInfo.getOrderRegServ()).isNull();
    }

    @Test
    void licenceInfoTest() throws Exception {
        LicenceInfo licenceInfo = getLicenceInfoRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        licenceInfo.setLicenceInfo(orderRegistrationInfoBack);
        assertThat(licenceInfo.getLicenceInfo()).isEqualTo(orderRegistrationInfoBack);

        licenceInfo.licenceInfo(null);
        assertThat(licenceInfo.getLicenceInfo()).isNull();
    }
}
