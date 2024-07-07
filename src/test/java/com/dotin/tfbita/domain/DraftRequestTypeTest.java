package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftCertificateTypeTestSamples.*;
import static com.dotin.tfbita.domain.DraftRequestTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DraftRequestTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftRequestType.class);
        DraftRequestType draftRequestType1 = getDraftRequestTypeSample1();
        DraftRequestType draftRequestType2 = new DraftRequestType();
        assertThat(draftRequestType1).isNotEqualTo(draftRequestType2);

        draftRequestType2.setId(draftRequestType1.getId());
        assertThat(draftRequestType1).isEqualTo(draftRequestType2);

        draftRequestType2 = getDraftRequestTypeSample2();
        assertThat(draftRequestType1).isNotEqualTo(draftRequestType2);
    }

    @Test
    void hashCodeVerifier() {
        DraftRequestType draftRequestType = new DraftRequestType();
        assertThat(draftRequestType.hashCode()).isZero();

        DraftRequestType draftRequestType1 = getDraftRequestTypeSample1();
        draftRequestType.setId(draftRequestType1.getId());
        assertThat(draftRequestType).hasSameHashCodeAs(draftRequestType1);
    }

    @Test
    void certificateTypeListTest() {
        DraftRequestType draftRequestType = getDraftRequestTypeRandomSampleGenerator();
        DraftCertificateType draftCertificateTypeBack = getDraftCertificateTypeRandomSampleGenerator();

        draftRequestType.addCertificateTypeList(draftCertificateTypeBack);
        assertThat(draftRequestType.getCertificateTypeLists()).containsOnly(draftCertificateTypeBack);
        assertThat(draftCertificateTypeBack.getDraftRequestType()).isEqualTo(draftRequestType);

        draftRequestType.removeCertificateTypeList(draftCertificateTypeBack);
        assertThat(draftRequestType.getCertificateTypeLists()).doesNotContain(draftCertificateTypeBack);
        assertThat(draftCertificateTypeBack.getDraftRequestType()).isNull();

        draftRequestType.certificateTypeLists(new HashSet<>(Set.of(draftCertificateTypeBack)));
        assertThat(draftRequestType.getCertificateTypeLists()).containsOnly(draftCertificateTypeBack);
        assertThat(draftCertificateTypeBack.getDraftRequestType()).isEqualTo(draftRequestType);

        draftRequestType.setCertificateTypeLists(new HashSet<>());
        assertThat(draftRequestType.getCertificateTypeLists()).doesNotContain(draftCertificateTypeBack);
        assertThat(draftCertificateTypeBack.getDraftRequestType()).isNull();
    }
}
