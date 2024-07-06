package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.OrderRegServiceTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.ServiceTariffTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderRegServiceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRegService.class);
        OrderRegService orderRegService1 = getOrderRegServiceSample1();
        OrderRegService orderRegService2 = new OrderRegService();
        assertThat(orderRegService1).isNotEqualTo(orderRegService2);

        orderRegService2.setId(orderRegService1.getId());
        assertThat(orderRegService1).isEqualTo(orderRegService2);

        orderRegService2 = getOrderRegServiceSample2();
        assertThat(orderRegService1).isNotEqualTo(orderRegService2);
    }

    @Test
    void serviceTariffTest() {
        OrderRegService orderRegService = getOrderRegServiceRandomSampleGenerator();
        ServiceTariff serviceTariffBack = getServiceTariffRandomSampleGenerator();

        orderRegService.setServiceTariff(serviceTariffBack);
        assertThat(orderRegService.getServiceTariff()).isEqualTo(serviceTariffBack);

        orderRegService.serviceTariff(null);
        assertThat(orderRegService.getServiceTariff()).isNull();
    }

    @Test
    void orderRegistrationInfoTest() {
        OrderRegService orderRegService = getOrderRegServiceRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        orderRegService.setOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(orderRegService.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfoBack);

        orderRegService.orderRegistrationInfo(null);
        assertThat(orderRegService.getOrderRegistrationInfo()).isNull();
    }
}
