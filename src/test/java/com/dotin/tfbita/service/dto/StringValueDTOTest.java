package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StringValueDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StringValueDTO.class);
        StringValueDTO stringValueDTO1 = new StringValueDTO();
        stringValueDTO1.setId(1L);
        StringValueDTO stringValueDTO2 = new StringValueDTO();
        assertThat(stringValueDTO1).isNotEqualTo(stringValueDTO2);
        stringValueDTO2.setId(stringValueDTO1.getId());
        assertThat(stringValueDTO1).isEqualTo(stringValueDTO2);
        stringValueDTO2.setId(2L);
        assertThat(stringValueDTO1).isNotEqualTo(stringValueDTO2);
        stringValueDTO1.setId(null);
        assertThat(stringValueDTO1).isNotEqualTo(stringValueDTO2);
    }
}
