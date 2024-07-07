package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftExtendTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftExtendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftExtend.class);
        DraftExtend draftExtend1 = getDraftExtendSample1();
        DraftExtend draftExtend2 = new DraftExtend();
        assertThat(draftExtend1).isNotEqualTo(draftExtend2);

        draftExtend2.setId(draftExtend1.getId());
        assertThat(draftExtend1).isEqualTo(draftExtend2);

        draftExtend2 = getDraftExtendSample2();
        assertThat(draftExtend1).isNotEqualTo(draftExtend2);
    }

    @Test
    void draftFieldTest() {
        DraftExtend draftExtend = getDraftExtendRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftExtend.setDraftField(draftBack);
        assertThat(draftExtend.getDraftField()).isEqualTo(draftBack);

        draftExtend.draftField(null);
        assertThat(draftExtend.getDraftField()).isNull();
    }
}
