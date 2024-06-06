package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderRegServDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRegServDTO.class);
        OrderRegServDTO orderRegServDTO1 = new OrderRegServDTO();
        orderRegServDTO1.setId(1L);
        OrderRegServDTO orderRegServDTO2 = new OrderRegServDTO();
        assertThat(orderRegServDTO1).isNotEqualTo(orderRegServDTO2);
        orderRegServDTO2.setId(orderRegServDTO1.getId());
        assertThat(orderRegServDTO1).isEqualTo(orderRegServDTO2);
        orderRegServDTO2.setId(2L);
        assertThat(orderRegServDTO1).isNotEqualTo(orderRegServDTO2);
        orderRegServDTO1.setId(null);
        assertThat(orderRegServDTO1).isNotEqualTo(orderRegServDTO2);
    }
}
