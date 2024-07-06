package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.TransferMethodManagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TransferMethodManagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransferMethodManagement.class);
        TransferMethodManagement transferMethodManagement1 = getTransferMethodManagementSample1();
        TransferMethodManagement transferMethodManagement2 = new TransferMethodManagement();
        assertThat(transferMethodManagement1).isNotEqualTo(transferMethodManagement2);

        transferMethodManagement2.setId(transferMethodManagement1.getId());
        assertThat(transferMethodManagement1).isEqualTo(transferMethodManagement2);

        transferMethodManagement2 = getTransferMethodManagementSample2();
        assertThat(transferMethodManagement1).isNotEqualTo(transferMethodManagement2);
    }

    @Test
    void typeTest() {
        TransferMethodManagement transferMethodManagement = getTransferMethodManagementRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        transferMethodManagement.setType(categoryElementBack);
        assertThat(transferMethodManagement.getType()).isEqualTo(categoryElementBack);

        transferMethodManagement.type(null);
        assertThat(transferMethodManagement.getType()).isNull();
    }
}
