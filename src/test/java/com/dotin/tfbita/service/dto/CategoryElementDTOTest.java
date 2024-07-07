package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryElementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryElementDTO.class);
        CategoryElementDTO categoryElementDTO1 = new CategoryElementDTO();
        categoryElementDTO1.setId(1L);
        CategoryElementDTO categoryElementDTO2 = new CategoryElementDTO();
        assertThat(categoryElementDTO1).isNotEqualTo(categoryElementDTO2);
        categoryElementDTO2.setId(categoryElementDTO1.getId());
        assertThat(categoryElementDTO1).isEqualTo(categoryElementDTO2);
        categoryElementDTO2.setId(2L);
        assertThat(categoryElementDTO1).isNotEqualTo(categoryElementDTO2);
        categoryElementDTO1.setId(null);
        assertThat(categoryElementDTO1).isNotEqualTo(categoryElementDTO2);
    }
}
