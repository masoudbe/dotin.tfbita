package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftAccountInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftAccountInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftAccountInfo.class);
        DraftAccountInfo draftAccountInfo1 = getDraftAccountInfoSample1();
        DraftAccountInfo draftAccountInfo2 = new DraftAccountInfo();
        assertThat(draftAccountInfo1).isNotEqualTo(draftAccountInfo2);

        draftAccountInfo2.setId(draftAccountInfo1.getId());
        assertThat(draftAccountInfo1).isEqualTo(draftAccountInfo2);

        draftAccountInfo2 = getDraftAccountInfoSample2();
        assertThat(draftAccountInfo1).isNotEqualTo(draftAccountInfo2);
    }
}
