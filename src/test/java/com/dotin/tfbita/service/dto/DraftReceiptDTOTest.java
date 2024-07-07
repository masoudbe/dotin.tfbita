package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftReceiptDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftReceiptDTO.class);
        DraftReceiptDTO draftReceiptDTO1 = new DraftReceiptDTO();
        draftReceiptDTO1.setId(1L);
        DraftReceiptDTO draftReceiptDTO2 = new DraftReceiptDTO();
        assertThat(draftReceiptDTO1).isNotEqualTo(draftReceiptDTO2);
        draftReceiptDTO2.setId(draftReceiptDTO1.getId());
        assertThat(draftReceiptDTO1).isEqualTo(draftReceiptDTO2);
        draftReceiptDTO2.setId(2L);
        assertThat(draftReceiptDTO1).isNotEqualTo(draftReceiptDTO2);
        draftReceiptDTO1.setId(null);
        assertThat(draftReceiptDTO1).isNotEqualTo(draftReceiptDTO2);
    }
}
