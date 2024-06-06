package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
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
}
