package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftReceiptAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.repository.DraftReceiptRepository;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import com.dotin.tfbita.service.mapper.DraftReceiptMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DraftReceiptResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftReceiptResourceIT {

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_DELIVER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_DELIVER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELETE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DELETE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVER_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DELIVER_DURATION = 1;
    private static final Integer UPDATED_DELIVER_DURATION = 2;

    private static final Boolean DEFAULT_DELIVERED = false;
    private static final Boolean UPDATED_DELIVERED = true;

    private static final String DEFAULT_DOCUMENT_TRANSACTION_EFFECTIVE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TRANSACTION_EFFECTIVE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_FREIGHT_LETTER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_FREIGHT_LETTER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_FREIGHT_LETTER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FREIGHT_LETTER_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FREIGHT_LETTER_STAMP_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_FREIGHT_LETTER_STAMP_COST = new BigDecimal(2);

    private static final String DEFAULT_ISSUE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_DATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISSUE_DOCUMENT = false;
    private static final Boolean UPDATED_ISSUE_DOCUMENT = true;

    private static final BigDecimal DEFAULT_PRODUCT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRODUCT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RECEIPT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RECEIPT_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_RECEIPT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROW = 1;
    private static final Integer UPDATED_ROW = 2;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSPORT_ROW = "AAAAAAAAAA";
    private static final String UPDATED_TRANSPORT_ROW = "BBBBBBBBBB";

    private static final Boolean DEFAULT_USABLE = false;
    private static final Boolean UPDATED_USABLE = true;

    private static final String DEFAULT_PAYMENT_CURRENCY_RATE_TYPE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CURRENCY_RATE_TYPE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ITEM_TYPE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_TYPE_DESC = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_NET_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NET_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_GROSS_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_GROSS_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL_NET_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL_NET_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL_GROSS_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL_GROSS_WEIGHT = new BigDecimal(2);

    private static final String DEFAULT_LETTER_NUMBER_TAZIRAT = "AAAAAAAAAA";
    private static final String UPDATED_LETTER_NUMBER_TAZIRAT = "BBBBBBBBBB";

    private static final String DEFAULT_LETTER_DATE_TAZIRAT = "AAAAAAAAAA";
    private static final String UPDATED_LETTER_DATE_TAZIRAT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FOB_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_FOB_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHIPPING_FARE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHIPPING_FARE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INSPECTION_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSPECTION_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final String DEFAULT_DEADLINE_SUBMIT_DOCUMENT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DEADLINE_SUBMIT_DOCUMENT_DATE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CURRENCY_PROVISION_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CURRENCY_PROVISION_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CURRENCY_PROVISION_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CURRENCY_PROVISION_FILE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_IS_MIGRATIONAL = false;
    private static final Boolean UPDATED_IS_MIGRATIONAL = true;

    private static final BigDecimal DEFAULT_OTHER_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTHER_COST = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/draft-receipts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftReceiptRepository draftReceiptRepository;

    @Autowired
    private DraftReceiptMapper draftReceiptMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftReceiptMockMvc;

    private DraftReceipt draftReceipt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftReceipt createEntity(EntityManager em) {
        DraftReceipt draftReceipt = new DraftReceipt()
            .comment(DEFAULT_COMMENT)
            .customerDeliverDate(DEFAULT_CUSTOMER_DELIVER_DATE)
            .date(DEFAULT_DATE)
            .deleteDate(DEFAULT_DELETE_DATE)
            .deliverDate(DEFAULT_DELIVER_DATE)
            .deliverDuration(DEFAULT_DELIVER_DURATION)
            .delivered(DEFAULT_DELIVERED)
            .documentTransactionEffectiveDate(DEFAULT_DOCUMENT_TRANSACTION_EFFECTIVE_DATE)
            .freightLetterDate(DEFAULT_FREIGHT_LETTER_DATE)
            .freightLetterNumber(DEFAULT_FREIGHT_LETTER_NUMBER)
            .freightLetterStampCost(DEFAULT_FREIGHT_LETTER_STAMP_COST)
            .issueDate(DEFAULT_ISSUE_DATE)
            .issueDocument(DEFAULT_ISSUE_DOCUMENT)
            .productAmount(DEFAULT_PRODUCT_AMOUNT)
            .receiptAmount(DEFAULT_RECEIPT_AMOUNT)
            .receiptDate(DEFAULT_RECEIPT_DATE)
            .row(DEFAULT_ROW)
            .serial(DEFAULT_SERIAL)
            .transportRow(DEFAULT_TRANSPORT_ROW)
            .usable(DEFAULT_USABLE)
            .paymentCurrencyRateTypeDesc(DEFAULT_PAYMENT_CURRENCY_RATE_TYPE_DESC)
            .paymentItemTypeDesc(DEFAULT_PAYMENT_ITEM_TYPE_DESC)
            .netWeight(DEFAULT_NET_WEIGHT)
            .grossWeight(DEFAULT_GROSS_WEIGHT)
            .totalNetWeight(DEFAULT_TOTAL_NET_WEIGHT)
            .totalGrossWeight(DEFAULT_TOTAL_GROSS_WEIGHT)
            .letterNumberTazirat(DEFAULT_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(DEFAULT_LETTER_DATE_TAZIRAT)
            .fobAmount(DEFAULT_FOB_AMOUNT)
            .shippingFare(DEFAULT_SHIPPING_FARE)
            .inspectionCost(DEFAULT_INSPECTION_COST)
            .discount(DEFAULT_DISCOUNT)
            .deadlineSubmitDocumentDate(DEFAULT_DEADLINE_SUBMIT_DOCUMENT_DATE)
            .currencyProvisionFile(DEFAULT_CURRENCY_PROVISION_FILE)
            .currencyProvisionFileContentType(DEFAULT_CURRENCY_PROVISION_FILE_CONTENT_TYPE)
            .isMigrational(DEFAULT_IS_MIGRATIONAL)
            .otherCost(DEFAULT_OTHER_COST);
        return draftReceipt;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftReceipt createUpdatedEntity(EntityManager em) {
        DraftReceipt draftReceipt = new DraftReceipt()
            .comment(UPDATED_COMMENT)
            .customerDeliverDate(UPDATED_CUSTOMER_DELIVER_DATE)
            .date(UPDATED_DATE)
            .deleteDate(UPDATED_DELETE_DATE)
            .deliverDate(UPDATED_DELIVER_DATE)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .delivered(UPDATED_DELIVERED)
            .documentTransactionEffectiveDate(UPDATED_DOCUMENT_TRANSACTION_EFFECTIVE_DATE)
            .freightLetterDate(UPDATED_FREIGHT_LETTER_DATE)
            .freightLetterNumber(UPDATED_FREIGHT_LETTER_NUMBER)
            .freightLetterStampCost(UPDATED_FREIGHT_LETTER_STAMP_COST)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDocument(UPDATED_ISSUE_DOCUMENT)
            .productAmount(UPDATED_PRODUCT_AMOUNT)
            .receiptAmount(UPDATED_RECEIPT_AMOUNT)
            .receiptDate(UPDATED_RECEIPT_DATE)
            .row(UPDATED_ROW)
            .serial(UPDATED_SERIAL)
            .transportRow(UPDATED_TRANSPORT_ROW)
            .usable(UPDATED_USABLE)
            .paymentCurrencyRateTypeDesc(UPDATED_PAYMENT_CURRENCY_RATE_TYPE_DESC)
            .paymentItemTypeDesc(UPDATED_PAYMENT_ITEM_TYPE_DESC)
            .netWeight(UPDATED_NET_WEIGHT)
            .grossWeight(UPDATED_GROSS_WEIGHT)
            .totalNetWeight(UPDATED_TOTAL_NET_WEIGHT)
            .totalGrossWeight(UPDATED_TOTAL_GROSS_WEIGHT)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .shippingFare(UPDATED_SHIPPING_FARE)
            .inspectionCost(UPDATED_INSPECTION_COST)
            .discount(UPDATED_DISCOUNT)
            .deadlineSubmitDocumentDate(UPDATED_DEADLINE_SUBMIT_DOCUMENT_DATE)
            .currencyProvisionFile(UPDATED_CURRENCY_PROVISION_FILE)
            .currencyProvisionFileContentType(UPDATED_CURRENCY_PROVISION_FILE_CONTENT_TYPE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .otherCost(UPDATED_OTHER_COST);
        return draftReceipt;
    }

    @BeforeEach
    public void initTest() {
        draftReceipt = createEntity(em);
    }

    @Test
    @Transactional
    void createDraftReceipt() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);
        var returnedDraftReceiptDTO = om.readValue(
            restDraftReceiptMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftReceiptDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftReceiptDTO.class
        );

        // Validate the DraftReceipt in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftReceipt = draftReceiptMapper.toEntity(returnedDraftReceiptDTO);
        assertDraftReceiptUpdatableFieldsEquals(returnedDraftReceipt, getPersistedDraftReceipt(returnedDraftReceipt));
    }

    @Test
    @Transactional
    void createDraftReceiptWithExistingId() throws Exception {
        // Create the DraftReceipt with an existing ID
        draftReceipt.setId(1L);
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftReceiptMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftReceiptDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftReceipts() throws Exception {
        // Initialize the database
        draftReceiptRepository.saveAndFlush(draftReceipt);

        // Get all the draftReceiptList
        restDraftReceiptMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftReceipt.getId().intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].customerDeliverDate").value(hasItem(DEFAULT_CUSTOMER_DELIVER_DATE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].deleteDate").value(hasItem(DEFAULT_DELETE_DATE)))
            .andExpect(jsonPath("$.[*].deliverDate").value(hasItem(DEFAULT_DELIVER_DATE)))
            .andExpect(jsonPath("$.[*].deliverDuration").value(hasItem(DEFAULT_DELIVER_DURATION)))
            .andExpect(jsonPath("$.[*].delivered").value(hasItem(DEFAULT_DELIVERED.booleanValue())))
            .andExpect(jsonPath("$.[*].documentTransactionEffectiveDate").value(hasItem(DEFAULT_DOCUMENT_TRANSACTION_EFFECTIVE_DATE)))
            .andExpect(jsonPath("$.[*].freightLetterDate").value(hasItem(DEFAULT_FREIGHT_LETTER_DATE)))
            .andExpect(jsonPath("$.[*].freightLetterNumber").value(hasItem(DEFAULT_FREIGHT_LETTER_NUMBER)))
            .andExpect(jsonPath("$.[*].freightLetterStampCost").value(hasItem(sameNumber(DEFAULT_FREIGHT_LETTER_STAMP_COST))))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE)))
            .andExpect(jsonPath("$.[*].issueDocument").value(hasItem(DEFAULT_ISSUE_DOCUMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].productAmount").value(hasItem(sameNumber(DEFAULT_PRODUCT_AMOUNT))))
            .andExpect(jsonPath("$.[*].receiptAmount").value(hasItem(sameNumber(DEFAULT_RECEIPT_AMOUNT))))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE)))
            .andExpect(jsonPath("$.[*].row").value(hasItem(DEFAULT_ROW)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)))
            .andExpect(jsonPath("$.[*].transportRow").value(hasItem(DEFAULT_TRANSPORT_ROW)))
            .andExpect(jsonPath("$.[*].usable").value(hasItem(DEFAULT_USABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].paymentCurrencyRateTypeDesc").value(hasItem(DEFAULT_PAYMENT_CURRENCY_RATE_TYPE_DESC)))
            .andExpect(jsonPath("$.[*].paymentItemTypeDesc").value(hasItem(DEFAULT_PAYMENT_ITEM_TYPE_DESC)))
            .andExpect(jsonPath("$.[*].netWeight").value(hasItem(sameNumber(DEFAULT_NET_WEIGHT))))
            .andExpect(jsonPath("$.[*].grossWeight").value(hasItem(sameNumber(DEFAULT_GROSS_WEIGHT))))
            .andExpect(jsonPath("$.[*].totalNetWeight").value(hasItem(sameNumber(DEFAULT_TOTAL_NET_WEIGHT))))
            .andExpect(jsonPath("$.[*].totalGrossWeight").value(hasItem(sameNumber(DEFAULT_TOTAL_GROSS_WEIGHT))))
            .andExpect(jsonPath("$.[*].letterNumberTazirat").value(hasItem(DEFAULT_LETTER_NUMBER_TAZIRAT)))
            .andExpect(jsonPath("$.[*].letterDateTazirat").value(hasItem(DEFAULT_LETTER_DATE_TAZIRAT)))
            .andExpect(jsonPath("$.[*].fobAmount").value(hasItem(sameNumber(DEFAULT_FOB_AMOUNT))))
            .andExpect(jsonPath("$.[*].shippingFare").value(hasItem(sameNumber(DEFAULT_SHIPPING_FARE))))
            .andExpect(jsonPath("$.[*].inspectionCost").value(hasItem(sameNumber(DEFAULT_INSPECTION_COST))))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(sameNumber(DEFAULT_DISCOUNT))))
            .andExpect(jsonPath("$.[*].deadlineSubmitDocumentDate").value(hasItem(DEFAULT_DEADLINE_SUBMIT_DOCUMENT_DATE)))
            .andExpect(jsonPath("$.[*].currencyProvisionFileContentType").value(hasItem(DEFAULT_CURRENCY_PROVISION_FILE_CONTENT_TYPE)))
            .andExpect(
                jsonPath("$.[*].currencyProvisionFile").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_CURRENCY_PROVISION_FILE)))
            )
            .andExpect(jsonPath("$.[*].isMigrational").value(hasItem(DEFAULT_IS_MIGRATIONAL.booleanValue())))
            .andExpect(jsonPath("$.[*].otherCost").value(hasItem(sameNumber(DEFAULT_OTHER_COST))));
    }

    @Test
    @Transactional
    void getDraftReceipt() throws Exception {
        // Initialize the database
        draftReceiptRepository.saveAndFlush(draftReceipt);

        // Get the draftReceipt
        restDraftReceiptMockMvc
            .perform(get(ENTITY_API_URL_ID, draftReceipt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftReceipt.getId().intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.customerDeliverDate").value(DEFAULT_CUSTOMER_DELIVER_DATE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.deleteDate").value(DEFAULT_DELETE_DATE))
            .andExpect(jsonPath("$.deliverDate").value(DEFAULT_DELIVER_DATE))
            .andExpect(jsonPath("$.deliverDuration").value(DEFAULT_DELIVER_DURATION))
            .andExpect(jsonPath("$.delivered").value(DEFAULT_DELIVERED.booleanValue()))
            .andExpect(jsonPath("$.documentTransactionEffectiveDate").value(DEFAULT_DOCUMENT_TRANSACTION_EFFECTIVE_DATE))
            .andExpect(jsonPath("$.freightLetterDate").value(DEFAULT_FREIGHT_LETTER_DATE))
            .andExpect(jsonPath("$.freightLetterNumber").value(DEFAULT_FREIGHT_LETTER_NUMBER))
            .andExpect(jsonPath("$.freightLetterStampCost").value(sameNumber(DEFAULT_FREIGHT_LETTER_STAMP_COST)))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE))
            .andExpect(jsonPath("$.issueDocument").value(DEFAULT_ISSUE_DOCUMENT.booleanValue()))
            .andExpect(jsonPath("$.productAmount").value(sameNumber(DEFAULT_PRODUCT_AMOUNT)))
            .andExpect(jsonPath("$.receiptAmount").value(sameNumber(DEFAULT_RECEIPT_AMOUNT)))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE))
            .andExpect(jsonPath("$.row").value(DEFAULT_ROW))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL))
            .andExpect(jsonPath("$.transportRow").value(DEFAULT_TRANSPORT_ROW))
            .andExpect(jsonPath("$.usable").value(DEFAULT_USABLE.booleanValue()))
            .andExpect(jsonPath("$.paymentCurrencyRateTypeDesc").value(DEFAULT_PAYMENT_CURRENCY_RATE_TYPE_DESC))
            .andExpect(jsonPath("$.paymentItemTypeDesc").value(DEFAULT_PAYMENT_ITEM_TYPE_DESC))
            .andExpect(jsonPath("$.netWeight").value(sameNumber(DEFAULT_NET_WEIGHT)))
            .andExpect(jsonPath("$.grossWeight").value(sameNumber(DEFAULT_GROSS_WEIGHT)))
            .andExpect(jsonPath("$.totalNetWeight").value(sameNumber(DEFAULT_TOTAL_NET_WEIGHT)))
            .andExpect(jsonPath("$.totalGrossWeight").value(sameNumber(DEFAULT_TOTAL_GROSS_WEIGHT)))
            .andExpect(jsonPath("$.letterNumberTazirat").value(DEFAULT_LETTER_NUMBER_TAZIRAT))
            .andExpect(jsonPath("$.letterDateTazirat").value(DEFAULT_LETTER_DATE_TAZIRAT))
            .andExpect(jsonPath("$.fobAmount").value(sameNumber(DEFAULT_FOB_AMOUNT)))
            .andExpect(jsonPath("$.shippingFare").value(sameNumber(DEFAULT_SHIPPING_FARE)))
            .andExpect(jsonPath("$.inspectionCost").value(sameNumber(DEFAULT_INSPECTION_COST)))
            .andExpect(jsonPath("$.discount").value(sameNumber(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.deadlineSubmitDocumentDate").value(DEFAULT_DEADLINE_SUBMIT_DOCUMENT_DATE))
            .andExpect(jsonPath("$.currencyProvisionFileContentType").value(DEFAULT_CURRENCY_PROVISION_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.currencyProvisionFile").value(Base64.getEncoder().encodeToString(DEFAULT_CURRENCY_PROVISION_FILE)))
            .andExpect(jsonPath("$.isMigrational").value(DEFAULT_IS_MIGRATIONAL.booleanValue()))
            .andExpect(jsonPath("$.otherCost").value(sameNumber(DEFAULT_OTHER_COST)));
    }

    @Test
    @Transactional
    void getNonExistingDraftReceipt() throws Exception {
        // Get the draftReceipt
        restDraftReceiptMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftReceipt() throws Exception {
        // Initialize the database
        draftReceiptRepository.saveAndFlush(draftReceipt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftReceipt
        DraftReceipt updatedDraftReceipt = draftReceiptRepository.findById(draftReceipt.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftReceipt are not directly saved in db
        em.detach(updatedDraftReceipt);
        updatedDraftReceipt
            .comment(UPDATED_COMMENT)
            .customerDeliverDate(UPDATED_CUSTOMER_DELIVER_DATE)
            .date(UPDATED_DATE)
            .deleteDate(UPDATED_DELETE_DATE)
            .deliverDate(UPDATED_DELIVER_DATE)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .delivered(UPDATED_DELIVERED)
            .documentTransactionEffectiveDate(UPDATED_DOCUMENT_TRANSACTION_EFFECTIVE_DATE)
            .freightLetterDate(UPDATED_FREIGHT_LETTER_DATE)
            .freightLetterNumber(UPDATED_FREIGHT_LETTER_NUMBER)
            .freightLetterStampCost(UPDATED_FREIGHT_LETTER_STAMP_COST)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDocument(UPDATED_ISSUE_DOCUMENT)
            .productAmount(UPDATED_PRODUCT_AMOUNT)
            .receiptAmount(UPDATED_RECEIPT_AMOUNT)
            .receiptDate(UPDATED_RECEIPT_DATE)
            .row(UPDATED_ROW)
            .serial(UPDATED_SERIAL)
            .transportRow(UPDATED_TRANSPORT_ROW)
            .usable(UPDATED_USABLE)
            .paymentCurrencyRateTypeDesc(UPDATED_PAYMENT_CURRENCY_RATE_TYPE_DESC)
            .paymentItemTypeDesc(UPDATED_PAYMENT_ITEM_TYPE_DESC)
            .netWeight(UPDATED_NET_WEIGHT)
            .grossWeight(UPDATED_GROSS_WEIGHT)
            .totalNetWeight(UPDATED_TOTAL_NET_WEIGHT)
            .totalGrossWeight(UPDATED_TOTAL_GROSS_WEIGHT)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .shippingFare(UPDATED_SHIPPING_FARE)
            .inspectionCost(UPDATED_INSPECTION_COST)
            .discount(UPDATED_DISCOUNT)
            .deadlineSubmitDocumentDate(UPDATED_DEADLINE_SUBMIT_DOCUMENT_DATE)
            .currencyProvisionFile(UPDATED_CURRENCY_PROVISION_FILE)
            .currencyProvisionFileContentType(UPDATED_CURRENCY_PROVISION_FILE_CONTENT_TYPE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .otherCost(UPDATED_OTHER_COST);
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(updatedDraftReceipt);

        restDraftReceiptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftReceiptDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftReceiptToMatchAllProperties(updatedDraftReceipt);
    }

    @Test
    @Transactional
    void putNonExistingDraftReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceipt.setId(longCount.incrementAndGet());

        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftReceiptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftReceiptDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceipt.setId(longCount.incrementAndGet());

        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceipt.setId(longCount.incrementAndGet());

        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftReceiptDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftReceiptWithPatch() throws Exception {
        // Initialize the database
        draftReceiptRepository.saveAndFlush(draftReceipt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftReceipt using partial update
        DraftReceipt partialUpdatedDraftReceipt = new DraftReceipt();
        partialUpdatedDraftReceipt.setId(draftReceipt.getId());

        partialUpdatedDraftReceipt
            .comment(UPDATED_COMMENT)
            .date(UPDATED_DATE)
            .deliverDate(UPDATED_DELIVER_DATE)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .delivered(UPDATED_DELIVERED)
            .freightLetterDate(UPDATED_FREIGHT_LETTER_DATE)
            .freightLetterStampCost(UPDATED_FREIGHT_LETTER_STAMP_COST)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDocument(UPDATED_ISSUE_DOCUMENT)
            .productAmount(UPDATED_PRODUCT_AMOUNT)
            .receiptDate(UPDATED_RECEIPT_DATE)
            .paymentCurrencyRateTypeDesc(UPDATED_PAYMENT_CURRENCY_RATE_TYPE_DESC)
            .paymentItemTypeDesc(UPDATED_PAYMENT_ITEM_TYPE_DESC)
            .totalGrossWeight(UPDATED_TOTAL_GROSS_WEIGHT)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .shippingFare(UPDATED_SHIPPING_FARE)
            .deadlineSubmitDocumentDate(UPDATED_DEADLINE_SUBMIT_DOCUMENT_DATE)
            .otherCost(UPDATED_OTHER_COST);

        restDraftReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftReceipt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftReceipt))
            )
            .andExpect(status().isOk());

        // Validate the DraftReceipt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftReceiptUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftReceipt, draftReceipt),
            getPersistedDraftReceipt(draftReceipt)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftReceiptWithPatch() throws Exception {
        // Initialize the database
        draftReceiptRepository.saveAndFlush(draftReceipt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftReceipt using partial update
        DraftReceipt partialUpdatedDraftReceipt = new DraftReceipt();
        partialUpdatedDraftReceipt.setId(draftReceipt.getId());

        partialUpdatedDraftReceipt
            .comment(UPDATED_COMMENT)
            .customerDeliverDate(UPDATED_CUSTOMER_DELIVER_DATE)
            .date(UPDATED_DATE)
            .deleteDate(UPDATED_DELETE_DATE)
            .deliverDate(UPDATED_DELIVER_DATE)
            .deliverDuration(UPDATED_DELIVER_DURATION)
            .delivered(UPDATED_DELIVERED)
            .documentTransactionEffectiveDate(UPDATED_DOCUMENT_TRANSACTION_EFFECTIVE_DATE)
            .freightLetterDate(UPDATED_FREIGHT_LETTER_DATE)
            .freightLetterNumber(UPDATED_FREIGHT_LETTER_NUMBER)
            .freightLetterStampCost(UPDATED_FREIGHT_LETTER_STAMP_COST)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueDocument(UPDATED_ISSUE_DOCUMENT)
            .productAmount(UPDATED_PRODUCT_AMOUNT)
            .receiptAmount(UPDATED_RECEIPT_AMOUNT)
            .receiptDate(UPDATED_RECEIPT_DATE)
            .row(UPDATED_ROW)
            .serial(UPDATED_SERIAL)
            .transportRow(UPDATED_TRANSPORT_ROW)
            .usable(UPDATED_USABLE)
            .paymentCurrencyRateTypeDesc(UPDATED_PAYMENT_CURRENCY_RATE_TYPE_DESC)
            .paymentItemTypeDesc(UPDATED_PAYMENT_ITEM_TYPE_DESC)
            .netWeight(UPDATED_NET_WEIGHT)
            .grossWeight(UPDATED_GROSS_WEIGHT)
            .totalNetWeight(UPDATED_TOTAL_NET_WEIGHT)
            .totalGrossWeight(UPDATED_TOTAL_GROSS_WEIGHT)
            .letterNumberTazirat(UPDATED_LETTER_NUMBER_TAZIRAT)
            .letterDateTazirat(UPDATED_LETTER_DATE_TAZIRAT)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .shippingFare(UPDATED_SHIPPING_FARE)
            .inspectionCost(UPDATED_INSPECTION_COST)
            .discount(UPDATED_DISCOUNT)
            .deadlineSubmitDocumentDate(UPDATED_DEADLINE_SUBMIT_DOCUMENT_DATE)
            .currencyProvisionFile(UPDATED_CURRENCY_PROVISION_FILE)
            .currencyProvisionFileContentType(UPDATED_CURRENCY_PROVISION_FILE_CONTENT_TYPE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .otherCost(UPDATED_OTHER_COST);

        restDraftReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftReceipt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftReceipt))
            )
            .andExpect(status().isOk());

        // Validate the DraftReceipt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftReceiptUpdatableFieldsEquals(partialUpdatedDraftReceipt, getPersistedDraftReceipt(partialUpdatedDraftReceipt));
    }

    @Test
    @Transactional
    void patchNonExistingDraftReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceipt.setId(longCount.incrementAndGet());

        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftReceiptDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftReceiptDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceipt.setId(longCount.incrementAndGet());

        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftReceiptDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceipt.setId(longCount.incrementAndGet());

        // Create the DraftReceipt
        DraftReceiptDTO draftReceiptDTO = draftReceiptMapper.toDto(draftReceipt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftReceiptDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftReceipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftReceipt() throws Exception {
        // Initialize the database
        draftReceiptRepository.saveAndFlush(draftReceipt);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftReceipt
        restDraftReceiptMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftReceipt.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftReceiptRepository.count();
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

    protected DraftReceipt getPersistedDraftReceipt(DraftReceipt draftReceipt) {
        return draftReceiptRepository.findById(draftReceipt.getId()).orElseThrow();
    }

    protected void assertPersistedDraftReceiptToMatchAllProperties(DraftReceipt expectedDraftReceipt) {
        assertDraftReceiptAllPropertiesEquals(expectedDraftReceipt, getPersistedDraftReceipt(expectedDraftReceipt));
    }

    protected void assertPersistedDraftReceiptToMatchUpdatableProperties(DraftReceipt expectedDraftReceipt) {
        assertDraftReceiptAllUpdatablePropertiesEquals(expectedDraftReceipt, getPersistedDraftReceipt(expectedDraftReceipt));
    }
}
