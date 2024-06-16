package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditCompanyInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditCompanyInfoDTO.class);
        AuditCompanyInfoDTO auditCompanyInfoDTO1 = new AuditCompanyInfoDTO();
        auditCompanyInfoDTO1.setId(1L);
        AuditCompanyInfoDTO auditCompanyInfoDTO2 = new AuditCompanyInfoDTO();
        assertThat(auditCompanyInfoDTO1).isNotEqualTo(auditCompanyInfoDTO2);
        auditCompanyInfoDTO2.setId(auditCompanyInfoDTO1.getId());
        assertThat(auditCompanyInfoDTO1).isEqualTo(auditCompanyInfoDTO2);
        auditCompanyInfoDTO2.setId(2L);
        assertThat(auditCompanyInfoDTO1).isNotEqualTo(auditCompanyInfoDTO2);
        auditCompanyInfoDTO1.setId(null);
        assertThat(auditCompanyInfoDTO1).isNotEqualTo(auditCompanyInfoDTO2);
    }
}
