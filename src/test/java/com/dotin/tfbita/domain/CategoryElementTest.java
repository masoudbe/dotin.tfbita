package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryElementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryElement.class);
        CategoryElement categoryElement1 = getCategoryElementSample1();
        CategoryElement categoryElement2 = new CategoryElement();
        assertThat(categoryElement1).isNotEqualTo(categoryElement2);

        categoryElement2.setId(categoryElement1.getId());
        assertThat(categoryElement1).isEqualTo(categoryElement2);

        categoryElement2 = getCategoryElementSample2();
        assertThat(categoryElement1).isNotEqualTo(categoryElement2);
    }

    @Test
    void categoryTest() {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        categoryElement.setCategory(categoryBack);
        assertThat(categoryElement.getCategory()).isEqualTo(categoryBack);

        categoryElement.category(null);
        assertThat(categoryElement.getCategory()).isNull();
    }
}
