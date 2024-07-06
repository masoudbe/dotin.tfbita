package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.SuggestedSanctionInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SuggestedSanctionInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuggestedSanctionInfo.class);
        SuggestedSanctionInfo suggestedSanctionInfo1 = getSuggestedSanctionInfoSample1();
        SuggestedSanctionInfo suggestedSanctionInfo2 = new SuggestedSanctionInfo();
        assertThat(suggestedSanctionInfo1).isNotEqualTo(suggestedSanctionInfo2);

        suggestedSanctionInfo2.setId(suggestedSanctionInfo1.getId());
        assertThat(suggestedSanctionInfo1).isEqualTo(suggestedSanctionInfo2);

        suggestedSanctionInfo2 = getSuggestedSanctionInfoSample2();
        assertThat(suggestedSanctionInfo1).isNotEqualTo(suggestedSanctionInfo2);
    }

    @Test
    void draftTest() {
        SuggestedSanctionInfo suggestedSanctionInfo = getSuggestedSanctionInfoRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        suggestedSanctionInfo.addDraft(draftBack);
        assertThat(suggestedSanctionInfo.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getSuggestedSanctions()).containsOnly(suggestedSanctionInfo);

        suggestedSanctionInfo.removeDraft(draftBack);
        assertThat(suggestedSanctionInfo.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getSuggestedSanctions()).doesNotContain(suggestedSanctionInfo);

        suggestedSanctionInfo.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(suggestedSanctionInfo.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getSuggestedSanctions()).containsOnly(suggestedSanctionInfo);

        suggestedSanctionInfo.setDrafts(new HashSet<>());
        assertThat(suggestedSanctionInfo.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getSuggestedSanctions()).doesNotContain(suggestedSanctionInfo);
    }
}
