package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;
import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftStatusInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryElementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryElement.class);
        CategoryElement categoryElement1 = getCategoryElementSample1();
        CategoryElement categoryElement2 = new CategoryElement();
        assertThat(categoryElement1).isNotEqualTo(categoryElement2);

        categoryElement2.setId(categoryElement1.getId());
        assertThat(categoryElement1).isEqualTo(categoryElement2);

        categoryElement2 = getCategoryElementSample2();
        assertThat(categoryElement1).isNotEqualTo(categoryElement2);
    }

    @Test
    void orderRegTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setOrderRegType(orderRegistrationInfoBack);
        assertThat(categoryElement.getOrderRegType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.orderRegType(null);
        assertThat(categoryElement.getOrderRegType()).isNull();
    }

    @Test
    void requestTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setRequestType(orderRegistrationInfoBack);
        assertThat(categoryElement.getRequestType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.requestType(null);
        assertThat(categoryElement.getRequestType()).isNull();
    }

    @Test
    void importTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setImportType(orderRegistrationInfoBack);
        assertThat(categoryElement.getImportType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.importType(null);
        assertThat(categoryElement.getImportType()).isNull();
    }

    @Test
    void operationTypTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setOperationTyp(orderRegistrationInfoBack);
        assertThat(categoryElement.getOperationTyp()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.operationTyp(null);
        assertThat(categoryElement.getOperationTyp()).isNull();
    }

    @Test
    void currencyProvisionTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setCurrencyProvisionType(orderRegistrationInfoBack);
        assertThat(categoryElement.getCurrencyProvisionType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.currencyProvisionType(null);
        assertThat(categoryElement.getCurrencyProvisionType()).isNull();
    }

    @Test
    void paymentToolTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setPaymentTool(orderRegistrationInfoBack);
        assertThat(categoryElement.getPaymentTool()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.paymentTool(null);
        assertThat(categoryElement.getPaymentTool()).isNull();
    }

    @Test
    void activityTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setActivityType(orderRegistrationInfoBack);
        assertThat(categoryElement.getActivityType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.activityType(null);
        assertThat(categoryElement.getActivityType()).isNull();
    }

    @Test
    void ownerTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setOwnerType(orderRegistrationInfoBack);
        assertThat(categoryElement.getOwnerType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.ownerType(null);
        assertThat(categoryElement.getOwnerType()).isNull();
    }

    @Test
    void statusTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setStatus(orderRegistrationInfoBack);
        assertThat(categoryElement.getStatus()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.status(null);
        assertThat(categoryElement.getStatus()).isNull();
    }

    @Test
    void externalCustomerTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setExternalCustomerType(orderRegistrationInfoBack);
        assertThat(categoryElement.getExternalCustomerType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.externalCustomerType(null);
        assertThat(categoryElement.getExternalCustomerType()).isNull();
    }

    @Test
    void transportTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setTransportType(orderRegistrationInfoBack);
        assertThat(categoryElement.getTransportType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.transportType(null);
        assertThat(categoryElement.getTransportType()).isNull();
    }

    @Test
    void currencySupplierTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        PurchaseFromOtherResources purchaseFromOtherResourcesBack = getPurchaseFromOtherResourcesRandomSampleGenerator();

        categoryElement.setCurrencySupplier(purchaseFromOtherResourcesBack);
        assertThat(categoryElement.getCurrencySupplier()).isEqualTo(purchaseFromOtherResourcesBack);

        categoryElement.currencySupplier(null);
        assertThat(categoryElement.getCurrencySupplier()).isNull();
    }

    @Test
    void statusPurchaseTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        PurchaseFromOtherResources purchaseFromOtherResourcesBack = getPurchaseFromOtherResourcesRandomSampleGenerator();

        categoryElement.setStatusPurchase(purchaseFromOtherResourcesBack);
        assertThat(categoryElement.getStatusPurchase()).isEqualTo(purchaseFromOtherResourcesBack);

        categoryElement.statusPurchase(null);
        assertThat(categoryElement.getStatusPurchase()).isNull();
    }

    @Test
    void transportVehicleTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        categoryElement.setTransportVehicleType(orderRegistrationInfoBack);
        assertThat(categoryElement.getTransportVehicleType()).isEqualTo(orderRegistrationInfoBack);

        categoryElement.transportVehicleType(null);
        assertThat(categoryElement.getTransportVehicleType()).isNull();
    }

    @Test
    void freightLetterTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setFreightLetterType(draftBack);
        assertThat(categoryElement.getFreightLetterType()).isEqualTo(draftBack);

        categoryElement.freightLetterType(null);
        assertThat(categoryElement.getFreightLetterType()).isNull();
    }

    @Test
    void actionCodeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setActionCode(draftBack);
        assertThat(categoryElement.getActionCode()).isEqualTo(draftBack);

        categoryElement.actionCode(null);
        assertThat(categoryElement.getActionCode()).isNull();
    }

    @Test
    void ownershipCodeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setOwnershipCode(draftBack);
        assertThat(categoryElement.getOwnershipCode()).isEqualTo(draftBack);

        categoryElement.ownershipCode(null);
        assertThat(categoryElement.getOwnershipCode()).isNull();
    }

    @Test
    void currencyContainerPlaceTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setCurrencyContainerPlace(draftBack);
        assertThat(categoryElement.getCurrencyContainerPlace()).isEqualTo(draftBack);

        categoryElement.currencyContainerPlace(null);
        assertThat(categoryElement.getCurrencyContainerPlace()).isNull();
    }

    @Test
    void draftSourceTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setDraftSource(draftBack);
        assertThat(categoryElement.getDraftSource()).isEqualTo(draftBack);

        categoryElement.draftSource(null);
        assertThat(categoryElement.getDraftSource()).isNull();
    }

    @Test
    void chargedExchangeBrokerTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setChargedExchangeBroker(draftBack);
        assertThat(categoryElement.getChargedExchangeBroker()).isEqualTo(draftBack);

        categoryElement.chargedExchangeBroker(null);
        assertThat(categoryElement.getChargedExchangeBroker()).isNull();
    }

    @Test
    void impartTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setImpartType(draftBack);
        assertThat(categoryElement.getImpartType()).isEqualTo(draftBack);

        categoryElement.impartType(null);
        assertThat(categoryElement.getImpartType()).isNull();
    }

    @Test
    void insuranceLetterTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setInsuranceLetterType(draftBack);
        assertThat(categoryElement.getInsuranceLetterType()).isEqualTo(draftBack);

        categoryElement.insuranceLetterType(null);
        assertThat(categoryElement.getInsuranceLetterType()).isNull();
    }

    @Test
    void advisorDepositTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setAdvisorDepositType(draftBack);
        assertThat(categoryElement.getAdvisorDepositType()).isEqualTo(draftBack);

        categoryElement.advisorDepositType(null);
        assertThat(categoryElement.getAdvisorDepositType()).isNull();
    }

    @Test
    void interfaceAdvisorDepositTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setInterfaceAdvisorDepositType(draftBack);
        assertThat(categoryElement.getInterfaceAdvisorDepositType()).isEqualTo(draftBack);

        categoryElement.interfaceAdvisorDepositType(null);
        assertThat(categoryElement.getInterfaceAdvisorDepositType()).isNull();
    }

    @Test
    void paymentTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setPaymentType(draftBack);
        assertThat(categoryElement.getPaymentType()).isEqualTo(draftBack);

        categoryElement.paymentType(null);
        assertThat(categoryElement.getPaymentType()).isNull();
    }

    @Test
    void dealTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setDealType(draftBack);
        assertThat(categoryElement.getDealType()).isEqualTo(draftBack);

        categoryElement.dealType(null);
        assertThat(categoryElement.getDealType()).isNull();
    }

    @Test
    void coveringAdvisorDepositTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        categoryElement.setCoveringAdvisorDepositType(draftBack);
        assertThat(categoryElement.getCoveringAdvisorDepositType()).isEqualTo(draftBack);

        categoryElement.coveringAdvisorDepositType(null);
        assertThat(categoryElement.getCoveringAdvisorDepositType()).isNull();
    }

    @Test
    void depositTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        AdvisorDefinition advisorDefinitionBack = getAdvisorDefinitionRandomSampleGenerator();

        categoryElement.setDepositType(advisorDefinitionBack);
        assertThat(categoryElement.getDepositType()).isEqualTo(advisorDefinitionBack);

        categoryElement.depositType(null);
        assertThat(categoryElement.getDepositType()).isNull();
    }

    @Test
    void typeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftType draftTypeBack = getDraftTypeRandomSampleGenerator();

        categoryElement.setType(draftTypeBack);
        assertThat(categoryElement.getType()).isEqualTo(draftTypeBack);

        categoryElement.type(null);
        assertThat(categoryElement.getType()).isNull();
    }

    @Test
    void secondaryTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftType draftTypeBack = getDraftTypeRandomSampleGenerator();

        categoryElement.setSecondaryType(draftTypeBack);
        assertThat(categoryElement.getSecondaryType()).isEqualTo(draftTypeBack);

        categoryElement.secondaryType(null);
        assertThat(categoryElement.getSecondaryType()).isNull();
    }

    @Test
    void divisionTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftType draftTypeBack = getDraftTypeRandomSampleGenerator();

        categoryElement.setDivision(draftTypeBack);
        assertThat(categoryElement.getDivision()).isEqualTo(draftTypeBack);

        categoryElement.division(null);
        assertThat(categoryElement.getDivision()).isNull();
    }

    @Test
    void productDimensionTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        categoryElement.setProductDimension(draftReceiptBack);
        assertThat(categoryElement.getProductDimension()).isEqualTo(draftReceiptBack);

        categoryElement.productDimension(null);
        assertThat(categoryElement.getProductDimension()).isNull();
    }

    @Test
    void stateOfDocumentsTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        categoryElement.setStateOfDocuments(draftReceiptBack);
        assertThat(categoryElement.getStateOfDocuments()).isEqualTo(draftReceiptBack);

        categoryElement.stateOfDocuments(null);
        assertThat(categoryElement.getStateOfDocuments()).isNull();
    }

    @Test
    void currencyProvisionFileTypeTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        categoryElement.setCurrencyProvisionFileType(draftReceiptBack);
        assertThat(categoryElement.getCurrencyProvisionFileType()).isEqualTo(draftReceiptBack);

        categoryElement.currencyProvisionFileType(null);
        assertThat(categoryElement.getCurrencyProvisionFileType()).isNull();
    }

    @Test
    void statusDraftTest() throws Exception {
        CategoryElement categoryElement = getCategoryElementRandomSampleGenerator();
        DraftStatusInfo draftStatusInfoBack = getDraftStatusInfoRandomSampleGenerator();

        categoryElement.setStatusDraft(draftStatusInfoBack);
        assertThat(categoryElement.getStatusDraft()).isEqualTo(draftStatusInfoBack);

        categoryElement.statusDraft(null);
        assertThat(categoryElement.getStatusDraft()).isNull();
    }
}
