package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.PaymentCurrencyRateTypeRepository;
import com.dotin.tfbita.service.PaymentCurrencyRateTypeService;
import com.dotin.tfbita.service.dto.PaymentCurrencyRateTypeDTO;
import com.dotin.tfbita.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dotin.tfbita.domain.PaymentCurrencyRateType}.
 */
@RestController
@RequestMapping("/api/payment-currency-rate-types")
public class PaymentCurrencyRateTypeResource {

    private final Logger log = LoggerFactory.getLogger(PaymentCurrencyRateTypeResource.class);

    private static final String ENTITY_NAME = "paymentCurrencyRateType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentCurrencyRateTypeService paymentCurrencyRateTypeService;

    private final PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository;

    public PaymentCurrencyRateTypeResource(
        PaymentCurrencyRateTypeService paymentCurrencyRateTypeService,
        PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository
    ) {
        this.paymentCurrencyRateTypeService = paymentCurrencyRateTypeService;
        this.paymentCurrencyRateTypeRepository = paymentCurrencyRateTypeRepository;
    }

    /**
     * {@code POST  /payment-currency-rate-types} : Create a new paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the paymentCurrencyRateTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentCurrencyRateTypeDTO, or with status {@code 400 (Bad Request)} if the paymentCurrencyRateType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PaymentCurrencyRateTypeDTO> createPaymentCurrencyRateType(
        @RequestBody PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PaymentCurrencyRateType : {}", paymentCurrencyRateTypeDTO);
        if (paymentCurrencyRateTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentCurrencyRateType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeService.save(paymentCurrencyRateTypeDTO);
        return ResponseEntity.created(new URI("/api/payment-currency-rate-types/" + paymentCurrencyRateTypeDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, paymentCurrencyRateTypeDTO.getId().toString())
            )
            .body(paymentCurrencyRateTypeDTO);
    }

    /**
     * {@code PUT  /payment-currency-rate-types/:id} : Updates an existing paymentCurrencyRateType.
     *
     * @param id the id of the paymentCurrencyRateTypeDTO to save.
     * @param paymentCurrencyRateTypeDTO the paymentCurrencyRateTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentCurrencyRateTypeDTO,
     * or with status {@code 400 (Bad Request)} if the paymentCurrencyRateTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentCurrencyRateTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentCurrencyRateTypeDTO> updatePaymentCurrencyRateType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentCurrencyRateType : {}, {}", id, paymentCurrencyRateTypeDTO);
        if (paymentCurrencyRateTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentCurrencyRateTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentCurrencyRateTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeService.update(paymentCurrencyRateTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentCurrencyRateTypeDTO.getId().toString()))
            .body(paymentCurrencyRateTypeDTO);
    }

    /**
     * {@code PATCH  /payment-currency-rate-types/:id} : Partial updates given fields of an existing paymentCurrencyRateType, field will ignore if it is null
     *
     * @param id the id of the paymentCurrencyRateTypeDTO to save.
     * @param paymentCurrencyRateTypeDTO the paymentCurrencyRateTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentCurrencyRateTypeDTO,
     * or with status {@code 400 (Bad Request)} if the paymentCurrencyRateTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paymentCurrencyRateTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentCurrencyRateTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PaymentCurrencyRateTypeDTO> partialUpdatePaymentCurrencyRateType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentCurrencyRateType partially : {}, {}", id, paymentCurrencyRateTypeDTO);
        if (paymentCurrencyRateTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentCurrencyRateTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentCurrencyRateTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaymentCurrencyRateTypeDTO> result = paymentCurrencyRateTypeService.partialUpdate(paymentCurrencyRateTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentCurrencyRateTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payment-currency-rate-types} : get all the paymentCurrencyRateTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentCurrencyRateTypes in body.
     */
    @GetMapping("")
    public List<PaymentCurrencyRateTypeDTO> getAllPaymentCurrencyRateTypes() {
        log.debug("REST request to get all PaymentCurrencyRateTypes");
        return paymentCurrencyRateTypeService.findAll();
    }

    /**
     * {@code GET  /payment-currency-rate-types/:id} : get the "id" paymentCurrencyRateType.
     *
     * @param id the id of the paymentCurrencyRateTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentCurrencyRateTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentCurrencyRateTypeDTO> getPaymentCurrencyRateType(@PathVariable("id") Long id) {
        log.debug("REST request to get PaymentCurrencyRateType : {}", id);
        Optional<PaymentCurrencyRateTypeDTO> paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentCurrencyRateTypeDTO);
    }

    /**
     * {@code DELETE  /payment-currency-rate-types/:id} : delete the "id" paymentCurrencyRateType.
     *
     * @param id the id of the paymentCurrencyRateTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentCurrencyRateType(@PathVariable("id") Long id) {
        log.debug("REST request to delete PaymentCurrencyRateType : {}", id);
        paymentCurrencyRateTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
