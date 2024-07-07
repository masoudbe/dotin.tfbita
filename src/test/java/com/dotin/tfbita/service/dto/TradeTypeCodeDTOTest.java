package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeTypeCodeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeTypeCodeDTO.class);
        TradeTypeCodeDTO tradeTypeCodeDTO1 = new TradeTypeCodeDTO();
        tradeTypeCodeDTO1.setId(1L);
        TradeTypeCodeDTO tradeTypeCodeDTO2 = new TradeTypeCodeDTO();
        assertThat(tradeTypeCodeDTO1).isNotEqualTo(tradeTypeCodeDTO2);
        tradeTypeCodeDTO2.setId(tradeTypeCodeDTO1.getId());
        assertThat(tradeTypeCodeDTO1).isEqualTo(tradeTypeCodeDTO2);
        tradeTypeCodeDTO2.setId(2L);
        assertThat(tradeTypeCodeDTO1).isNotEqualTo(tradeTypeCodeDTO2);
        tradeTypeCodeDTO1.setId(null);
        assertThat(tradeTypeCodeDTO1).isNotEqualTo(tradeTypeCodeDTO2);
    }
}
