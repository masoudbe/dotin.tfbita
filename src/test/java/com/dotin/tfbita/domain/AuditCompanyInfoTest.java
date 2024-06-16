package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AuditCompanyInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditCompanyInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditCompanyInfo.class);
        AuditCompanyInfo auditCompanyInfo1 = getAuditCompanyInfoSample1();
        AuditCompanyInfo auditCompanyInfo2 = new AuditCompanyInfo();
        assertThat(auditCompanyInfo1).isNotEqualTo(auditCompanyInfo2);

        auditCompanyInfo2.setId(auditCompanyInfo1.getId());
        assertThat(auditCompanyInfo1).isEqualTo(auditCompanyInfo2);

        auditCompanyInfo2 = getAuditCompanyInfoSample2();
        assertThat(auditCompanyInfo1).isNotEqualTo(auditCompanyInfo2);
    }

    @Test
    void auditCompanyInfoTest() throws Exception {
        AuditCompanyInfo auditCompanyInfo = getAuditCompanyInfoRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        auditCompanyInfo.setAuditCompanyInfo(draftBack);
        assertThat(auditCompanyInfo.getAuditCompanyInfo()).isEqualTo(draftBack);

        auditCompanyInfo.auditCompanyInfo(null);
        assertThat(auditCompanyInfo.getAuditCompanyInfo()).isNull();
    }
}
