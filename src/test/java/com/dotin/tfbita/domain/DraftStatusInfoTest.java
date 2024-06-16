package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftStatusInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftStatusInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftStatusInfo.class);
        DraftStatusInfo draftStatusInfo1 = getDraftStatusInfoSample1();
        DraftStatusInfo draftStatusInfo2 = new DraftStatusInfo();
        assertThat(draftStatusInfo1).isNotEqualTo(draftStatusInfo2);

        draftStatusInfo2.setId(draftStatusInfo1.getId());
        assertThat(draftStatusInfo1).isEqualTo(draftStatusInfo2);

        draftStatusInfo2 = getDraftStatusInfoSample2();
        assertThat(draftStatusInfo1).isNotEqualTo(draftStatusInfo2);
    }

    @Test
    void statusInfoTest() throws Exception {
        DraftStatusInfo draftStatusInfo = getDraftStatusInfoRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftStatusInfo.setStatusInfo(draftBack);
        assertThat(draftStatusInfo.getStatusInfo()).isEqualTo(draftBack);

        draftStatusInfo.statusInfo(null);
        assertThat(draftStatusInfo.getStatusInfo()).isNull();
    }
}
