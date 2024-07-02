package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderRegServiceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRegServiceDTO.class);
        OrderRegServiceDTO orderRegServiceDTO1 = new OrderRegServiceDTO();
        orderRegServiceDTO1.setId(1L);
        OrderRegServiceDTO orderRegServiceDTO2 = new OrderRegServiceDTO();
        assertThat(orderRegServiceDTO1).isNotEqualTo(orderRegServiceDTO2);
        orderRegServiceDTO2.setId(orderRegServiceDTO1.getId());
        assertThat(orderRegServiceDTO1).isEqualTo(orderRegServiceDTO2);
        orderRegServiceDTO2.setId(2L);
        assertThat(orderRegServiceDTO1).isNotEqualTo(orderRegServiceDTO2);
        orderRegServiceDTO1.setId(null);
        assertThat(orderRegServiceDTO1).isNotEqualTo(orderRegServiceDTO2);
    }
}
