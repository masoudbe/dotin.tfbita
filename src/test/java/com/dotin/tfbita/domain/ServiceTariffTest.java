package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.ServiceTariffTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ServiceTariffTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceTariff.class);
        ServiceTariff serviceTariff1 = getServiceTariffSample1();
        ServiceTariff serviceTariff2 = new ServiceTariff();
        assertThat(serviceTariff1).isNotEqualTo(serviceTariff2);

        serviceTariff2.setId(serviceTariff1.getId());
        assertThat(serviceTariff1).isEqualTo(serviceTariff2);

        serviceTariff2 = getServiceTariffSample2();
        assertThat(serviceTariff1).isNotEqualTo(serviceTariff2);
    }

    @Test
    void draftTest() {
        ServiceTariff serviceTariff = getServiceTariffRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        serviceTariff.addDraft(draftBack);
        assertThat(serviceTariff.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getServices()).containsOnly(serviceTariff);

        serviceTariff.removeDraft(draftBack);
        assertThat(serviceTariff.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getServices()).doesNotContain(serviceTariff);

        serviceTariff.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(serviceTariff.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getServices()).containsOnly(serviceTariff);

        serviceTariff.setDrafts(new HashSet<>());
        assertThat(serviceTariff.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getServices()).doesNotContain(serviceTariff);
    }
}
