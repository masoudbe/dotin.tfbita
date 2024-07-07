package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TypeAttributeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAttributeDTO.class);
        TypeAttributeDTO typeAttributeDTO1 = new TypeAttributeDTO();
        typeAttributeDTO1.setId(1L);
        TypeAttributeDTO typeAttributeDTO2 = new TypeAttributeDTO();
        assertThat(typeAttributeDTO1).isNotEqualTo(typeAttributeDTO2);
        typeAttributeDTO2.setId(typeAttributeDTO1.getId());
        assertThat(typeAttributeDTO1).isEqualTo(typeAttributeDTO2);
        typeAttributeDTO2.setId(2L);
        assertThat(typeAttributeDTO1).isNotEqualTo(typeAttributeDTO2);
        typeAttributeDTO1.setId(null);
        assertThat(typeAttributeDTO1).isNotEqualTo(typeAttributeDTO2);
    }
}
