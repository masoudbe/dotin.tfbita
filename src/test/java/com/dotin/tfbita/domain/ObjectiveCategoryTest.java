package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.ObjectiveCategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.ObjectiveCategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ObjectiveCategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectiveCategory.class);
        ObjectiveCategory objectiveCategory1 = getObjectiveCategorySample1();
        ObjectiveCategory objectiveCategory2 = new ObjectiveCategory();
        assertThat(objectiveCategory1).isNotEqualTo(objectiveCategory2);

        objectiveCategory2.setId(objectiveCategory1.getId());
        assertThat(objectiveCategory1).isEqualTo(objectiveCategory2);

        objectiveCategory2 = getObjectiveCategorySample2();
        assertThat(objectiveCategory1).isNotEqualTo(objectiveCategory2);
    }

    @Test
    void possibleValuesTest() {
        ObjectiveCategory objectiveCategory = getObjectiveCategoryRandomSampleGenerator();
        ObjectiveCategoryElement objectiveCategoryElementBack = getObjectiveCategoryElementRandomSampleGenerator();

        objectiveCategory.addPossibleValues(objectiveCategoryElementBack);
        assertThat(objectiveCategory.getPossibleValues()).containsOnly(objectiveCategoryElementBack);
        assertThat(objectiveCategoryElementBack.getObjectiveCategory()).isEqualTo(objectiveCategory);

        objectiveCategory.removePossibleValues(objectiveCategoryElementBack);
        assertThat(objectiveCategory.getPossibleValues()).doesNotContain(objectiveCategoryElementBack);
        assertThat(objectiveCategoryElementBack.getObjectiveCategory()).isNull();

        objectiveCategory.possibleValues(new HashSet<>(Set.of(objectiveCategoryElementBack)));
        assertThat(objectiveCategory.getPossibleValues()).containsOnly(objectiveCategoryElementBack);
        assertThat(objectiveCategoryElementBack.getObjectiveCategory()).isEqualTo(objectiveCategory);

        objectiveCategory.setPossibleValues(new HashSet<>());
        assertThat(objectiveCategory.getPossibleValues()).doesNotContain(objectiveCategoryElementBack);
        assertThat(objectiveCategoryElementBack.getObjectiveCategory()).isNull();
    }
}
