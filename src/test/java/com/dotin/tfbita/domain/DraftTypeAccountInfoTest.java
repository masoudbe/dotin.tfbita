package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTypeAccountInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTypeAccountInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTypeAccountInfo.class);
        DraftTypeAccountInfo draftTypeAccountInfo1 = getDraftTypeAccountInfoSample1();
        DraftTypeAccountInfo draftTypeAccountInfo2 = new DraftTypeAccountInfo();
        assertThat(draftTypeAccountInfo1).isNotEqualTo(draftTypeAccountInfo2);

        draftTypeAccountInfo2.setId(draftTypeAccountInfo1.getId());
        assertThat(draftTypeAccountInfo1).isEqualTo(draftTypeAccountInfo2);

        draftTypeAccountInfo2 = getDraftTypeAccountInfoSample2();
        assertThat(draftTypeAccountInfo1).isNotEqualTo(draftTypeAccountInfo2);
    }
}
