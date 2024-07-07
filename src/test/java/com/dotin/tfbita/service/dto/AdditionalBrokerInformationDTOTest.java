package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdditionalBrokerInformationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalBrokerInformationDTO.class);
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO1 = new AdditionalBrokerInformationDTO();
        additionalBrokerInformationDTO1.setId(1L);
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO2 = new AdditionalBrokerInformationDTO();
        assertThat(additionalBrokerInformationDTO1).isNotEqualTo(additionalBrokerInformationDTO2);
        additionalBrokerInformationDTO2.setId(additionalBrokerInformationDTO1.getId());
        assertThat(additionalBrokerInformationDTO1).isEqualTo(additionalBrokerInformationDTO2);
        additionalBrokerInformationDTO2.setId(2L);
        assertThat(additionalBrokerInformationDTO1).isNotEqualTo(additionalBrokerInformationDTO2);
        additionalBrokerInformationDTO1.setId(null);
        assertThat(additionalBrokerInformationDTO1).isNotEqualTo(additionalBrokerInformationDTO2);
    }
}
