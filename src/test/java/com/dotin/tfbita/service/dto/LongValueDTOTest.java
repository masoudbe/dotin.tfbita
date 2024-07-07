package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LongValueDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LongValueDTO.class);
        LongValueDTO longValueDTO1 = new LongValueDTO();
        longValueDTO1.setId(1L);
        LongValueDTO longValueDTO2 = new LongValueDTO();
        assertThat(longValueDTO1).isNotEqualTo(longValueDTO2);
        longValueDTO2.setId(longValueDTO1.getId());
        assertThat(longValueDTO1).isEqualTo(longValueDTO2);
        longValueDTO2.setId(2L);
        assertThat(longValueDTO1).isNotEqualTo(longValueDTO2);
        longValueDTO1.setId(null);
        assertThat(longValueDTO1).isNotEqualTo(longValueDTO2);
    }
}
