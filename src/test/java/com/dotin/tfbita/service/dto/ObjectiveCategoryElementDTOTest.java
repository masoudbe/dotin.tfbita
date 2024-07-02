package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectiveCategoryElementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectiveCategoryElementDTO.class);
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO1 = new ObjectiveCategoryElementDTO();
        objectiveCategoryElementDTO1.setId(1L);
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO2 = new ObjectiveCategoryElementDTO();
        assertThat(objectiveCategoryElementDTO1).isNotEqualTo(objectiveCategoryElementDTO2);
        objectiveCategoryElementDTO2.setId(objectiveCategoryElementDTO1.getId());
        assertThat(objectiveCategoryElementDTO1).isEqualTo(objectiveCategoryElementDTO2);
        objectiveCategoryElementDTO2.setId(2L);
        assertThat(objectiveCategoryElementDTO1).isNotEqualTo(objectiveCategoryElementDTO2);
        objectiveCategoryElementDTO1.setId(null);
        assertThat(objectiveCategoryElementDTO1).isNotEqualTo(objectiveCategoryElementDTO2);
    }
}
