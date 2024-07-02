package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static com.dotin.tfbita.domain.LicenceInfoTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegServiceTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesTestSamples.*;
import static com.dotin.tfbita.domain.StringValueTestSamples.*;
import static com.dotin.tfbita.domain.TransportationTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OrderRegistrationInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRegistrationInfo.class);
        OrderRegistrationInfo orderRegistrationInfo1 = getOrderRegistrationInfoSample1();
        OrderRegistrationInfo orderRegistrationInfo2 = new OrderRegistrationInfo();
        assertThat(orderRegistrationInfo1).isNotEqualTo(orderRegistrationInfo2);

        orderRegistrationInfo2.setId(orderRegistrationInfo1.getId());
        assertThat(orderRegistrationInfo1).isEqualTo(orderRegistrationInfo2);

        orderRegistrationInfo2 = getOrderRegistrationInfoSample2();
        assertThat(orderRegistrationInfo1).isNotEqualTo(orderRegistrationInfo2);
    }

    @Test
    void serviceInfoTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        OrderRegService orderRegServiceBack = getOrderRegServiceRandomSampleGenerator();

        orderRegistrationInfo.addServiceInfo(orderRegServiceBack);
        assertThat(orderRegistrationInfo.getServiceInfos()).containsOnly(orderRegServiceBack);
        assertThat(orderRegServiceBack.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.removeServiceInfo(orderRegServiceBack);
        assertThat(orderRegistrationInfo.getServiceInfos()).doesNotContain(orderRegServiceBack);
        assertThat(orderRegServiceBack.getOrderRegistrationInfo()).isNull();

        orderRegistrationInfo.serviceInfos(new HashSet<>(Set.of(orderRegServiceBack)));
        assertThat(orderRegistrationInfo.getServiceInfos()).containsOnly(orderRegServiceBack);
        assertThat(orderRegServiceBack.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.setServiceInfos(new HashSet<>());
        assertThat(orderRegistrationInfo.getServiceInfos()).doesNotContain(orderRegServiceBack);
        assertThat(orderRegServiceBack.getOrderRegistrationInfo()).isNull();
    }

    @Test
    void purchaseFromOtherResourcesListTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        PurchaseFromOtherResources purchaseFromOtherResourcesBack = getPurchaseFromOtherResourcesRandomSampleGenerator();

        orderRegistrationInfo.addPurchaseFromOtherResourcesList(purchaseFromOtherResourcesBack);
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResourcesLists()).containsOnly(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.removePurchaseFromOtherResourcesList(purchaseFromOtherResourcesBack);
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResourcesLists()).doesNotContain(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getOrderRegistrationInfo()).isNull();

        orderRegistrationInfo.purchaseFromOtherResourcesLists(new HashSet<>(Set.of(purchaseFromOtherResourcesBack)));
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResourcesLists()).containsOnly(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.setPurchaseFromOtherResourcesLists(new HashSet<>());
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResourcesLists()).doesNotContain(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getOrderRegistrationInfo()).isNull();
    }

    @Test
    void orderRegTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setOrderRegType(categoryElementBack);
        assertThat(orderRegistrationInfo.getOrderRegType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.orderRegType(null);
        assertThat(orderRegistrationInfo.getOrderRegType()).isNull();
    }

    @Test
    void requestTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setRequestType(categoryElementBack);
        assertThat(orderRegistrationInfo.getRequestType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.requestType(null);
        assertThat(orderRegistrationInfo.getRequestType()).isNull();
    }

    @Test
    void importTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setImportType(categoryElementBack);
        assertThat(orderRegistrationInfo.getImportType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.importType(null);
        assertThat(orderRegistrationInfo.getImportType()).isNull();
    }

    @Test
    void operationTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setOperationType(categoryElementBack);
        assertThat(orderRegistrationInfo.getOperationType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.operationType(null);
        assertThat(orderRegistrationInfo.getOperationType()).isNull();
    }

    @Test
    void currencyProvisionTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setCurrencyProvisionType(categoryElementBack);
        assertThat(orderRegistrationInfo.getCurrencyProvisionType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.currencyProvisionType(null);
        assertThat(orderRegistrationInfo.getCurrencyProvisionType()).isNull();
    }

    @Test
    void paymentToolTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setPaymentTool(categoryElementBack);
        assertThat(orderRegistrationInfo.getPaymentTool()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.paymentTool(null);
        assertThat(orderRegistrationInfo.getPaymentTool()).isNull();
    }

    @Test
    void activityTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setActivityType(categoryElementBack);
        assertThat(orderRegistrationInfo.getActivityType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.activityType(null);
        assertThat(orderRegistrationInfo.getActivityType()).isNull();
    }

    @Test
    void ownerTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setOwnerType(categoryElementBack);
        assertThat(orderRegistrationInfo.getOwnerType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.ownerType(null);
        assertThat(orderRegistrationInfo.getOwnerType()).isNull();
    }

    @Test
    void statusTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setStatus(categoryElementBack);
        assertThat(orderRegistrationInfo.getStatus()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.status(null);
        assertThat(orderRegistrationInfo.getStatus()).isNull();
    }

    @Test
    void externalCustomerTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.setExternalCustomerType(categoryElementBack);
        assertThat(orderRegistrationInfo.getExternalCustomerType()).isEqualTo(categoryElementBack);

        orderRegistrationInfo.externalCustomerType(null);
        assertThat(orderRegistrationInfo.getExternalCustomerType()).isNull();
    }

    @Test
    void transportTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        TransportationType transportationTypeBack = getTransportationTypeRandomSampleGenerator();

        orderRegistrationInfo.setTransportType(transportationTypeBack);
        assertThat(orderRegistrationInfo.getTransportType()).isEqualTo(transportationTypeBack);

        orderRegistrationInfo.transportType(null);
        assertThat(orderRegistrationInfo.getTransportType()).isNull();
    }

    @Test
    void destCoustomersTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        orderRegistrationInfo.setDestCoustomers(customBack);
        assertThat(orderRegistrationInfo.getDestCoustomers()).isEqualTo(customBack);

        orderRegistrationInfo.destCoustomers(null);
        assertThat(orderRegistrationInfo.getDestCoustomers()).isNull();
    }

    @Test
    void cargoPlaceCustomsTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        orderRegistrationInfo.setCargoPlaceCustoms(customBack);
        assertThat(orderRegistrationInfo.getCargoPlaceCustoms()).isEqualTo(customBack);

        orderRegistrationInfo.cargoPlaceCustoms(null);
        assertThat(orderRegistrationInfo.getCargoPlaceCustoms()).isNull();
    }

    @Test
    void entranceBordersTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        orderRegistrationInfo.setEntranceBorders(customBack);
        assertThat(orderRegistrationInfo.getEntranceBorders()).isEqualTo(customBack);

        orderRegistrationInfo.entranceBorders(null);
        assertThat(orderRegistrationInfo.getEntranceBorders()).isNull();
    }

    @Test
    void transportVehicleTypeTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        orderRegistrationInfo.addTransportVehicleType(categoryElementBack);
        assertThat(orderRegistrationInfo.getTransportVehicleTypes()).containsOnly(categoryElementBack);

        orderRegistrationInfo.removeTransportVehicleType(categoryElementBack);
        assertThat(orderRegistrationInfo.getTransportVehicleTypes()).doesNotContain(categoryElementBack);

        orderRegistrationInfo.transportVehicleTypes(new HashSet<>(Set.of(categoryElementBack)));
        assertThat(orderRegistrationInfo.getTransportVehicleTypes()).containsOnly(categoryElementBack);

        orderRegistrationInfo.setTransportVehicleTypes(new HashSet<>());
        assertThat(orderRegistrationInfo.getTransportVehicleTypes()).doesNotContain(categoryElementBack);
    }

    @Test
    void productInfoTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        orderRegistrationInfo.addProductInfo(productBack);
        assertThat(orderRegistrationInfo.getProductInfos()).containsOnly(productBack);

        orderRegistrationInfo.removeProductInfo(productBack);
        assertThat(orderRegistrationInfo.getProductInfos()).doesNotContain(productBack);

        orderRegistrationInfo.productInfos(new HashSet<>(Set.of(productBack)));
        assertThat(orderRegistrationInfo.getProductInfos()).containsOnly(productBack);

        orderRegistrationInfo.setProductInfos(new HashSet<>());
        assertThat(orderRegistrationInfo.getProductInfos()).doesNotContain(productBack);
    }

    @Test
    void commissionTransactionNumberTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        StringValue stringValueBack = getStringValueRandomSampleGenerator();

        orderRegistrationInfo.addCommissionTransactionNumber(stringValueBack);
        assertThat(orderRegistrationInfo.getCommissionTransactionNumbers()).containsOnly(stringValueBack);

        orderRegistrationInfo.removeCommissionTransactionNumber(stringValueBack);
        assertThat(orderRegistrationInfo.getCommissionTransactionNumbers()).doesNotContain(stringValueBack);

        orderRegistrationInfo.commissionTransactionNumbers(new HashSet<>(Set.of(stringValueBack)));
        assertThat(orderRegistrationInfo.getCommissionTransactionNumbers()).containsOnly(stringValueBack);

        orderRegistrationInfo.setCommissionTransactionNumbers(new HashSet<>());
        assertThat(orderRegistrationInfo.getCommissionTransactionNumbers()).doesNotContain(stringValueBack);
    }

    @Test
    void licenceInfoTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        LicenceInfo licenceInfoBack = getLicenceInfoRandomSampleGenerator();

        orderRegistrationInfo.addLicenceInfo(licenceInfoBack);
        assertThat(orderRegistrationInfo.getLicenceInfos()).containsOnly(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.removeLicenceInfo(licenceInfoBack);
        assertThat(orderRegistrationInfo.getLicenceInfos()).doesNotContain(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegistrationInfo()).isNull();

        orderRegistrationInfo.licenceInfos(new HashSet<>(Set.of(licenceInfoBack)));
        assertThat(orderRegistrationInfo.getLicenceInfos()).containsOnly(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.setLicenceInfos(new HashSet<>());
        assertThat(orderRegistrationInfo.getLicenceInfos()).doesNotContain(licenceInfoBack);
        assertThat(licenceInfoBack.getOrderRegistrationInfo()).isNull();
    }
}
