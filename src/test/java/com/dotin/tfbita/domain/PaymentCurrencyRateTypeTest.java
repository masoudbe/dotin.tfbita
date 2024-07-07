package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.PaymentCurrencyRateTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentCurrencyRateTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentCurrencyRateType.class);
        PaymentCurrencyRateType paymentCurrencyRateType1 = getPaymentCurrencyRateTypeSample1();
        PaymentCurrencyRateType paymentCurrencyRateType2 = new PaymentCurrencyRateType();
        assertThat(paymentCurrencyRateType1).isNotEqualTo(paymentCurrencyRateType2);

        paymentCurrencyRateType2.setId(paymentCurrencyRateType1.getId());
        assertThat(paymentCurrencyRateType1).isEqualTo(paymentCurrencyRateType2);

        paymentCurrencyRateType2 = getPaymentCurrencyRateTypeSample2();
        assertThat(paymentCurrencyRateType1).isNotEqualTo(paymentCurrencyRateType2);
    }
}
