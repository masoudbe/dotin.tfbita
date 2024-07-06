package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BasicInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BasicInfoDTO.class);
        BasicInfoDTO basicInfoDTO1 = new BasicInfoDTO();
        basicInfoDTO1.setId(1L);
        BasicInfoDTO basicInfoDTO2 = new BasicInfoDTO();
        assertThat(basicInfoDTO1).isNotEqualTo(basicInfoDTO2);
        basicInfoDTO2.setId(basicInfoDTO1.getId());
        assertThat(basicInfoDTO1).isEqualTo(basicInfoDTO2);
        basicInfoDTO2.setId(2L);
        assertThat(basicInfoDTO1).isNotEqualTo(basicInfoDTO2);
        basicInfoDTO1.setId(null);
        assertThat(basicInfoDTO1).isNotEqualTo(basicInfoDTO2);
    }
}
