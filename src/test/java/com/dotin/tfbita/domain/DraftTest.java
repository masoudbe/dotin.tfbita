package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;
import static com.dotin.tfbita.domain.AuditCompanyInfoTestSamples.*;
import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CurrencyExchangeInfoTestSamples.*;
import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static com.dotin.tfbita.domain.DraftAccountInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftCustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerTestSamples.*;
import static com.dotin.tfbita.domain.DraftExtendTestSamples.*;
import static com.dotin.tfbita.domain.DraftFactorTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftStatusInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftTaxTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeTestSamples.*;
import static com.dotin.tfbita.domain.DraftUsedAssuranceTestSamples.*;
import static com.dotin.tfbita.domain.InsuranceCompanyInfoTestSamples.*;
import static com.dotin.tfbita.domain.LongValueTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.ServiceTariffTestSamples.*;
import static com.dotin.tfbita.domain.StringValueTestSamples.*;
import static com.dotin.tfbita.domain.SuggestedSanctionInfoTestSamples.*;
import static com.dotin.tfbita.domain.TransportationTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DraftTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Draft.class);
        Draft draft1 = getDraftSample1();
        Draft draft2 = new Draft();
        assertThat(draft1).isNotEqualTo(draft2);

        draft2.setId(draft1.getId());
        assertThat(draft1).isEqualTo(draft2);

        draft2 = getDraftSample2();
        assertThat(draft1).isNotEqualTo(draft2);
    }

    @Test
    void receiptsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        draft.addReceipts(draftReceiptBack);
        assertThat(draft.getReceipts()).containsOnly(draftReceiptBack);
        assertThat(draftReceiptBack.getDraft()).isEqualTo(draft);

        draft.removeReceipts(draftReceiptBack);
        assertThat(draft.getReceipts()).doesNotContain(draftReceiptBack);
        assertThat(draftReceiptBack.getDraft()).isNull();

        draft.receipts(new HashSet<>(Set.of(draftReceiptBack)));
        assertThat(draft.getReceipts()).containsOnly(draftReceiptBack);
        assertThat(draftReceiptBack.getDraft()).isEqualTo(draft);

        draft.setReceipts(new HashSet<>());
        assertThat(draft.getReceipts()).doesNotContain(draftReceiptBack);
        assertThat(draftReceiptBack.getDraft()).isNull();
    }

    @Test
    void taxesTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftTax draftTaxBack = getDraftTaxRandomSampleGenerator();

        draft.addTaxes(draftTaxBack);
        assertThat(draft.getTaxes()).containsOnly(draftTaxBack);
        assertThat(draftTaxBack.getDraft()).isEqualTo(draft);

        draft.removeTaxes(draftTaxBack);
        assertThat(draft.getTaxes()).doesNotContain(draftTaxBack);
        assertThat(draftTaxBack.getDraft()).isNull();

        draft.taxes(new HashSet<>(Set.of(draftTaxBack)));
        assertThat(draft.getTaxes()).containsOnly(draftTaxBack);
        assertThat(draftTaxBack.getDraft()).isEqualTo(draft);

        draft.setTaxes(new HashSet<>());
        assertThat(draft.getTaxes()).doesNotContain(draftTaxBack);
        assertThat(draftTaxBack.getDraft()).isNull();
    }

    @Test
    void extensionsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftExtend draftExtendBack = getDraftExtendRandomSampleGenerator();

        draft.addExtensions(draftExtendBack);
        assertThat(draft.getExtensions()).containsOnly(draftExtendBack);
        assertThat(draftExtendBack.getDraftField()).isEqualTo(draft);

        draft.removeExtensions(draftExtendBack);
        assertThat(draft.getExtensions()).doesNotContain(draftExtendBack);
        assertThat(draftExtendBack.getDraftField()).isNull();

        draft.extensions(new HashSet<>(Set.of(draftExtendBack)));
        assertThat(draft.getExtensions()).containsOnly(draftExtendBack);
        assertThat(draftExtendBack.getDraftField()).isEqualTo(draft);

        draft.setExtensions(new HashSet<>());
        assertThat(draft.getExtensions()).doesNotContain(draftExtendBack);
        assertThat(draftExtendBack.getDraftField()).isNull();
    }

    @Test
    void draftFactorsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftFactor draftFactorBack = getDraftFactorRandomSampleGenerator();

        draft.addDraftFactors(draftFactorBack);
        assertThat(draft.getDraftFactors()).containsOnly(draftFactorBack);
        assertThat(draftFactorBack.getDraft()).isEqualTo(draft);

        draft.removeDraftFactors(draftFactorBack);
        assertThat(draft.getDraftFactors()).doesNotContain(draftFactorBack);
        assertThat(draftFactorBack.getDraft()).isNull();

        draft.draftFactors(new HashSet<>(Set.of(draftFactorBack)));
        assertThat(draft.getDraftFactors()).containsOnly(draftFactorBack);
        assertThat(draftFactorBack.getDraft()).isEqualTo(draft);

        draft.setDraftFactors(new HashSet<>());
        assertThat(draft.getDraftFactors()).doesNotContain(draftFactorBack);
        assertThat(draftFactorBack.getDraft()).isNull();
    }

    @Test
    void usedAssurancesTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftUsedAssurance draftUsedAssuranceBack = getDraftUsedAssuranceRandomSampleGenerator();

        draft.addUsedAssurances(draftUsedAssuranceBack);
        assertThat(draft.getUsedAssurances()).containsOnly(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getDraft()).isEqualTo(draft);

        draft.removeUsedAssurances(draftUsedAssuranceBack);
        assertThat(draft.getUsedAssurances()).doesNotContain(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getDraft()).isNull();

        draft.usedAssurances(new HashSet<>(Set.of(draftUsedAssuranceBack)));
        assertThat(draft.getUsedAssurances()).containsOnly(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getDraft()).isEqualTo(draft);

        draft.setUsedAssurances(new HashSet<>());
        assertThat(draft.getUsedAssurances()).doesNotContain(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getDraft()).isNull();
    }

    @Test
    void draftJustificationsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftCustomJustification draftCustomJustificationBack = getDraftCustomJustificationRandomSampleGenerator();

        draft.addDraftJustifications(draftCustomJustificationBack);
        assertThat(draft.getDraftJustifications()).containsOnly(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraft()).isEqualTo(draft);

        draft.removeDraftJustifications(draftCustomJustificationBack);
        assertThat(draft.getDraftJustifications()).doesNotContain(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraft()).isNull();

        draft.draftJustifications(new HashSet<>(Set.of(draftCustomJustificationBack)));
        assertThat(draft.getDraftJustifications()).containsOnly(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraft()).isEqualTo(draft);

        draft.setDraftJustifications(new HashSet<>());
        assertThat(draft.getDraftJustifications()).doesNotContain(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraft()).isNull();
    }

    @Test
    void chargedExchangeBrokerTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setChargedExchangeBroker(categoryElementBack);
        assertThat(draft.getChargedExchangeBroker()).isEqualTo(categoryElementBack);

        draft.chargedExchangeBroker(null);
        assertThat(draft.getChargedExchangeBroker()).isNull();
    }

    @Test
    void insuranceLetterTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setInsuranceLetterType(categoryElementBack);
        assertThat(draft.getInsuranceLetterType()).isEqualTo(categoryElementBack);

        draft.insuranceLetterType(null);
        assertThat(draft.getInsuranceLetterType()).isNull();
    }

    @Test
    void advisorDepositTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setAdvisorDepositType(categoryElementBack);
        assertThat(draft.getAdvisorDepositType()).isEqualTo(categoryElementBack);

        draft.advisorDepositType(null);
        assertThat(draft.getAdvisorDepositType()).isNull();
    }

    @Test
    void interfaceAdvisorDepositTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setInterfaceAdvisorDepositType(categoryElementBack);
        assertThat(draft.getInterfaceAdvisorDepositType()).isEqualTo(categoryElementBack);

        draft.interfaceAdvisorDepositType(null);
        assertThat(draft.getInterfaceAdvisorDepositType()).isNull();
    }

    @Test
    void coveringAdvisorDepositTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setCoveringAdvisorDepositType(categoryElementBack);
        assertThat(draft.getCoveringAdvisorDepositType()).isEqualTo(categoryElementBack);

        draft.coveringAdvisorDepositType(null);
        assertThat(draft.getCoveringAdvisorDepositType()).isNull();
    }

    @Test
    void impartTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setImpartType(categoryElementBack);
        assertThat(draft.getImpartType()).isEqualTo(categoryElementBack);

        draft.impartType(null);
        assertThat(draft.getImpartType()).isNull();
    }

    @Test
    void dealTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setDealType(categoryElementBack);
        assertThat(draft.getDealType()).isEqualTo(categoryElementBack);

        draft.dealType(null);
        assertThat(draft.getDealType()).isNull();
    }

    @Test
    void transportVehicleTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setTransportVehicleType(categoryElementBack);
        assertThat(draft.getTransportVehicleType()).isEqualTo(categoryElementBack);

        draft.transportVehicleType(null);
        assertThat(draft.getTransportVehicleType()).isNull();
    }

    @Test
    void freightLetterTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setFreightLetterType(categoryElementBack);
        assertThat(draft.getFreightLetterType()).isEqualTo(categoryElementBack);

        draft.freightLetterType(null);
        assertThat(draft.getFreightLetterType()).isNull();
    }

    @Test
    void actionCodeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setActionCode(categoryElementBack);
        assertThat(draft.getActionCode()).isEqualTo(categoryElementBack);

        draft.actionCode(null);
        assertThat(draft.getActionCode()).isNull();
    }

    @Test
    void ownershipCodeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setOwnershipCode(categoryElementBack);
        assertThat(draft.getOwnershipCode()).isEqualTo(categoryElementBack);

        draft.ownershipCode(null);
        assertThat(draft.getOwnershipCode()).isNull();
    }

    @Test
    void currencyContainerPlaceTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setCurrencyContainerPlace(categoryElementBack);
        assertThat(draft.getCurrencyContainerPlace()).isEqualTo(categoryElementBack);

        draft.currencyContainerPlace(null);
        assertThat(draft.getCurrencyContainerPlace()).isNull();
    }

    @Test
    void paymentTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setPaymentType(categoryElementBack);
        assertThat(draft.getPaymentType()).isEqualTo(categoryElementBack);

        draft.paymentType(null);
        assertThat(draft.getPaymentType()).isNull();
    }

    @Test
    void draftSourceTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draft.setDraftSource(categoryElementBack);
        assertThat(draft.getDraftSource()).isEqualTo(categoryElementBack);

        draft.draftSource(null);
        assertThat(draft.getDraftSource()).isNull();
    }

    @Test
    void loadSwitchPlaceTest() {
        Draft draft = getDraftRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        draft.setLoadSwitchPlace(customBack);
        assertThat(draft.getLoadSwitchPlace()).isEqualTo(customBack);

        draft.loadSwitchPlace(null);
        assertThat(draft.getLoadSwitchPlace()).isNull();
    }

    @Test
    void draftTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftType draftTypeBack = getDraftTypeRandomSampleGenerator();

        draft.setDraftType(draftTypeBack);
        assertThat(draft.getDraftType()).isEqualTo(draftTypeBack);

        draft.draftType(null);
        assertThat(draft.getDraftType()).isNull();
    }

    @Test
    void statusInfoTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftStatusInfo draftStatusInfoBack = getDraftStatusInfoRandomSampleGenerator();

        draft.setStatusInfo(draftStatusInfoBack);
        assertThat(draft.getStatusInfo()).isEqualTo(draftStatusInfoBack);

        draft.statusInfo(null);
        assertThat(draft.getStatusInfo()).isNull();
    }

    @Test
    void insuranceCompanyInfoTest() {
        Draft draft = getDraftRandomSampleGenerator();
        InsuranceCompanyInfo insuranceCompanyInfoBack = getInsuranceCompanyInfoRandomSampleGenerator();

        draft.setInsuranceCompanyInfo(insuranceCompanyInfoBack);
        assertThat(draft.getInsuranceCompanyInfo()).isEqualTo(insuranceCompanyInfoBack);

        draft.insuranceCompanyInfo(null);
        assertThat(draft.getInsuranceCompanyInfo()).isNull();
    }

    @Test
    void advisingBankTest() {
        Draft draft = getDraftRandomSampleGenerator();
        AdvisorDefinition advisorDefinitionBack = getAdvisorDefinitionRandomSampleGenerator();

        draft.setAdvisingBank(advisorDefinitionBack);
        assertThat(draft.getAdvisingBank()).isEqualTo(advisorDefinitionBack);

        draft.advisingBank(null);
        assertThat(draft.getAdvisingBank()).isNull();
    }

    @Test
    void interfaceAdvisingBankTest() {
        Draft draft = getDraftRandomSampleGenerator();
        AdvisorDefinition advisorDefinitionBack = getAdvisorDefinitionRandomSampleGenerator();

        draft.setInterfaceAdvisingBank(advisorDefinitionBack);
        assertThat(draft.getInterfaceAdvisingBank()).isEqualTo(advisorDefinitionBack);

        draft.interfaceAdvisingBank(null);
        assertThat(draft.getInterfaceAdvisingBank()).isNull();
    }

    @Test
    void coveringBankTest() {
        Draft draft = getDraftRandomSampleGenerator();
        AdvisorDefinition advisorDefinitionBack = getAdvisorDefinitionRandomSampleGenerator();

        draft.setCoveringBank(advisorDefinitionBack);
        assertThat(draft.getCoveringBank()).isEqualTo(advisorDefinitionBack);

        draft.coveringBank(null);
        assertThat(draft.getCoveringBank()).isNull();
    }

    @Test
    void auditCompanyInfoTest() {
        Draft draft = getDraftRandomSampleGenerator();
        AuditCompanyInfo auditCompanyInfoBack = getAuditCompanyInfoRandomSampleGenerator();

        draft.setAuditCompanyInfo(auditCompanyInfoBack);
        assertThat(draft.getAuditCompanyInfo()).isEqualTo(auditCompanyInfoBack);

        draft.auditCompanyInfo(null);
        assertThat(draft.getAuditCompanyInfo()).isNull();
    }

    @Test
    void transportTypeTest() {
        Draft draft = getDraftRandomSampleGenerator();
        TransportationType transportationTypeBack = getTransportationTypeRandomSampleGenerator();

        draft.setTransportType(transportationTypeBack);
        assertThat(draft.getTransportType()).isEqualTo(transportationTypeBack);

        draft.transportType(null);
        assertThat(draft.getTransportType()).isNull();
    }

    @Test
    void currencyExchangeInfoTest() {
        Draft draft = getDraftRandomSampleGenerator();
        CurrencyExchangeInfo currencyExchangeInfoBack = getCurrencyExchangeInfoRandomSampleGenerator();

        draft.setCurrencyExchangeInfo(currencyExchangeInfoBack);
        assertThat(draft.getCurrencyExchangeInfo()).isEqualTo(currencyExchangeInfoBack);

        draft.currencyExchangeInfo(null);
        assertThat(draft.getCurrencyExchangeInfo()).isNull();
    }

    @Test
    void accountInfoTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftAccountInfo draftAccountInfoBack = getDraftAccountInfoRandomSampleGenerator();

        draft.setAccountInfo(draftAccountInfoBack);
        assertThat(draft.getAccountInfo()).isEqualTo(draftAccountInfoBack);

        draft.accountInfo(null);
        assertThat(draft.getAccountInfo()).isNull();
    }

    @Test
    void destinationCustomCompaniesTest() {
        Draft draft = getDraftRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        draft.setDestinationCustomCompanies(customBack);
        assertThat(draft.getDestinationCustomCompanies()).isEqualTo(customBack);

        draft.destinationCustomCompanies(null);
        assertThat(draft.getDestinationCustomCompanies()).isNull();
    }

    @Test
    void sourceCustomCompaniesTest() {
        Draft draft = getDraftRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        draft.setSourceCustomCompanies(customBack);
        assertThat(draft.getSourceCustomCompanies()).isEqualTo(customBack);

        draft.sourceCustomCompanies(null);
        assertThat(draft.getSourceCustomCompanies()).isNull();
    }

    @Test
    void servicesTest() {
        Draft draft = getDraftRandomSampleGenerator();
        ServiceTariff serviceTariffBack = getServiceTariffRandomSampleGenerator();

        draft.addServices(serviceTariffBack);
        assertThat(draft.getServices()).containsOnly(serviceTariffBack);

        draft.removeServices(serviceTariffBack);
        assertThat(draft.getServices()).doesNotContain(serviceTariffBack);

        draft.services(new HashSet<>(Set.of(serviceTariffBack)));
        assertThat(draft.getServices()).containsOnly(serviceTariffBack);

        draft.setServices(new HashSet<>());
        assertThat(draft.getServices()).doesNotContain(serviceTariffBack);
    }

    @Test
    void productsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        draft.addProducts(productBack);
        assertThat(draft.getProducts()).containsOnly(productBack);

        draft.removeProducts(productBack);
        assertThat(draft.getProducts()).doesNotContain(productBack);

        draft.products(new HashSet<>(Set.of(productBack)));
        assertThat(draft.getProducts()).containsOnly(productBack);

        draft.setProducts(new HashSet<>());
        assertThat(draft.getProducts()).doesNotContain(productBack);
    }

    @Test
    void sanctionSerialsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        StringValue stringValueBack = getStringValueRandomSampleGenerator();

        draft.addSanctionSerials(stringValueBack);
        assertThat(draft.getSanctionSerials()).containsOnly(stringValueBack);

        draft.removeSanctionSerials(stringValueBack);
        assertThat(draft.getSanctionSerials()).doesNotContain(stringValueBack);

        draft.sanctionSerials(new HashSet<>(Set.of(stringValueBack)));
        assertThat(draft.getSanctionSerials()).containsOnly(stringValueBack);

        draft.setSanctionSerials(new HashSet<>());
        assertThat(draft.getSanctionSerials()).doesNotContain(stringValueBack);
    }

    @Test
    void customerNumbersTest() {
        Draft draft = getDraftRandomSampleGenerator();
        LongValue longValueBack = getLongValueRandomSampleGenerator();

        draft.addCustomerNumbers(longValueBack);
        assertThat(draft.getCustomerNumbers()).containsOnly(longValueBack);

        draft.removeCustomerNumbers(longValueBack);
        assertThat(draft.getCustomerNumbers()).doesNotContain(longValueBack);

        draft.customerNumbers(new HashSet<>(Set.of(longValueBack)));
        assertThat(draft.getCustomerNumbers()).containsOnly(longValueBack);

        draft.setCustomerNumbers(new HashSet<>());
        assertThat(draft.getCustomerNumbers()).doesNotContain(longValueBack);
    }

    @Test
    void suggestedSanctionsTest() {
        Draft draft = getDraftRandomSampleGenerator();
        SuggestedSanctionInfo suggestedSanctionInfoBack = getSuggestedSanctionInfoRandomSampleGenerator();

        draft.addSuggestedSanctions(suggestedSanctionInfoBack);
        assertThat(draft.getSuggestedSanctions()).containsOnly(suggestedSanctionInfoBack);

        draft.removeSuggestedSanctions(suggestedSanctionInfoBack);
        assertThat(draft.getSuggestedSanctions()).doesNotContain(suggestedSanctionInfoBack);

        draft.suggestedSanctions(new HashSet<>(Set.of(suggestedSanctionInfoBack)));
        assertThat(draft.getSuggestedSanctions()).containsOnly(suggestedSanctionInfoBack);

        draft.setSuggestedSanctions(new HashSet<>());
        assertThat(draft.getSuggestedSanctions()).doesNotContain(suggestedSanctionInfoBack);
    }

    @Test
    void documentTransactionContainerTest() {
        Draft draft = getDraftRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        draft.addDocumentTransactionContainer(draftDocumentTransactionContainerBack);
        assertThat(draft.getDocumentTransactionContainers()).containsOnly(draftDocumentTransactionContainerBack);

        draft.removeDocumentTransactionContainer(draftDocumentTransactionContainerBack);
        assertThat(draft.getDocumentTransactionContainers()).doesNotContain(draftDocumentTransactionContainerBack);

        draft.documentTransactionContainers(new HashSet<>(Set.of(draftDocumentTransactionContainerBack)));
        assertThat(draft.getDocumentTransactionContainers()).containsOnly(draftDocumentTransactionContainerBack);

        draft.setDocumentTransactionContainers(new HashSet<>());
        assertThat(draft.getDocumentTransactionContainers()).doesNotContain(draftDocumentTransactionContainerBack);
    }
}
