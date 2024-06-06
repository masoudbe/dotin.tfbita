package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.OrderRegServAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.OrderRegServ;
import com.dotin.tfbita.repository.OrderRegServRepository;
import com.dotin.tfbita.service.dto.OrderRegServDTO;
import com.dotin.tfbita.service.mapper.OrderRegServMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link OrderRegServResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderRegServResourceIT {

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CURRENCY_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/order-reg-servs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OrderRegServRepository orderRegServRepository;

    @Autowired
    private OrderRegServMapper orderRegServMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderRegServMockMvc;

    private OrderRegServ orderRegServ;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRegServ createEntity(EntityManager em) {
        OrderRegServ orderRegServ = new OrderRegServ()
            .amount(DEFAULT_AMOUNT)
            .currencyAmount(DEFAULT_CURRENCY_AMOUNT)
            .unit(DEFAULT_UNIT)
            .title(DEFAULT_TITLE)
            .code(DEFAULT_CODE);
        return orderRegServ;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRegServ createUpdatedEntity(EntityManager em) {
        OrderRegServ orderRegServ = new OrderRegServ()
            .amount(UPDATED_AMOUNT)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .unit(UPDATED_UNIT)
            .title(UPDATED_TITLE)
            .code(UPDATED_CODE);
        return orderRegServ;
    }

    @BeforeEach
    public void initTest() {
        orderRegServ = createEntity(em);
    }

    @Test
    @Transactional
    void createOrderRegServ() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);
        var returnedOrderRegServDTO = om.readValue(
            restOrderRegServMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegServDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OrderRegServDTO.class
        );

        // Validate the OrderRegServ in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedOrderRegServ = orderRegServMapper.toEntity(returnedOrderRegServDTO);
        assertOrderRegServUpdatableFieldsEquals(returnedOrderRegServ, getPersistedOrderRegServ(returnedOrderRegServ));
    }

    @Test
    @Transactional
    void createOrderRegServWithExistingId() throws Exception {
        // Create the OrderRegServ with an existing ID
        orderRegServ.setId(1L);
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderRegServMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegServDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOrderRegServs() throws Exception {
        // Initialize the database
        orderRegServRepository.saveAndFlush(orderRegServ);

        // Get all the orderRegServList
        restOrderRegServMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderRegServ.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].currencyAmount").value(hasItem(sameNumber(DEFAULT_CURRENCY_AMOUNT))))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }

    @Test
    @Transactional
    void getOrderRegServ() throws Exception {
        // Initialize the database
        orderRegServRepository.saveAndFlush(orderRegServ);

        // Get the orderRegServ
        restOrderRegServMockMvc
            .perform(get(ENTITY_API_URL_ID, orderRegServ.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderRegServ.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.currencyAmount").value(sameNumber(DEFAULT_CURRENCY_AMOUNT)))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    void getNonExistingOrderRegServ() throws Exception {
        // Get the orderRegServ
        restOrderRegServMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOrderRegServ() throws Exception {
        // Initialize the database
        orderRegServRepository.saveAndFlush(orderRegServ);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegServ
        OrderRegServ updatedOrderRegServ = orderRegServRepository.findById(orderRegServ.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOrderRegServ are not directly saved in db
        em.detach(updatedOrderRegServ);
        updatedOrderRegServ
            .amount(UPDATED_AMOUNT)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .unit(UPDATED_UNIT)
            .title(UPDATED_TITLE)
            .code(UPDATED_CODE);
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(updatedOrderRegServ);

        restOrderRegServMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderRegServDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegServDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOrderRegServToMatchAllProperties(updatedOrderRegServ);
    }

    @Test
    @Transactional
    void putNonExistingOrderRegServ() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegServ.setId(longCount.incrementAndGet());

        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRegServMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderRegServDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegServDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderRegServ() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegServ.setId(longCount.incrementAndGet());

        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(orderRegServDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderRegServ() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegServ.setId(longCount.incrementAndGet());

        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(orderRegServDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderRegServWithPatch() throws Exception {
        // Initialize the database
        orderRegServRepository.saveAndFlush(orderRegServ);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegServ using partial update
        OrderRegServ partialUpdatedOrderRegServ = new OrderRegServ();
        partialUpdatedOrderRegServ.setId(orderRegServ.getId());

        partialUpdatedOrderRegServ.currencyAmount(UPDATED_CURRENCY_AMOUNT).unit(UPDATED_UNIT).code(UPDATED_CODE);

        restOrderRegServMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderRegServ.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOrderRegServ))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegServ in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOrderRegServUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOrderRegServ, orderRegServ),
            getPersistedOrderRegServ(orderRegServ)
        );
    }

    @Test
    @Transactional
    void fullUpdateOrderRegServWithPatch() throws Exception {
        // Initialize the database
        orderRegServRepository.saveAndFlush(orderRegServ);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the orderRegServ using partial update
        OrderRegServ partialUpdatedOrderRegServ = new OrderRegServ();
        partialUpdatedOrderRegServ.setId(orderRegServ.getId());

        partialUpdatedOrderRegServ
            .amount(UPDATED_AMOUNT)
            .currencyAmount(UPDATED_CURRENCY_AMOUNT)
            .unit(UPDATED_UNIT)
            .title(UPDATED_TITLE)
            .code(UPDATED_CODE);

        restOrderRegServMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderRegServ.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOrderRegServ))
            )
            .andExpect(status().isOk());

        // Validate the OrderRegServ in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOrderRegServUpdatableFieldsEquals(partialUpdatedOrderRegServ, getPersistedOrderRegServ(partialUpdatedOrderRegServ));
    }

    @Test
    @Transactional
    void patchNonExistingOrderRegServ() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegServ.setId(longCount.incrementAndGet());

        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRegServMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderRegServDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(orderRegServDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderRegServ() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegServ.setId(longCount.incrementAndGet());

        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(orderRegServDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderRegServ() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        orderRegServ.setId(longCount.incrementAndGet());

        // Create the OrderRegServ
        OrderRegServDTO orderRegServDTO = orderRegServMapper.toDto(orderRegServ);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderRegServMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(orderRegServDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderRegServ in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderRegServ() throws Exception {
        // Initialize the database
        orderRegServRepository.saveAndFlush(orderRegServ);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the orderRegServ
        restOrderRegServMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderRegServ.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return orderRegServRepository.count();
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

    protected OrderRegServ getPersistedOrderRegServ(OrderRegServ orderRegServ) {
        return orderRegServRepository.findById(orderRegServ.getId()).orElseThrow();
    }

    protected void assertPersistedOrderRegServToMatchAllProperties(OrderRegServ expectedOrderRegServ) {
        assertOrderRegServAllPropertiesEquals(expectedOrderRegServ, getPersistedOrderRegServ(expectedOrderRegServ));
    }

    protected void assertPersistedOrderRegServToMatchUpdatableProperties(OrderRegServ expectedOrderRegServ) {
        assertOrderRegServAllUpdatablePropertiesEquals(expectedOrderRegServ, getPersistedOrderRegServ(expectedOrderRegServ));
    }
}
