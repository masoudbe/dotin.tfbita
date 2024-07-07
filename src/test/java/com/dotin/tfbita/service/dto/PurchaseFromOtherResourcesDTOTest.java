package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseFromOtherResourcesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseFromOtherResourcesDTO.class);
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO1 = new PurchaseFromOtherResourcesDTO();
        purchaseFromOtherResourcesDTO1.setId(1L);
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO2 = new PurchaseFromOtherResourcesDTO();
        assertThat(purchaseFromOtherResourcesDTO1).isNotEqualTo(purchaseFromOtherResourcesDTO2);
        purchaseFromOtherResourcesDTO2.setId(purchaseFromOtherResourcesDTO1.getId());
        assertThat(purchaseFromOtherResourcesDTO1).isEqualTo(purchaseFromOtherResourcesDTO2);
        purchaseFromOtherResourcesDTO2.setId(2L);
        assertThat(purchaseFromOtherResourcesDTO1).isNotEqualTo(purchaseFromOtherResourcesDTO2);
        purchaseFromOtherResourcesDTO1.setId(null);
        assertThat(purchaseFromOtherResourcesDTO1).isNotEqualTo(purchaseFromOtherResourcesDTO2);
    }
}
