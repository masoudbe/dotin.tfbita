package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftFactorTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftFactorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftFactor.class);
        DraftFactor draftFactor1 = getDraftFactorSample1();
        DraftFactor draftFactor2 = new DraftFactor();
        assertThat(draftFactor1).isNotEqualTo(draftFactor2);

        draftFactor2.setId(draftFactor1.getId());
        assertThat(draftFactor1).isEqualTo(draftFactor2);

        draftFactor2 = getDraftFactorSample2();
        assertThat(draftFactor1).isNotEqualTo(draftFactor2);
    }

    @Test
    void draftFactorsTest() throws Exception {
        DraftFactor draftFactor = getDraftFactorRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftFactor.setDraftFactors(draftBack);
        assertThat(draftFactor.getDraftFactors()).isEqualTo(draftBack);

        draftFactor.draftFactors(null);
        assertThat(draftFactor.getDraftFactors()).isNull();
    }
}
