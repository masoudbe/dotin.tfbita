package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdvisorDefinitionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdvisorDefinition.class);
        AdvisorDefinition advisorDefinition1 = getAdvisorDefinitionSample1();
        AdvisorDefinition advisorDefinition2 = new AdvisorDefinition();
        assertThat(advisorDefinition1).isNotEqualTo(advisorDefinition2);

        advisorDefinition2.setId(advisorDefinition1.getId());
        assertThat(advisorDefinition1).isEqualTo(advisorDefinition2);

        advisorDefinition2 = getAdvisorDefinitionSample2();
        assertThat(advisorDefinition1).isNotEqualTo(advisorDefinition2);
    }

    @Test
    void advisingBankTest() throws Exception {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        advisorDefinition.setAdvisingBank(draftBack);
        assertThat(advisorDefinition.getAdvisingBank()).isEqualTo(draftBack);

        advisorDefinition.advisingBank(null);
        assertThat(advisorDefinition.getAdvisingBank()).isNull();
    }

    @Test
    void interfaceAdvisingBankTest() throws Exception {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        advisorDefinition.setInterfaceAdvisingBank(draftBack);
        assertThat(advisorDefinition.getInterfaceAdvisingBank()).isEqualTo(draftBack);

        advisorDefinition.interfaceAdvisingBank(null);
        assertThat(advisorDefinition.getInterfaceAdvisingBank()).isNull();
    }

    @Test
    void coveringBankTest() throws Exception {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        advisorDefinition.setCoveringBank(draftBack);
        assertThat(advisorDefinition.getCoveringBank()).isEqualTo(draftBack);

        advisorDefinition.coveringBank(null);
        assertThat(advisorDefinition.getCoveringBank()).isNull();
    }
}
