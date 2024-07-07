package com.dotin.tfbita.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SuggestedSanctionInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuggestedSanctionInfoDTO.class);
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO1 = new SuggestedSanctionInfoDTO();
        suggestedSanctionInfoDTO1.setId(1L);
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO2 = new SuggestedSanctionInfoDTO();
        assertThat(suggestedSanctionInfoDTO1).isNotEqualTo(suggestedSanctionInfoDTO2);
        suggestedSanctionInfoDTO2.setId(suggestedSanctionInfoDTO1.getId());
        assertThat(suggestedSanctionInfoDTO1).isEqualTo(suggestedSanctionInfoDTO2);
        suggestedSanctionInfoDTO2.setId(2L);
        assertThat(suggestedSanctionInfoDTO1).isNotEqualTo(suggestedSanctionInfoDTO2);
        suggestedSanctionInfoDTO1.setId(null);
        assertThat(suggestedSanctionInfoDTO1).isNotEqualTo(suggestedSanctionInfoDTO2);
    }
}
