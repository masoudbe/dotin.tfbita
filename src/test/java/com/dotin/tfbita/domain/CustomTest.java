package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CustomTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Custom.class);
        Custom custom1 = getCustomSample1();
        Custom custom2 = new Custom();
        assertThat(custom1).isNotEqualTo(custom2);

        custom2.setId(custom1.getId());
        assertThat(custom1).isEqualTo(custom2);

        custom2 = getCustomSample2();
        assertThat(custom1).isNotEqualTo(custom2);
    }

    @Test
    void draftTest() throws Exception {
        Custom custom = getCustomRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        custom.addDraft(draftBack);
        assertThat(custom.getDrafts()).containsOnly(draftBack);

        custom.removeDraft(draftBack);
        assertThat(custom.getDrafts()).doesNotContain(draftBack);

        custom.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(custom.getDrafts()).containsOnly(draftBack);

        custom.setDrafts(new HashSet<>());
        assertThat(custom.getDrafts()).doesNotContain(draftBack);
    }
}
