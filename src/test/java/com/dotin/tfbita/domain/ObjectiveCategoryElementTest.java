package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.ObjectiveCategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.ObjectiveCategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectiveCategoryElementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectiveCategoryElement.class);
        ObjectiveCategoryElement objectiveCategoryElement1 = getObjectiveCategoryElementSample1();
        ObjectiveCategoryElement objectiveCategoryElement2 = new ObjectiveCategoryElement();
        assertThat(objectiveCategoryElement1).isNotEqualTo(objectiveCategoryElement2);

        objectiveCategoryElement2.setId(objectiveCategoryElement1.getId());
        assertThat(objectiveCategoryElement1).isEqualTo(objectiveCategoryElement2);

        objectiveCategoryElement2 = getObjectiveCategoryElementSample2();
        assertThat(objectiveCategoryElement1).isNotEqualTo(objectiveCategoryElement2);
    }

    @Test
    void objectiveCategoryTest() {
        ObjectiveCategoryElement objectiveCategoryElement = getObjectiveCategoryElementRandomSampleGenerator();
        ObjectiveCategory objectiveCategoryBack = getObjectiveCategoryRandomSampleGenerator();

        objectiveCategoryElement.setObjectiveCategory(objectiveCategoryBack);
        assertThat(objectiveCategoryElement.getObjectiveCategory()).isEqualTo(objectiveCategoryBack);

        objectiveCategoryElement.objectiveCategory(null);
        assertThat(objectiveCategoryElement.getObjectiveCategory()).isNull();
    }
}
