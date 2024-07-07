package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.TransportationTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TransportationTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportationType.class);
        TransportationType transportationType1 = getTransportationTypeSample1();
        TransportationType transportationType2 = new TransportationType();
        assertThat(transportationType1).isNotEqualTo(transportationType2);

        transportationType2.setId(transportationType1.getId());
        assertThat(transportationType1).isEqualTo(transportationType2);

        transportationType2 = getTransportationTypeSample2();
        assertThat(transportationType1).isNotEqualTo(transportationType2);
    }
}
