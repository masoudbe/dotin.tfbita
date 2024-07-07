package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.LongValueTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LongValueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LongValue.class);
        LongValue longValue1 = getLongValueSample1();
        LongValue longValue2 = new LongValue();
        assertThat(longValue1).isNotEqualTo(longValue2);

        longValue2.setId(longValue1.getId());
        assertThat(longValue1).isEqualTo(longValue2);

        longValue2 = getLongValueSample2();
        assertThat(longValue1).isNotEqualTo(longValue2);
    }

    @Test
    void draftTest() {
        LongValue longValue = getLongValueRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        longValue.addDraft(draftBack);
        assertThat(longValue.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getCustomerNumbers()).containsOnly(longValue);

        longValue.removeDraft(draftBack);
        assertThat(longValue.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getCustomerNumbers()).doesNotContain(longValue);

        longValue.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(longValue.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getCustomerNumbers()).containsOnly(longValue);

        longValue.setDrafts(new HashSet<>());
        assertThat(longValue.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getCustomerNumbers()).doesNotContain(longValue);
    }
}
