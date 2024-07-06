package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.SwiftBicTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SwiftBicTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SwiftBic.class);
        SwiftBic swiftBic1 = getSwiftBicSample1();
        SwiftBic swiftBic2 = new SwiftBic();
        assertThat(swiftBic1).isNotEqualTo(swiftBic2);

        swiftBic2.setId(swiftBic1.getId());
        assertThat(swiftBic1).isEqualTo(swiftBic2);

        swiftBic2 = getSwiftBicSample2();
        assertThat(swiftBic1).isNotEqualTo(swiftBic2);
    }
}
