package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.TradeTypeCodeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeTypeCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeTypeCode.class);
        TradeTypeCode tradeTypeCode1 = getTradeTypeCodeSample1();
        TradeTypeCode tradeTypeCode2 = new TradeTypeCode();
        assertThat(tradeTypeCode1).isNotEqualTo(tradeTypeCode2);

        tradeTypeCode2.setId(tradeTypeCode1.getId());
        assertThat(tradeTypeCode1).isEqualTo(tradeTypeCode2);

        tradeTypeCode2 = getTradeTypeCodeSample2();
        assertThat(tradeTypeCode1).isNotEqualTo(tradeTypeCode2);
    }
}
