package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CreditTypeConditionInfoTestSamples.*;
import static com.dotin.tfbita.domain.CreditTypeConditionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CreditTypeConditionInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditTypeConditionInfo.class);
        CreditTypeConditionInfo creditTypeConditionInfo1 = getCreditTypeConditionInfoSample1();
        CreditTypeConditionInfo creditTypeConditionInfo2 = new CreditTypeConditionInfo();
        assertThat(creditTypeConditionInfo1).isNotEqualTo(creditTypeConditionInfo2);

        creditTypeConditionInfo2.setId(creditTypeConditionInfo1.getId());
        assertThat(creditTypeConditionInfo1).isEqualTo(creditTypeConditionInfo2);

        creditTypeConditionInfo2 = getCreditTypeConditionInfoSample2();
        assertThat(creditTypeConditionInfo1).isNotEqualTo(creditTypeConditionInfo2);
    }

    @Test
    void conditionsTest() {
        CreditTypeConditionInfo creditTypeConditionInfo = getCreditTypeConditionInfoRandomSampleGenerator();
        CreditTypeCondition creditTypeConditionBack = getCreditTypeConditionRandomSampleGenerator();

        creditTypeConditionInfo.addConditions(creditTypeConditionBack);
        assertThat(creditTypeConditionInfo.getConditions()).containsOnly(creditTypeConditionBack);
        assertThat(creditTypeConditionBack.getCreditTypeConditionInfo()).isEqualTo(creditTypeConditionInfo);

        creditTypeConditionInfo.removeConditions(creditTypeConditionBack);
        assertThat(creditTypeConditionInfo.getConditions()).doesNotContain(creditTypeConditionBack);
        assertThat(creditTypeConditionBack.getCreditTypeConditionInfo()).isNull();

        creditTypeConditionInfo.conditions(new HashSet<>(Set.of(creditTypeConditionBack)));
        assertThat(creditTypeConditionInfo.getConditions()).containsOnly(creditTypeConditionBack);
        assertThat(creditTypeConditionBack.getCreditTypeConditionInfo()).isEqualTo(creditTypeConditionInfo);

        creditTypeConditionInfo.setConditions(new HashSet<>());
        assertThat(creditTypeConditionInfo.getConditions()).doesNotContain(creditTypeConditionBack);
        assertThat(creditTypeConditionBack.getCreditTypeConditionInfo()).isNull();
    }

    @Test
    void defaultConditionTest() {
        CreditTypeConditionInfo creditTypeConditionInfo = getCreditTypeConditionInfoRandomSampleGenerator();
        CreditTypeCondition creditTypeConditionBack = getCreditTypeConditionRandomSampleGenerator();

        creditTypeConditionInfo.setDefaultCondition(creditTypeConditionBack);
        assertThat(creditTypeConditionInfo.getDefaultCondition()).isEqualTo(creditTypeConditionBack);

        creditTypeConditionInfo.defaultCondition(null);
        assertThat(creditTypeConditionInfo.getDefaultCondition()).isNull();
    }
}
