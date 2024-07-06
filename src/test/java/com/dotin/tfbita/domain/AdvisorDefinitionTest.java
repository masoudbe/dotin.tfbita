package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AdditionalBrokerInformationTestSamples.*;
import static com.dotin.tfbita.domain.AdvisorDefinitionDepositTestSamples.*;
import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;
import static com.dotin.tfbita.domain.SwiftBicTestSamples.*;
import static com.dotin.tfbita.domain.TransferMethodManagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
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
    void additionalBrokerInformationTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        AdditionalBrokerInformation additionalBrokerInformationBack = getAdditionalBrokerInformationRandomSampleGenerator();

        advisorDefinition.setAdditionalBrokerInformation(additionalBrokerInformationBack);
        assertThat(advisorDefinition.getAdditionalBrokerInformation()).isEqualTo(additionalBrokerInformationBack);

        advisorDefinition.additionalBrokerInformation(null);
        assertThat(advisorDefinition.getAdditionalBrokerInformation()).isNull();
    }

    @Test
    void advisorDepositTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        AdvisorDefinitionDeposit advisorDefinitionDepositBack = getAdvisorDefinitionDepositRandomSampleGenerator();

        advisorDefinition.addAdvisorDeposit(advisorDefinitionDepositBack);
        assertThat(advisorDefinition.getAdvisorDeposits()).containsOnly(advisorDefinitionDepositBack);
        assertThat(advisorDefinitionDepositBack.getAdvisorDefinition()).isEqualTo(advisorDefinition);

        advisorDefinition.removeAdvisorDeposit(advisorDefinitionDepositBack);
        assertThat(advisorDefinition.getAdvisorDeposits()).doesNotContain(advisorDefinitionDepositBack);
        assertThat(advisorDefinitionDepositBack.getAdvisorDefinition()).isNull();

        advisorDefinition.advisorDeposits(new HashSet<>(Set.of(advisorDefinitionDepositBack)));
        assertThat(advisorDefinition.getAdvisorDeposits()).containsOnly(advisorDefinitionDepositBack);
        assertThat(advisorDefinitionDepositBack.getAdvisorDefinition()).isEqualTo(advisorDefinition);

        advisorDefinition.setAdvisorDeposits(new HashSet<>());
        assertThat(advisorDefinition.getAdvisorDeposits()).doesNotContain(advisorDefinitionDepositBack);
        assertThat(advisorDefinitionDepositBack.getAdvisorDefinition()).isNull();
    }

    @Test
    void defaultVostroDepositTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        AdvisorDefinitionDeposit advisorDefinitionDepositBack = getAdvisorDefinitionDepositRandomSampleGenerator();

        advisorDefinition.setDefaultVostroDeposit(advisorDefinitionDepositBack);
        assertThat(advisorDefinition.getDefaultVostroDeposit()).isEqualTo(advisorDefinitionDepositBack);

        advisorDefinition.defaultVostroDeposit(null);
        assertThat(advisorDefinition.getDefaultVostroDeposit()).isNull();
    }

    @Test
    void defaultNostroDepositTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        AdvisorDefinitionDeposit advisorDefinitionDepositBack = getAdvisorDefinitionDepositRandomSampleGenerator();

        advisorDefinition.setDefaultNostroDeposit(advisorDefinitionDepositBack);
        assertThat(advisorDefinition.getDefaultNostroDeposit()).isEqualTo(advisorDefinitionDepositBack);

        advisorDefinition.defaultNostroDeposit(null);
        assertThat(advisorDefinition.getDefaultNostroDeposit()).isNull();
    }

    @Test
    void receiveMethodTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        TransferMethodManagement transferMethodManagementBack = getTransferMethodManagementRandomSampleGenerator();

        advisorDefinition.setReceiveMethod(transferMethodManagementBack);
        assertThat(advisorDefinition.getReceiveMethod()).isEqualTo(transferMethodManagementBack);

        advisorDefinition.receiveMethod(null);
        assertThat(advisorDefinition.getReceiveMethod()).isNull();
    }

    @Test
    void payMethodTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        TransferMethodManagement transferMethodManagementBack = getTransferMethodManagementRandomSampleGenerator();

        advisorDefinition.setPayMethod(transferMethodManagementBack);
        assertThat(advisorDefinition.getPayMethod()).isEqualTo(transferMethodManagementBack);

        advisorDefinition.payMethod(null);
        assertThat(advisorDefinition.getPayMethod()).isNull();
    }

    @Test
    void swiftBicTest() {
        AdvisorDefinition advisorDefinition = getAdvisorDefinitionRandomSampleGenerator();
        SwiftBic swiftBicBack = getSwiftBicRandomSampleGenerator();

        advisorDefinition.setSwiftBic(swiftBicBack);
        assertThat(advisorDefinition.getSwiftBic()).isEqualTo(swiftBicBack);

        advisorDefinition.swiftBic(null);
        assertThat(advisorDefinition.getSwiftBic()).isNull();
    }
}
