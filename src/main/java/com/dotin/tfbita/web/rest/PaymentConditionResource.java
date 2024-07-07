package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.PaymentConditionRepository;
import com.dotin.tfbita.service.PaymentConditionService;
import com.dotin.tfbita.service.dto.PaymentConditionDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.PaymentCondition}.
 */
@RestController
@RequestMapping("/api/payment-conditions")
public class PaymentConditionResource {

    private static final Logger log = LoggerFactory.getLogger(PaymentConditionResource.class);

    private static final String ENTITY_NAME = "paymentCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentConditionService paymentConditionService;

    private final PaymentConditionRepository paymentConditionRepository;

    public PaymentConditionResource(
        PaymentConditionService paymentConditionService,
        PaymentConditionRepository paymentConditionRepository
    ) {
        this.paymentConditionService = paymentConditionService;
        this.paymentConditionRepository = paymentConditionRepository;
    }

    /**
     * {@code POST  /payment-conditions} : Create a new paymentCondition.
     *
     * @param paymentConditionDTO the paymentConditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentConditionDTO, or with status {@code 400 (Bad Request)} if the paymentCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PaymentConditionDTO> createPaymentCondition(@RequestBody PaymentConditionDTO paymentConditionDTO)
        throws URISyntaxException {
        log.debug("REST request to save PaymentCondition : {}", paymentConditionDTO);
        if (paymentConditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentConditionDTO = paymentConditionService.save(paymentConditionDTO);
        return ResponseEntity.created(new URI("/api/payment-conditions/" + paymentConditionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, paymentConditionDTO.getId().toString()))
            .body(paymentConditionDTO);
    }

    /**
     * {@code PUT  /payment-conditions/:id} : Updates an existing paymentCondition.
     *
     * @param id the id of the paymentConditionDTO to save.
     * @param paymentConditionDTO the paymentConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentConditionDTO,
     * or with status {@code 400 (Bad Request)} if the paymentConditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentConditionDTO> updatePaymentCondition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentConditionDTO paymentConditionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentCondition : {}, {}", id, paymentConditionDTO);
        if (paymentConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentConditionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        paymentConditionDTO = paymentConditionService.update(paymentConditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentConditionDTO.getId().toString()))
            .body(paymentConditionDTO);
    }

    /**
     * {@code PATCH  /payment-conditions/:id} : Partial updates given fields of an existing paymentCondition, field will ignore if it is null
     *
     * @param id the id of the paymentConditionDTO to save.
     * @param paymentConditionDTO the paymentConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentConditionDTO,
     * or with status {@code 400 (Bad Request)} if the paymentConditionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paymentConditionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PaymentConditionDTO> partialUpdatePaymentCondition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentConditionDTO paymentConditionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentCondition partially : {}, {}", id, paymentConditionDTO);
        if (paymentConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentConditionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaymentConditionDTO> result = paymentConditionService.partialUpdate(paymentConditionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentConditionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payment-conditions} : get all the paymentConditions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentConditions in body.
     */
    @GetMapping("")
    public List<PaymentConditionDTO> getAllPaymentConditions() {
        log.debug("REST request to get all PaymentConditions");
        return paymentConditionService.findAll();
    }

    /**
     * {@code GET  /payment-conditions/:id} : get the "id" paymentCondition.
     *
     * @param id the id of the paymentConditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentConditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentConditionDTO> getPaymentCondition(@PathVariable("id") Long id) {
        log.debug("REST request to get PaymentCondition : {}", id);
        Optional<PaymentConditionDTO> paymentConditionDTO = paymentConditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentConditionDTO);
    }

    /**
     * {@code DELETE  /payment-conditions/:id} : delete the "id" paymentCondition.
     *
     * @param id the id of the paymentConditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentCondition(@PathVariable("id") Long id) {
        log.debug("REST request to delete PaymentCondition : {}", id);
        paymentConditionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
