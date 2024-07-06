package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TransferMethodManagementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransferMethodManagementDTO.class);
        TransferMethodManagementDTO transferMethodManagementDTO1 = new TransferMethodManagementDTO();
        transferMethodManagementDTO1.setId(1L);
        TransferMethodManagementDTO transferMethodManagementDTO2 = new TransferMethodManagementDTO();
        assertThat(transferMethodManagementDTO1).isNotEqualTo(transferMethodManagementDTO2);
        transferMethodManagementDTO2.setId(transferMethodManagementDTO1.getId());
        assertThat(transferMethodManagementDTO1).isEqualTo(transferMethodManagementDTO2);
        transferMethodManagementDTO2.setId(2L);
        assertThat(transferMethodManagementDTO1).isNotEqualTo(transferMethodManagementDTO2);
        transferMethodManagementDTO1.setId(null);
        assertThat(transferMethodManagementDTO1).isNotEqualTo(transferMethodManagementDTO2);
    }
}
