package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.PaymentItemTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentItemTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentItemType.class);
        PaymentItemType paymentItemType1 = getPaymentItemTypeSample1();
        PaymentItemType paymentItemType2 = new PaymentItemType();
        assertThat(paymentItemType1).isNotEqualTo(paymentItemType2);

        paymentItemType2.setId(paymentItemType1.getId());
        assertThat(paymentItemType1).isEqualTo(paymentItemType2);

        paymentItemType2 = getPaymentItemTypeSample2();
        assertThat(paymentItemType1).isNotEqualTo(paymentItemType2);
    }
}
