package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CurrencyExchangeInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CurrencyExchangeInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrencyExchangeInfo.class);
        CurrencyExchangeInfo currencyExchangeInfo1 = getCurrencyExchangeInfoSample1();
        CurrencyExchangeInfo currencyExchangeInfo2 = new CurrencyExchangeInfo();
        assertThat(currencyExchangeInfo1).isNotEqualTo(currencyExchangeInfo2);

        currencyExchangeInfo2.setId(currencyExchangeInfo1.getId());
        assertThat(currencyExchangeInfo1).isEqualTo(currencyExchangeInfo2);

        currencyExchangeInfo2 = getCurrencyExchangeInfoSample2();
        assertThat(currencyExchangeInfo1).isNotEqualTo(currencyExchangeInfo2);
    }
}
