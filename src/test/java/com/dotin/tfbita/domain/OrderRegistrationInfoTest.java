package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static com.dotin.tfbita.domain.LicenceInfoTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegServTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesTestSamples.*;
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
    void licenceInfoTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        LicenceInfo licenceInfoBack = getLicenceInfoRandomSampleGenerator();

        orderRegistrationInfo.addLicenceInfo(licenceInfoBack);
        assertThat(orderRegistrationInfo.getLicenceInfos()).containsOnly(licenceInfoBack);
        assertThat(licenceInfoBack.getLicenceInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.removeLicenceInfo(licenceInfoBack);
        assertThat(orderRegistrationInfo.getLicenceInfos()).doesNotContain(licenceInfoBack);
        assertThat(licenceInfoBack.getLicenceInfo()).isNull();

        orderRegistrationInfo.licenceInfos(new HashSet<>(Set.of(licenceInfoBack)));
        assertThat(orderRegistrationInfo.getLicenceInfos()).containsOnly(licenceInfoBack);
        assertThat(licenceInfoBack.getLicenceInfo()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.setLicenceInfos(new HashSet<>());
        assertThat(orderRegistrationInfo.getLicenceInfos()).doesNotContain(licenceInfoBack);
        assertThat(licenceInfoBack.getLicenceInfo()).isNull();
    }

    @Test
    void orderRegServTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        OrderRegServ orderRegServBack = getOrderRegServRandomSampleGenerator();

        orderRegistrationInfo.addOrderRegServ(orderRegServBack);
        assertThat(orderRegistrationInfo.getOrderRegServs()).containsOnly(orderRegServBack);
        assertThat(orderRegServBack.getOrderRegService()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.removeOrderRegServ(orderRegServBack);
        assertThat(orderRegistrationInfo.getOrderRegServs()).doesNotContain(orderRegServBack);
        assertThat(orderRegServBack.getOrderRegService()).isNull();

        orderRegistrationInfo.orderRegServs(new HashSet<>(Set.of(orderRegServBack)));
        assertThat(orderRegistrationInfo.getOrderRegServs()).containsOnly(orderRegServBack);
        assertThat(orderRegServBack.getOrderRegService()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.setOrderRegServs(new HashSet<>());
        assertThat(orderRegistrationInfo.getOrderRegServs()).doesNotContain(orderRegServBack);
        assertThat(orderRegServBack.getOrderRegService()).isNull();
    }

    @Test
    void purchaseFromOtherResourcesTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        PurchaseFromOtherResources purchaseFromOtherResourcesBack = getPurchaseFromOtherResourcesRandomSampleGenerator();

        orderRegistrationInfo.addPurchaseFromOtherResources(purchaseFromOtherResourcesBack);
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResources()).containsOnly(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getPurchaseFromOtherResources()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.removePurchaseFromOtherResources(purchaseFromOtherResourcesBack);
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResources()).doesNotContain(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getPurchaseFromOtherResources()).isNull();

        orderRegistrationInfo.purchaseFromOtherResources(new HashSet<>(Set.of(purchaseFromOtherResourcesBack)));
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResources()).containsOnly(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getPurchaseFromOtherResources()).isEqualTo(orderRegistrationInfo);

        orderRegistrationInfo.setPurchaseFromOtherResources(new HashSet<>());
        assertThat(orderRegistrationInfo.getPurchaseFromOtherResources()).doesNotContain(purchaseFromOtherResourcesBack);
        assertThat(purchaseFromOtherResourcesBack.getPurchaseFromOtherResources()).isNull();
    }

    @Test
    void customTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        orderRegistrationInfo.addCustom(customBack);
        assertThat(orderRegistrationInfo.getCustoms()).containsOnly(customBack);
        assertThat(customBack.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfo);

        orderRegistrationInfo.removeCustom(customBack);
        assertThat(orderRegistrationInfo.getCustoms()).doesNotContain(customBack);
        assertThat(customBack.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfo);

        orderRegistrationInfo.customs(new HashSet<>(Set.of(customBack)));
        assertThat(orderRegistrationInfo.getCustoms()).containsOnly(customBack);
        assertThat(customBack.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfo);

        orderRegistrationInfo.setCustoms(new HashSet<>());
        assertThat(orderRegistrationInfo.getCustoms()).doesNotContain(customBack);
        assertThat(customBack.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfo);
    }

    @Test
    void productInfoTest() throws Exception {
        OrderRegistrationInfo orderRegistrationInfo = getOrderRegistrationInfoRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        orderRegistrationInfo.addProductInfo(productBack);
        assertThat(orderRegistrationInfo.getProductInfos()).containsOnly(productBack);
        assertThat(productBack.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfo);

        orderRegistrationInfo.removeProductInfo(productBack);
        assertThat(orderRegistrationInfo.getProductInfos()).doesNotContain(productBack);
        assertThat(productBack.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfo);

        orderRegistrationInfo.productInfos(new HashSet<>(Set.of(productBack)));
        assertThat(orderRegistrationInfo.getProductInfos()).containsOnly(productBack);
        assertThat(productBack.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfo);

        orderRegistrationInfo.setProductInfos(new HashSet<>());
        assertThat(orderRegistrationInfo.getProductInfos()).doesNotContain(productBack);
        assertThat(productBack.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfo);
    }
}
