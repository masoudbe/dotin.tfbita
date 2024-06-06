package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderRegistrationInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRegistrationInfoDTO.class);
        OrderRegistrationInfoDTO orderRegistrationInfoDTO1 = new OrderRegistrationInfoDTO();
        orderRegistrationInfoDTO1.setId(1L);
        OrderRegistrationInfoDTO orderRegistrationInfoDTO2 = new OrderRegistrationInfoDTO();
        assertThat(orderRegistrationInfoDTO1).isNotEqualTo(orderRegistrationInfoDTO2);
        orderRegistrationInfoDTO2.setId(orderRegistrationInfoDTO1.getId());
        assertThat(orderRegistrationInfoDTO1).isEqualTo(orderRegistrationInfoDTO2);
        orderRegistrationInfoDTO2.setId(2L);
        assertThat(orderRegistrationInfoDTO1).isNotEqualTo(orderRegistrationInfoDTO2);
        orderRegistrationInfoDTO1.setId(null);
        assertThat(orderRegistrationInfoDTO1).isNotEqualTo(orderRegistrationInfoDTO2);
    }
}
