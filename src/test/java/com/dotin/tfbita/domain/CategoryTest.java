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
    void categoryElementTest() throws Exception {
        Category category = getCategoryRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        category.addCategoryElement(categoryElementBack);
        assertThat(category.getCategoryElements()).containsOnly(categoryElementBack);
        assertThat(categoryElementBack.getCategoryElement()).isEqualTo(category);

        category.removeCategoryElement(categoryElementBack);
        assertThat(category.getCategoryElements()).doesNotContain(categoryElementBack);
        assertThat(categoryElementBack.getCategoryElement()).isNull();

        category.categoryElements(new HashSet<>(Set.of(categoryElementBack)));
        assertThat(category.getCategoryElements()).containsOnly(categoryElementBack);
        assertThat(categoryElementBack.getCategoryElement()).isEqualTo(category);

        category.setCategoryElements(new HashSet<>());
        assertThat(category.getCategoryElements()).doesNotContain(categoryElementBack);
        assertThat(categoryElementBack.getCategoryElement()).isNull();
    }
}
