package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static com.dotin.tfbita.domain.DraftCustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.DraftExtendTestSamples.*;
import static com.dotin.tfbita.domain.DraftFactorTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftTaxTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.DraftUsedAssuranceTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.ServiceTariffTestSamples.*;
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
    void draftReceiptTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        draft.addDraftReceipt(draftReceiptBack);
        assertThat(draft.getDraftReceipts()).containsOnly(draftReceiptBack);
        assertThat(draftReceiptBack.getReceipts()).isEqualTo(draft);

        draft.removeDraftReceipt(draftReceiptBack);
        assertThat(draft.getDraftReceipts()).doesNotContain(draftReceiptBack);
        assertThat(draftReceiptBack.getReceipts()).isNull();

        draft.draftReceipts(new HashSet<>(Set.of(draftReceiptBack)));
        assertThat(draft.getDraftReceipts()).containsOnly(draftReceiptBack);
        assertThat(draftReceiptBack.getReceipts()).isEqualTo(draft);

        draft.setDraftReceipts(new HashSet<>());
        assertThat(draft.getDraftReceipts()).doesNotContain(draftReceiptBack);
        assertThat(draftReceiptBack.getReceipts()).isNull();
    }

    @Test
    void draftUsedAssuranceTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        DraftUsedAssurance draftUsedAssuranceBack = getDraftUsedAssuranceRandomSampleGenerator();

        draft.addDraftUsedAssurance(draftUsedAssuranceBack);
        assertThat(draft.getDraftUsedAssurances()).containsOnly(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getUsedAssurances()).isEqualTo(draft);

        draft.removeDraftUsedAssurance(draftUsedAssuranceBack);
        assertThat(draft.getDraftUsedAssurances()).doesNotContain(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getUsedAssurances()).isNull();

        draft.draftUsedAssurances(new HashSet<>(Set.of(draftUsedAssuranceBack)));
        assertThat(draft.getDraftUsedAssurances()).containsOnly(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getUsedAssurances()).isEqualTo(draft);

        draft.setDraftUsedAssurances(new HashSet<>());
        assertThat(draft.getDraftUsedAssurances()).doesNotContain(draftUsedAssuranceBack);
        assertThat(draftUsedAssuranceBack.getUsedAssurances()).isNull();
    }

    @Test
    void draftFactorTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        DraftFactor draftFactorBack = getDraftFactorRandomSampleGenerator();

        draft.addDraftFactor(draftFactorBack);
        assertThat(draft.getDraftFactors()).containsOnly(draftFactorBack);
        assertThat(draftFactorBack.getDraftFactors()).isEqualTo(draft);

        draft.removeDraftFactor(draftFactorBack);
        assertThat(draft.getDraftFactors()).doesNotContain(draftFactorBack);
        assertThat(draftFactorBack.getDraftFactors()).isNull();

        draft.draftFactors(new HashSet<>(Set.of(draftFactorBack)));
        assertThat(draft.getDraftFactors()).containsOnly(draftFactorBack);
        assertThat(draftFactorBack.getDraftFactors()).isEqualTo(draft);

        draft.setDraftFactors(new HashSet<>());
        assertThat(draft.getDraftFactors()).doesNotContain(draftFactorBack);
        assertThat(draftFactorBack.getDraftFactors()).isNull();
    }

    @Test
    void draftCustomJustificationTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        DraftCustomJustification draftCustomJustificationBack = getDraftCustomJustificationRandomSampleGenerator();

        draft.addDraftCustomJustification(draftCustomJustificationBack);
        assertThat(draft.getDraftCustomJustifications()).containsOnly(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftJustifications()).isEqualTo(draft);

        draft.removeDraftCustomJustification(draftCustomJustificationBack);
        assertThat(draft.getDraftCustomJustifications()).doesNotContain(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftJustifications()).isNull();

        draft.draftCustomJustifications(new HashSet<>(Set.of(draftCustomJustificationBack)));
        assertThat(draft.getDraftCustomJustifications()).containsOnly(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftJustifications()).isEqualTo(draft);

        draft.setDraftCustomJustifications(new HashSet<>());
        assertThat(draft.getDraftCustomJustifications()).doesNotContain(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftJustifications()).isNull();
    }

    @Test
    void draftExtendTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        DraftExtend draftExtendBack = getDraftExtendRandomSampleGenerator();

        draft.addDraftExtend(draftExtendBack);
        assertThat(draft.getDraftExtends()).containsOnly(draftExtendBack);
        assertThat(draftExtendBack.getExtensions()).isEqualTo(draft);

        draft.removeDraftExtend(draftExtendBack);
        assertThat(draft.getDraftExtends()).doesNotContain(draftExtendBack);
        assertThat(draftExtendBack.getExtensions()).isNull();

        draft.draftExtends(new HashSet<>(Set.of(draftExtendBack)));
        assertThat(draft.getDraftExtends()).containsOnly(draftExtendBack);
        assertThat(draftExtendBack.getExtensions()).isEqualTo(draft);

        draft.setDraftExtends(new HashSet<>());
        assertThat(draft.getDraftExtends()).doesNotContain(draftExtendBack);
        assertThat(draftExtendBack.getExtensions()).isNull();
    }

    @Test
    void draftTaxTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        DraftTax draftTaxBack = getDraftTaxRandomSampleGenerator();

        draft.addDraftTax(draftTaxBack);
        assertThat(draft.getDraftTaxes()).containsOnly(draftTaxBack);
        assertThat(draftTaxBack.getTaxes()).isEqualTo(draft);

        draft.removeDraftTax(draftTaxBack);
        assertThat(draft.getDraftTaxes()).doesNotContain(draftTaxBack);
        assertThat(draftTaxBack.getTaxes()).isNull();

        draft.draftTaxes(new HashSet<>(Set.of(draftTaxBack)));
        assertThat(draft.getDraftTaxes()).containsOnly(draftTaxBack);
        assertThat(draftTaxBack.getTaxes()).isEqualTo(draft);

        draft.setDraftTaxes(new HashSet<>());
        assertThat(draft.getDraftTaxes()).doesNotContain(draftTaxBack);
        assertThat(draftTaxBack.getTaxes()).isNull();
    }

    @Test
    void customTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        draft.addCustom(customBack);
        assertThat(draft.getCustoms()).containsOnly(customBack);
        assertThat(customBack.getDrafts()).containsOnly(draft);

        draft.removeCustom(customBack);
        assertThat(draft.getCustoms()).doesNotContain(customBack);
        assertThat(customBack.getDrafts()).doesNotContain(draft);

        draft.customs(new HashSet<>(Set.of(customBack)));
        assertThat(draft.getCustoms()).containsOnly(customBack);
        assertThat(customBack.getDrafts()).containsOnly(draft);

        draft.setCustoms(new HashSet<>());
        assertThat(draft.getCustoms()).doesNotContain(customBack);
        assertThat(customBack.getDrafts()).doesNotContain(draft);
    }

    @Test
    void productsTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        draft.addProducts(productBack);
        assertThat(draft.getProducts()).containsOnly(productBack);
        assertThat(productBack.getDrafts()).containsOnly(draft);

        draft.removeProducts(productBack);
        assertThat(draft.getProducts()).doesNotContain(productBack);
        assertThat(productBack.getDrafts()).doesNotContain(draft);

        draft.products(new HashSet<>(Set.of(productBack)));
        assertThat(draft.getProducts()).containsOnly(productBack);
        assertThat(productBack.getDrafts()).containsOnly(draft);

        draft.setProducts(new HashSet<>());
        assertThat(draft.getProducts()).doesNotContain(productBack);
        assertThat(productBack.getDrafts()).doesNotContain(draft);
    }

    @Test
    void servicesTest() throws Exception {
        Draft draft = getDraftRandomSampleGenerator();
        ServiceTariff serviceTariffBack = getServiceTariffRandomSampleGenerator();

        draft.addServices(serviceTariffBack);
        assertThat(draft.getServices()).containsOnly(serviceTariffBack);
        assertThat(serviceTariffBack.getDrafts()).containsOnly(draft);

        draft.removeServices(serviceTariffBack);
        assertThat(draft.getServices()).doesNotContain(serviceTariffBack);
        assertThat(serviceTariffBack.getDrafts()).doesNotContain(draft);

        draft.services(new HashSet<>(Set.of(serviceTariffBack)));
        assertThat(draft.getServices()).containsOnly(serviceTariffBack);
        assertThat(serviceTariffBack.getDrafts()).containsOnly(draft);

        draft.setServices(new HashSet<>());
        assertThat(draft.getServices()).doesNotContain(serviceTariffBack);
        assertThat(serviceTariffBack.getDrafts()).doesNotContain(draft);
    }
}
