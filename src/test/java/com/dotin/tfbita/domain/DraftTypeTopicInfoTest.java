package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTypeTopicInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTypeTopicInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTypeTopicInfo.class);
        DraftTypeTopicInfo draftTypeTopicInfo1 = getDraftTypeTopicInfoSample1();
        DraftTypeTopicInfo draftTypeTopicInfo2 = new DraftTypeTopicInfo();
        assertThat(draftTypeTopicInfo1).isNotEqualTo(draftTypeTopicInfo2);

        draftTypeTopicInfo2.setId(draftTypeTopicInfo1.getId());
        assertThat(draftTypeTopicInfo1).isEqualTo(draftTypeTopicInfo2);

        draftTypeTopicInfo2 = getDraftTypeTopicInfoSample2();
        assertThat(draftTypeTopicInfo1).isNotEqualTo(draftTypeTopicInfo2);
    }
}
