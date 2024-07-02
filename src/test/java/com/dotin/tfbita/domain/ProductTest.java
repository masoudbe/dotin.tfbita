package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AttributeValueTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.ProductTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = getProductSample1();
        Product product2 = new Product();
        assertThat(product1).isNotEqualTo(product2);

        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);

        product2 = getProductSample2();
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    void attributeValuesTest() throws Exception {
        Product product = getProductRandomSampleGenerator();
        AttributeValue attributeValueBack = getAttributeValueRandomSampleGenerator();

        product.addAttributeValues(attributeValueBack);
        assertThat(product.getAttributeValues()).containsOnly(attributeValueBack);
        assertThat(attributeValueBack.getProduct()).isEqualTo(product);

        product.removeAttributeValues(attributeValueBack);
        assertThat(product.getAttributeValues()).doesNotContain(attributeValueBack);
        assertThat(attributeValueBack.getProduct()).isNull();

        product.attributeValues(new HashSet<>(Set.of(attributeValueBack)));
        assertThat(product.getAttributeValues()).containsOnly(attributeValueBack);
        assertThat(attributeValueBack.getProduct()).isEqualTo(product);

        product.setAttributeValues(new HashSet<>());
        assertThat(product.getAttributeValues()).doesNotContain(attributeValueBack);
        assertThat(attributeValueBack.getProduct()).isNull();
    }

    @Test
    void productTypeTest() throws Exception {
        Product product = getProductRandomSampleGenerator();
        ProductType productTypeBack = getProductTypeRandomSampleGenerator();

        product.setProductType(productTypeBack);
        assertThat(product.getProductType()).isEqualTo(productTypeBack);

        product.productType(null);
        assertThat(product.getProductType()).isNull();
    }

    @Test
    void orderRegistrationInfoTest() throws Exception {
        Product product = getProductRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        product.addOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(product.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getProductInfos()).containsOnly(product);

        product.removeOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(product.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getProductInfos()).doesNotContain(product);

        product.orderRegistrationInfos(new HashSet<>(Set.of(orderRegistrationInfoBack)));
        assertThat(product.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getProductInfos()).containsOnly(product);

        product.setOrderRegistrationInfos(new HashSet<>());
        assertThat(product.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getProductInfos()).doesNotContain(product);
    }

    @Test
    void draftTest() throws Exception {
        Product product = getProductRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        product.addDraft(draftBack);
        assertThat(product.getDrafts()).containsOnly(draftBack);

        product.removeDraft(draftBack);
        assertThat(product.getDrafts()).doesNotContain(draftBack);

        product.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(product.getDrafts()).containsOnly(draftBack);

        product.setDrafts(new HashSet<>());
        assertThat(product.getDrafts()).doesNotContain(draftBack);
    }

    @Test
    void draftProductInfosTest() throws Exception {
        Product product = getProductRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        product.setDraftProductInfos(draftReceiptBack);
        assertThat(product.getDraftProductInfos()).isEqualTo(draftReceiptBack);

        product.draftProductInfos(null);
        assertThat(product.getDraftProductInfos()).isNull();
    }
}
