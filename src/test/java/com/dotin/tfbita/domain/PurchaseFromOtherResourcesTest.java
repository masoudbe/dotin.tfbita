package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseFromOtherResourcesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseFromOtherResources.class);
        PurchaseFromOtherResources purchaseFromOtherResources1 = getPurchaseFromOtherResourcesSample1();
        PurchaseFromOtherResources purchaseFromOtherResources2 = new PurchaseFromOtherResources();
        assertThat(purchaseFromOtherResources1).isNotEqualTo(purchaseFromOtherResources2);

        purchaseFromOtherResources2.setId(purchaseFromOtherResources1.getId());
        assertThat(purchaseFromOtherResources1).isEqualTo(purchaseFromOtherResources2);

        purchaseFromOtherResources2 = getPurchaseFromOtherResourcesSample2();
        assertThat(purchaseFromOtherResources1).isNotEqualTo(purchaseFromOtherResources2);
    }

    @Test
    void currencySupplierTest() {
        PurchaseFromOtherResources purchaseFromOtherResources = getPurchaseFromOtherResourcesRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        purchaseFromOtherResources.setCurrencySupplier(categoryElementBack);
        assertThat(purchaseFromOtherResources.getCurrencySupplier()).isEqualTo(categoryElementBack);

        purchaseFromOtherResources.currencySupplier(null);
        assertThat(purchaseFromOtherResources.getCurrencySupplier()).isNull();
    }

    @Test
    void statusTest() {
        PurchaseFromOtherResources purchaseFromOtherResources = getPurchaseFromOtherResourcesRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        purchaseFromOtherResources.setStatus(categoryElementBack);
        assertThat(purchaseFromOtherResources.getStatus()).isEqualTo(categoryElementBack);

        purchaseFromOtherResources.status(null);
        assertThat(purchaseFromOtherResources.getStatus()).isNull();
    }

    @Test
    void orderRegistrationInfoTest() {
        PurchaseFromOtherResources purchaseFromOtherResources = getPurchaseFromOtherResourcesRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        purchaseFromOtherResources.setOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(purchaseFromOtherResources.getOrderRegistrationInfo()).isEqualTo(orderRegistrationInfoBack);

        purchaseFromOtherResources.orderRegistrationInfo(null);
        assertThat(purchaseFromOtherResources.getOrderRegistrationInfo()).isNull();
    }
}
