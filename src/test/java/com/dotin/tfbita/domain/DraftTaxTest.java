package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftTaxTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTaxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTax.class);
        DraftTax draftTax1 = getDraftTaxSample1();
        DraftTax draftTax2 = new DraftTax();
        assertThat(draftTax1).isNotEqualTo(draftTax2);

        draftTax2.setId(draftTax1.getId());
        assertThat(draftTax1).isEqualTo(draftTax2);

        draftTax2 = getDraftTaxSample2();
        assertThat(draftTax1).isNotEqualTo(draftTax2);
    }

    @Test
    void taxesTest() throws Exception {
        DraftTax draftTax = getDraftTaxRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftTax.setTaxes(draftBack);
        assertThat(draftTax.getTaxes()).isEqualTo(draftBack);

        draftTax.taxes(null);
        assertThat(draftTax.getTaxes()).isNull();
    }
}
