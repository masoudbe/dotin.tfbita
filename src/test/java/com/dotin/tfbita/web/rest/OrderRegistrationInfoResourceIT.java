package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.OrderRegistrationInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.repository.OrderRegistrationInfoRepository;
import com.dotin.tfbita.service.OrderRegistrationInfoService;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.mapper.OrderRegistrationInfoMapper;
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
 * Integration tests for the {@link OrderRegistrationInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class OrderRegistrationInfoResourceIT {

    private static final String DEFAULT_ORDER_REG_NUM = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REG_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_START_ORDER_REG_DATE = "AAAAAAAAAA";
    private static final String UPDATED_START_ORDER_REG_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_END_ORDER_REG_DATE = "AAAAAAAAAA";
    private static final String UPDATED_END_ORDER_REG_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICANT_NATIONALCODE = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT_NATIONALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_EXPIRY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_EXPIRY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_DATE_PERSIAN = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_DATE_PERSIAN = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMA_EXPIRY_DATE_PERSIAN = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMA_EXPIRY_DATE_PERSIAN = "BBBBBBBBBB";

    private static final String DEFAULT_INFO_SUBMISSION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_INFO_SUBMISSION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SELLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SELLER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCER_COUNTRIES_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCER_COUNTRIES_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_COUNTRY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MULTIPLE_TRANSPORTABLE = false;
    private static final Boolean UPDATED_MULTIPLE_TRANSPORTABLE = true;

    private static final String DEFAULT_DELIVERY_TIME_OF_GOODS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TIME_OF_GOODS = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_WEIGHT_IN_KG = 1D;
    private static final Double UPDATED_TOTAL_WEIGHT_IN_KG = 2D;

    private static final Boolean DEFAULT_POSSIBILITY_CARRYING = false;
    private static final Boolean UPDATED_POSSIBILITY_CARRYING = true;

    private static final Boolean DEFAULT_POSSIBILITY_CLEARANCE = false;
    private static final Boolean UPDATED_POSSIBILITY_CLEARANCE = true;

    private static final Boolean DEFAULT_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER = false;
    private static final Boolean UPDATED_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER = true;

    private static final Boolean DEFAULT_FREE_ZONE = false;
    private static final Boolean UPDATED_FREE_ZONE = true;

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FOB_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_FOB_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHIPMENT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHIPMENT_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OTHR_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTHR_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL_AMOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_IS_FILE = false;
    private static final Boolean UPDATED_IS_FILE = true;

    private static final String DEFAULT_FILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXTENDED = false;
    private static final Boolean UPDATED_EXTENDED = true;

    private static final Boolean DEFAULT_UPDATED = false;
    private static final Boolean UPDATED_UPDATED = true;

    private static final Boolean DEFAULT_GENERATE_FROM_SERVICE = false;
    private static final Boolean UPDATED_GENERATE_FROM_SERVICE = true;

    private static final String DEFAULT_CERTIFICATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CENTRAL_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CENTRAL_BANK_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MIGRATIONAL = false;
    private static final Boolean UPDATED_IS_MIGRATIONAL = true;

    private static final Long DEFAULT_EXTERNAL_CUSTOMER = 1L;
    private static final Long UPDATED_EXTERNAL_CUSTOMER = 2L;

    private static final Long DEFAULT_SUM_REDEMPTION_DURATION = 1L;
    private static final Long UPDATED_SUM_REDEMPTION_DURATION = 2L;

    private static final Long DEFAULT_EXTENDED_DAY_NUMBER = 1L;
    private static final Long UPDATED_EXTENDED_DAY_NUMBER = 2L;

    private static final String DEFAULT_MAIN_GROUP_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_GROUP_PRODUCT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCER_COUNTRIES = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCER_COUNTRIES = "BBBBBBBBBB";

    private static final byte[] DEFAULT_EXCEL_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_EXCEL_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_EXCEL_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_EXCEL_FILE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_COMMISSION_TRANSACTION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMMISSION_TRANSACTION_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/order-registration-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OrderRegistrationInfoRepository orderRegistrationInfoRepository;

    @Mock
    private OrderRegistrationInfoRepository orderRegistrationInfoRepositoryMock;

    @Autowired
    private OrderRegistrationInfoMapper orderRegistrationInfoMapper;

    @Mock
    private OrderRegistrationInfoService orderRegistrationInfoServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderRegistrationInfoMockMvc;

    private OrderRegistrationInfo orderRegistrationInfo;

    private OrderRegistrationInfo insertedOrderRegistrationInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRegistrationInfo createEntity(EntityManager em) {
        OrderRegistrationInfo orderRegistrationInfo = new OrderRegistrationInfo()
            .orderRegNum(DEFAULT_ORDER_REG_NUM)
            .startOrderRegDate(DEFAULT_START_ORDER_REG_DATE)
            .endOrderRegDate(DEFAULT_END_ORDER_REG_DATE)
            .requestNumber(DEFAULT_REQUEST_NUMBER)
            .bankCode(DEFAULT_BANK_CODE)
            .branchCode(DEFAULT_BRANCH_CODE)
            .customerNumber(DEFAULT_CUSTOMER_NUMBER)
            .applicantNationalcode(DEFAULT_APPLICANT_NATIONALCODE)
            .performaNumber(DEFAULT_PERFORMA_NUMBER)
            .performaDate(DEFAULT_PERFORMA_DATE)
            .performaExpiryDate(DEFAULT_PERFORMA_EXPIRY_DATE)
            .performaDatePersian(DEFAULT_PERFORMA_DATE_PERSIAN)
            .performaExpiryDatePersian(DEFAULT_PERFORMA_EXPIRY_DATE_PERSIAN)
            .infoSubmissionDate(DEFAULT_INFO_SUBMISSION_DATE)
            .sellerName(DEFAULT_SELLER_NAME)
            .beneficiaryCountryCode(DEFAULT_BENEFICIARY_COUNTRY_CODE)
            .producerCountriesCode(DEFAULT_PRODUCER_COUNTRIES_CODE)
            .sourceCountry(DEFAULT_SOURCE_COUNTRY)
            .multipleTransportable(DEFAULT_MULTIPLE_TRANSPORTABLE)
            .deliveryTimeOfGoods(DEFAULT_DELIVERY_TIME_OF_GOODS)
            .totalWeightInKg(DEFAULT_TOTAL_WEIGHT_IN_KG)
            .possibilityCarrying(DEFAULT_POSSIBILITY_CARRYING)
            .possibilityClearance(DEFAULT_POSSIBILITY_CLEARANCE)
            .ableAddServiceInProductOrder(DEFAULT_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER)
            .freeZone(DEFAULT_FREE_ZONE)
            .currencyCode(DEFAULT_CURRENCY_CODE)
            .fobAmount(DEFAULT_FOB_AMOUNT)
            .discount(DEFAULT_DISCOUNT)
            .shipmentCost(DEFAULT_SHIPMENT_COST)
            .othrCost(DEFAULT_OTHR_COST)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .isFile(DEFAULT_IS_FILE)
            .fileNumber(DEFAULT_FILE_NUMBER)
            .extended(DEFAULT_EXTENDED)
            .updated(DEFAULT_UPDATED)
            .generateFromService(DEFAULT_GENERATE_FROM_SERVICE)
            .certificateNumber(DEFAULT_CERTIFICATE_NUMBER)
            .centralBankCode(DEFAULT_CENTRAL_BANK_CODE)
            .isMigrational(DEFAULT_IS_MIGRATIONAL)
            .externalCustomer(DEFAULT_EXTERNAL_CUSTOMER)
            .sumRedemptionDuration(DEFAULT_SUM_REDEMPTION_DURATION)
            .extendedDayNumber(DEFAULT_EXTENDED_DAY_NUMBER)
            .mainGroupProductCode(DEFAULT_MAIN_GROUP_PRODUCT_CODE)
            .producerCountries(DEFAULT_PRODUCER_COUNTRIES)
            .excelFile(DEFAULT_EXCEL_FILE)
            .excelFileContentType(DEFAULT_EXCEL_FILE_CONTENT_TYPE)
            .commissionTransactionNumber(DEFAULT_COMMISSION_TRANSACTION_NUMBER);
        return orderRegistrationInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRegistrationInfo createUpdatedEntity(EntityManager em) {
        OrderRegistrationInfo orderRegistrationInfo = new OrderRegistrationInfo()
            .orderRegNum(UPDATED_ORDER_REG_NUM)
            .startOrderRegDate(UPDATED_START_ORDER_REG_DATE)
            .endOrderRegDate(UPDATED_END_ORDER_REG_DATE)
            .requestNumber(UPDATED_REQUEST_NUMBER)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .applicantNationalcode(UPDATED_APPLICANT_NATIONALCODE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaDatePersian(UPDATED_PERFORMA_DATE_PERSIAN)
            .performaExpiryDatePersian(UPDATED_PERFORMA_EXPIRY_DATE_PERSIAN)
            .infoSubmissionDate(UPDATED_INFO_SUBMISSION_DATE)
            .sellerName(UPDATED_SELLER_NAME)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountriesCode(UPDATED_PRODUCER_COUNTRIES_CODE)
            .sourceCountry(UPDATED_SOURCE_COUNTRY)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .deliveryTimeOfGoods(UPDATED_DELIVERY_TIME_OF_GOODS)
            .totalWeightInKg(UPDATED_TOTAL_WEIGHT_IN_KG)
            .possibilityCarrying(UPDATED_POSSIBILITY_CARRYING)
            .possibilityClearance(UPDATED_POSSIBILITY_CLEARANCE)
            .ableAddServiceInProductOrder(UPDATED_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER)
            .freeZone(UPDATED_FREE_ZONE)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .discount(UPDATED_DISCOUNT)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .othrCost(UPDATED_OTHR_COST)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .isFile(UPDATED_IS_FILE)
            .fileNumber(UPDATED_FILE_NUMBER)
            .extended(UPDATED_EXTENDED)
            .updated(UPDATED_UPDATED)
            .generateFromService(UPDATED_GENERATE_FROM_SERVICE)
            .certificateNumber(UPDATED_CERTIFICATE_NUMBER)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .sumRedemptionDuration(UPDATED_SUM_REDEMPTION_DURATION)
            .extendedDayNumber(UPDATED_EXTENDED_DAY_NUMBER)
            .mainGroupProductCode(UPDATED_MAIN_GROUP_PRODUCT_CODE)
            .producerCountries(UPDATED_PRODUCER_COUNTRIES)
            .excelFile(UPDATED_EXCEL_FILE)
            .excelFileContentType(UPDATED_EXCEL_FILE_CONTENT_TYPE)
            .commissionTransactionNumber(UPDATED_COMMISSION_TRANSACTION_NUMBER);
        return orderRegistrationInfo;
    }

    @BeforeEach
    public void initTest() {
        orderRegistrationInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOrderRegistrationInfo != null) {
            orderRegistrationInfoRepository.delete(insertedOrderRegistrationInfo);
            insertedOrderRegistrationInfo = null;
        }
    }

    @Test
    @Transactional
    void createOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);
        var returnedOrderRegistrationInfoDTO = om.readValue(
            restOrderRegistrationInfoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegistrationInfoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OrderRegistrationInfoDTO.class
        );

        // Validate the OrderRegistrationInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedOrderRegistrationInfo = orderRegistrationInfoMapper.toEntity(returnedOrderRegistrationInfoDTO);
        assertOrderRegistrationInfoUpdatableFieldsEquals(
            returnedOrderRegistrationInfo,
            getPersistedOrderRegistrationInfo(returnedOrderRegistrationInfo)
        );

        insertedOrderRegistrationInfo = returnedOrderRegistrationInfo;
    }

    @Test
    @Transactional
    void createOrderRegistrationInfoWithExistingId() throws Exception {
        // Create the OrderRegistrationInfo with an existing ID
        orderRegistrationInfo.setId(1L);
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderRegistrationInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegistrationInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOrderRegistrationInfos() throws Exception {
        // Initialize the database
        insertedOrderRegistrationInfo = orderRegistrationInfoRepository.saveAndFlush(orderRegistrationInfo);

        // Get all the orderRegistrationInfoList
        restOrderRegistrationInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderRegistrationInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderRegNum").value(hasItem(DEFAULT_ORDER_REG_NUM)))
            .andExpect(jsonPath("$.[*].startOrderRegDate").value(hasItem(DEFAULT_START_ORDER_REG_DATE)))
            .andExpect(jsonPath("$.[*].endOrderRegDate").value(hasItem(DEFAULT_END_ORDER_REG_DATE)))
            .andExpect(jsonPath("$.[*].requestNumber").value(hasItem(DEFAULT_REQUEST_NUMBER)))
            .andExpect(jsonPath("$.[*].bankCode").value(hasItem(DEFAULT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].branchCode").value(hasItem(DEFAULT_BRANCH_CODE)))
            .andExpect(jsonPath("$.[*].customerNumber").value(hasItem(DEFAULT_CUSTOMER_NUMBER)))
            .andExpect(jsonPath("$.[*].applicantNationalcode").value(hasItem(DEFAULT_APPLICANT_NATIONALCODE)))
            .andExpect(jsonPath("$.[*].performaNumber").value(hasItem(DEFAULT_PERFORMA_NUMBER)))
            .andExpect(jsonPath("$.[*].performaDate").value(hasItem(DEFAULT_PERFORMA_DATE)))
            .andExpect(jsonPath("$.[*].performaExpiryDate").value(hasItem(DEFAULT_PERFORMA_EXPIRY_DATE)))
            .andExpect(jsonPath("$.[*].performaDatePersian").value(hasItem(DEFAULT_PERFORMA_DATE_PERSIAN)))
            .andExpect(jsonPath("$.[*].performaExpiryDatePersian").value(hasItem(DEFAULT_PERFORMA_EXPIRY_DATE_PERSIAN)))
            .andExpect(jsonPath("$.[*].infoSubmissionDate").value(hasItem(DEFAULT_INFO_SUBMISSION_DATE)))
            .andExpect(jsonPath("$.[*].sellerName").value(hasItem(DEFAULT_SELLER_NAME)))
            .andExpect(jsonPath("$.[*].beneficiaryCountryCode").value(hasItem(DEFAULT_BENEFICIARY_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].producerCountriesCode").value(hasItem(DEFAULT_PRODUCER_COUNTRIES_CODE)))
            .andExpect(jsonPath("$.[*].sourceCountry").value(hasItem(DEFAULT_SOURCE_COUNTRY)))
            .andExpect(jsonPath("$.[*].multipleTransportable").value(hasItem(DEFAULT_MULTIPLE_TRANSPORTABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].deliveryTimeOfGoods").value(hasItem(DEFAULT_DELIVERY_TIME_OF_GOODS)))
            .andExpect(jsonPath("$.[*].totalWeightInKg").value(hasItem(DEFAULT_TOTAL_WEIGHT_IN_KG.doubleValue())))
            .andExpect(jsonPath("$.[*].possibilityCarrying").value(hasItem(DEFAULT_POSSIBILITY_CARRYING.booleanValue())))
            .andExpect(jsonPath("$.[*].possibilityClearance").value(hasItem(DEFAULT_POSSIBILITY_CLEARANCE.booleanValue())))
            .andExpect(
                jsonPath("$.[*].ableAddServiceInProductOrder").value(hasItem(DEFAULT_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].freeZone").value(hasItem(DEFAULT_FREE_ZONE.booleanValue())))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].fobAmount").value(hasItem(sameNumber(DEFAULT_FOB_AMOUNT))))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(sameNumber(DEFAULT_DISCOUNT))))
            .andExpect(jsonPath("$.[*].shipmentCost").value(hasItem(sameNumber(DEFAULT_SHIPMENT_COST))))
            .andExpect(jsonPath("$.[*].othrCost").value(hasItem(sameNumber(DEFAULT_OTHR_COST))))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(sameNumber(DEFAULT_TOTAL_AMOUNT))))
            .andExpect(jsonPath("$.[*].isFile").value(hasItem(DEFAULT_IS_FILE.booleanValue())))
            .andExpect(jsonPath("$.[*].fileNumber").value(hasItem(DEFAULT_FILE_NUMBER)))
            .andExpect(jsonPath("$.[*].extended").value(hasItem(DEFAULT_EXTENDED.booleanValue())))
            .andExpect(jsonPath("$.[*].updated").value(hasItem(DEFAULT_UPDATED.booleanValue())))
            .andExpect(jsonPath("$.[*].generateFromService").value(hasItem(DEFAULT_GENERATE_FROM_SERVICE.booleanValue())))
            .andExpect(jsonPath("$.[*].certificateNumber").value(hasItem(DEFAULT_CERTIFICATE_NUMBER)))
            .andExpect(jsonPath("$.[*].centralBankCode").value(hasItem(DEFAULT_CENTRAL_BANK_CODE)))
            .andExpect(jsonPath("$.[*].isMigrational").value(hasItem(DEFAULT_IS_MIGRATIONAL.booleanValue())))
            .andExpect(jsonPath("$.[*].externalCustomer").value(hasItem(DEFAULT_EXTERNAL_CUSTOMER.intValue())))
            .andExpect(jsonPath("$.[*].sumRedemptionDuration").value(hasItem(DEFAULT_SUM_REDEMPTION_DURATION.intValue())))
            .andExpect(jsonPath("$.[*].extendedDayNumber").value(hasItem(DEFAULT_EXTENDED_DAY_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].mainGroupProductCode").value(hasItem(DEFAULT_MAIN_GROUP_PRODUCT_CODE)))
            .andExpect(jsonPath("$.[*].producerCountries").value(hasItem(DEFAULT_PRODUCER_COUNTRIES)))
            .andExpect(jsonPath("$.[*].excelFileContentType").value(hasItem(DEFAULT_EXCEL_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].excelFile").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_EXCEL_FILE))))
            .andExpect(jsonPath("$.[*].commissionTransactionNumber").value(hasItem(DEFAULT_COMMISSION_TRANSACTION_NUMBER)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOrderRegistrationInfosWithEagerRelationshipsIsEnabled() throws Exception {
        when(orderRegistrationInfoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOrderRegistrationInfoMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(orderRegistrationInfoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOrderRegistrationInfosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(orderRegistrationInfoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOrderRegistrationInfoMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(orderRegistrationInfoRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getOrderRegistrationInfo() throws Exception {
        // Initialize the database
        insertedOrderRegistrationInfo = orderRegistrationInfoRepository.saveAndFlush(orderRegistrationInfo);

        // Get the orderRegistrationInfo
        restOrderRegistrationInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, orderRegistrationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderRegistrationInfo.getId().intValue()))
            .andExpect(jsonPath("$.orderRegNum").value(DEFAULT_ORDER_REG_NUM))
            .andExpect(jsonPath("$.startOrderRegDate").value(DEFAULT_START_ORDER_REG_DATE))
            .andExpect(jsonPath("$.endOrderRegDate").value(DEFAULT_END_ORDER_REG_DATE))
            .andExpect(jsonPath("$.requestNumber").value(DEFAULT_REQUEST_NUMBER))
            .andExpect(jsonPath("$.bankCode").value(DEFAULT_BANK_CODE))
            .andExpect(jsonPath("$.branchCode").value(DEFAULT_BRANCH_CODE))
            .andExpect(jsonPath("$.customerNumber").value(DEFAULT_CUSTOMER_NUMBER))
            .andExpect(jsonPath("$.applicantNationalcode").value(DEFAULT_APPLICANT_NATIONALCODE))
            .andExpect(jsonPath("$.performaNumber").value(DEFAULT_PERFORMA_NUMBER))
            .andExpect(jsonPath("$.performaDate").value(DEFAULT_PERFORMA_DATE))
            .andExpect(jsonPath("$.performaExpiryDate").value(DEFAULT_PERFORMA_EXPIRY_DATE))
            .andExpect(jsonPath("$.performaDatePersian").value(DEFAULT_PERFORMA_DATE_PERSIAN))
            .andExpect(jsonPath("$.performaExpiryDatePersian").value(DEFAULT_PERFORMA_EXPIRY_DATE_PERSIAN))
            .andExpect(jsonPath("$.infoSubmissionDate").value(DEFAULT_INFO_SUBMISSION_DATE))
            .andExpect(jsonPath("$.sellerName").value(DEFAULT_SELLER_NAME))
            .andExpect(jsonPath("$.beneficiaryCountryCode").value(DEFAULT_BENEFICIARY_COUNTRY_CODE))
            .andExpect(jsonPath("$.producerCountriesCode").value(DEFAULT_PRODUCER_COUNTRIES_CODE))
            .andExpect(jsonPath("$.sourceCountry").value(DEFAULT_SOURCE_COUNTRY))
            .andExpect(jsonPath("$.multipleTransportable").value(DEFAULT_MULTIPLE_TRANSPORTABLE.booleanValue()))
            .andExpect(jsonPath("$.deliveryTimeOfGoods").value(DEFAULT_DELIVERY_TIME_OF_GOODS))
            .andExpect(jsonPath("$.totalWeightInKg").value(DEFAULT_TOTAL_WEIGHT_IN_KG.doubleValue()))
            .andExpect(jsonPath("$.possibilityCarrying").value(DEFAULT_POSSIBILITY_CARRYING.booleanValue()))
            .andExpect(jsonPath("$.possibilityClearance").value(DEFAULT_POSSIBILITY_CLEARANCE.booleanValue()))
            .andExpect(jsonPath("$.ableAddServiceInProductOrder").value(DEFAULT_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER.booleanValue()))
            .andExpect(jsonPath("$.freeZone").value(DEFAULT_FREE_ZONE.booleanValue()))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.fobAmount").value(sameNumber(DEFAULT_FOB_AMOUNT)))
            .andExpect(jsonPath("$.discount").value(sameNumber(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.shipmentCost").value(sameNumber(DEFAULT_SHIPMENT_COST)))
            .andExpect(jsonPath("$.othrCost").value(sameNumber(DEFAULT_OTHR_COST)))
            .andExpect(jsonPath("$.totalAmount").value(sameNumber(DEFAULT_TOTAL_AMOUNT)))
            .andExpect(jsonPath("$.isFile").value(DEFAULT_IS_FILE.booleanValue()))
            .andExpect(jsonPath("$.fileNumber").value(DEFAULT_FILE_NUMBER))
            .andExpect(jsonPath("$.extended").value(DEFAULT_EXTENDED.booleanValue()))
            .andExpect(jsonPath("$.updated").value(DEFAULT_UPDATED.booleanValue()))
            .andExpect(jsonPath("$.generateFromService").value(DEFAULT_GENERATE_FROM_SERVICE.booleanValue()))
            .andExpect(jsonPath("$.certificateNumber").value(DEFAULT_CERTIFICATE_NUMBER))
            .andExpect(jsonPath("$.centralBankCode").value(DEFAULT_CENTRAL_BANK_CODE))
            .andExpect(jsonPath("$.isMigrational").value(DEFAULT_IS_MIGRATIONAL.booleanValue()))
            .andExpect(jsonPath("$.externalCustomer").value(DEFAULT_EXTERNAL_CUSTOMER.intValue()))
            .andExpect(jsonPath("$.sumRedemptionDuration").value(DEFAULT_SUM_REDEMPTION_DURATION.intValue()))
            .andExpect(jsonPath("$.extendedDayNumber").value(DEFAULT_EXTENDED_DAY_NUMBER.intValue()))
            .andExpect(jsonPath("$.mainGroupProductCode").value(DEFAULT_MAIN_GROUP_PRODUCT_CODE))
            .andExpect(jsonPath("$.producerCountries").value(DEFAULT_PRODUCER_COUNTRIES))
            .andExpect(jsonPath("$.excelFileContentType").value(DEFAULT_EXCEL_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.excelFile").value(Base64.getEncoder().encodeToString(DEFAULT_EXCEL_FILE)))
            .andExpect(jsonPath("$.commissionTransactionNumber").value(DEFAULT_COMMISSION_TRANSACTION_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingOrderRegistrationInfo() throws Exception {
        // Get the orderRegistrationInfo
        restOrderRegistrationInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOrderRegistrationInfo() throws Exception {
        // Initialize the database
        insertedOrderRegistrationInfo = orderRegistrationInfoRepository.saveAndFlush(orderRegistrationInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegistrationInfo
        OrderRegistrationInfo updatedOrderRegistrationInfo = orderRegistrationInfoRepository
            .findById(orderRegistrationInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOrderRegistrationInfo are not directly saved in db
        em.detach(updatedOrderRegistrationInfo);
        updatedOrderRegistrationInfo
            .orderRegNum(UPDATED_ORDER_REG_NUM)
            .startOrderRegDate(UPDATED_START_ORDER_REG_DATE)
            .endOrderRegDate(UPDATED_END_ORDER_REG_DATE)
            .requestNumber(UPDATED_REQUEST_NUMBER)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .applicantNationalcode(UPDATED_APPLICANT_NATIONALCODE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaDatePersian(UPDATED_PERFORMA_DATE_PERSIAN)
            .performaExpiryDatePersian(UPDATED_PERFORMA_EXPIRY_DATE_PERSIAN)
            .infoSubmissionDate(UPDATED_INFO_SUBMISSION_DATE)
            .sellerName(UPDATED_SELLER_NAME)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountriesCode(UPDATED_PRODUCER_COUNTRIES_CODE)
            .sourceCountry(UPDATED_SOURCE_COUNTRY)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .deliveryTimeOfGoods(UPDATED_DELIVERY_TIME_OF_GOODS)
            .totalWeightInKg(UPDATED_TOTAL_WEIGHT_IN_KG)
            .possibilityCarrying(UPDATED_POSSIBILITY_CARRYING)
            .possibilityClearance(UPDATED_POSSIBILITY_CLEARANCE)
            .ableAddServiceInProductOrder(UPDATED_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER)
            .freeZone(UPDATED_FREE_ZONE)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .discount(UPDATED_DISCOUNT)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .othrCost(UPDATED_OTHR_COST)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .isFile(UPDATED_IS_FILE)
            .fileNumber(UPDATED_FILE_NUMBER)
            .extended(UPDATED_EXTENDED)
            .updated(UPDATED_UPDATED)
            .generateFromService(UPDATED_GENERATE_FROM_SERVICE)
            .certificateNumber(UPDATED_CERTIFICATE_NUMBER)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .sumRedemptionDuration(UPDATED_SUM_REDEMPTION_DURATION)
            .extendedDayNumber(UPDATED_EXTENDED_DAY_NUMBER)
            .mainGroupProductCode(UPDATED_MAIN_GROUP_PRODUCT_CODE)
            .producerCountries(UPDATED_PRODUCER_COUNTRIES)
            .excelFile(UPDATED_EXCEL_FILE)
            .excelFileContentType(UPDATED_EXCEL_FILE_CONTENT_TYPE)
            .commissionTransactionNumber(UPDATED_COMMISSION_TRANSACTION_NUMBER);
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(updatedOrderRegistrationInfo);

        restOrderRegistrationInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderRegistrationInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegistrationInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOrderRegistrationInfoToMatchAllProperties(updatedOrderRegistrationInfo);
    }

    @Test
    @Transactional
    void putNonExistingOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegistrationInfo.setId(longCount.incrementAndGet());

        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRegistrationInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderRegistrationInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegistrationInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegistrationInfo.setId(longCount.incrementAndGet());

        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegistrationInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegistrationInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegistrationInfo.setId(longCount.incrementAndGet());

        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegistrationInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegistrationInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderRegistrationInfoWithPatch() throws Exception {
        // Initialize the database
        insertedOrderRegistrationInfo = orderRegistrationInfoRepository.saveAndFlush(orderRegistrationInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegistrationInfo using partial update
        OrderRegistrationInfo partialUpdatedOrderRegistrationInfo = new OrderRegistrationInfo();
        partialUpdatedOrderRegistrationInfo.setId(orderRegistrationInfo.getId());

        partialUpdatedOrderRegistrationInfo
            .endOrderRegDate(UPDATED_END_ORDER_REG_DATE)
            .requestNumber(UPDATED_REQUEST_NUMBER)
            .branchCode(UPDATED_BRANCH_CODE)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaDatePersian(UPDATED_PERFORMA_DATE_PERSIAN)
            .performaExpiryDatePersian(UPDATED_PERFORMA_EXPIRY_DATE_PERSIAN)
            .infoSubmissionDate(UPDATED_INFO_SUBMISSION_DATE)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountriesCode(UPDATED_PRODUCER_COUNTRIES_CODE)
            .deliveryTimeOfGoods(UPDATED_DELIVERY_TIME_OF_GOODS)
            .possibilityCarrying(UPDATED_POSSIBILITY_CARRYING)
            .possibilityClearance(UPDATED_POSSIBILITY_CLEARANCE)
            .freeZone(UPDATED_FREE_ZONE)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .isFile(UPDATED_IS_FILE)
            .extended(UPDATED_EXTENDED)
            .updated(UPDATED_UPDATED)
            .certificateNumber(UPDATED_CERTIFICATE_NUMBER)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .extendedDayNumber(UPDATED_EXTENDED_DAY_NUMBER)
            .producerCountries(UPDATED_PRODUCER_COUNTRIES)
            .excelFile(UPDATED_EXCEL_FILE)
            .excelFileContentType(UPDATED_EXCEL_FILE_CONTENT_TYPE)
            .commissionTransactionNumber(UPDATED_COMMISSION_TRANSACTION_NUMBER);

        restOrderRegistrationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderRegistrationInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOrderRegistrationInfo))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegistrationInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOrderRegistrationInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOrderRegistrationInfo, orderRegistrationInfo),
            getPersistedOrderRegistrationInfo(orderRegistrationInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateOrderRegistrationInfoWithPatch() throws Exception {
        // Initialize the database
        insertedOrderRegistrationInfo = orderRegistrationInfoRepository.saveAndFlush(orderRegistrationInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegistrationInfo using partial update
        OrderRegistrationInfo partialUpdatedOrderRegistrationInfo = new OrderRegistrationInfo();
        partialUpdatedOrderRegistrationInfo.setId(orderRegistrationInfo.getId());

        partialUpdatedOrderRegistrationInfo
            .orderRegNum(UPDATED_ORDER_REG_NUM)
            .startOrderRegDate(UPDATED_START_ORDER_REG_DATE)
            .endOrderRegDate(UPDATED_END_ORDER_REG_DATE)
            .requestNumber(UPDATED_REQUEST_NUMBER)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .applicantNationalcode(UPDATED_APPLICANT_NATIONALCODE)
            .performaNumber(UPDATED_PERFORMA_NUMBER)
            .performaDate(UPDATED_PERFORMA_DATE)
            .performaExpiryDate(UPDATED_PERFORMA_EXPIRY_DATE)
            .performaDatePersian(UPDATED_PERFORMA_DATE_PERSIAN)
            .performaExpiryDatePersian(UPDATED_PERFORMA_EXPIRY_DATE_PERSIAN)
            .infoSubmissionDate(UPDATED_INFO_SUBMISSION_DATE)
            .sellerName(UPDATED_SELLER_NAME)
            .beneficiaryCountryCode(UPDATED_BENEFICIARY_COUNTRY_CODE)
            .producerCountriesCode(UPDATED_PRODUCER_COUNTRIES_CODE)
            .sourceCountry(UPDATED_SOURCE_COUNTRY)
            .multipleTransportable(UPDATED_MULTIPLE_TRANSPORTABLE)
            .deliveryTimeOfGoods(UPDATED_DELIVERY_TIME_OF_GOODS)
            .totalWeightInKg(UPDATED_TOTAL_WEIGHT_IN_KG)
            .possibilityCarrying(UPDATED_POSSIBILITY_CARRYING)
            .possibilityClearance(UPDATED_POSSIBILITY_CLEARANCE)
            .ableAddServiceInProductOrder(UPDATED_ABLE_ADD_SERVICE_IN_PRODUCT_ORDER)
            .freeZone(UPDATED_FREE_ZONE)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .fobAmount(UPDATED_FOB_AMOUNT)
            .discount(UPDATED_DISCOUNT)
            .shipmentCost(UPDATED_SHIPMENT_COST)
            .othrCost(UPDATED_OTHR_COST)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .isFile(UPDATED_IS_FILE)
            .fileNumber(UPDATED_FILE_NUMBER)
            .extended(UPDATED_EXTENDED)
            .updated(UPDATED_UPDATED)
            .generateFromService(UPDATED_GENERATE_FROM_SERVICE)
            .certificateNumber(UPDATED_CERTIFICATE_NUMBER)
            .centralBankCode(UPDATED_CENTRAL_BANK_CODE)
            .isMigrational(UPDATED_IS_MIGRATIONAL)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .sumRedemptionDuration(UPDATED_SUM_REDEMPTION_DURATION)
            .extendedDayNumber(UPDATED_EXTENDED_DAY_NUMBER)
            .mainGroupProductCode(UPDATED_MAIN_GROUP_PRODUCT_CODE)
            .producerCountries(UPDATED_PRODUCER_COUNTRIES)
            .excelFile(UPDATED_EXCEL_FILE)
            .excelFileContentType(UPDATED_EXCEL_FILE_CONTENT_TYPE)
            .commissionTransactionNumber(UPDATED_COMMISSION_TRANSACTION_NUMBER);

        restOrderRegistrationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderRegistrationInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOrderRegistrationInfo))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegistrationInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOrderRegistrationInfoUpdatableFieldsEquals(
            partialUpdatedOrderRegistrationInfo,
            getPersistedOrderRegistrationInfo(partialUpdatedOrderRegistrationInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegistrationInfo.setId(longCount.incrementAndGet());

        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRegistrationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderRegistrationInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(orderRegistrationInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegistrationInfo.setId(longCount.incrementAndGet());

        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegistrationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(orderRegistrationInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderRegistrationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegistrationInfo.setId(longCount.incrementAndGet());

        // Create the OrderRegistrationInfo
        OrderRegistrationInfoDTO orderRegistrationInfoDTO = orderRegistrationInfoMapper.toDto(orderRegistrationInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegistrationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(orderRegistrationInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderRegistrationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderRegistrationInfo() throws Exception {
        // Initialize the database
        insertedOrderRegistrationInfo = orderRegistrationInfoRepository.saveAndFlush(orderRegistrationInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the orderRegistrationInfo
        restOrderRegistrationInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderRegistrationInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return orderRegistrationInfoRepository.count();
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

    protected OrderRegistrationInfo getPersistedOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        return orderRegistrationInfoRepository.findById(orderRegistrationInfo.getId()).orElseThrow();
    }

    protected void assertPersistedOrderRegistrationInfoToMatchAllProperties(OrderRegistrationInfo expectedOrderRegistrationInfo) {
        assertOrderRegistrationInfoAllPropertiesEquals(
            expectedOrderRegistrationInfo,
            getPersistedOrderRegistrationInfo(expectedOrderRegistrationInfo)
        );
    }

    protected void assertPersistedOrderRegistrationInfoToMatchUpdatableProperties(OrderRegistrationInfo expectedOrderRegistrationInfo) {
        assertOrderRegistrationInfoAllUpdatablePropertiesEquals(
            expectedOrderRegistrationInfo,
            getPersistedOrderRegistrationInfo(expectedOrderRegistrationInfo)
        );
    }
}
