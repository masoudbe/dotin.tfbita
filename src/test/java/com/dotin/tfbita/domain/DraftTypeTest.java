package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftType.class);
        DraftType draftType1 = getDraftTypeSample1();
        DraftType draftType2 = new DraftType();
        assertThat(draftType1).isNotEqualTo(draftType2);

        draftType2.setId(draftType1.getId());
        assertThat(draftType1).isEqualTo(draftType2);

        draftType2 = getDraftTypeSample2();
        assertThat(draftType1).isNotEqualTo(draftType2);
    }

    @Test
    void draftTypeTest() throws Exception {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftType.setDraftType(draftBack);
        assertThat(draftType.getDraftType()).isEqualTo(draftBack);

        draftType.draftType(null);
        assertThat(draftType.getDraftType()).isNull();
    }
}
