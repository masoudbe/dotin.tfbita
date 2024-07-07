package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentCurrencyRateTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentCurrencyRateTypeDTO.class);
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO1 = new PaymentCurrencyRateTypeDTO();
        paymentCurrencyRateTypeDTO1.setId(1L);
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO2 = new PaymentCurrencyRateTypeDTO();
        assertThat(paymentCurrencyRateTypeDTO1).isNotEqualTo(paymentCurrencyRateTypeDTO2);
        paymentCurrencyRateTypeDTO2.setId(paymentCurrencyRateTypeDTO1.getId());
        assertThat(paymentCurrencyRateTypeDTO1).isEqualTo(paymentCurrencyRateTypeDTO2);
        paymentCurrencyRateTypeDTO2.setId(2L);
        assertThat(paymentCurrencyRateTypeDTO1).isNotEqualTo(paymentCurrencyRateTypeDTO2);
        paymentCurrencyRateTypeDTO1.setId(null);
        assertThat(paymentCurrencyRateTypeDTO1).isNotEqualTo(paymentCurrencyRateTypeDTO2);
    }
}
