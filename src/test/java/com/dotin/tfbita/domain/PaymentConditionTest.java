package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.PaymentConditionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentConditionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentCondition.class);
        PaymentCondition paymentCondition1 = getPaymentConditionSample1();
        PaymentCondition paymentCondition2 = new PaymentCondition();
        assertThat(paymentCondition1).isNotEqualTo(paymentCondition2);

        paymentCondition2.setId(paymentCondition1.getId());
        assertThat(paymentCondition1).isEqualTo(paymentCondition2);

        paymentCondition2 = getPaymentConditionSample2();
        assertThat(paymentCondition1).isNotEqualTo(paymentCondition2);
    }
}
