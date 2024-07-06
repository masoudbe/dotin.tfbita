package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SwiftBicDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SwiftBicDTO.class);
        SwiftBicDTO swiftBicDTO1 = new SwiftBicDTO();
        swiftBicDTO1.setId(1L);
        SwiftBicDTO swiftBicDTO2 = new SwiftBicDTO();
        assertThat(swiftBicDTO1).isNotEqualTo(swiftBicDTO2);
        swiftBicDTO2.setId(swiftBicDTO1.getId());
        assertThat(swiftBicDTO1).isEqualTo(swiftBicDTO2);
        swiftBicDTO2.setId(2L);
        assertThat(swiftBicDTO1).isNotEqualTo(swiftBicDTO2);
        swiftBicDTO1.setId(null);
        assertThat(swiftBicDTO1).isNotEqualTo(swiftBicDTO2);
    }
}
