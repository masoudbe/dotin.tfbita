package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AdvisorDefinitionDepositTestSamples.*;
import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;
import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdvisorDefinitionDepositTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdvisorDefinitionDeposit.class);
        AdvisorDefinitionDeposit advisorDefinitionDeposit1 = getAdvisorDefinitionDepositSample1();
        AdvisorDefinitionDeposit advisorDefinitionDeposit2 = new AdvisorDefinitionDeposit();
        assertThat(advisorDefinitionDeposit1).isNotEqualTo(advisorDefinitionDeposit2);

        advisorDefinitionDeposit2.setId(advisorDefinitionDeposit1.getId());
        assertThat(advisorDefinitionDeposit1).isEqualTo(advisorDefinitionDeposit2);

        advisorDefinitionDeposit2 = getAdvisorDefinitionDepositSample2();
        assertThat(advisorDefinitionDeposit1).isNotEqualTo(advisorDefinitionDeposit2);
    }

    @Test
    void depositTypeTest() {
        AdvisorDefinitionDeposit advisorDefinitionDeposit = getAdvisorDefinitionDepositRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        advisorDefinitionDeposit.setDepositType(categoryElementBack);
        assertThat(advisorDefinitionDeposit.getDepositType()).isEqualTo(categoryElementBack);

        advisorDefinitionDeposit.depositType(null);
        assertThat(advisorDefinitionDeposit.getDepositType()).isNull();
    }

    @Test
    void advisorDefinitionTest() {
        AdvisorDefinitionDeposit advisorDefinitionDeposit = getAdvisorDefinitionDepositRandomSampleGenerator();
        AdvisorDefinition advisorDefinitionBack = getAdvisorDefinitionRandomSampleGenerator();

        advisorDefinitionDeposit.setAdvisorDefinition(advisorDefinitionBack);
        assertThat(advisorDefinitionDeposit.getAdvisorDefinition()).isEqualTo(advisorDefinitionBack);

        advisorDefinitionDeposit.advisorDefinition(null);
        assertThat(advisorDefinitionDeposit.getAdvisorDefinition()).isNull();
    }
}
