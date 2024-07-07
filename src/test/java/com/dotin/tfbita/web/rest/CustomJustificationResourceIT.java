package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CustomJustificationAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.repository.CustomJustificationRepository;
import com.dotin.tfbita.service.CustomJustificationService;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import com.dotin.tfbita.service.mapper.CustomJustificationMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
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
 * Integration tests for the {@link CustomJustificationResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CustomJustificationResourceIT {

    private static final Long DEFAULT_AGENT_ID = 1L;
    private static final Long UPDATED_AGENT_ID = 2L;

    private static final String DEFAULT_AGRICULTURAL_PUBLIC_POLICIES = "AAAAAAAAAA";
    private static final String UPDATED_AGRICULTURAL_PUBLIC_POLICIES = "BBBBBBBBBB";

    private static final String DEFAULT_ASSESSMENT_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_ASSESSMENT_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHED_DOCUMENTS = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHED_DOCUMENTS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BALANCE_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE_RATE = new BigDecimal(2);

    private static final String DEFAULT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BORDER_TRANSPORT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BORDER_TRANSPORT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BOX_COUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BOX_COUNT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BOX_MARKS = "AAAAAAAAAA";
    private static final String UPDATED_BOX_MARKS = "BBBBBBBBBB";

    private static final String DEFAULT_CARGO_INDEX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CARGO_INDEX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CENTRAL_BANK_CREDIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CENTRAL_BANK_CREDIT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_CONSTRUCTOR_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CONSTRUCTOR_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COST_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_COST_DETAILS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_COTTAGE_NUMBER = new BigDecimal(1);
    private static final BigDecimal UPDATED_COTTAGE_NUMBER = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CREDIT_EQUIVALENT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREDIT_EQUIVALENT_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CURRENCY_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_RATE = new BigDecimal(2);

    private static final String DEFAULT_CURRENCY_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_COMPANY_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEST_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_CUSTOM = "AAAAAAAAAA";
    private static final String UPDATED_DEST_CUSTOM = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_CUSTOM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEST_CUSTOM_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISCIPLINARY_DOCUMENTS_ISSUED = false;
    private static final Boolean UPDATED_DISCIPLINARY_DOCUMENTS_ISSUED = true;

    private static final BigDecimal DEFAULT_DISCOUNT_PERCENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT_PERCENT = new BigDecimal(2);

    private static final String DEFAULT_EIGHT_DIGIT_ORDER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EIGHT_DIGIT_ORDER_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_ENTRANCE_CUSTOM_COMPANY = 1L;
    private static final Long UPDATED_ENTRANCE_CUSTOM_COMPANY = 2L;

    private static final Long DEFAULT_ENTRANCE_CUSTOM_COMPANY_ID = 1L;
    private static final Long UPDATED_ENTRANCE_CUSTOM_COMPANY_ID = 2L;

    private static final String DEFAULT_EVACUATION_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_EVACUATION_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_EVALUATION_METHOD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EVALUATION_METHOD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_EXPORT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORTER = "AAAAAAAAAA";
    private static final String UPDATED_EXPORTER = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORTER_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXPORTER_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENSION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_EXTENSION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_FACTOR_TOTAL_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FACTOR_TOTAL_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_FREIGHT_INDEX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FREIGHT_INDEX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FRIGHT_LETTER = "AAAAAAAAAA";
    private static final String UPDATED_FRIGHT_LETTER = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORT_LICENCE = "AAAAAAAAAA";
    private static final String UPDATED_IMPORT_LICENCE = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORT_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_IMPORT_LICENSE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_IMPURE_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMPURE_WEIGHT = new BigDecimal(2);

    private static final String DEFAULT_INDICES = "AAAAAAAAAA";
    private static final String UPDATED_INDICES = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_TRANSPORT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_TRANSPORT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUANCE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUANCE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_ITEMS = 1;
    private static final Integer UPDATED_ITEMS = 2;

    private static final String DEFAULT_JUSTIFICATION_AGENT = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATION_AGENT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_JUSTIFICATION_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_JUSTIFICATION_CURRENCY_RATE = new BigDecimal(2);

    private static final String DEFAULT_LICENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LICENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MAKE_CERTIFICATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MAKE_CERTIFICATE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_NEW_BORDER_TRANSPORT_TYPE = 1L;
    private static final Long UPDATED_NEW_BORDER_TRANSPORT_TYPE = 2L;

    private static final Long DEFAULT_NEW_EVACUATION_PLACE = 1L;
    private static final Long UPDATED_NEW_EVACUATION_PLACE = 2L;

    private static final Long DEFAULT_NEW_INTERNAL_TRANSPORT_TYPE = 1L;
    private static final Long UPDATED_NEW_INTERNAL_TRANSPORT_TYPE = 2L;

    private static final BigDecimal DEFAULT_NEW_PRODUCT_ITEM_CUSTOM_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_NEW_PRODUCT_ITEM_CUSTOM_VALUE = new BigDecimal(2);

    private static final String DEFAULT_ORDER_REGISTRATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REGISTRATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAPERS = 1;
    private static final Integer UPDATED_PAPERS = 2;

    private static final String DEFAULT_PAYMENT_CONDITIONS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CONDITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_PREFERENCES = "AAAAAAAAAA";
    private static final String UPDATED_PREFERENCES = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCT_BOX_COUNT = 1;
    private static final Integer UPDATED_PRODUCT_BOX_COUNT = 2;

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRODUCT_ITEM_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRODUCT_ITEM_COST = new BigDecimal(2);

    private static final String DEFAULT_PRODUCT_ITEM_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ITEM_COUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ITEM_CUSTOM_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ITEM_CUSTOM_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ITEM_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ITEM_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_MEASURE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_MEASURE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_RELEASE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_RELEASE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRODUCT_WORTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRODUCT_WORTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PROFIT_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROFIT_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PURE_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PURE_WEIGHT = new BigDecimal(2);

    private static final String DEFAULT_QUOTA = "AAAAAAAAAA";
    private static final String UPDATED_QUOTA = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final Integer DEFAULT_REFERENCE_NUMBER = 1;
    private static final Integer UPDATED_REFERENCE_NUMBER = 2;

    private static final String DEFAULT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED = false;
    private static final Boolean UPDATED_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED = true;

    private static final BigDecimal DEFAULT_RIGHTS_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_RIGHTS_RATE = new BigDecimal(2);

    private static final String DEFAULT_TRADING_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WAREHOUSE_FACTOR_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WAREHOUSE_FACTOR_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CONSTRUCTOR_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONSTRUCTOR_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_JUSTIFICATION_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATION_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_CURRENCY_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MIGRATIONAL = false;
    private static final Boolean UPDATED_IS_MIGRATIONAL = true;

    private static final byte[] DEFAULT_ORIGINAL_LETTER_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ORIGINAL_LETTER_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_LETTER_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LETTER_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LETTER_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LETTER_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SOURCE_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/custom-justifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomJustificationRepository customJustificationRepository;

    @Mock
    private CustomJustificationRepository customJustificationRepositoryMock;

    @Autowired
    private CustomJustificationMapper customJustificationMapper;

    @Mock
    private CustomJustificationService customJustificationServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomJustificationMockMvc;

    private CustomJustification customJustification;

    private CustomJustification insertedCustomJustification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomJustification createEntity(EntityManager em) {
        CustomJustification customJustification = new CustomJustification()
            .agentId(DEFAULT_AGENT_ID)
            .agriculturalPublicPolicies(DEFAULT_AGRICULTURAL_PUBLIC_POLICIES)
            .assessmentPlace(DEFAULT_ASSESSMENT_PLACE)
            .attachedDocuments(DEFAULT_ATTACHED_DOCUMENTS)
            .balanceRate(DEFAULT_BALANCE_RATE)
            .bankCode(DEFAULT_BANK_CODE)
            .borderTransportType(DEFAULT_BORDER_TRANSPORT_TYPE)
            .boxCountType(DEFAULT_BOX_COUNT_TYPE)
            .boxMarks(DEFAULT_BOX_MARKS)
            .cargoIndexNumber(DEFAULT_CARGO_INDEX_NUMBER)
            .centralBankCreditCode(DEFAULT_CENTRAL_BANK_CREDIT_CODE)
            .comments(DEFAULT_COMMENTS)
            .constructorCountryCode(DEFAULT_CONSTRUCTOR_COUNTRY_CODE)
            .costDetails(DEFAULT_COST_DETAILS)
            .cottageNumber(DEFAULT_COTTAGE_NUMBER)
            .creditEquivalentAmount(DEFAULT_CREDIT_EQUIVALENT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .currencyAmount(DEFAULT_CURRENCY_AMOUNT)
            .currencyRate(DEFAULT_CURRENCY_RATE)
            .currencySwiftCode(DEFAULT_CURRENCY_SWIFT_CODE)
            .customCompanyCode(DEFAULT_CUSTOM_COMPANY_CODE)
            .customerId(DEFAULT_CUSTOMER_ID)
            .date(DEFAULT_DATE)
            .destCountryCode(DEFAULT_DEST_COUNTRY_CODE)
            .destCustom(DEFAULT_DEST_CUSTOM)
            .destCustomCode(DEFAULT_DEST_CUSTOM_CODE)
            .disciplinaryDocumentsIssued(DEFAULT_DISCIPLINARY_DOCUMENTS_ISSUED)
            .discountPercent(DEFAULT_DISCOUNT_PERCENT)
            .eightDigitOrderCode(DEFAULT_EIGHT_DIGIT_ORDER_CODE)
            .entranceCustomCompany(DEFAULT_ENTRANCE_CUSTOM_COMPANY)
            .entranceCustomCompanyId(DEFAULT_ENTRANCE_CUSTOM_COMPANY_ID)
            .evacuationPlace(DEFAULT_EVACUATION_PLACE)
            .evaluationMethodCode(DEFAULT_EVALUATION_METHOD_CODE)
            .exportDate(DEFAULT_EXPORT_DATE)
            .exporter(DEFAULT_EXPORTER)
            .exporterCountryCode(DEFAULT_EXPORTER_COUNTRY_CODE)
            .extensionDate(DEFAULT_EXTENSION_DATE)
            .factorTotalAmount(DEFAULT_FACTOR_TOTAL_AMOUNT)
            .freightIndexNumber(DEFAULT_FREIGHT_INDEX_NUMBER)
            .frightLetter(DEFAULT_FRIGHT_LETTER)
            .importLicence(DEFAULT_IMPORT_LICENCE)
            .importLicense(DEFAULT_IMPORT_LICENSE)
            .impureWeight(DEFAULT_IMPURE_WEIGHT)
            .indices(DEFAULT_INDICES)
            .internalTransportType(DEFAULT_INTERNAL_TRANSPORT_TYPE)
            .issuanceDate(DEFAULT_ISSUANCE_DATE)
            .itemNumber(DEFAULT_ITEM_NUMBER)
            .items(DEFAULT_ITEMS)
            .justificationAgent(DEFAULT_JUSTIFICATION_AGENT)
            .justificationCurrencyRate(DEFAULT_JUSTIFICATION_CURRENCY_RATE)
            .licenceNumber(DEFAULT_LICENCE_NUMBER)
            .makeCertificateNumber(DEFAULT_MAKE_CERTIFICATE_NUMBER)
            .newBorderTransportType(DEFAULT_NEW_BORDER_TRANSPORT_TYPE)
            .newEvacuationPlace(DEFAULT_NEW_EVACUATION_PLACE)
            .newInternalTransportType(DEFAULT_NEW_INTERNAL_TRANSPORT_TYPE)
            .newProductItemCustomValue(DEFAULT_NEW_PRODUCT_ITEM_CUSTOM_VALUE)
            .orderRegistrationDate(DEFAULT_ORDER_REGISTRATION_DATE)
            .orderRegistrationNumber(DEFAULT_ORDER_REGISTRATION_NUMBER)
            .papers(DEFAULT_PAPERS)
            .paymentConditions(DEFAULT_PAYMENT_CONDITIONS)
            .preferences(DEFAULT_PREFERENCES)
            .procedure(DEFAULT_PROCEDURE)
            .productBoxCount(DEFAULT_PRODUCT_BOX_COUNT)
            .productCode(DEFAULT_PRODUCT_CODE)
            .productItemCost(DEFAULT_PRODUCT_ITEM_COST)
            .productItemCount(DEFAULT_PRODUCT_ITEM_COUNT)
            .productItemCustomValue(DEFAULT_PRODUCT_ITEM_CUSTOM_VALUE)
            .productItemValue(DEFAULT_PRODUCT_ITEM_VALUE)
            .productMeasure(DEFAULT_PRODUCT_MEASURE)
            .productOwner(DEFAULT_PRODUCT_OWNER)
            .productReleaseDate(DEFAULT_PRODUCT_RELEASE_DATE)
            .productType(DEFAULT_PRODUCT_TYPE)
            .productWorth(DEFAULT_PRODUCT_WORTH)
            .profitRate(DEFAULT_PROFIT_RATE)
            .pureWeight(DEFAULT_PURE_WEIGHT)
            .quota(DEFAULT_QUOTA)
            .receiver(DEFAULT_RECEIVER)
            .referenceNumber(DEFAULT_REFERENCE_NUMBER)
            .registrationNumber(DEFAULT_REGISTRATION_NUMBER)
            .reverseOfJustificationDisciplinaryDocumentNumber(DEFAULT_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER)
            .reverseOfJustificationDocumentsIssued(DEFAULT_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED)
            .rightsRate(DEFAULT_RIGHTS_RATE)
            .tradingCountryCode(DEFAULT_TRADING_COUNTRY_CODE)
            .transactionTypeCode(DEFAULT_TRANSACTION_TYPE_CODE)
            .warehouseFactorNumber(DEFAULT_WAREHOUSE_FACTOR_NUMBER)
            .constructorCountryName(DEFAULT_CONSTRUCTOR_COUNTRY_NAME)
            .lastCountryName(DEFAULT_LAST_COUNTRY_NAME)
            .branchCode(DEFAULT_BRANCH_CODE)
            .justificationCurrencyCode(DEFAULT_JUSTIFICATION_CURRENCY_CODE)
            .creditCurrencyCode(DEFAULT_CREDIT_CURRENCY_CODE)
            .isMigrational(DEFAULT_IS_MIGRATIONAL)
            .originalLetterImage(DEFAULT_ORIGINAL_LETTER_IMAGE)
            .originalLetterImageContentType(DEFAULT_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE)
            .letterImage(DEFAULT_LETTER_IMAGE)
            .letterImageContentType(DEFAULT_LETTER_IMAGE_CONTENT_TYPE)
            .sourceCountryCode(DEFAULT_SOURCE_COUNTRY_CODE);
        return customJustification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomJustification createUpdatedEntity(EntityManager em) {
        CustomJustification customJustification = new CustomJustification()
            .agentId(UPDATED_AGENT_ID)
            .agriculturalPublicPolicies(UPDATED_AGRICULTURAL_PUBLIC_POLICIES)
            .assessmentPlace(UPDATED_ASSESSMENT_PLACE)
            .attachedDocuments(UPDATED_ATTACHED_DOCUMENTS)
            .balanceRate(UPDATED_BALANCE_RATE)
            .bankCode(UPDATED_BANK_CODE)
            .borderTransportType(UPDATED_BORDER_TRANSPORT_TYPE)
            .boxCountType(UPDATED_BOX_COUNT_TYPE)
            .boxMarks(UPDATED_BOX_MARKS)
            .cargoIndexNumber(UPDATED_CARGO_INDEX_NUMBER)
            .centralBankCreditCode(UPDATED_CENTRAL_BANK_CREDIT_CODE)
            .comments(UPDATED_COMMENTS)
            .constructorCountryCode(UPDATED_CONSTRUCTOR_COUNTRY_CODE)
            .costDetails(UPDATED_COST_DETAILS)
            .cottageNumber(UPDATED_COTTAGE_NUMBER)
            .creditEquivalentAmount(UPDATED_CREDIT_EQUIVALENT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .currencySwiftCode(UPDATED_CURRENCY_SWIFT_CODE)
            .customCompanyCode(UPDATED_CUSTOM_COMPANY_CODE)
            .customerId(UPDATED_CUSTOMER_ID)
            .date(UPDATED_DATE)
            .destCountryCode(UPDATED_DEST_COUNTRY_CODE)
            .destCustom(UPDATED_DEST_CUSTOM)
            .destCustomCode(UPDATED_DEST_CUSTOM_CODE)
            .disciplinaryDocumentsIssued(UPDATED_DISCIPLINARY_DOCUMENTS_ISSUED)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .eightDigitOrderCode(UPDATED_EIGHT_DIGIT_ORDER_CODE)
            .entranceCustomCompany(UPDATED_ENTRANCE_CUSTOM_COMPANY)
            .entranceCustomCompanyId(UPDATED_ENTRANCE_CUSTOM_COMPANY_ID)
            .evacuationPlace(UPDATED_EVACUATION_PLACE)
            .evaluationMethodCode(UPDATED_EVALUATION_METHOD_CODE)
            .exportDate(UPDATED_EXPORT_DATE)
            .exporter(UPDATED_EXPORTER)
            .exporterCountryCode(UPDATED_EXPORTER_COUNTRY_CODE)
            .extensionDate(UPDATED_EXTENSION_DATE)
            .factorTotalAmount(UPDATED_FACTOR_TOTAL_AMOUNT)
            .freightIndexNumber(UPDATED_FREIGHT_INDEX_NUMBER)
            .frightLetter(UPDATED_FRIGHT_LETTER)
            .importLicence(UPDATED_IMPORT_LICENCE)
            .importLicense(UPDATED_IMPORT_LICENSE)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .indices(UPDATED_INDICES)
            .internalTransportType(UPDATED_INTERNAL_TRANSPORT_TYPE)
            .issuanceDate(UPDATED_ISSUANCE_DATE)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .items(UPDATED_ITEMS)
            .justificationAgent(UPDATED_JUSTIFICATION_AGENT)
            .justificationCurrencyRate(UPDATED_JUSTIFICATION_CURRENCY_RATE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .makeCertificateNumber(UPDATED_MAKE_CERTIFICATE_NUMBER)
            .newBorderTransportType(UPDATED_NEW_BORDER_TRANSPORT_TYPE)
            .newEvacuationPlace(UPDATED_NEW_EVACUATION_PLACE)
            .newInternalTransportType(UPDATED_NEW_INTERNAL_TRANSPORT_TYPE)
            .newProductItemCustomValue(UPDATED_NEW_PRODUCT_ITEM_CUSTOM_VALUE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .papers(UPDATED_PAPERS)
            .paymentConditions(UPDATED_PAYMENT_CONDITIONS)
            .preferences(UPDATED_PREFERENCES)
            .procedure(UPDATED_PROCEDURE)
            .productBoxCount(UPDATED_PRODUCT_BOX_COUNT)
            .productCode(UPDATED_PRODUCT_CODE)
            .productItemCost(UPDATED_PRODUCT_ITEM_COST)
            .productItemCount(UPDATED_PRODUCT_ITEM_COUNT)
            .productItemCustomValue(UPDATED_PRODUCT_ITEM_CUSTOM_VALUE)
            .productItemValue(UPDATED_PRODUCT_ITEM_VALUE)
            .productMeasure(UPDATED_PRODUCT_MEASURE)
            .productOwner(UPDATED_PRODUCT_OWNER)
            .productReleaseDate(UPDATED_PRODUCT_RELEASE_DATE)
            .productType(UPDATED_PRODUCT_TYPE)
            .productWorth(UPDATED_PRODUCT_WORTH)
            .profitRate(UPDATED_PROFIT_RATE)
            .pureWeight(UPDATED_PURE_WEIGHT)
            .quota(UPDATED_QUOTA)
            .receiver(UPDATED_RECEIVER)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .reverseOfJustificationDisciplinaryDocumentNumber(UPDATED_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER)
            .reverseOfJustificationDocumentsIssued(UPDATED_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED)
            .rightsRate(UPDATED_RIGHTS_RATE)
            .tradingCountryCode(UPDATED_TRADING_COUNTRY_CODE)
            .transactionTypeCode(UPDATED_TRANSACTION_TYPE_CODE)
            .warehouseFactorNumber(UPDATED_WAREHOUSE_FACTOR_NUMBER)
            .constructorCountryName(UPDATED_CONSTRUCTOR_COUNTRY_NAME)
            .lastCountryName(UPDATED_LAST_COUNTRY_NAME)
            .branchCode(UPDATED_BRANCH_CODE)
            .justificationCurrencyCode(UPDATED_JUSTIFICATION_CURRENCY_CODE)
            .creditCurrencyCode(UPDATED_CREDIT_CURRENCY_CODE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .originalLetterImage(UPDATED_ORIGINAL_LETTER_IMAGE)
            .originalLetterImageContentType(UPDATED_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .sourceCountryCode(UPDATED_SOURCE_COUNTRY_CODE);
        return customJustification;
    }

    @BeforeEach
    public void initTest() {
        customJustification = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomJustification != null) {
            customJustificationRepository.delete(insertedCustomJustification);
            insertedCustomJustification = null;
        }
    }

    @Test
    @Transactional
    void createCustomJustification() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);
        var returnedCustomJustificationDTO = om.readValue(
            restCustomJustificationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customJustificationDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CustomJustificationDTO.class
        );

        // Validate the CustomJustification in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCustomJustification = customJustificationMapper.toEntity(returnedCustomJustificationDTO);
        assertCustomJustificationUpdatableFieldsEquals(
            returnedCustomJustification,
            getPersistedCustomJustification(returnedCustomJustification)
        );

        insertedCustomJustification = returnedCustomJustification;
    }

    @Test
    @Transactional
    void createCustomJustificationWithExistingId() throws Exception {
        // Create the CustomJustification with an existing ID
        customJustification.setId(1L);
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomJustificationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customJustificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomJustifications() throws Exception {
        // Initialize the database
        insertedCustomJustification = customJustificationRepository.saveAndFlush(customJustification);

        // Get all the customJustificationList
        restCustomJustificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customJustification.getId().intValue())))
            .andExpect(jsonPath("$.[*].agentId").value(hasItem(DEFAULT_AGENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].agriculturalPublicPolicies").value(hasItem(DEFAULT_AGRICULTURAL_PUBLIC_POLICIES)))
            .andExpect(jsonPath("$.[*].assessmentPlace").value(hasItem(DEFAULT_ASSESSMENT_PLACE)))
            .andExpect(jsonPath("$.[*].attachedDocuments").value(hasItem(DEFAULT_ATTACHED_DOCUMENTS)))
            .andExpect(jsonPath("$.[*].balanceRate").value(hasItem(sameNumber(DEFAULT_BALANCE_RATE))))
            .andExpect(jsonPath("$.[*].bankCode").value(hasItem(DEFAULT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].borderTransportType").value(hasItem(DEFAULT_BORDER_TRANSPORT_TYPE)))
            .andExpect(jsonPath("$.[*].boxCountType").value(hasItem(DEFAULT_BOX_COUNT_TYPE)))
            .andExpect(jsonPath("$.[*].boxMarks").value(hasItem(DEFAULT_BOX_MARKS)))
            .andExpect(jsonPath("$.[*].cargoIndexNumber").value(hasItem(DEFAULT_CARGO_INDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].centralBankCreditCode").value(hasItem(DEFAULT_CENTRAL_BANK_CREDIT_CODE)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].constructorCountryCode").value(hasItem(DEFAULT_CONSTRUCTOR_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].costDetails").value(hasItem(DEFAULT_COST_DETAILS)))
            .andExpect(jsonPath("$.[*].cottageNumber").value(hasItem(sameNumber(DEFAULT_COTTAGE_NUMBER))))
            .andExpect(jsonPath("$.[*].creditEquivalentAmount").value(hasItem(sameNumber(DEFAULT_CREDIT_EQUIVALENT_AMOUNT))))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].currencyAmount").value(hasItem(sameNumber(DEFAULT_CURRENCY_AMOUNT))))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(sameNumber(DEFAULT_CURRENCY_RATE))))
            .andExpect(jsonPath("$.[*].currencySwiftCode").value(hasItem(DEFAULT_CURRENCY_SWIFT_CODE)))
            .andExpect(jsonPath("$.[*].customCompanyCode").value(hasItem(DEFAULT_CUSTOM_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].destCountryCode").value(hasItem(DEFAULT_DEST_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].destCustom").value(hasItem(DEFAULT_DEST_CUSTOM)))
            .andExpect(jsonPath("$.[*].destCustomCode").value(hasItem(DEFAULT_DEST_CUSTOM_CODE)))
            .andExpect(jsonPath("$.[*].disciplinaryDocumentsIssued").value(hasItem(DEFAULT_DISCIPLINARY_DOCUMENTS_ISSUED.booleanValue())))
            .andExpect(jsonPath("$.[*].discountPercent").value(hasItem(sameNumber(DEFAULT_DISCOUNT_PERCENT))))
            .andExpect(jsonPath("$.[*].eightDigitOrderCode").value(hasItem(DEFAULT_EIGHT_DIGIT_ORDER_CODE)))
            .andExpect(jsonPath("$.[*].entranceCustomCompany").value(hasItem(DEFAULT_ENTRANCE_CUSTOM_COMPANY.intValue())))
            .andExpect(jsonPath("$.[*].entranceCustomCompanyId").value(hasItem(DEFAULT_ENTRANCE_CUSTOM_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].evacuationPlace").value(hasItem(DEFAULT_EVACUATION_PLACE)))
            .andExpect(jsonPath("$.[*].evaluationMethodCode").value(hasItem(DEFAULT_EVALUATION_METHOD_CODE)))
            .andExpect(jsonPath("$.[*].exportDate").value(hasItem(DEFAULT_EXPORT_DATE)))
            .andExpect(jsonPath("$.[*].exporter").value(hasItem(DEFAULT_EXPORTER)))
            .andExpect(jsonPath("$.[*].exporterCountryCode").value(hasItem(DEFAULT_EXPORTER_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].extensionDate").value(hasItem(DEFAULT_EXTENSION_DATE)))
            .andExpect(jsonPath("$.[*].factorTotalAmount").value(hasItem(DEFAULT_FACTOR_TOTAL_AMOUNT)))
            .andExpect(jsonPath("$.[*].freightIndexNumber").value(hasItem(DEFAULT_FREIGHT_INDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].frightLetter").value(hasItem(DEFAULT_FRIGHT_LETTER)))
            .andExpect(jsonPath("$.[*].importLicence").value(hasItem(DEFAULT_IMPORT_LICENCE)))
            .andExpect(jsonPath("$.[*].importLicense").value(hasItem(DEFAULT_IMPORT_LICENSE)))
            .andExpect(jsonPath("$.[*].impureWeight").value(hasItem(sameNumber(DEFAULT_IMPURE_WEIGHT))))
            .andExpect(jsonPath("$.[*].indices").value(hasItem(DEFAULT_INDICES)))
            .andExpect(jsonPath("$.[*].internalTransportType").value(hasItem(DEFAULT_INTERNAL_TRANSPORT_TYPE)))
            .andExpect(jsonPath("$.[*].issuanceDate").value(hasItem(DEFAULT_ISSUANCE_DATE)))
            .andExpect(jsonPath("$.[*].itemNumber").value(hasItem(DEFAULT_ITEM_NUMBER)))
            .andExpect(jsonPath("$.[*].items").value(hasItem(DEFAULT_ITEMS)))
            .andExpect(jsonPath("$.[*].justificationAgent").value(hasItem(DEFAULT_JUSTIFICATION_AGENT)))
            .andExpect(jsonPath("$.[*].justificationCurrencyRate").value(hasItem(sameNumber(DEFAULT_JUSTIFICATION_CURRENCY_RATE))))
            .andExpect(jsonPath("$.[*].licenceNumber").value(hasItem(DEFAULT_LICENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].makeCertificateNumber").value(hasItem(DEFAULT_MAKE_CERTIFICATE_NUMBER)))
            .andExpect(jsonPath("$.[*].newBorderTransportType").value(hasItem(DEFAULT_NEW_BORDER_TRANSPORT_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].newEvacuationPlace").value(hasItem(DEFAULT_NEW_EVACUATION_PLACE.intValue())))
            .andExpect(jsonPath("$.[*].newInternalTransportType").value(hasItem(DEFAULT_NEW_INTERNAL_TRANSPORT_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].newProductItemCustomValue").value(hasItem(sameNumber(DEFAULT_NEW_PRODUCT_ITEM_CUSTOM_VALUE))))
            .andExpect(jsonPath("$.[*].orderRegistrationDate").value(hasItem(DEFAULT_ORDER_REGISTRATION_DATE)))
            .andExpect(jsonPath("$.[*].orderRegistrationNumber").value(hasItem(DEFAULT_ORDER_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].papers").value(hasItem(DEFAULT_PAPERS)))
            .andExpect(jsonPath("$.[*].paymentConditions").value(hasItem(DEFAULT_PAYMENT_CONDITIONS)))
            .andExpect(jsonPath("$.[*].preferences").value(hasItem(DEFAULT_PREFERENCES)))
            .andExpect(jsonPath("$.[*].procedure").value(hasItem(DEFAULT_PROCEDURE)))
            .andExpect(jsonPath("$.[*].productBoxCount").value(hasItem(DEFAULT_PRODUCT_BOX_COUNT)))
            .andExpect(jsonPath("$.[*].productCode").value(hasItem(DEFAULT_PRODUCT_CODE)))
            .andExpect(jsonPath("$.[*].productItemCost").value(hasItem(sameNumber(DEFAULT_PRODUCT_ITEM_COST))))
            .andExpect(jsonPath("$.[*].productItemCount").value(hasItem(DEFAULT_PRODUCT_ITEM_COUNT)))
            .andExpect(jsonPath("$.[*].productItemCustomValue").value(hasItem(DEFAULT_PRODUCT_ITEM_CUSTOM_VALUE)))
            .andExpect(jsonPath("$.[*].productItemValue").value(hasItem(DEFAULT_PRODUCT_ITEM_VALUE)))
            .andExpect(jsonPath("$.[*].productMeasure").value(hasItem(DEFAULT_PRODUCT_MEASURE)))
            .andExpect(jsonPath("$.[*].productOwner").value(hasItem(DEFAULT_PRODUCT_OWNER)))
            .andExpect(jsonPath("$.[*].productReleaseDate").value(hasItem(DEFAULT_PRODUCT_RELEASE_DATE)))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE)))
            .andExpect(jsonPath("$.[*].productWorth").value(hasItem(sameNumber(DEFAULT_PRODUCT_WORTH))))
            .andExpect(jsonPath("$.[*].profitRate").value(hasItem(sameNumber(DEFAULT_PROFIT_RATE))))
            .andExpect(jsonPath("$.[*].pureWeight").value(hasItem(sameNumber(DEFAULT_PURE_WEIGHT))))
            .andExpect(jsonPath("$.[*].quota").value(hasItem(DEFAULT_QUOTA)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].referenceNumber").value(hasItem(DEFAULT_REFERENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].registrationNumber").value(hasItem(DEFAULT_REGISTRATION_NUMBER)))
            .andExpect(
                jsonPath("$.[*].reverseOfJustificationDisciplinaryDocumentNumber").value(
                    hasItem(DEFAULT_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER)
                )
            )
            .andExpect(
                jsonPath("$.[*].reverseOfJustificationDocumentsIssued").value(
                    hasItem(DEFAULT_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED.booleanValue())
                )
            )
            .andExpect(jsonPath("$.[*].rightsRate").value(hasItem(sameNumber(DEFAULT_RIGHTS_RATE))))
            .andExpect(jsonPath("$.[*].tradingCountryCode").value(hasItem(DEFAULT_TRADING_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].transactionTypeCode").value(hasItem(DEFAULT_TRANSACTION_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].warehouseFactorNumber").value(hasItem(DEFAULT_WAREHOUSE_FACTOR_NUMBER)))
            .andExpect(jsonPath("$.[*].constructorCountryName").value(hasItem(DEFAULT_CONSTRUCTOR_COUNTRY_NAME)))
            .andExpect(jsonPath("$.[*].lastCountryName").value(hasItem(DEFAULT_LAST_COUNTRY_NAME)))
            .andExpect(jsonPath("$.[*].branchCode").value(hasItem(DEFAULT_BRANCH_CODE)))
            .andExpect(jsonPath("$.[*].justificationCurrencyCode").value(hasItem(DEFAULT_JUSTIFICATION_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].creditCurrencyCode").value(hasItem(DEFAULT_CREDIT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].isMigrational").value(hasItem(DEFAULT_IS_MIGRATIONAL.booleanValue())))
            .andExpect(jsonPath("$.[*].originalLetterImageContentType").value(hasItem(DEFAULT_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE)))
            .andExpect(
                jsonPath("$.[*].originalLetterImage").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_ORIGINAL_LETTER_IMAGE)))
            )
            .andExpect(jsonPath("$.[*].letterImageContentType").value(hasItem(DEFAULT_LETTER_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].letterImage").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_LETTER_IMAGE))))
            .andExpect(jsonPath("$.[*].sourceCountryCode").value(hasItem(DEFAULT_SOURCE_COUNTRY_CODE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCustomJustificationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(customJustificationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCustomJustificationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(customJustificationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCustomJustificationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(customJustificationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCustomJustificationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(customJustificationRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCustomJustification() throws Exception {
        // Initialize the database
        insertedCustomJustification = customJustificationRepository.saveAndFlush(customJustification);

        // Get the customJustification
        restCustomJustificationMockMvc
            .perform(get(ENTITY_API_URL_ID, customJustification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customJustification.getId().intValue()))
            .andExpect(jsonPath("$.agentId").value(DEFAULT_AGENT_ID.intValue()))
            .andExpect(jsonPath("$.agriculturalPublicPolicies").value(DEFAULT_AGRICULTURAL_PUBLIC_POLICIES))
            .andExpect(jsonPath("$.assessmentPlace").value(DEFAULT_ASSESSMENT_PLACE))
            .andExpect(jsonPath("$.attachedDocuments").value(DEFAULT_ATTACHED_DOCUMENTS))
            .andExpect(jsonPath("$.balanceRate").value(sameNumber(DEFAULT_BALANCE_RATE)))
            .andExpect(jsonPath("$.bankCode").value(DEFAULT_BANK_CODE))
            .andExpect(jsonPath("$.borderTransportType").value(DEFAULT_BORDER_TRANSPORT_TYPE))
            .andExpect(jsonPath("$.boxCountType").value(DEFAULT_BOX_COUNT_TYPE))
            .andExpect(jsonPath("$.boxMarks").value(DEFAULT_BOX_MARKS))
            .andExpect(jsonPath("$.cargoIndexNumber").value(DEFAULT_CARGO_INDEX_NUMBER))
            .andExpect(jsonPath("$.centralBankCreditCode").value(DEFAULT_CENTRAL_BANK_CREDIT_CODE))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.constructorCountryCode").value(DEFAULT_CONSTRUCTOR_COUNTRY_CODE))
            .andExpect(jsonPath("$.costDetails").value(DEFAULT_COST_DETAILS))
            .andExpect(jsonPath("$.cottageNumber").value(sameNumber(DEFAULT_COTTAGE_NUMBER)))
            .andExpect(jsonPath("$.creditEquivalentAmount").value(sameNumber(DEFAULT_CREDIT_EQUIVALENT_AMOUNT)))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.currencyAmount").value(sameNumber(DEFAULT_CURRENCY_AMOUNT)))
            .andExpect(jsonPath("$.currencyRate").value(sameNumber(DEFAULT_CURRENCY_RATE)))
            .andExpect(jsonPath("$.currencySwiftCode").value(DEFAULT_CURRENCY_SWIFT_CODE))
            .andExpect(jsonPath("$.customCompanyCode").value(DEFAULT_CUSTOM_COMPANY_CODE))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.destCountryCode").value(DEFAULT_DEST_COUNTRY_CODE))
            .andExpect(jsonPath("$.destCustom").value(DEFAULT_DEST_CUSTOM))
            .andExpect(jsonPath("$.destCustomCode").value(DEFAULT_DEST_CUSTOM_CODE))
            .andExpect(jsonPath("$.disciplinaryDocumentsIssued").value(DEFAULT_DISCIPLINARY_DOCUMENTS_ISSUED.booleanValue()))
            .andExpect(jsonPath("$.discountPercent").value(sameNumber(DEFAULT_DISCOUNT_PERCENT)))
            .andExpect(jsonPath("$.eightDigitOrderCode").value(DEFAULT_EIGHT_DIGIT_ORDER_CODE))
            .andExpect(jsonPath("$.entranceCustomCompany").value(DEFAULT_ENTRANCE_CUSTOM_COMPANY.intValue()))
            .andExpect(jsonPath("$.entranceCustomCompanyId").value(DEFAULT_ENTRANCE_CUSTOM_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.evacuationPlace").value(DEFAULT_EVACUATION_PLACE))
            .andExpect(jsonPath("$.evaluationMethodCode").value(DEFAULT_EVALUATION_METHOD_CODE))
            .andExpect(jsonPath("$.exportDate").value(DEFAULT_EXPORT_DATE))
            .andExpect(jsonPath("$.exporter").value(DEFAULT_EXPORTER))
            .andExpect(jsonPath("$.exporterCountryCode").value(DEFAULT_EXPORTER_COUNTRY_CODE))
            .andExpect(jsonPath("$.extensionDate").value(DEFAULT_EXTENSION_DATE))
            .andExpect(jsonPath("$.factorTotalAmount").value(DEFAULT_FACTOR_TOTAL_AMOUNT))
            .andExpect(jsonPath("$.freightIndexNumber").value(DEFAULT_FREIGHT_INDEX_NUMBER))
            .andExpect(jsonPath("$.frightLetter").value(DEFAULT_FRIGHT_LETTER))
            .andExpect(jsonPath("$.importLicence").value(DEFAULT_IMPORT_LICENCE))
            .andExpect(jsonPath("$.importLicense").value(DEFAULT_IMPORT_LICENSE))
            .andExpect(jsonPath("$.impureWeight").value(sameNumber(DEFAULT_IMPURE_WEIGHT)))
            .andExpect(jsonPath("$.indices").value(DEFAULT_INDICES))
            .andExpect(jsonPath("$.internalTransportType").value(DEFAULT_INTERNAL_TRANSPORT_TYPE))
            .andExpect(jsonPath("$.issuanceDate").value(DEFAULT_ISSUANCE_DATE))
            .andExpect(jsonPath("$.itemNumber").value(DEFAULT_ITEM_NUMBER))
            .andExpect(jsonPath("$.items").value(DEFAULT_ITEMS))
            .andExpect(jsonPath("$.justificationAgent").value(DEFAULT_JUSTIFICATION_AGENT))
            .andExpect(jsonPath("$.justificationCurrencyRate").value(sameNumber(DEFAULT_JUSTIFICATION_CURRENCY_RATE)))
            .andExpect(jsonPath("$.licenceNumber").value(DEFAULT_LICENCE_NUMBER))
            .andExpect(jsonPath("$.makeCertificateNumber").value(DEFAULT_MAKE_CERTIFICATE_NUMBER))
            .andExpect(jsonPath("$.newBorderTransportType").value(DEFAULT_NEW_BORDER_TRANSPORT_TYPE.intValue()))
            .andExpect(jsonPath("$.newEvacuationPlace").value(DEFAULT_NEW_EVACUATION_PLACE.intValue()))
            .andExpect(jsonPath("$.newInternalTransportType").value(DEFAULT_NEW_INTERNAL_TRANSPORT_TYPE.intValue()))
            .andExpect(jsonPath("$.newProductItemCustomValue").value(sameNumber(DEFAULT_NEW_PRODUCT_ITEM_CUSTOM_VALUE)))
            .andExpect(jsonPath("$.orderRegistrationDate").value(DEFAULT_ORDER_REGISTRATION_DATE))
            .andExpect(jsonPath("$.orderRegistrationNumber").value(DEFAULT_ORDER_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.papers").value(DEFAULT_PAPERS))
            .andExpect(jsonPath("$.paymentConditions").value(DEFAULT_PAYMENT_CONDITIONS))
            .andExpect(jsonPath("$.preferences").value(DEFAULT_PREFERENCES))
            .andExpect(jsonPath("$.procedure").value(DEFAULT_PROCEDURE))
            .andExpect(jsonPath("$.productBoxCount").value(DEFAULT_PRODUCT_BOX_COUNT))
            .andExpect(jsonPath("$.productCode").value(DEFAULT_PRODUCT_CODE))
            .andExpect(jsonPath("$.productItemCost").value(sameNumber(DEFAULT_PRODUCT_ITEM_COST)))
            .andExpect(jsonPath("$.productItemCount").value(DEFAULT_PRODUCT_ITEM_COUNT))
            .andExpect(jsonPath("$.productItemCustomValue").value(DEFAULT_PRODUCT_ITEM_CUSTOM_VALUE))
            .andExpect(jsonPath("$.productItemValue").value(DEFAULT_PRODUCT_ITEM_VALUE))
            .andExpect(jsonPath("$.productMeasure").value(DEFAULT_PRODUCT_MEASURE))
            .andExpect(jsonPath("$.productOwner").value(DEFAULT_PRODUCT_OWNER))
            .andExpect(jsonPath("$.productReleaseDate").value(DEFAULT_PRODUCT_RELEASE_DATE))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE))
            .andExpect(jsonPath("$.productWorth").value(sameNumber(DEFAULT_PRODUCT_WORTH)))
            .andExpect(jsonPath("$.profitRate").value(sameNumber(DEFAULT_PROFIT_RATE)))
            .andExpect(jsonPath("$.pureWeight").value(sameNumber(DEFAULT_PURE_WEIGHT)))
            .andExpect(jsonPath("$.quota").value(DEFAULT_QUOTA))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.referenceNumber").value(DEFAULT_REFERENCE_NUMBER))
            .andExpect(jsonPath("$.registrationNumber").value(DEFAULT_REGISTRATION_NUMBER))
            .andExpect(
                jsonPath("$.reverseOfJustificationDisciplinaryDocumentNumber").value(
                    DEFAULT_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER
                )
            )
            .andExpect(
                jsonPath("$.reverseOfJustificationDocumentsIssued").value(DEFAULT_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED.booleanValue())
            )
            .andExpect(jsonPath("$.rightsRate").value(sameNumber(DEFAULT_RIGHTS_RATE)))
            .andExpect(jsonPath("$.tradingCountryCode").value(DEFAULT_TRADING_COUNTRY_CODE))
            .andExpect(jsonPath("$.transactionTypeCode").value(DEFAULT_TRANSACTION_TYPE_CODE))
            .andExpect(jsonPath("$.warehouseFactorNumber").value(DEFAULT_WAREHOUSE_FACTOR_NUMBER))
            .andExpect(jsonPath("$.constructorCountryName").value(DEFAULT_CONSTRUCTOR_COUNTRY_NAME))
            .andExpect(jsonPath("$.lastCountryName").value(DEFAULT_LAST_COUNTRY_NAME))
            .andExpect(jsonPath("$.branchCode").value(DEFAULT_BRANCH_CODE))
            .andExpect(jsonPath("$.justificationCurrencyCode").value(DEFAULT_JUSTIFICATION_CURRENCY_CODE))
            .andExpect(jsonPath("$.creditCurrencyCode").value(DEFAULT_CREDIT_CURRENCY_CODE))
            .andExpect(jsonPath("$.isMigrational").value(DEFAULT_IS_MIGRATIONAL.booleanValue()))
            .andExpect(jsonPath("$.originalLetterImageContentType").value(DEFAULT_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.originalLetterImage").value(Base64.getEncoder().encodeToString(DEFAULT_ORIGINAL_LETTER_IMAGE)))
            .andExpect(jsonPath("$.letterImageContentType").value(DEFAULT_LETTER_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.letterImage").value(Base64.getEncoder().encodeToString(DEFAULT_LETTER_IMAGE)))
            .andExpect(jsonPath("$.sourceCountryCode").value(DEFAULT_SOURCE_COUNTRY_CODE));
    }

    @Test
    @Transactional
    void getNonExistingCustomJustification() throws Exception {
        // Get the customJustification
        restCustomJustificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomJustification() throws Exception {
        // Initialize the database
        insertedCustomJustification = customJustificationRepository.saveAndFlush(customJustification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customJustification
        CustomJustification updatedCustomJustification = customJustificationRepository.findById(customJustification.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomJustification are not directly saved in db
        em.detach(updatedCustomJustification);
        updatedCustomJustification
            .agentId(UPDATED_AGENT_ID)
            .agriculturalPublicPolicies(UPDATED_AGRICULTURAL_PUBLIC_POLICIES)
            .assessmentPlace(UPDATED_ASSESSMENT_PLACE)
            .attachedDocuments(UPDATED_ATTACHED_DOCUMENTS)
            .balanceRate(UPDATED_BALANCE_RATE)
            .bankCode(UPDATED_BANK_CODE)
            .borderTransportType(UPDATED_BORDER_TRANSPORT_TYPE)
            .boxCountType(UPDATED_BOX_COUNT_TYPE)
            .boxMarks(UPDATED_BOX_MARKS)
            .cargoIndexNumber(UPDATED_CARGO_INDEX_NUMBER)
            .centralBankCreditCode(UPDATED_CENTRAL_BANK_CREDIT_CODE)
            .comments(UPDATED_COMMENTS)
            .constructorCountryCode(UPDATED_CONSTRUCTOR_COUNTRY_CODE)
            .costDetails(UPDATED_COST_DETAILS)
            .cottageNumber(UPDATED_COTTAGE_NUMBER)
            .creditEquivalentAmount(UPDATED_CREDIT_EQUIVALENT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .currencySwiftCode(UPDATED_CURRENCY_SWIFT_CODE)
            .customCompanyCode(UPDATED_CUSTOM_COMPANY_CODE)
            .customerId(UPDATED_CUSTOMER_ID)
            .date(UPDATED_DATE)
            .destCountryCode(UPDATED_DEST_COUNTRY_CODE)
            .destCustom(UPDATED_DEST_CUSTOM)
            .destCustomCode(UPDATED_DEST_CUSTOM_CODE)
            .disciplinaryDocumentsIssued(UPDATED_DISCIPLINARY_DOCUMENTS_ISSUED)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .eightDigitOrderCode(UPDATED_EIGHT_DIGIT_ORDER_CODE)
            .entranceCustomCompany(UPDATED_ENTRANCE_CUSTOM_COMPANY)
            .entranceCustomCompanyId(UPDATED_ENTRANCE_CUSTOM_COMPANY_ID)
            .evacuationPlace(UPDATED_EVACUATION_PLACE)
            .evaluationMethodCode(UPDATED_EVALUATION_METHOD_CODE)
            .exportDate(UPDATED_EXPORT_DATE)
            .exporter(UPDATED_EXPORTER)
            .exporterCountryCode(UPDATED_EXPORTER_COUNTRY_CODE)
            .extensionDate(UPDATED_EXTENSION_DATE)
            .factorTotalAmount(UPDATED_FACTOR_TOTAL_AMOUNT)
            .freightIndexNumber(UPDATED_FREIGHT_INDEX_NUMBER)
            .frightLetter(UPDATED_FRIGHT_LETTER)
            .importLicence(UPDATED_IMPORT_LICENCE)
            .importLicense(UPDATED_IMPORT_LICENSE)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .indices(UPDATED_INDICES)
            .internalTransportType(UPDATED_INTERNAL_TRANSPORT_TYPE)
            .issuanceDate(UPDATED_ISSUANCE_DATE)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .items(UPDATED_ITEMS)
            .justificationAgent(UPDATED_JUSTIFICATION_AGENT)
            .justificationCurrencyRate(UPDATED_JUSTIFICATION_CURRENCY_RATE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .makeCertificateNumber(UPDATED_MAKE_CERTIFICATE_NUMBER)
            .newBorderTransportType(UPDATED_NEW_BORDER_TRANSPORT_TYPE)
            .newEvacuationPlace(UPDATED_NEW_EVACUATION_PLACE)
            .newInternalTransportType(UPDATED_NEW_INTERNAL_TRANSPORT_TYPE)
            .newProductItemCustomValue(UPDATED_NEW_PRODUCT_ITEM_CUSTOM_VALUE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .papers(UPDATED_PAPERS)
            .paymentConditions(UPDATED_PAYMENT_CONDITIONS)
            .preferences(UPDATED_PREFERENCES)
            .procedure(UPDATED_PROCEDURE)
            .productBoxCount(UPDATED_PRODUCT_BOX_COUNT)
            .productCode(UPDATED_PRODUCT_CODE)
            .productItemCost(UPDATED_PRODUCT_ITEM_COST)
            .productItemCount(UPDATED_PRODUCT_ITEM_COUNT)
            .productItemCustomValue(UPDATED_PRODUCT_ITEM_CUSTOM_VALUE)
            .productItemValue(UPDATED_PRODUCT_ITEM_VALUE)
            .productMeasure(UPDATED_PRODUCT_MEASURE)
            .productOwner(UPDATED_PRODUCT_OWNER)
            .productReleaseDate(UPDATED_PRODUCT_RELEASE_DATE)
            .productType(UPDATED_PRODUCT_TYPE)
            .productWorth(UPDATED_PRODUCT_WORTH)
            .profitRate(UPDATED_PROFIT_RATE)
            .pureWeight(UPDATED_PURE_WEIGHT)
            .quota(UPDATED_QUOTA)
            .receiver(UPDATED_RECEIVER)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .reverseOfJustificationDisciplinaryDocumentNumber(UPDATED_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER)
            .reverseOfJustificationDocumentsIssued(UPDATED_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED)
            .rightsRate(UPDATED_RIGHTS_RATE)
            .tradingCountryCode(UPDATED_TRADING_COUNTRY_CODE)
            .transactionTypeCode(UPDATED_TRANSACTION_TYPE_CODE)
            .warehouseFactorNumber(UPDATED_WAREHOUSE_FACTOR_NUMBER)
            .constructorCountryName(UPDATED_CONSTRUCTOR_COUNTRY_NAME)
            .lastCountryName(UPDATED_LAST_COUNTRY_NAME)
            .branchCode(UPDATED_BRANCH_CODE)
            .justificationCurrencyCode(UPDATED_JUSTIFICATION_CURRENCY_CODE)
            .creditCurrencyCode(UPDATED_CREDIT_CURRENCY_CODE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .originalLetterImage(UPDATED_ORIGINAL_LETTER_IMAGE)
            .originalLetterImageContentType(UPDATED_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .sourceCountryCode(UPDATED_SOURCE_COUNTRY_CODE);
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(updatedCustomJustification);

        restCustomJustificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customJustificationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customJustificationDTO))
            )
            .andExpect(status().isOk());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomJustificationToMatchAllProperties(updatedCustomJustification);
    }

    @Test
    @Transactional
    void putNonExistingCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustification.setId(longCount.incrementAndGet());

        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomJustificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customJustificationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustification.setId(longCount.incrementAndGet());

        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustification.setId(longCount.incrementAndGet());

        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customJustificationDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomJustificationWithPatch() throws Exception {
        // Initialize the database
        insertedCustomJustification = customJustificationRepository.saveAndFlush(customJustification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customJustification using partial update
        CustomJustification partialUpdatedCustomJustification = new CustomJustification();
        partialUpdatedCustomJustification.setId(customJustification.getId());

        partialUpdatedCustomJustification
            .agriculturalPublicPolicies(UPDATED_AGRICULTURAL_PUBLIC_POLICIES)
            .assessmentPlace(UPDATED_ASSESSMENT_PLACE)
            .balanceRate(UPDATED_BALANCE_RATE)
            .borderTransportType(UPDATED_BORDER_TRANSPORT_TYPE)
            .boxCountType(UPDATED_BOX_COUNT_TYPE)
            .boxMarks(UPDATED_BOX_MARKS)
            .cargoIndexNumber(UPDATED_CARGO_INDEX_NUMBER)
            .costDetails(UPDATED_COST_DETAILS)
            .cottageNumber(UPDATED_COTTAGE_NUMBER)
            .creditEquivalentAmount(UPDATED_CREDIT_EQUIVALENT_AMOUNT)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .date(UPDATED_DATE)
            .destCountryCode(UPDATED_DEST_COUNTRY_CODE)
            .destCustom(UPDATED_DEST_CUSTOM)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .eightDigitOrderCode(UPDATED_EIGHT_DIGIT_ORDER_CODE)
            .entranceCustomCompany(UPDATED_ENTRANCE_CUSTOM_COMPANY)
            .evacuationPlace(UPDATED_EVACUATION_PLACE)
            .exportDate(UPDATED_EXPORT_DATE)
            .exporter(UPDATED_EXPORTER)
            .exporterCountryCode(UPDATED_EXPORTER_COUNTRY_CODE)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .indices(UPDATED_INDICES)
            .internalTransportType(UPDATED_INTERNAL_TRANSPORT_TYPE)
            .issuanceDate(UPDATED_ISSUANCE_DATE)
            .items(UPDATED_ITEMS)
            .justificationAgent(UPDATED_JUSTIFICATION_AGENT)
            .justificationCurrencyRate(UPDATED_JUSTIFICATION_CURRENCY_RATE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .newBorderTransportType(UPDATED_NEW_BORDER_TRANSPORT_TYPE)
            .newInternalTransportType(UPDATED_NEW_INTERNAL_TRANSPORT_TYPE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .papers(UPDATED_PAPERS)
            .paymentConditions(UPDATED_PAYMENT_CONDITIONS)
            .preferences(UPDATED_PREFERENCES)
            .procedure(UPDATED_PROCEDURE)
            .productBoxCount(UPDATED_PRODUCT_BOX_COUNT)
            .productCode(UPDATED_PRODUCT_CODE)
            .productItemCost(UPDATED_PRODUCT_ITEM_COST)
            .productItemCustomValue(UPDATED_PRODUCT_ITEM_CUSTOM_VALUE)
            .productItemValue(UPDATED_PRODUCT_ITEM_VALUE)
            .productMeasure(UPDATED_PRODUCT_MEASURE)
            .productOwner(UPDATED_PRODUCT_OWNER)
            .productReleaseDate(UPDATED_PRODUCT_RELEASE_DATE)
            .profitRate(UPDATED_PROFIT_RATE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .reverseOfJustificationDisciplinaryDocumentNumber(UPDATED_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER)
            .reverseOfJustificationDocumentsIssued(UPDATED_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED)
            .transactionTypeCode(UPDATED_TRANSACTION_TYPE_CODE)
            .constructorCountryName(UPDATED_CONSTRUCTOR_COUNTRY_NAME)
            .branchCode(UPDATED_BRANCH_CODE)
            .justificationCurrencyCode(UPDATED_JUSTIFICATION_CURRENCY_CODE)
            .originalLetterImage(UPDATED_ORIGINAL_LETTER_IMAGE)
            .originalLetterImageContentType(UPDATED_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .sourceCountryCode(UPDATED_SOURCE_COUNTRY_CODE);

        restCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomJustification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomJustification))
            )
            .andExpect(status().isOk());

        // Validate the CustomJustification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomJustificationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCustomJustification, customJustification),
            getPersistedCustomJustification(customJustification)
        );
    }

    @Test
    @Transactional
    void fullUpdateCustomJustificationWithPatch() throws Exception {
        // Initialize the database
        insertedCustomJustification = customJustificationRepository.saveAndFlush(customJustification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customJustification using partial update
        CustomJustification partialUpdatedCustomJustification = new CustomJustification();
        partialUpdatedCustomJustification.setId(customJustification.getId());

        partialUpdatedCustomJustification
            .agentId(UPDATED_AGENT_ID)
            .agriculturalPublicPolicies(UPDATED_AGRICULTURAL_PUBLIC_POLICIES)
            .assessmentPlace(UPDATED_ASSESSMENT_PLACE)
            .attachedDocuments(UPDATED_ATTACHED_DOCUMENTS)
            .balanceRate(UPDATED_BALANCE_RATE)
            .bankCode(UPDATED_BANK_CODE)
            .borderTransportType(UPDATED_BORDER_TRANSPORT_TYPE)
            .boxCountType(UPDATED_BOX_COUNT_TYPE)
            .boxMarks(UPDATED_BOX_MARKS)
            .cargoIndexNumber(UPDATED_CARGO_INDEX_NUMBER)
            .centralBankCreditCode(UPDATED_CENTRAL_BANK_CREDIT_CODE)
            .comments(UPDATED_COMMENTS)
            .constructorCountryCode(UPDATED_CONSTRUCTOR_COUNTRY_CODE)
            .costDetails(UPDATED_COST_DETAILS)
            .cottageNumber(UPDATED_COTTAGE_NUMBER)
            .creditEquivalentAmount(UPDATED_CREDIT_EQUIVALENT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .currencySwiftCode(UPDATED_CURRENCY_SWIFT_CODE)
            .customCompanyCode(UPDATED_CUSTOM_COMPANY_CODE)
            .customerId(UPDATED_CUSTOMER_ID)
            .date(UPDATED_DATE)
            .destCountryCode(UPDATED_DEST_COUNTRY_CODE)
            .destCustom(UPDATED_DEST_CUSTOM)
            .destCustomCode(UPDATED_DEST_CUSTOM_CODE)
            .disciplinaryDocumentsIssued(UPDATED_DISCIPLINARY_DOCUMENTS_ISSUED)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .eightDigitOrderCode(UPDATED_EIGHT_DIGIT_ORDER_CODE)
            .entranceCustomCompany(UPDATED_ENTRANCE_CUSTOM_COMPANY)
            .entranceCustomCompanyId(UPDATED_ENTRANCE_CUSTOM_COMPANY_ID)
            .evacuationPlace(UPDATED_EVACUATION_PLACE)
            .evaluationMethodCode(UPDATED_EVALUATION_METHOD_CODE)
            .exportDate(UPDATED_EXPORT_DATE)
            .exporter(UPDATED_EXPORTER)
            .exporterCountryCode(UPDATED_EXPORTER_COUNTRY_CODE)
            .extensionDate(UPDATED_EXTENSION_DATE)
            .factorTotalAmount(UPDATED_FACTOR_TOTAL_AMOUNT)
            .freightIndexNumber(UPDATED_FREIGHT_INDEX_NUMBER)
            .frightLetter(UPDATED_FRIGHT_LETTER)
            .importLicence(UPDATED_IMPORT_LICENCE)
            .importLicense(UPDATED_IMPORT_LICENSE)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .indices(UPDATED_INDICES)
            .internalTransportType(UPDATED_INTERNAL_TRANSPORT_TYPE)
            .issuanceDate(UPDATED_ISSUANCE_DATE)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .items(UPDATED_ITEMS)
            .justificationAgent(UPDATED_JUSTIFICATION_AGENT)
            .justificationCurrencyRate(UPDATED_JUSTIFICATION_CURRENCY_RATE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .makeCertificateNumber(UPDATED_MAKE_CERTIFICATE_NUMBER)
            .newBorderTransportType(UPDATED_NEW_BORDER_TRANSPORT_TYPE)
            .newEvacuationPlace(UPDATED_NEW_EVACUATION_PLACE)
            .newInternalTransportType(UPDATED_NEW_INTERNAL_TRANSPORT_TYPE)
            .newProductItemCustomValue(UPDATED_NEW_PRODUCT_ITEM_CUSTOM_VALUE)
            .orderRegistrationDate(UPDATED_ORDER_REGISTRATION_DATE)
            .orderRegistrationNumber(UPDATED_ORDER_REGISTRATION_NUMBER)
            .papers(UPDATED_PAPERS)
            .paymentConditions(UPDATED_PAYMENT_CONDITIONS)
            .preferences(UPDATED_PREFERENCES)
            .procedure(UPDATED_PROCEDURE)
            .productBoxCount(UPDATED_PRODUCT_BOX_COUNT)
            .productCode(UPDATED_PRODUCT_CODE)
            .productItemCost(UPDATED_PRODUCT_ITEM_COST)
            .productItemCount(UPDATED_PRODUCT_ITEM_COUNT)
            .productItemCustomValue(UPDATED_PRODUCT_ITEM_CUSTOM_VALUE)
            .productItemValue(UPDATED_PRODUCT_ITEM_VALUE)
            .productMeasure(UPDATED_PRODUCT_MEASURE)
            .productOwner(UPDATED_PRODUCT_OWNER)
            .productReleaseDate(UPDATED_PRODUCT_RELEASE_DATE)
            .productType(UPDATED_PRODUCT_TYPE)
            .productWorth(UPDATED_PRODUCT_WORTH)
            .profitRate(UPDATED_PROFIT_RATE)
            .pureWeight(UPDATED_PURE_WEIGHT)
            .quota(UPDATED_QUOTA)
            .receiver(UPDATED_RECEIVER)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .reverseOfJustificationDisciplinaryDocumentNumber(UPDATED_REVERSE_OF_JUSTIFICATION_DISCIPLINARY_DOCUMENT_NUMBER)
            .reverseOfJustificationDocumentsIssued(UPDATED_REVERSE_OF_JUSTIFICATION_DOCUMENTS_ISSUED)
            .rightsRate(UPDATED_RIGHTS_RATE)
            .tradingCountryCode(UPDATED_TRADING_COUNTRY_CODE)
            .transactionTypeCode(UPDATED_TRANSACTION_TYPE_CODE)
            .warehouseFactorNumber(UPDATED_WAREHOUSE_FACTOR_NUMBER)
            .constructorCountryName(UPDATED_CONSTRUCTOR_COUNTRY_NAME)
            .lastCountryName(UPDATED_LAST_COUNTRY_NAME)
            .branchCode(UPDATED_BRANCH_CODE)
            .justificationCurrencyCode(UPDATED_JUSTIFICATION_CURRENCY_CODE)
            .creditCurrencyCode(UPDATED_CREDIT_CURRENCY_CODE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .originalLetterImage(UPDATED_ORIGINAL_LETTER_IMAGE)
            .originalLetterImageContentType(UPDATED_ORIGINAL_LETTER_IMAGE_CONTENT_TYPE)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .sourceCountryCode(UPDATED_SOURCE_COUNTRY_CODE);

        restCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomJustification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomJustification))
            )
            .andExpect(status().isOk());

        // Validate the CustomJustification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomJustificationUpdatableFieldsEquals(
            partialUpdatedCustomJustification,
            getPersistedCustomJustification(partialUpdatedCustomJustification)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustification.setId(longCount.incrementAndGet());

        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customJustificationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustification.setId(longCount.incrementAndGet());

        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustification.setId(longCount.incrementAndGet());

        // Create the CustomJustification
        CustomJustificationDTO customJustificationDTO = customJustificationMapper.toDto(customJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customJustificationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomJustification() throws Exception {
        // Initialize the database
        insertedCustomJustification = customJustificationRepository.saveAndFlush(customJustification);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customJustification
        restCustomJustificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, customJustification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customJustificationRepository.count();
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

    protected CustomJustification getPersistedCustomJustification(CustomJustification customJustification) {
        return customJustificationRepository.findById(customJustification.getId()).orElseThrow();
    }

    protected void assertPersistedCustomJustificationToMatchAllProperties(CustomJustification expectedCustomJustification) {
        assertCustomJustificationAllPropertiesEquals(
            expectedCustomJustification,
            getPersistedCustomJustification(expectedCustomJustification)
        );
    }

    protected void assertPersistedCustomJustificationToMatchUpdatableProperties(CustomJustification expectedCustomJustification) {
        assertCustomJustificationAllUpdatablePropertiesEquals(
            expectedCustomJustification,
            getPersistedCustomJustification(expectedCustomJustification)
        );
    }
}
