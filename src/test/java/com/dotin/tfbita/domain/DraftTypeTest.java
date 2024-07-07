package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CreditTypeConditionInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftRequestTypeTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeAccountInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeTestSamples.*;
import static com.dotin.tfbita.domain.DraftTypeTopicInfoTestSamples.*;
import static com.dotin.tfbita.domain.ObjectiveCategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.StringValueTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DraftTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftType.class);
        DraftType draftType1 = getDraftTypeSample1();
        DraftType draftType2 = new DraftType();
        assertThat(draftType1).isNotEqualTo(draftType2);

        draftType2.setId(draftType1.getId());
        assertThat(draftType1).isEqualTo(draftType2);

        draftType2 = getDraftTypeSample2();
        assertThat(draftType1).isNotEqualTo(draftType2);
    }

    @Test
    void typeTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draftType.setType(categoryElementBack);
        assertThat(draftType.getType()).isEqualTo(categoryElementBack);

        draftType.type(null);
        assertThat(draftType.getType()).isNull();
    }

    @Test
    void secondaryTypeTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draftType.setSecondaryType(categoryElementBack);
        assertThat(draftType.getSecondaryType()).isEqualTo(categoryElementBack);

        draftType.secondaryType(null);
        assertThat(draftType.getSecondaryType()).isNull();
    }

    @Test
    void divisionTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draftType.setDivision(categoryElementBack);
        assertThat(draftType.getDivision()).isEqualTo(categoryElementBack);

        draftType.division(null);
        assertThat(draftType.getDivision()).isNull();
    }

    @Test
    void topicInfoTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        DraftTypeTopicInfo draftTypeTopicInfoBack = getDraftTypeTopicInfoRandomSampleGenerator();

        draftType.setTopicInfo(draftTypeTopicInfoBack);
        assertThat(draftType.getTopicInfo()).isEqualTo(draftTypeTopicInfoBack);

        draftType.topicInfo(null);
        assertThat(draftType.getTopicInfo()).isNull();
    }

    @Test
    void conditionInfoTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        CreditTypeConditionInfo creditTypeConditionInfoBack = getCreditTypeConditionInfoRandomSampleGenerator();

        draftType.setConditionInfo(creditTypeConditionInfoBack);
        assertThat(draftType.getConditionInfo()).isEqualTo(creditTypeConditionInfoBack);

        draftType.conditionInfo(null);
        assertThat(draftType.getConditionInfo()).isNull();
    }

    @Test
    void accountInfoTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        DraftTypeAccountInfo draftTypeAccountInfoBack = getDraftTypeAccountInfoRandomSampleGenerator();

        draftType.setAccountInfo(draftTypeAccountInfoBack);
        assertThat(draftType.getAccountInfo()).isEqualTo(draftTypeAccountInfoBack);

        draftType.accountInfo(null);
        assertThat(draftType.getAccountInfo()).isNull();
    }

    @Test
    void requestTypeTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        DraftRequestType draftRequestTypeBack = getDraftRequestTypeRandomSampleGenerator();

        draftType.setRequestType(draftRequestTypeBack);
        assertThat(draftType.getRequestType()).isEqualTo(draftRequestTypeBack);

        draftType.requestType(null);
        assertThat(draftType.getRequestType()).isNull();
    }

    @Test
    void acceptableProductTypesTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        ObjectiveCategoryElement objectiveCategoryElementBack = getObjectiveCategoryElementRandomSampleGenerator();

        draftType.setAcceptableProductTypes(objectiveCategoryElementBack);
        assertThat(draftType.getAcceptableProductTypes()).isEqualTo(objectiveCategoryElementBack);

        draftType.acceptableProductTypes(null);
        assertThat(draftType.getAcceptableProductTypes()).isNull();
    }

    @Test
    void userGroupsTest() {
        DraftType draftType = getDraftTypeRandomSampleGenerator();
        StringValue stringValueBack = getStringValueRandomSampleGenerator();

        draftType.addUserGroups(stringValueBack);
        assertThat(draftType.getUserGroups()).containsOnly(stringValueBack);

        draftType.removeUserGroups(stringValueBack);
        assertThat(draftType.getUserGroups()).doesNotContain(stringValueBack);

        draftType.userGroups(new HashSet<>(Set.of(stringValueBack)));
        assertThat(draftType.getUserGroups()).containsOnly(stringValueBack);

        draftType.setUserGroups(new HashSet<>());
        assertThat(draftType.getUserGroups()).doesNotContain(stringValueBack);
    }
}
