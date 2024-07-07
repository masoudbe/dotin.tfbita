package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CurrencyExchangeInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrencyExchangeInfoDTO.class);
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO1 = new CurrencyExchangeInfoDTO();
        currencyExchangeInfoDTO1.setId(1L);
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO2 = new CurrencyExchangeInfoDTO();
        assertThat(currencyExchangeInfoDTO1).isNotEqualTo(currencyExchangeInfoDTO2);
        currencyExchangeInfoDTO2.setId(currencyExchangeInfoDTO1.getId());
        assertThat(currencyExchangeInfoDTO1).isEqualTo(currencyExchangeInfoDTO2);
        currencyExchangeInfoDTO2.setId(2L);
        assertThat(currencyExchangeInfoDTO1).isNotEqualTo(currencyExchangeInfoDTO2);
        currencyExchangeInfoDTO1.setId(null);
        assertThat(currencyExchangeInfoDTO1).isNotEqualTo(currencyExchangeInfoDTO2);
    }
}
