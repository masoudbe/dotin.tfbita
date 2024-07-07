package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftReceiptDocumentTransactionContainerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftReceiptDocumentTransactionContainerDTO.class);
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO1 =
            new DraftReceiptDocumentTransactionContainerDTO();
        draftReceiptDocumentTransactionContainerDTO1.setId(1L);
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO2 =
            new DraftReceiptDocumentTransactionContainerDTO();
        assertThat(draftReceiptDocumentTransactionContainerDTO1).isNotEqualTo(draftReceiptDocumentTransactionContainerDTO2);
        draftReceiptDocumentTransactionContainerDTO2.setId(draftReceiptDocumentTransactionContainerDTO1.getId());
        assertThat(draftReceiptDocumentTransactionContainerDTO1).isEqualTo(draftReceiptDocumentTransactionContainerDTO2);
        draftReceiptDocumentTransactionContainerDTO2.setId(2L);
        assertThat(draftReceiptDocumentTransactionContainerDTO1).isNotEqualTo(draftReceiptDocumentTransactionContainerDTO2);
        draftReceiptDocumentTransactionContainerDTO1.setId(null);
        assertThat(draftReceiptDocumentTransactionContainerDTO1).isNotEqualTo(draftReceiptDocumentTransactionContainerDTO2);
    }
}
