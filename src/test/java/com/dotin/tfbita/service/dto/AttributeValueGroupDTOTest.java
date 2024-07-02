package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AttributeValueGroupDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttributeValueGroupDTO.class);
        AttributeValueGroupDTO attributeValueGroupDTO1 = new AttributeValueGroupDTO();
        attributeValueGroupDTO1.setId(1L);
        AttributeValueGroupDTO attributeValueGroupDTO2 = new AttributeValueGroupDTO();
        assertThat(attributeValueGroupDTO1).isNotEqualTo(attributeValueGroupDTO2);
        attributeValueGroupDTO2.setId(attributeValueGroupDTO1.getId());
        assertThat(attributeValueGroupDTO1).isEqualTo(attributeValueGroupDTO2);
        attributeValueGroupDTO2.setId(2L);
        assertThat(attributeValueGroupDTO1).isNotEqualTo(attributeValueGroupDTO2);
        attributeValueGroupDTO1.setId(null);
        assertThat(attributeValueGroupDTO1).isNotEqualTo(attributeValueGroupDTO2);
    }
}
