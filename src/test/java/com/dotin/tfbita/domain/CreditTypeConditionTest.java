package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CreditTypeConditionInfoTestSamples.*;
import static com.dotin.tfbita.domain.CreditTypeConditionTestSamples.*;
import static com.dotin.tfbita.domain.ObjectiveCategoryElementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditTypeConditionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditTypeCondition.class);
        CreditTypeCondition creditTypeCondition1 = getCreditTypeConditionSample1();
        CreditTypeCondition creditTypeCondition2 = new CreditTypeCondition();
        assertThat(creditTypeCondition1).isNotEqualTo(creditTypeCondition2);

        creditTypeCondition2.setId(creditTypeCondition1.getId());
        assertThat(creditTypeCondition1).isEqualTo(creditTypeCondition2);

        creditTypeCondition2 = getCreditTypeConditionSample2();
        assertThat(creditTypeCondition1).isNotEqualTo(creditTypeCondition2);
    }

    @Test
    void serviceOrProductTest() {
        CreditTypeCondition creditTypeCondition = getCreditTypeConditionRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        creditTypeCondition.setServiceOrProduct(categoryElementBack);
        assertThat(creditTypeCondition.getServiceOrProduct()).isEqualTo(categoryElementBack);

        creditTypeCondition.serviceOrProduct(null);
        assertThat(creditTypeCondition.getServiceOrProduct()).isNull();
    }

    @Test
    void neededIdentificationDocTypesTest() {
        CreditTypeCondition creditTypeCondition = getCreditTypeConditionRandomSampleGenerator();
        ObjectiveCategoryElement objectiveCategoryElementBack = getObjectiveCategoryElementRandomSampleGenerator();

        creditTypeCondition.setNeededIdentificationDocTypes(objectiveCategoryElementBack);
        assertThat(creditTypeCondition.getNeededIdentificationDocTypes()).isEqualTo(objectiveCategoryElementBack);

        creditTypeCondition.neededIdentificationDocTypes(null);
        assertThat(creditTypeCondition.getNeededIdentificationDocTypes()).isNull();
    }

    @Test
    void productTypesTest() {
        CreditTypeCondition creditTypeCondition = getCreditTypeConditionRandomSampleGenerator();
        ObjectiveCategoryElement objectiveCategoryElementBack = getObjectiveCategoryElementRandomSampleGenerator();

        creditTypeCondition.setProductTypes(objectiveCategoryElementBack);
        assertThat(creditTypeCondition.getProductTypes()).isEqualTo(objectiveCategoryElementBack);

        creditTypeCondition.productTypes(null);
        assertThat(creditTypeCondition.getProductTypes()).isNull();
    }

    @Test
    void assuranceTypesTest() {
        CreditTypeCondition creditTypeCondition = getCreditTypeConditionRandomSampleGenerator();
        ObjectiveCategoryElement objectiveCategoryElementBack = getObjectiveCategoryElementRandomSampleGenerator();

        creditTypeCondition.setAssuranceTypes(objectiveCategoryElementBack);
        assertThat(creditTypeCondition.getAssuranceTypes()).isEqualTo(objectiveCategoryElementBack);

        creditTypeCondition.assuranceTypes(null);
        assertThat(creditTypeCondition.getAssuranceTypes()).isNull();
    }

    @Test
    void creditTypeConditionInfoTest() {
        CreditTypeCondition creditTypeCondition = getCreditTypeConditionRandomSampleGenerator();
        CreditTypeConditionInfo creditTypeConditionInfoBack = getCreditTypeConditionInfoRandomSampleGenerator();

        creditTypeCondition.setCreditTypeConditionInfo(creditTypeConditionInfoBack);
        assertThat(creditTypeCondition.getCreditTypeConditionInfo()).isEqualTo(creditTypeConditionInfoBack);

        creditTypeCondition.creditTypeConditionInfo(null);
        assertThat(creditTypeCondition.getCreditTypeConditionInfo()).isNull();
    }
}
