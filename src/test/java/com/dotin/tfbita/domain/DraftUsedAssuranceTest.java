package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.DraftUsedAssuranceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftUsedAssuranceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftUsedAssurance.class);
        DraftUsedAssurance draftUsedAssurance1 = getDraftUsedAssuranceSample1();
        DraftUsedAssurance draftUsedAssurance2 = new DraftUsedAssurance();
        assertThat(draftUsedAssurance1).isNotEqualTo(draftUsedAssurance2);

        draftUsedAssurance2.setId(draftUsedAssurance1.getId());
        assertThat(draftUsedAssurance1).isEqualTo(draftUsedAssurance2);

        draftUsedAssurance2 = getDraftUsedAssuranceSample2();
        assertThat(draftUsedAssurance1).isNotEqualTo(draftUsedAssurance2);
    }

    @Test
    void usedAssurancesTest() throws Exception {
        DraftUsedAssurance draftUsedAssurance = getDraftUsedAssuranceRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftUsedAssurance.setUsedAssurances(draftBack);
        assertThat(draftUsedAssurance.getUsedAssurances()).isEqualTo(draftBack);

        draftUsedAssurance.usedAssurances(null);
        assertThat(draftUsedAssurance.getUsedAssurances()).isNull();
    }
}
