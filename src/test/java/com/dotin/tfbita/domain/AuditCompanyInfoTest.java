package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AuditCompanyInfoTestSamples.*;
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
}
