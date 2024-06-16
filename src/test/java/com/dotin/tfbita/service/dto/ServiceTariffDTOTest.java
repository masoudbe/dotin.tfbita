package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceTariffDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceTariffDTO.class);
        ServiceTariffDTO serviceTariffDTO1 = new ServiceTariffDTO();
        serviceTariffDTO1.setId(1L);
        ServiceTariffDTO serviceTariffDTO2 = new ServiceTariffDTO();
        assertThat(serviceTariffDTO1).isNotEqualTo(serviceTariffDTO2);
        serviceTariffDTO2.setId(serviceTariffDTO1.getId());
        assertThat(serviceTariffDTO1).isEqualTo(serviceTariffDTO2);
        serviceTariffDTO2.setId(2L);
        assertThat(serviceTariffDTO1).isNotEqualTo(serviceTariffDTO2);
        serviceTariffDTO1.setId(null);
        assertThat(serviceTariffDTO1).isNotEqualTo(serviceTariffDTO2);
    }
}
