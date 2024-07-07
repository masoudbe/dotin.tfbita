package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AdditionalBrokerInformationTestSamples.*;
import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdditionalBrokerInformationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalBrokerInformation.class);
        AdditionalBrokerInformation additionalBrokerInformation1 = getAdditionalBrokerInformationSample1();
        AdditionalBrokerInformation additionalBrokerInformation2 = new AdditionalBrokerInformation();
        assertThat(additionalBrokerInformation1).isNotEqualTo(additionalBrokerInformation2);

        additionalBrokerInformation2.setId(additionalBrokerInformation1.getId());
        assertThat(additionalBrokerInformation1).isEqualTo(additionalBrokerInformation2);

        additionalBrokerInformation2 = getAdditionalBrokerInformationSample2();
        assertThat(additionalBrokerInformation1).isNotEqualTo(additionalBrokerInformation2);
    }

    @Test
    void advisorDefinitionTest() {
        AdditionalBrokerInformation additionalBrokerInformation = getAdditionalBrokerInformationRandomSampleGenerator();
        AdvisorDefinition advisorDefinitionBack = getAdvisorDefinitionRandomSampleGenerator();

        additionalBrokerInformation.setAdvisorDefinition(advisorDefinitionBack);
        assertThat(additionalBrokerInformation.getAdvisorDefinition()).isEqualTo(advisorDefinitionBack);
        assertThat(advisorDefinitionBack.getAdditionalBrokerInformation()).isEqualTo(additionalBrokerInformation);

        additionalBrokerInformation.advisorDefinition(null);
        assertThat(additionalBrokerInformation.getAdvisorDefinition()).isNull();
        assertThat(advisorDefinitionBack.getAdditionalBrokerInformation()).isNull();
    }
}
