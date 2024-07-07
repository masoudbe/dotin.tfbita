package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftDocumentTransactionContainerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftDocumentTransactionContainerDTO.class);
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO1 = new DraftDocumentTransactionContainerDTO();
        draftDocumentTransactionContainerDTO1.setId(1L);
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO2 = new DraftDocumentTransactionContainerDTO();
        assertThat(draftDocumentTransactionContainerDTO1).isNotEqualTo(draftDocumentTransactionContainerDTO2);
        draftDocumentTransactionContainerDTO2.setId(draftDocumentTransactionContainerDTO1.getId());
        assertThat(draftDocumentTransactionContainerDTO1).isEqualTo(draftDocumentTransactionContainerDTO2);
        draftDocumentTransactionContainerDTO2.setId(2L);
        assertThat(draftDocumentTransactionContainerDTO1).isNotEqualTo(draftDocumentTransactionContainerDTO2);
        draftDocumentTransactionContainerDTO1.setId(null);
        assertThat(draftDocumentTransactionContainerDTO1).isNotEqualTo(draftDocumentTransactionContainerDTO2);
    }
}
