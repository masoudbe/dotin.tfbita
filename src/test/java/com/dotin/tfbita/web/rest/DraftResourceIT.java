package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.repository.DraftRepository;
import com.dotin.tfbita.service.DraftService;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.mapper.DraftMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DraftResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DraftResourceIT {

    private static final String DEFAULT_ADVISOR_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ADVISOR_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ADVISOR_REQUEST_DELETED = false;
    private static final Boolean UPDATED_ADVISOR_REQUEST_DELETED = true;

    private static final BigDecimal DEFAULT_AUDIT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_AUDIT_COST = new BigDecimal(2);

    private static final String DEFAULT_BENEFICIARY_INFO = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_INFO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BRANCH_STAMP_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_BRANCH_STAMP_COST = new BigDecimal(2);

    private static final String DEFAULT_CENTRAL_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CENTRAL_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CENTRAL_BANK_CODE_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_CENTRAL_BANK_CODE_REFERENCE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CHARGED_EXCHANGE_BROKER_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CHARGED_EXCHANGE_BROKER_AMOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_CHARTER_ACCEPTABLE = false;
    private static final Boolean UPDATED_CHARTER_ACCEPTABLE = true;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLETION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_COMPLETION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_COVERING_BANK_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COVERING_BANK_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_EXCHANGE_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_EXCHANGE_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_DELIVER_DURATION = 1;
    private static final Integer UPDATED_DELIVER_DURATION = 2;

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final String DEFAULT_DRAFT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DRAFT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DRAFT_ORDER_REG_PRODUCT_WORTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_DRAFT_ORDER_REG_PRODUCT_WORTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DRAFT_ORDER_REG_SERVICE_WORTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_DRAFT_ORDER_REG_SERVICE_WORTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DRAFT_ORDER_REG_TOTAL_WORTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_DRAFT_ORDER_REG_TOTAL_WORTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DRAFT_OTHER_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_DRAFT_OTHER_COST = new BigDecimal(2);

    private static final Boolean DEFAULT_HAS_TRANSPORT_JUSTIFICATION = false;
    private static final Boolean UPDATED_HAS_TRANSPORT_JUSTIFICATION = true;

    private static final BigDecimal DEFAULT_INSURANCE_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSURANCE_COST = new BigDecimal(2);

    private static final String DEFAULT_INSURANCE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_EXPIRY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_EXPIRY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INTERFACE_ADVISOR_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INTERFACE_ADVISOR_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_DATE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ISSUE_DRAFT_COMMISSION = new BigDecimal(1);
    private static final BigDecimal UPDATED_ISSUE_DRAFT_COMMISSION = new BigDecimal(2);

    private static final String DEFAULT_LAST_SHIPMENT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_SHIPMENT_DATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MAIN_CUSTOMER_NUMBER = 1L;
    private static final Long UPDATED_MAIN_CUSTOMER_NUMBER = 2L;

    private static final Boolean DEFAULT_MAKE_CERTIFICATION = false;
    private static final Boolean UPDATED_MAKE_CERTIFICATION = true;

    private static final Boolean DEFAULT_MULTIPLE_TRANSPORTABLE = false;
    private static final Boolean UPDATED_MULTIPLE_TRANSPORTABLE = true;

    private static final String DEFAULT_ORDER_REGISTRATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REGISTRATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_REGISTRATION_EXPIRY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REGISTRATION_EXPIRY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_OTHER_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTHER_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PAYMENT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYMENT_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_PERFORMA_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_EXPIRY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_EXPIRY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_POST_SWIFT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_POST_SWIFT_COST = new BigDecimal(2);

    private static final Boolean DEFAULT_PRODUCT_SOURCE_CHANGEABLE = false;
    private static final Boolean UPDATED_PRODUCT_SOURCE_CHANGEABLE = true;

    private static final Boolean DEFAULT_RECEIVE_ALL_COMMISSION = false;
    private static final Boolean UPDATED_RECEIVE_ALL_COMMISSION = true;

    private static final BigDecimal DEFAULT_REGISTERATION_JUSTIFICATION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGISTERATION_JUSTIFICATION_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_REQUEST_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SANCTION_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SANCTION_SERIAL = "BBBBBBBBBB";

    private static final Long DEFAULT_SERIAL = 1L;
    private static final Long UPDATED_SERIAL = 2L;

    private static final BigDecimal DEFAULT_SHIPMENT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHIPMENT_COST = new BigDecimal(2);

    private static final String DEFAULT_SOURCE_TRANSPORT_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TRANSPORT_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_SWIFT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_SWIFT_COMMENT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TRANSFER_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRANSFER_AMOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_TRANSPORT_VEHICLE_CHANGEABLE = false;
    private static final Boolean UPDATED_TRANSPORT_VEHICLE_CHANGEABLE = true;

    private static final String DEFAULT_PAYMENT_TOOL = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TOOL = "BBBBBBBBBB";

    private static final String DEFAULT_LETTER_NUMBER_TAZIRAT = "AAAAAAAAAA";
    private static final String UPDATED_LETTER_NUMBER_TAZIRAT = "BBBBBBBBBB";

    private static final String DEFAULT_LETTER_DATE_TAZIRAT = "AAAAAAAAAA";
    private static final String UPDATED_LETTER_DATE_TAZIRAT = "BBBBBBBBBB";

    private static final String DEFAULT_LOAN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOAN_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MIGRATIONAL = false;
    private static final Boolean UPDATED_IS_MIGRATIONAL = true;

    private static final Boolean DEFAULT_IS_NEW_CERTIFICATE = false;
    private static final Boolean UPDATED_IS_NEW_CERTIFICATE = true;

    private static final Boolean DEFAULT_IS_WITHOUT_PAYMENT = false;
    private static final Boolean UPDATED_IS_WITHOUT_PAYMENT = true;

    private static final String DEFAULT_MAIN_ACCOUNT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_ACCOUNT_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_REG_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REG_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCER_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCER_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTERATION_JUSTIFICATION_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTERATION_JUSTIFICATION_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATIONAL_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATIONAL_BRANCH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_SENDER_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_SENDER_BRANCH_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/drafts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftRepository draftRepository;

    @Mock
    private DraftRepository draftRepositoryMock;

    @Autowired
    private DraftMapper draftMapper;

    @Mock
    private DraftService draftServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftMockMvc;

    private Draft draft;

    private Draft insertedDraft;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Draft createEntity(EntityManager em) {
        Draft draft = new Draft()
            .advisorDepositNumber(DEFAULT_ADVISOR_DEPOSIT_NUMBER)
            .advisorRequestDeleted(DEFAULT_ADVISOR_REQUEST_DELETED)
            .auditCost(DEFAULT_AUDIT_COST)
            .beneficiaryInfo(DEFAULT_BENEFICIARY_INFO)
            .branchStampCost(DEFAULT_BRANCH_STAMP_COST)
            .centralBankCode(DEFAULT_CENTRAL_BANK_CODE)
            .centralBankCodeReference(DEFAULT_CENTRAL_BANK_CODE_REFERENCE)
            .chargedExchangeBrokerAmount(DEFAULT_CHARGED_EXCHANGE_BROKER_AMOUNT)
            .charterAcceptable(DEFAULT_CHARTER_ACCEPTABLE)
            .comment(DEFAULT_COMMENT)
            .completionDate(DEFAULT_COMPLETION_DATE)
            .coveringBankDepositNumber(DEFAULT_COVERING_BANK_DEPOSIT_NUMBER)
            .currencyExchangeDepositNumber(DEFAULT_CURRENCY_EXCHANGE_DEPOSIT_NUMBER)
            .customerDepositNumber(DEFAULT_CUSTOMER_DEPOSIT_NUMBER)
            .deliverDuration(DEFAULT_DELIVER_DURATION)
            .discount(DEFAULT_DISCOUNT)
            .draftNumber(DEFAULT_DRAFT_NUMBER)
            .draftOrderRegProductWorth(DEFAULT_DRAFT_ORDER_REG_PRODUCT_WORTH)
            .draftOrderRegServiceWorth(DEFAULT_DRAFT_ORDER_REG_SERVICE_WORTH)
            .draftOrderRegTotalWorth(DEFAULT_DRAFT_ORDER_REG_TOTAL_WORTH)
            .draftOtherCost(DEFAULT_DRAFT_OTHER_COST)
            .hasTransportJustification(DEFAULT_HAS_TRANSPORT_JUSTIFICATION)
            .insuranceCost(DEFAULT_INSURANCE_COST)
            .insuranceDate(DEFAULT_INSURANCE_DATE)
            .insuranceExpiryDate(DEFAULT_INSURANCE_EXPIRY_DATE)
            .insuranceNumber(DEFAULT_INSURANCE_NUMBER)
            .interfaceAdvisorDepositNumber(DEFAULT_INTERFACE_ADVISOR_DEPOSIT_NUMBER)
            .issueDate(DEFAULT_ISSUE_DATE)
            .issueDraftCommission(DEFAULT_ISSUE_DRAFT_COMMISSION)
            .lastShipmentDate(DEFAULT_LAST_SHIPMENT_DATE)
            .mainCustomerNumber(DEFAULT_MAIN_CUSTOMER_NUMBER)
            .makeCertification(DEFAULT_MAKE_CERTIFICATION)
            .multipleTransportable(DEFAULT_MULTIPLE_TRANSPORTABLE)
            .orderRegistrationDate(DEFAULT_ORDER_REGISTRATION_DATE)
            .orderRegistrationExpiryDate(DEFAULT_ORDER_REGISTRATION_EXPIRY_DATE)
            .orderRegistrationNumber(DEFAULT_ORDER_REGISTRATION_NUMBER)
            .otherCost(DEFAULT_OTHER_COST)
            .paymentAmount(DEFAULT_PAYMENT_AMOUNT)
            .performaDate(DEFAULT_PERFORMA_DATE)
            .performaExpiryDate(DEFAULT_PERFORMA_EXPIRY_DATE)
            .performaNumber(DEFAULT_PERFORMA_NUMBER)
            .postSwiftCost(DEFAULT_POST_SWIFT_COST)
            .productSourceChangeable(DEFAULT_PRODUCT_SOURCE_CHANGEABLE)
            .receiveAllCommission(DEFAULT_RECEIVE_ALL_COMMISSION)
            .registerationJustificationAmount(DEFAULT_REGISTERATION_JUSTIFICATION_AMOUNT)
            .requestDate(DEFAULT_REQUEST_DATE)
            .sanctionSerial(DEFAULT_SANCTION_SERIAL)
            .serial(DEFAULT_SERIAL)
            .shipmentCost(DEFAULT_SHIPMENT_COST)
            .sourceTransportPlace(DEFAULT_SOURCE_TRANSPORT_PLACE)
            .swiftComment(DEFAULT_SWIFT_COMMENT)
            .transferAmount(DEFAULT_TRANSFER_AMOUNT)
            .transportVehicleChangeable(DEFAULT_TRANSPORT_VEHICLE_CHANGEABLE)
            .paymentTool(DEFAULT_PAYMENT_TOOL)
            .letterNumberTazirat(DEFAULT_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(DEFAULT_LETTER_DATE_TAZIRAT)
            .loanNumber(DEFAULT_LOAN_NUMBER)
            .isMigrational(DEFAULT_IS_MIGRATIONAL)
            .isNewCertificate(DEFAULT_IS_NEW_CERTIFICATE)
            .isWithoutPayment(DEFAULT_IS_WITHOUT_PAYMENT)
            .mainAccountCurrencyCode(DEFAULT_MAIN_ACCOUNT_CURRENCY_CODE)
            .orderRegCurrencyCode(DEFAULT_ORDER_REG_CURRENCY_CODE)
            .chargedExchangeBrokerCurrencyCode(DEFAULT_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE)
            .destinationCountryCode(DEFAULT_DESTINATION_COUNTRY_CODE)
            .beneficiaryCountryCode(DEFAULT_BENEFICIARY_COUNTRY_CODE)
            .producerCountryCode(DEFAULT_PRODUCER_COUNTRY_CODE)
            .registerationJustificationCurrencyCode(DEFAULT_REGISTERATION_JUSTIFICATION_CURRENCY_CODE)
            .branchCode(DEFAULT_BRANCH_CODE)
            .operationalBranchCode(DEFAULT_OPERATIONAL_BRANCH_CODE)
            .certificateSenderBranchCode(DEFAULT_CERTIFICATE_SENDER_BRANCH_CODE);
        return draft;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Draft createUpdatedEntity(EntityManager em) {
        Draft draft = new Draft()
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .advisorRequestDeleted(UPDATED_ADVISOR_REQUEST_DELETED)
            .auditCost(UPDATED_AUDIT_COST)
            .beneficiaryInfo(UPDATED_BENEFICIARY_INFO)
            .branchStampCost(UPDATED_BRANCH_STAMP_COST)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .centralBankCodeReference(UPDATED_CENTRAL_BANK_CODE_REFERENCE)
            .chargedExchangeBrokerAmount(UPDATED_CHARGED_EXCHANGE_BROKER_AMOUNT)
            .charterAcceptable(UPDATED_CHARTER_ACCEPTABLE)
            .comment(UPDATED_COMMENT)
            .completionDate(UPDATED_COMPLETION_DATE)
            .coveringBankDepositNumber(UPDATED_COVERING_BANK_DEPOSIT_NUMBER)
            .currencyExchangeDepositNumber(UPDATED_CURRENCY_EXCHANGE_DEPOSIT_NUMBER)
            .customerDepositNumber(UPDATED_CUSTOMER_DEPOSIT_NUMBER)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .discount(UPDATED_DISCOUNT)
            .draftNumber(UPDATED_DRAFT_NUMBER)
            .draftOrderRegProductWorth(UPDATED_DRAFT_ORDER_REG_PRODUCT_WORTH)
            .draftOrderRegServiceWorth(UPDATED_DRAFT_ORDER_REG_SERVICE_WORTH)
            .draftOrderRegTotalWorth(UPDATED_DRAFT_ORDER_REG_TOTAL_WORTH)
            .draftOtherCost(UPDATED_DRAFT_OTHER_COST)
            .hasTransportJustification(UPDATED_HAS_TRANSPORT_JUSTIFICATION)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .insuranceDate(UPDATED_INSURANCE_DATE)
            .insuranceExpiryDate(UPDATED_INSURANCE_EXPIRY_DATE)
            .insuranceNumber(UPDATED_INSURANCE_NUMBER)
            .interfaceAdvisorDepositNumber(UPDATED_INTERFACE_ADVISOR_DEPOSIT_NUMBER)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDraftCommission(UPDATED_ISSUE_DRAFT_COMMISSION)
            .lastShipmentDate(UPDATED_LAST_SHIPMENT_DATE)
            .mainCustomerNumber(UPDATED_MAIN_CUSTOMER_NUMBER)
            .makeCertification(UPDATED_MAKE_CERTIFICATION)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationExpiryDate(UPDATED_ORDER_REGISTRATION_EXPIRY_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .otherCost(UPDATED_OTHER_COST)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .postSwiftCost(UPDATED_POST_SWIFT_COST)
            .productSourceChangeable(UPDATED_PRODUCT_SOURCE_CHANGEABLE)
            .receiveAllCommission(UPDATED_RECEIVE_ALL_COMMISSION)
            .registerationJustificationAmount(UPDATED_REGISTERATION_JUSTIFICATION_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .sanctionSerial(UPDATED_SANCTION_SERIAL)
            .serial(UPDATED_SERIAL)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .sourceTransportPlace(UPDATED_SOURCE_TRANSPORT_PLACE)
            .swiftComment(UPDATED_SWIFT_COMMENT)
            .transferAmount(UPDATED_TRANSFER_AMOUNT)
            .transportVehicleChangeable(UPDATED_TRANSPORT_VEHICLE_CHANGEABLE)
            .paymentTool(UPDATED_PAYMENT_TOOL)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .loanNumber(UPDATED_LOAN_NUMBER)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .isNewCertificate(UPDATED_IS_NEW_CERTIFICATE)
            .isWithoutPayment(UPDATED_IS_WITHOUT_PAYMENT)
            .mainAccountCurrencyCode(UPDATED_MAIN_ACCOUNT_CURRENCY_CODE)
            .orderRegCurrencyCode(UPDATED_ORDER_REG_CURRENCY_CODE)
            .chargedExchangeBrokerCurrencyCode(UPDATED_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE)
            .destinationCountryCode(UPDATED_DESTINATION_COUNTRY_CODE)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountryCode(UPDATED_PRODUCER_COUNTRY_CODE)
            .registerationJustificationCurrencyCode(UPDATED_REGISTERATION_JUSTIFICATION_CURRENCY_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .operationalBranchCode(UPDATED_OPERATIONAL_BRANCH_CODE)
            .certificateSenderBranchCode(UPDATED_CERTIFICATE_SENDER_BRANCH_CODE);
        return draft;
    }

    @BeforeEach
    public void initTest() {
        draft = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraft != null) {
            draftRepository.delete(insertedDraft);
            insertedDraft = null;
        }
    }

    @Test
    @Transactional
    void createDraft() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);
        var returnedDraftDTO = om.readValue(
            restDraftMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftDTO.class
        );

        // Validate the Draft in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraft = draftMapper.toEntity(returnedDraftDTO);
        assertDraftUpdatableFieldsEquals(returnedDraft, getPersistedDraft(returnedDraft));

        insertedDraft = returnedDraft;
    }

    @Test
    @Transactional
    void createDraftWithExistingId() throws Exception {
        // Create the Draft with an existing ID
        draft.setId(1L);
        DraftDTO draftDTO = draftMapper.toDto(draft);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDrafts() throws Exception {
        // Initialize the database
        insertedDraft = draftRepository.saveAndFlush(draft);

        // Get all the draftList
        restDraftMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draft.getId().intValue())))
            .andExpect(jsonPath("$.[*].advisorDepositNumber").value(hasItem(DEFAULT_ADVISOR_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].advisorRequestDeleted").value(hasItem(DEFAULT_ADVISOR_REQUEST_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].auditCost").value(hasItem(sameNumber(DEFAULT_AUDIT_COST))))
            .andExpect(jsonPath("$.[*].beneficiaryInfo").value(hasItem(DEFAULT_BENEFICIARY_INFO)))
            .andExpect(jsonPath("$.[*].branchStampCost").value(hasItem(sameNumber(DEFAULT_BRANCH_STAMP_COST))))
            .andExpect(jsonPath("$.[*].centralBankCode").value(hasItem(DEFAULT_CENTRAL_BANK_CODE)))
            .andExpect(jsonPath("$.[*].centralBankCodeReference").value(hasItem(DEFAULT_CENTRAL_BANK_CODE_REFERENCE)))
            .andExpect(jsonPath("$.[*].chargedExchangeBrokerAmount").value(hasItem(sameNumber(DEFAULT_CHARGED_EXCHANGE_BROKER_AMOUNT))))
            .andExpect(jsonPath("$.[*].charterAcceptable").value(hasItem(DEFAULT_CHARTER_ACCEPTABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].completionDate").value(hasItem(DEFAULT_COMPLETION_DATE)))
            .andExpect(jsonPath("$.[*].coveringBankDepositNumber").value(hasItem(DEFAULT_COVERING_BANK_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].currencyExchangeDepositNumber").value(hasItem(DEFAULT_CURRENCY_EXCHANGE_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].customerDepositNumber").value(hasItem(DEFAULT_CUSTOMER_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].deliverDuration").value(hasItem(DEFAULT_DELIVER_DURATION)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(sameNumber(DEFAULT_DISCOUNT))))
            .andExpect(jsonPath("$.[*].draftNumber").value(hasItem(DEFAULT_DRAFT_NUMBER)))
            .andExpect(jsonPath("$.[*].draftOrderRegProductWorth").value(hasItem(sameNumber(DEFAULT_DRAFT_ORDER_REG_PRODUCT_WORTH))))
            .andExpect(jsonPath("$.[*].draftOrderRegServiceWorth").value(hasItem(sameNumber(DEFAULT_DRAFT_ORDER_REG_SERVICE_WORTH))))
            .andExpect(jsonPath("$.[*].draftOrderRegTotalWorth").value(hasItem(sameNumber(DEFAULT_DRAFT_ORDER_REG_TOTAL_WORTH))))
            .andExpect(jsonPath("$.[*].draftOtherCost").value(hasItem(sameNumber(DEFAULT_DRAFT_OTHER_COST))))
            .andExpect(jsonPath("$.[*].hasTransportJustification").value(hasItem(DEFAULT_HAS_TRANSPORT_JUSTIFICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].insuranceCost").value(hasItem(sameNumber(DEFAULT_INSURANCE_COST))))
            .andExpect(jsonPath("$.[*].insuranceDate").value(hasItem(DEFAULT_INSURANCE_DATE)))
            .andExpect(jsonPath("$.[*].insuranceExpiryDate").value(hasItem(DEFAULT_INSURANCE_EXPIRY_DATE)))
            .andExpect(jsonPath("$.[*].insuranceNumber").value(hasItem(DEFAULT_INSURANCE_NUMBER)))
            .andExpect(jsonPath("$.[*].interfaceAdvisorDepositNumber").value(hasItem(DEFAULT_INTERFACE_ADVISOR_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE)))
            .andExpect(jsonPath("$.[*].issueDraftCommission").value(hasItem(sameNumber(DEFAULT_ISSUE_DRAFT_COMMISSION))))
            .andExpect(jsonPath("$.[*].lastShipmentDate").value(hasItem(DEFAULT_LAST_SHIPMENT_DATE)))
            .andExpect(jsonPath("$.[*].mainCustomerNumber").value(hasItem(DEFAULT_MAIN_CUSTOMER_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].makeCertification").value(hasItem(DEFAULT_MAKE_CERTIFICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].multipleTransportable").value(hasItem(DEFAULT_MULTIPLE_TRANSPORTABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].orderRegistrationDate").value(hasItem(DEFAULT_ORDER_REGISTRATION_DATE)))
            .andExpect(jsonPath("$.[*].orderRegistrationExpiryDate").value(hasItem(DEFAULT_ORDER_REGISTRATION_EXPIRY_DATE)))
            .andExpect(jsonPath("$.[*].orderRegistrationNumber").value(hasItem(DEFAULT_ORDER_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].otherCost").value(hasItem(sameNumber(DEFAULT_OTHER_COST))))
            .andExpect(jsonPath("$.[*].paymentAmount").value(hasItem(sameNumber(DEFAULT_PAYMENT_AMOUNT))))
            .andExpect(jsonPath("$.[*].performaDate").value(hasItem(DEFAULT_PERFORMA_DATE)))
            .andExpect(jsonPath("$.[*].performaExpiryDate").value(hasItem(DEFAULT_PERFORMA_EXPIRY_DATE)))
            .andExpect(jsonPath("$.[*].performaNumber").value(hasItem(DEFAULT_PERFORMA_NUMBER)))
            .andExpect(jsonPath("$.[*].postSwiftCost").value(hasItem(sameNumber(DEFAULT_POST_SWIFT_COST))))
            .andExpect(jsonPath("$.[*].productSourceChangeable").value(hasItem(DEFAULT_PRODUCT_SOURCE_CHANGEABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].receiveAllCommission").value(hasItem(DEFAULT_RECEIVE_ALL_COMMISSION.booleanValue())))
            .andExpect(
                jsonPath("$.[*].registerationJustificationAmount").value(hasItem(sameNumber(DEFAULT_REGISTERATION_JUSTIFICATION_AMOUNT)))
            )
            .andExpect(jsonPath("$.[*].requestDate").value(hasItem(DEFAULT_REQUEST_DATE)))
            .andExpect(jsonPath("$.[*].sanctionSerial").value(hasItem(DEFAULT_SANCTION_SERIAL)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.intValue())))
            .andExpect(jsonPath("$.[*].shipmentCost").value(hasItem(sameNumber(DEFAULT_SHIPMENT_COST))))
            .andExpect(jsonPath("$.[*].sourceTransportPlace").value(hasItem(DEFAULT_SOURCE_TRANSPORT_PLACE)))
            .andExpect(jsonPath("$.[*].swiftComment").value(hasItem(DEFAULT_SWIFT_COMMENT)))
            .andExpect(jsonPath("$.[*].transferAmount").value(hasItem(sameNumber(DEFAULT_TRANSFER_AMOUNT))))
            .andExpect(jsonPath("$.[*].transportVehicleChangeable").value(hasItem(DEFAULT_TRANSPORT_VEHICLE_CHANGEABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].paymentTool").value(hasItem(DEFAULT_PAYMENT_TOOL)))
            .andExpect(jsonPath("$.[*].letterNumberTazirat").value(hasItem(DEFAULT_LETTER_NUMBER_TAZIRAT)))
            .andExpect(jsonPath("$.[*].letterDateTazirat").value(hasItem(DEFAULT_LETTER_DATE_TAZIRAT)))
            .andExpect(jsonPath("$.[*].loanNumber").value(hasItem(DEFAULT_LOAN_NUMBER)))
            .andExpect(jsonPath("$.[*].isMigrational").value(hasItem(DEFAULT_IS_MIGRATIONAL.booleanValue())))
            .andExpect(jsonPath("$.[*].isNewCertificate").value(hasItem(DEFAULT_IS_NEW_CERTIFICATE.booleanValue())))
            .andExpect(jsonPath("$.[*].isWithoutPayment").value(hasItem(DEFAULT_IS_WITHOUT_PAYMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].mainAccountCurrencyCode").value(hasItem(DEFAULT_MAIN_ACCOUNT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].orderRegCurrencyCode").value(hasItem(DEFAULT_ORDER_REG_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].chargedExchangeBrokerCurrencyCode").value(hasItem(DEFAULT_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].destinationCountryCode").value(hasItem(DEFAULT_DESTINATION_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].beneficiaryCountryCode").value(hasItem(DEFAULT_BENEFICIARY_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].producerCountryCode").value(hasItem(DEFAULT_PRODUCER_COUNTRY_CODE)))
            .andExpect(
                jsonPath("$.[*].registerationJustificationCurrencyCode").value(hasItem(DEFAULT_REGISTERATION_JUSTIFICATION_CURRENCY_CODE))
            )
            .andExpect(jsonPath("$.[*].branchCode").value(hasItem(DEFAULT_BRANCH_CODE)))
            .andExpect(jsonPath("$.[*].operationalBranchCode").value(hasItem(DEFAULT_OPERATIONAL_BRANCH_CODE)))
            .andExpect(jsonPath("$.[*].certificateSenderBranchCode").value(hasItem(DEFAULT_CERTIFICATE_SENDER_BRANCH_CODE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDraftsWithEagerRelationshipsIsEnabled() throws Exception {
        when(draftServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDraftMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(draftServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDraftsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(draftServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDraftMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(draftRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getDraft() throws Exception {
        // Initialize the database
        insertedDraft = draftRepository.saveAndFlush(draft);

        // Get the draft
        restDraftMockMvc
            .perform(get(ENTITY_API_URL_ID, draft.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draft.getId().intValue()))
            .andExpect(jsonPath("$.advisorDepositNumber").value(DEFAULT_ADVISOR_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.advisorRequestDeleted").value(DEFAULT_ADVISOR_REQUEST_DELETED.booleanValue()))
            .andExpect(jsonPath("$.auditCost").value(sameNumber(DEFAULT_AUDIT_COST)))
            .andExpect(jsonPath("$.beneficiaryInfo").value(DEFAULT_BENEFICIARY_INFO))
            .andExpect(jsonPath("$.branchStampCost").value(sameNumber(DEFAULT_BRANCH_STAMP_COST)))
            .andExpect(jsonPath("$.centralBankCode").value(DEFAULT_CENTRAL_BANK_CODE))
            .andExpect(jsonPath("$.centralBankCodeReference").value(DEFAULT_CENTRAL_BANK_CODE_REFERENCE))
            .andExpect(jsonPath("$.chargedExchangeBrokerAmount").value(sameNumber(DEFAULT_CHARGED_EXCHANGE_BROKER_AMOUNT)))
            .andExpect(jsonPath("$.charterAcceptable").value(DEFAULT_CHARTER_ACCEPTABLE.booleanValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.completionDate").value(DEFAULT_COMPLETION_DATE))
            .andExpect(jsonPath("$.coveringBankDepositNumber").value(DEFAULT_COVERING_BANK_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.currencyExchangeDepositNumber").value(DEFAULT_CURRENCY_EXCHANGE_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.customerDepositNumber").value(DEFAULT_CUSTOMER_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.deliverDuration").value(DEFAULT_DELIVER_DURATION))
            .andExpect(jsonPath("$.discount").value(sameNumber(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.draftNumber").value(DEFAULT_DRAFT_NUMBER))
            .andExpect(jsonPath("$.draftOrderRegProductWorth").value(sameNumber(DEFAULT_DRAFT_ORDER_REG_PRODUCT_WORTH)))
            .andExpect(jsonPath("$.draftOrderRegServiceWorth").value(sameNumber(DEFAULT_DRAFT_ORDER_REG_SERVICE_WORTH)))
            .andExpect(jsonPath("$.draftOrderRegTotalWorth").value(sameNumber(DEFAULT_DRAFT_ORDER_REG_TOTAL_WORTH)))
            .andExpect(jsonPath("$.draftOtherCost").value(sameNumber(DEFAULT_DRAFT_OTHER_COST)))
            .andExpect(jsonPath("$.hasTransportJustification").value(DEFAULT_HAS_TRANSPORT_JUSTIFICATION.booleanValue()))
            .andExpect(jsonPath("$.insuranceCost").value(sameNumber(DEFAULT_INSURANCE_COST)))
            .andExpect(jsonPath("$.insuranceDate").value(DEFAULT_INSURANCE_DATE))
            .andExpect(jsonPath("$.insuranceExpiryDate").value(DEFAULT_INSURANCE_EXPIRY_DATE))
            .andExpect(jsonPath("$.insuranceNumber").value(DEFAULT_INSURANCE_NUMBER))
            .andExpect(jsonPath("$.interfaceAdvisorDepositNumber").value(DEFAULT_INTERFACE_ADVISOR_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE))
            .andExpect(jsonPath("$.issueDraftCommission").value(sameNumber(DEFAULT_ISSUE_DRAFT_COMMISSION)))
            .andExpect(jsonPath("$.lastShipmentDate").value(DEFAULT_LAST_SHIPMENT_DATE))
            .andExpect(jsonPath("$.mainCustomerNumber").value(DEFAULT_MAIN_CUSTOMER_NUMBER.intValue()))
            .andExpect(jsonPath("$.makeCertification").value(DEFAULT_MAKE_CERTIFICATION.booleanValue()))
            .andExpect(jsonPath("$.multipleTransportable").value(DEFAULT_MULTIPLE_TRANSPORTABLE.booleanValue()))
            .andExpect(jsonPath("$.orderRegistrationDate").value(DEFAULT_ORDER_REGISTRATION_DATE))
            .andExpect(jsonPath("$.orderRegistrationExpiryDate").value(DEFAULT_ORDER_REGISTRATION_EXPIRY_DATE))
            .andExpect(jsonPath("$.orderRegistrationNumber").value(DEFAULT_ORDER_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.otherCost").value(sameNumber(DEFAULT_OTHER_COST)))
            .andExpect(jsonPath("$.paymentAmount").value(sameNumber(DEFAULT_PAYMENT_AMOUNT)))
            .andExpect(jsonPath("$.performaDate").value(DEFAULT_PERFORMA_DATE))
            .andExpect(jsonPath("$.performaExpiryDate").value(DEFAULT_PERFORMA_EXPIRY_DATE))
            .andExpect(jsonPath("$.performaNumber").value(DEFAULT_PERFORMA_NUMBER))
            .andExpect(jsonPath("$.postSwiftCost").value(sameNumber(DEFAULT_POST_SWIFT_COST)))
            .andExpect(jsonPath("$.productSourceChangeable").value(DEFAULT_PRODUCT_SOURCE_CHANGEABLE.booleanValue()))
            .andExpect(jsonPath("$.receiveAllCommission").value(DEFAULT_RECEIVE_ALL_COMMISSION.booleanValue()))
            .andExpect(jsonPath("$.registerationJustificationAmount").value(sameNumber(DEFAULT_REGISTERATION_JUSTIFICATION_AMOUNT)))
            .andExpect(jsonPath("$.requestDate").value(DEFAULT_REQUEST_DATE))
            .andExpect(jsonPath("$.sanctionSerial").value(DEFAULT_SANCTION_SERIAL))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.intValue()))
            .andExpect(jsonPath("$.shipmentCost").value(sameNumber(DEFAULT_SHIPMENT_COST)))
            .andExpect(jsonPath("$.sourceTransportPlace").value(DEFAULT_SOURCE_TRANSPORT_PLACE))
            .andExpect(jsonPath("$.swiftComment").value(DEFAULT_SWIFT_COMMENT))
            .andExpect(jsonPath("$.transferAmount").value(sameNumber(DEFAULT_TRANSFER_AMOUNT)))
            .andExpect(jsonPath("$.transportVehicleChangeable").value(DEFAULT_TRANSPORT_VEHICLE_CHANGEABLE.booleanValue()))
            .andExpect(jsonPath("$.paymentTool").value(DEFAULT_PAYMENT_TOOL))
            .andExpect(jsonPath("$.letterNumberTazirat").value(DEFAULT_LETTER_NUMBER_TAZIRAT))
            .andExpect(jsonPath("$.letterDateTazirat").value(DEFAULT_LETTER_DATE_TAZIRAT))
            .andExpect(jsonPath("$.loanNumber").value(DEFAULT_LOAN_NUMBER))
            .andExpect(jsonPath("$.isMigrational").value(DEFAULT_IS_MIGRATIONAL.booleanValue()))
            .andExpect(jsonPath("$.isNewCertificate").value(DEFAULT_IS_NEW_CERTIFICATE.booleanValue()))
            .andExpect(jsonPath("$.isWithoutPayment").value(DEFAULT_IS_WITHOUT_PAYMENT.booleanValue()))
            .andExpect(jsonPath("$.mainAccountCurrencyCode").value(DEFAULT_MAIN_ACCOUNT_CURRENCY_CODE))
            .andExpect(jsonPath("$.orderRegCurrencyCode").value(DEFAULT_ORDER_REG_CURRENCY_CODE))
            .andExpect(jsonPath("$.chargedExchangeBrokerCurrencyCode").value(DEFAULT_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE))
            .andExpect(jsonPath("$.destinationCountryCode").value(DEFAULT_DESTINATION_COUNTRY_CODE))
            .andExpect(jsonPath("$.beneficiaryCountryCode").value(DEFAULT_BENEFICIARY_COUNTRY_CODE))
            .andExpect(jsonPath("$.producerCountryCode").value(DEFAULT_PRODUCER_COUNTRY_CODE))
            .andExpect(jsonPath("$.registerationJustificationCurrencyCode").value(DEFAULT_REGISTERATION_JUSTIFICATION_CURRENCY_CODE))
            .andExpect(jsonPath("$.branchCode").value(DEFAULT_BRANCH_CODE))
            .andExpect(jsonPath("$.operationalBranchCode").value(DEFAULT_OPERATIONAL_BRANCH_CODE))
            .andExpect(jsonPath("$.certificateSenderBranchCode").value(DEFAULT_CERTIFICATE_SENDER_BRANCH_CODE));
    }

    @Test
    @Transactional
    void getNonExistingDraft() throws Exception {
        // Get the draft
        restDraftMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraft() throws Exception {
        // Initialize the database
        insertedDraft = draftRepository.saveAndFlush(draft);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draft
        Draft updatedDraft = draftRepository.findById(draft.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraft are not directly saved in db
        em.detach(updatedDraft);
        updatedDraft
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .advisorRequestDeleted(UPDATED_ADVISOR_REQUEST_DELETED)
            .auditCost(UPDATED_AUDIT_COST)
            .beneficiaryInfo(UPDATED_BENEFICIARY_INFO)
            .branchStampCost(UPDATED_BRANCH_STAMP_COST)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .centralBankCodeReference(UPDATED_CENTRAL_BANK_CODE_REFERENCE)
            .chargedExchangeBrokerAmount(UPDATED_CHARGED_EXCHANGE_BROKER_AMOUNT)
            .charterAcceptable(UPDATED_CHARTER_ACCEPTABLE)
            .comment(UPDATED_COMMENT)
            .completionDate(UPDATED_COMPLETION_DATE)
            .coveringBankDepositNumber(UPDATED_COVERING_BANK_DEPOSIT_NUMBER)
            .currencyExchangeDepositNumber(UPDATED_CURRENCY_EXCHANGE_DEPOSIT_NUMBER)
            .customerDepositNumber(UPDATED_CUSTOMER_DEPOSIT_NUMBER)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .discount(UPDATED_DISCOUNT)
            .draftNumber(UPDATED_DRAFT_NUMBER)
            .draftOrderRegProductWorth(UPDATED_DRAFT_ORDER_REG_PRODUCT_WORTH)
            .draftOrderRegServiceWorth(UPDATED_DRAFT_ORDER_REG_SERVICE_WORTH)
            .draftOrderRegTotalWorth(UPDATED_DRAFT_ORDER_REG_TOTAL_WORTH)
            .draftOtherCost(UPDATED_DRAFT_OTHER_COST)
            .hasTransportJustification(UPDATED_HAS_TRANSPORT_JUSTIFICATION)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .insuranceDate(UPDATED_INSURANCE_DATE)
            .insuranceExpiryDate(UPDATED_INSURANCE_EXPIRY_DATE)
            .insuranceNumber(UPDATED_INSURANCE_NUMBER)
            .interfaceAdvisorDepositNumber(UPDATED_INTERFACE_ADVISOR_DEPOSIT_NUMBER)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDraftCommission(UPDATED_ISSUE_DRAFT_COMMISSION)
            .lastShipmentDate(UPDATED_LAST_SHIPMENT_DATE)
            .mainCustomerNumber(UPDATED_MAIN_CUSTOMER_NUMBER)
            .makeCertification(UPDATED_MAKE_CERTIFICATION)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationExpiryDate(UPDATED_ORDER_REGISTRATION_EXPIRY_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .otherCost(UPDATED_OTHER_COST)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .postSwiftCost(UPDATED_POST_SWIFT_COST)
            .productSourceChangeable(UPDATED_PRODUCT_SOURCE_CHANGEABLE)
            .receiveAllCommission(UPDATED_RECEIVE_ALL_COMMISSION)
            .registerationJustificationAmount(UPDATED_REGISTERATION_JUSTIFICATION_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .sanctionSerial(UPDATED_SANCTION_SERIAL)
            .serial(UPDATED_SERIAL)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .sourceTransportPlace(UPDATED_SOURCE_TRANSPORT_PLACE)
            .swiftComment(UPDATED_SWIFT_COMMENT)
            .transferAmount(UPDATED_TRANSFER_AMOUNT)
            .transportVehicleChangeable(UPDATED_TRANSPORT_VEHICLE_CHANGEABLE)
            .paymentTool(UPDATED_PAYMENT_TOOL)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .loanNumber(UPDATED_LOAN_NUMBER)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .isNewCertificate(UPDATED_IS_NEW_CERTIFICATE)
            .isWithoutPayment(UPDATED_IS_WITHOUT_PAYMENT)
            .mainAccountCurrencyCode(UPDATED_MAIN_ACCOUNT_CURRENCY_CODE)
            .orderRegCurrencyCode(UPDATED_ORDER_REG_CURRENCY_CODE)
            .chargedExchangeBrokerCurrencyCode(UPDATED_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE)
            .destinationCountryCode(UPDATED_DESTINATION_COUNTRY_CODE)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountryCode(UPDATED_PRODUCER_COUNTRY_CODE)
            .registerationJustificationCurrencyCode(UPDATED_REGISTERATION_JUSTIFICATION_CURRENCY_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .operationalBranchCode(UPDATED_OPERATIONAL_BRANCH_CODE)
            .certificateSenderBranchCode(UPDATED_CERTIFICATE_SENDER_BRANCH_CODE);
        DraftDTO draftDTO = draftMapper.toDto(updatedDraft);

        restDraftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftDTO))
            )
            .andExpect(status().isOk());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftToMatchAllProperties(updatedDraft);
    }

    @Test
    @Transactional
    void putNonExistingDraft() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draft.setId(longCount.incrementAndGet());

        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraft() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draft.setId(longCount.incrementAndGet());

        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraft() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draft.setId(longCount.incrementAndGet());

        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftWithPatch() throws Exception {
        // Initialize the database
        insertedDraft = draftRepository.saveAndFlush(draft);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draft using partial update
        Draft partialUpdatedDraft = new Draft();
        partialUpdatedDraft.setId(draft.getId());

        partialUpdatedDraft
            .advisorRequestDeleted(UPDATED_ADVISOR_REQUEST_DELETED)
            .beneficiaryInfo(UPDATED_BENEFICIARY_INFO)
            .branchStampCost(UPDATED_BRANCH_STAMP_COST)
            .centralBankCodeReference(UPDATED_CENTRAL_BANK_CODE_REFERENCE)
            .charterAcceptable(UPDATED_CHARTER_ACCEPTABLE)
            .completionDate(UPDATED_COMPLETION_DATE)
            .coveringBankDepositNumber(UPDATED_COVERING_BANK_DEPOSIT_NUMBER)
            .currencyExchangeDepositNumber(UPDATED_CURRENCY_EXCHANGE_DEPOSIT_NUMBER)
            .draftOtherCost(UPDATED_DRAFT_OTHER_COST)
            .insuranceNumber(UPDATED_INSURANCE_NUMBER)
            .interfaceAdvisorDepositNumber(UPDATED_INTERFACE_ADVISOR_DEPOSIT_NUMBER)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDraftCommission(UPDATED_ISSUE_DRAFT_COMMISSION)
            .lastShipmentDate(UPDATED_LAST_SHIPMENT_DATE)
            .makeCertification(UPDATED_MAKE_CERTIFICATION)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .orderRegistrationExpiryDate(UPDATED_ORDER_REGISTRATION_EXPIRY_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .requestDate(UPDATED_REQUEST_DATE)
            .sourceTransportPlace(UPDATED_SOURCE_TRANSPORT_PLACE)
            .swiftComment(UPDATED_SWIFT_COMMENT)
            .transferAmount(UPDATED_TRANSFER_AMOUNT)
            .paymentTool(UPDATED_PAYMENT_TOOL)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .loanNumber(UPDATED_LOAN_NUMBER)
            .isNewCertificate(UPDATED_IS_NEW_CERTIFICATE)
            .destinationCountryCode(UPDATED_DESTINATION_COUNTRY_CODE)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .registerationJustificationCurrencyCode(UPDATED_REGISTERATION_JUSTIFICATION_CURRENCY_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .operationalBranchCode(UPDATED_OPERATIONAL_BRANCH_CODE);

        restDraftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraft.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraft))
            )
            .andExpect(status().isOk());

        // Validate the Draft in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDraft, draft), getPersistedDraft(draft));
    }

    @Test
    @Transactional
    void fullUpdateDraftWithPatch() throws Exception {
        // Initialize the database
        insertedDraft = draftRepository.saveAndFlush(draft);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draft using partial update
        Draft partialUpdatedDraft = new Draft();
        partialUpdatedDraft.setId(draft.getId());

        partialUpdatedDraft
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .advisorRequestDeleted(UPDATED_ADVISOR_REQUEST_DELETED)
            .auditCost(UPDATED_AUDIT_COST)
            .beneficiaryInfo(UPDATED_BENEFICIARY_INFO)
            .branchStampCost(UPDATED_BRANCH_STAMP_COST)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .centralBankCodeReference(UPDATED_CENTRAL_BANK_CODE_REFERENCE)
            .chargedExchangeBrokerAmount(UPDATED_CHARGED_EXCHANGE_BROKER_AMOUNT)
            .charterAcceptable(UPDATED_CHARTER_ACCEPTABLE)
            .comment(UPDATED_COMMENT)
            .completionDate(UPDATED_COMPLETION_DATE)
            .coveringBankDepositNumber(UPDATED_COVERING_BANK_DEPOSIT_NUMBER)
            .currencyExchangeDepositNumber(UPDATED_CURRENCY_EXCHANGE_DEPOSIT_NUMBER)
            .customerDepositNumber(UPDATED_CUSTOMER_DEPOSIT_NUMBER)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .discount(UPDATED_DISCOUNT)
            .draftNumber(UPDATED_DRAFT_NUMBER)
            .draftOrderRegProductWorth(UPDATED_DRAFT_ORDER_REG_PRODUCT_WORTH)
            .draftOrderRegServiceWorth(UPDATED_DRAFT_ORDER_REG_SERVICE_WORTH)
            .draftOrderRegTotalWorth(UPDATED_DRAFT_ORDER_REG_TOTAL_WORTH)
            .draftOtherCost(UPDATED_DRAFT_OTHER_COST)
            .hasTransportJustification(UPDATED_HAS_TRANSPORT_JUSTIFICATION)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .insuranceDate(UPDATED_INSURANCE_DATE)
            .insuranceExpiryDate(UPDATED_INSURANCE_EXPIRY_DATE)
            .insuranceNumber(UPDATED_INSURANCE_NUMBER)
            .interfaceAdvisorDepositNumber(UPDATED_INTERFACE_ADVISOR_DEPOSIT_NUMBER)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDraftCommission(UPDATED_ISSUE_DRAFT_COMMISSION)
            .lastShipmentDate(UPDATED_LAST_SHIPMENT_DATE)
            .mainCustomerNumber(UPDATED_MAIN_CUSTOMER_NUMBER)
            .makeCertification(UPDATED_MAKE_CERTIFICATION)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationExpiryDate(UPDATED_ORDER_REGISTRATION_EXPIRY_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .otherCost(UPDATED_OTHER_COST)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .postSwiftCost(UPDATED_POST_SWIFT_COST)
            .productSourceChangeable(UPDATED_PRODUCT_SOURCE_CHANGEABLE)
            .receiveAllCommission(UPDATED_RECEIVE_ALL_COMMISSION)
            .registerationJustificationAmount(UPDATED_REGISTERATION_JUSTIFICATION_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .sanctionSerial(UPDATED_SANCTION_SERIAL)
            .serial(UPDATED_SERIAL)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .sourceTransportPlace(UPDATED_SOURCE_TRANSPORT_PLACE)
            .swiftComment(UPDATED_SWIFT_COMMENT)
            .transferAmount(UPDATED_TRANSFER_AMOUNT)
            .transportVehicleChangeable(UPDATED_TRANSPORT_VEHICLE_CHANGEABLE)
            .paymentTool(UPDATED_PAYMENT_TOOL)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .loanNumber(UPDATED_LOAN_NUMBER)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .isNewCertificate(UPDATED_IS_NEW_CERTIFICATE)
            .isWithoutPayment(UPDATED_IS_WITHOUT_PAYMENT)
            .mainAccountCurrencyCode(UPDATED_MAIN_ACCOUNT_CURRENCY_CODE)
            .orderRegCurrencyCode(UPDATED_ORDER_REG_CURRENCY_CODE)
            .chargedExchangeBrokerCurrencyCode(UPDATED_CHARGED_EXCHANGE_BROKER_CURRENCY_CODE)
            .destinationCountryCode(UPDATED_DESTINATION_COUNTRY_CODE)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountryCode(UPDATED_PRODUCER_COUNTRY_CODE)
            .registerationJustificationCurrencyCode(UPDATED_REGISTERATION_JUSTIFICATION_CURRENCY_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .operationalBranchCode(UPDATED_OPERATIONAL_BRANCH_CODE)
            .certificateSenderBranchCode(UPDATED_CERTIFICATE_SENDER_BRANCH_CODE);

        restDraftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraft.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraft))
            )
            .andExpect(status().isOk());

        // Validate the Draft in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftUpdatableFieldsEquals(partialUpdatedDraft, getPersistedDraft(partialUpdatedDraft));
    }

    @Test
    @Transactional
    void patchNonExistingDraft() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draft.setId(longCount.incrementAndGet());

        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraft() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draft.setId(longCount.incrementAndGet());

        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraft() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draft.setId(longCount.incrementAndGet());

        // Create the Draft
        DraftDTO draftDTO = draftMapper.toDto(draft);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Draft in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraft() throws Exception {
        // Initialize the database
        insertedDraft = draftRepository.saveAndFlush(draft);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draft
        restDraftMockMvc
            .perform(delete(ENTITY_API_URL_ID, draft.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Draft getPersistedDraft(Draft draft) {
        return draftRepository.findById(draft.getId()).orElseThrow();
    }

    protected void assertPersistedDraftToMatchAllProperties(Draft expectedDraft) {
        assertDraftAllPropertiesEquals(expectedDraft, getPersistedDraft(expectedDraft));
    }

    protected void assertPersistedDraftToMatchUpdatableProperties(Draft expectedDraft) {
        assertDraftAllUpdatablePropertiesEquals(expectedDraft, getPersistedDraft(expectedDraft));
    }
}
