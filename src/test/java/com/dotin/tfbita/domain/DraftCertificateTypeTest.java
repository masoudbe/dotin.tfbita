package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftCertificateTypeTestSamples.*;
import static com.dotin.tfbita.domain.DraftRequestTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftCertificateTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftCertificateType.class);
        DraftCertificateType draftCertificateType1 = getDraftCertificateTypeSample1();
        DraftCertificateType draftCertificateType2 = new DraftCertificateType();
        assertThat(draftCertificateType1).isNotEqualTo(draftCertificateType2);

        draftCertificateType2.setId(draftCertificateType1.getId());
        assertThat(draftCertificateType1).isEqualTo(draftCertificateType2);

        draftCertificateType2 = getDraftCertificateTypeSample2();
        assertThat(draftCertificateType1).isNotEqualTo(draftCertificateType2);
    }

    @Test
    void draftRequestTypeTest() {
        DraftCertificateType draftCertificateType = getDraftCertificateTypeRandomSampleGenerator();
        DraftRequestType draftRequestTypeBack = getDraftRequestTypeRandomSampleGenerator();

        draftCertificateType.setDraftRequestType(draftRequestTypeBack);
        assertThat(draftCertificateType.getDraftRequestType()).isEqualTo(draftRequestTypeBack);

        draftCertificateType.draftRequestType(null);
        assertThat(draftCertificateType.getDraftRequestType()).isNull();
    }
}
