package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTransactionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTransactionDTO.class);
        DocumentTransactionDTO documentTransactionDTO1 = new DocumentTransactionDTO();
        documentTransactionDTO1.setId(1L);
        DocumentTransactionDTO documentTransactionDTO2 = new DocumentTransactionDTO();
        assertThat(documentTransactionDTO1).isNotEqualTo(documentTransactionDTO2);
        documentTransactionDTO2.setId(documentTransactionDTO1.getId());
        assertThat(documentTransactionDTO1).isEqualTo(documentTransactionDTO2);
        documentTransactionDTO2.setId(2L);
        assertThat(documentTransactionDTO1).isNotEqualTo(documentTransactionDTO2);
        documentTransactionDTO1.setId(null);
        assertThat(documentTransactionDTO1).isNotEqualTo(documentTransactionDTO2);
    }
}
