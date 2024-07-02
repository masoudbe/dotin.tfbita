package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectiveCategoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectiveCategoryDTO.class);
        ObjectiveCategoryDTO objectiveCategoryDTO1 = new ObjectiveCategoryDTO();
        objectiveCategoryDTO1.setId(1L);
        ObjectiveCategoryDTO objectiveCategoryDTO2 = new ObjectiveCategoryDTO();
        assertThat(objectiveCategoryDTO1).isNotEqualTo(objectiveCategoryDTO2);
        objectiveCategoryDTO2.setId(objectiveCategoryDTO1.getId());
        assertThat(objectiveCategoryDTO1).isEqualTo(objectiveCategoryDTO2);
        objectiveCategoryDTO2.setId(2L);
        assertThat(objectiveCategoryDTO1).isNotEqualTo(objectiveCategoryDTO2);
        objectiveCategoryDTO1.setId(null);
        assertThat(objectiveCategoryDTO1).isNotEqualTo(objectiveCategoryDTO2);
    }
}
