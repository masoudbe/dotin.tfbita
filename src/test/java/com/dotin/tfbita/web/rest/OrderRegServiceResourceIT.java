package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.OrderRegServiceAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.OrderRegService;
import com.dotin.tfbita.repository.OrderRegServiceRepository;
import com.dotin.tfbita.service.dto.OrderRegServiceDTO;
import com.dotin.tfbita.service.mapper.OrderRegServiceMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link OrderRegServiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderRegServiceResourceIT {

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CURRENCY_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/order-reg-services";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OrderRegServiceRepository orderRegServiceRepository;

    @Autowired
    private OrderRegServiceMapper orderRegServiceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderRegServiceMockMvc;

    private OrderRegService orderRegService;

    private OrderRegService insertedOrderRegService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRegService createEntity(EntityManager em) {
        OrderRegService orderRegService = new OrderRegService()
            .amount(DEFAULT_AMOUNT)
            .currencyAmount(DEFAULT_CURRENCY_AMOUNT)
            .unit(DEFAULT_UNIT);
        return orderRegService;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRegService createUpdatedEntity(EntityManager em) {
        OrderRegService orderRegService = new OrderRegService()
            .amount(UPDATED_AMOUNT)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .unit(UPDATED_UNIT);
        return orderRegService;
    }

    @BeforeEach
    public void initTest() {
        orderRegService = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOrderRegService != null) {
            orderRegServiceRepository.delete(insertedOrderRegService);
            insertedOrderRegService = null;
        }
    }

    @Test
    @Transactional
    void createOrderRegService() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);
        var returnedOrderRegServiceDTO = om.readValue(
            restOrderRegServiceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegServiceDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OrderRegServiceDTO.class
        );

        // Validate the OrderRegService in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedOrderRegService = orderRegServiceMapper.toEntity(returnedOrderRegServiceDTO);
        assertOrderRegServiceUpdatableFieldsEquals(returnedOrderRegService, getPersistedOrderRegService(returnedOrderRegService));

        insertedOrderRegService = returnedOrderRegService;
    }

    @Test
    @Transactional
    void createOrderRegServiceWithExistingId() throws Exception {
        // Create the OrderRegService with an existing ID
        orderRegService.setId(1L);
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderRegServiceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOrderRegServices() throws Exception {
        // Initialize the database
        insertedOrderRegService = orderRegServiceRepository.saveAndFlush(orderRegService);

        // Get all the orderRegServiceList
        restOrderRegServiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderRegService.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].currencyAmount").value(hasItem(sameNumber(DEFAULT_CURRENCY_AMOUNT))))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)));
    }

    @Test
    @Transactional
    void getOrderRegService() throws Exception {
        // Initialize the database
        insertedOrderRegService = orderRegServiceRepository.saveAndFlush(orderRegService);

        // Get the orderRegService
        restOrderRegServiceMockMvc
            .perform(get(ENTITY_API_URL_ID, orderRegService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderRegService.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.currencyAmount").value(sameNumber(DEFAULT_CURRENCY_AMOUNT)))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT));
    }

    @Test
    @Transactional
    void getNonExistingOrderRegService() throws Exception {
        // Get the orderRegService
        restOrderRegServiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOrderRegService() throws Exception {
        // Initialize the database
        insertedOrderRegService = orderRegServiceRepository.saveAndFlush(orderRegService);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegService
        OrderRegService updatedOrderRegService = orderRegServiceRepository.findById(orderRegService.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOrderRegService are not directly saved in db
        em.detach(updatedOrderRegService);
        updatedOrderRegService.amount(UPDATED_AMOUNT).currencyAmount(UPDATED_CURRENCY_AMOUNT).unit(UPDATED_UNIT);
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(updatedOrderRegService);

        restOrderRegServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderRegServiceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegServiceDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOrderRegServiceToMatchAllProperties(updatedOrderRegService);
    }

    @Test
    @Transactional
    void putNonExistingOrderRegService() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegService.setId(longCount.incrementAndGet());

        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRegServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderRegServiceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegServiceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderRegService() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegService.setId(longCount.incrementAndGet());

        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegServiceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderRegService() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegService.setId(longCount.incrementAndGet());

        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServiceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegServiceDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderRegServiceWithPatch() throws Exception {
        // Initialize the database
        insertedOrderRegService = orderRegServiceRepository.saveAndFlush(orderRegService);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegService using partial update
        OrderRegService partialUpdatedOrderRegService = new OrderRegService();
        partialUpdatedOrderRegService.setId(orderRegService.getId());

        partialUpdatedOrderRegService.amount(UPDATED_AMOUNT).unit(UPDATED_UNIT);

        restOrderRegServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderRegService.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOrderRegService))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegService in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOrderRegServiceUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOrderRegService, orderRegService),
            getPersistedOrderRegService(orderRegService)
        );
    }

    @Test
    @Transactional
    void fullUpdateOrderRegServiceWithPatch() throws Exception {
        // Initialize the database
        insertedOrderRegService = orderRegServiceRepository.saveAndFlush(orderRegService);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegService using partial update
        OrderRegService partialUpdatedOrderRegService = new OrderRegService();
        partialUpdatedOrderRegService.setId(orderRegService.getId());

        partialUpdatedOrderRegService.amount(UPDATED_AMOUNT).currencyAmount(UPDATED_CURRENCY_AMOUNT).unit(UPDATED_UNIT);

        restOrderRegServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderRegService.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOrderRegService))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegService in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOrderRegServiceUpdatableFieldsEquals(
            partialUpdatedOrderRegService,
            getPersistedOrderRegService(partialUpdatedOrderRegService)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOrderRegService() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegService.setId(longCount.incrementAndGet());

        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRegServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderRegServiceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(orderRegServiceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderRegService() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegService.setId(longCount.incrementAndGet());

        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(orderRegServiceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderRegService() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegService.setId(longCount.incrementAndGet());

        // Create the OrderRegService
        OrderRegServiceDTO orderRegServiceDTO = orderRegServiceMapper.toDto(orderRegService);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServiceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(orderRegServiceDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderRegService in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderRegService() throws Exception {
        // Initialize the database
        insertedOrderRegService = orderRegServiceRepository.saveAndFlush(orderRegService);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the orderRegService
        restOrderRegServiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderRegService.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return orderRegServiceRepository.count();
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

    protected OrderRegService getPersistedOrderRegService(OrderRegService orderRegService) {
        return orderRegServiceRepository.findById(orderRegService.getId()).orElseThrow();
    }

    protected void assertPersistedOrderRegServiceToMatchAllProperties(OrderRegService expectedOrderRegService) {
        assertOrderRegServiceAllPropertiesEquals(expectedOrderRegService, getPersistedOrderRegService(expectedOrderRegService));
    }

    protected void assertPersistedOrderRegServiceToMatchUpdatableProperties(OrderRegService expectedOrderRegService) {
        assertOrderRegServiceAllUpdatablePropertiesEquals(expectedOrderRegService, getPersistedOrderRegService(expectedOrderRegService));
    }
}
