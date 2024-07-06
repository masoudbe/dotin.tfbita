package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentItemTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentItemTypeDTO.class);
        PaymentItemTypeDTO paymentItemTypeDTO1 = new PaymentItemTypeDTO();
        paymentItemTypeDTO1.setId(1L);
        PaymentItemTypeDTO paymentItemTypeDTO2 = new PaymentItemTypeDTO();
        assertThat(paymentItemTypeDTO1).isNotEqualTo(paymentItemTypeDTO2);
        paymentItemTypeDTO2.setId(paymentItemTypeDTO1.getId());
        assertThat(paymentItemTypeDTO1).isEqualTo(paymentItemTypeDTO2);
        paymentItemTypeDTO2.setId(2L);
        assertThat(paymentItemTypeDTO1).isNotEqualTo(paymentItemTypeDTO2);
        paymentItemTypeDTO1.setId(null);
        assertThat(paymentItemTypeDTO1).isNotEqualTo(paymentItemTypeDTO2);
    }
}
