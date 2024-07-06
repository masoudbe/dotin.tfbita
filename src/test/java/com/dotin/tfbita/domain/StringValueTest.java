package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeTestSamples.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;
import static com.dotin.tfbita.domain.StringValueTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class StringValueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StringValue.class);
        StringValue stringValue1 = getStringValueSample1();
        StringValue stringValue2 = new StringValue();
        assertThat(stringValue1).isNotEqualTo(stringValue2);

        stringValue2.setId(stringValue1.getId());
        assertThat(stringValue1).isEqualTo(stringValue2);

        stringValue2 = getStringValueSample2();
        assertThat(stringValue1).isNotEqualTo(stringValue2);
    }

    @Test
    void orderRegistrationInfoTest() {
        StringValue stringValue = getStringValueRandomSampleGenerator();
        OrderRegistrationInfo orderRegistrationInfoBack = getOrderRegistrationInfoRandomSampleGenerator();

        stringValue.addOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(stringValue.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getCommissionTransactionNumbers()).containsOnly(stringValue);

        stringValue.removeOrderRegistrationInfo(orderRegistrationInfoBack);
        assertThat(stringValue.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getCommissionTransactionNumbers()).doesNotContain(stringValue);

        stringValue.orderRegistrationInfos(new HashSet<>(Set.of(orderRegistrationInfoBack)));
        assertThat(stringValue.getOrderRegistrationInfos()).containsOnly(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getCommissionTransactionNumbers()).containsOnly(stringValue);

        stringValue.setOrderRegistrationInfos(new HashSet<>());
        assertThat(stringValue.getOrderRegistrationInfos()).doesNotContain(orderRegistrationInfoBack);
        assertThat(orderRegistrationInfoBack.getCommissionTransactionNumbers()).doesNotContain(stringValue);
    }

    @Test
    void draftTest() {
        StringValue stringValue = getStringValueRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        stringValue.addDraft(draftBack);
        assertThat(stringValue.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getSanctionSerials()).containsOnly(stringValue);

        stringValue.removeDraft(draftBack);
        assertThat(stringValue.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getSanctionSerials()).doesNotContain(stringValue);

        stringValue.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(stringValue.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getSanctionSerials()).containsOnly(stringValue);

        stringValue.setDrafts(new HashSet<>());
        assertThat(stringValue.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getSanctionSerials()).doesNotContain(stringValue);
    }

    @Test
    void draftTypeTest() {
        StringValue stringValue = getStringValueRandomSampleGenerator();
        DraftType draftTypeBack = getDraftTypeRandomSampleGenerator();

        stringValue.addDraftType(draftTypeBack);
        assertThat(stringValue.getDraftTypes()).containsOnly(draftTypeBack);
        assertThat(draftTypeBack.getUserGroups()).containsOnly(stringValue);

        stringValue.removeDraftType(draftTypeBack);
        assertThat(stringValue.getDraftTypes()).doesNotContain(draftTypeBack);
        assertThat(draftTypeBack.getUserGroups()).doesNotContain(stringValue);

        stringValue.draftTypes(new HashSet<>(Set.of(draftTypeBack)));
        assertThat(stringValue.getDraftTypes()).containsOnly(draftTypeBack);
        assertThat(draftTypeBack.getUserGroups()).containsOnly(stringValue);

        stringValue.setDraftTypes(new HashSet<>());
        assertThat(stringValue.getDraftTypes()).doesNotContain(draftTypeBack);
        assertThat(draftTypeBack.getUserGroups()).doesNotContain(stringValue);
    }
}
