package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category.class);
        Category category1 = getCategorySample1();
        Category category2 = new Category();
        assertThat(category1).isNotEqualTo(category2);

        category2.setId(category1.getId());
        assertThat(category1).isEqualTo(category2);

        category2 = getCategorySample2();
        assertThat(category1).isNotEqualTo(category2);
    }

    @Test
    void possibleValuesTest() throws Exception {
        Category category = getCategoryRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        category.addPossibleValues(categoryElementBack);
        assertThat(category.getPossibleValues()).containsOnly(categoryElementBack);
        assertThat(categoryElementBack.getCategory()).isEqualTo(category);

        category.removePossibleValues(categoryElementBack);
        assertThat(category.getPossibleValues()).doesNotContain(categoryElementBack);
        assertThat(categoryElementBack.getCategory()).isNull();

        category.possibleValues(new HashSet<>(Set.of(categoryElementBack)));
        assertThat(category.getPossibleValues()).containsOnly(categoryElementBack);
        assertThat(categoryElementBack.getCategory()).isEqualTo(category);

        category.setPossibleValues(new HashSet<>());
        assertThat(category.getPossibleValues()).doesNotContain(categoryElementBack);
        assertThat(categoryElementBack.getCategory()).isNull();
    }
}
