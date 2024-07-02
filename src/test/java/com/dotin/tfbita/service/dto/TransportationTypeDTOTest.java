package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TransportationTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportationTypeDTO.class);
        TransportationTypeDTO transportationTypeDTO1 = new TransportationTypeDTO();
        transportationTypeDTO1.setId(1L);
        TransportationTypeDTO transportationTypeDTO2 = new TransportationTypeDTO();
        assertThat(transportationTypeDTO1).isNotEqualTo(transportationTypeDTO2);
        transportationTypeDTO2.setId(transportationTypeDTO1.getId());
        assertThat(transportationTypeDTO1).isEqualTo(transportationTypeDTO2);
        transportationTypeDTO2.setId(2L);
        assertThat(transportationTypeDTO1).isNotEqualTo(transportationTypeDTO2);
        transportationTypeDTO1.setId(null);
        assertThat(transportationTypeDTO1).isNotEqualTo(transportationTypeDTO2);
    }
}
