package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.BasicInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BasicInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BasicInfo.class);
        BasicInfo basicInfo1 = getBasicInfoSample1();
        BasicInfo basicInfo2 = new BasicInfo();
        assertThat(basicInfo1).isNotEqualTo(basicInfo2);

        basicInfo2.setId(basicInfo1.getId());
        assertThat(basicInfo1).isEqualTo(basicInfo2);

        basicInfo2 = getBasicInfoSample2();
        assertThat(basicInfo1).isNotEqualTo(basicInfo2);
    }
}
