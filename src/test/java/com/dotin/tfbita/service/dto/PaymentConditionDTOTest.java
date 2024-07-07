package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentConditionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentConditionDTO.class);
        PaymentConditionDTO paymentConditionDTO1 = new PaymentConditionDTO();
        paymentConditionDTO1.setId(1L);
        PaymentConditionDTO paymentConditionDTO2 = new PaymentConditionDTO();
        assertThat(paymentConditionDTO1).isNotEqualTo(paymentConditionDTO2);
        paymentConditionDTO2.setId(paymentConditionDTO1.getId());
        assertThat(paymentConditionDTO1).isEqualTo(paymentConditionDTO2);
        paymentConditionDTO2.setId(2L);
        assertThat(paymentConditionDTO1).isNotEqualTo(paymentConditionDTO2);
        paymentConditionDTO1.setId(null);
        assertThat(paymentConditionDTO1).isNotEqualTo(paymentConditionDTO2);
    }
}
