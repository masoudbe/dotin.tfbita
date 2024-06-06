package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.LicenceInfoTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegServTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OrderRegServTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRegServ.class);
        OrderRegServ orderRegServ1 = getOrderRegServSample1();
        OrderRegServ orderRegServ2 = new OrderRegServ();
        assertThat(orderRegServ1).isNotEqualTo(orderRegServ2);

        orderRegServ2.setId(orderRegServ1.getId());
        assertThat(orderRegServ1).isEqualTo(orderRegServ2);

        orderRegServ2 = getOrderRegServSample2();
        assertThat(orderRegServ1).isNotEqualTo(orderRegServ2);
    }

    @Test
    void orderRegistrationInfoTest() throws Exception {
        OrderRegServ orderRegServ = getOrderRegServRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        orderRegServ.setOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(orderRegServ.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfoBack);

        orderRegServ.orderRegistrationInfo(null);
        assertThat(orderRegServ.getOrderRegistrationInfo()).isNull();
    }

    @Test
    void licenceInfoTest() throws Exception {
        OrderRegServ orderRegServ = getOrderRegServRandomSampleGenerator();
        LicenceInfo licenceInfoBack = getLicenceInfoRandomSampleGenerator();

        orderRegServ.addLicenceInfo(licenceInfoBack);
        assertThat(orderRegServ.getLicenceInfos()).containsOnly(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegServ()).isEqualTo(orderRegServ);

        orderRegServ.removeLicenceInfo(licenceInfoBack);
        assertThat(orderRegServ.getLicenceInfos()).doesNotContain(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegServ()).isNull();

        orderRegServ.licenceInfos(new HashSet<>(Set.of(licenceInfoBack)));
        assertThat(orderRegServ.getLicenceInfos()).containsOnly(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegServ()).isEqualTo(orderRegServ);

        orderRegServ.setLicenceInfos(new HashSet<>());
        assertThat(orderRegServ.getLicenceInfos()).doesNotContain(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegServ()).isNull();
    }
}
