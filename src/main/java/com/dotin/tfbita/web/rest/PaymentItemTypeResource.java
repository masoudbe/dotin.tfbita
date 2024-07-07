package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.PaymentItemTypeRepository;
import com.dotin.tfbita.service.PaymentItemTypeService;
import com.dotin.tfbita.service.dto.PaymentItemTypeDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.PaymentItemType}.
 */
@RestController
@RequestMapping("/api/payment-item-types")
public class PaymentItemTypeResource {

    private static final Logger log = LoggerFactory.getLogger(PaymentItemTypeResource.class);

    private static final String ENTITY_NAME = "paymentItemType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentItemTypeService paymentItemTypeService;

    private final PaymentItemTypeRepository paymentItemTypeRepository;

    public PaymentItemTypeResource(PaymentItemTypeService paymentItemTypeService, PaymentItemTypeRepository paymentItemTypeRepository) {
        this.paymentItemTypeService = paymentItemTypeService;
        this.paymentItemTypeRepository = paymentItemTypeRepository;
    }

    /**
     * {@code POST  /payment-item-types} : Create a new paymentItemType.
     *
     * @param paymentItemTypeDTO the paymentItemTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentItemTypeDTO, or with status {@code 400 (Bad Request)} if the paymentItemType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PaymentItemTypeDTO> createPaymentItemType(@RequestBody PaymentItemTypeDTO paymentItemTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save PaymentItemType : {}", paymentItemTypeDTO);
        if (paymentItemTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentItemType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentItemTypeDTO = paymentItemTypeService.save(paymentItemTypeDTO);
        return ResponseEntity.created(new URI("/api/payment-item-types/" + paymentItemTypeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, paymentItemTypeDTO.getId().toString()))
            .body(paymentItemTypeDTO);
    }

    /**
     * {@code PUT  /payment-item-types/:id} : Updates an existing paymentItemType.
     *
     * @param id the id of the paymentItemTypeDTO to save.
     * @param paymentItemTypeDTO the paymentItemTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentItemTypeDTO,
     * or with status {@code 400 (Bad Request)} if the paymentItemTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentItemTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentItemTypeDTO> updatePaymentItemType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentItemTypeDTO paymentItemTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentItemType : {}, {}", id, paymentItemTypeDTO);
        if (paymentItemTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentItemTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentItemTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        paymentItemTypeDTO = paymentItemTypeService.update(paymentItemTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentItemTypeDTO.getId().toString()))
            .body(paymentItemTypeDTO);
    }

    /**
     * {@code PATCH  /payment-item-types/:id} : Partial updates given fields of an existing paymentItemType, field will ignore if it is null
     *
     * @param id the id of the paymentItemTypeDTO to save.
     * @param paymentItemTypeDTO the paymentItemTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentItemTypeDTO,
     * or with status {@code 400 (Bad Request)} if the paymentItemTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paymentItemTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentItemTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PaymentItemTypeDTO> partialUpdatePaymentItemType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentItemTypeDTO paymentItemTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentItemType partially : {}, {}", id, paymentItemTypeDTO);
        if (paymentItemTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentItemTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentItemTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaymentItemTypeDTO> result = paymentItemTypeService.partialUpdate(paymentItemTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentItemTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payment-item-types} : get all the paymentItemTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentItemTypes in body.
     */
    @GetMapping("")
    public List<PaymentItemTypeDTO> getAllPaymentItemTypes() {
        log.debug("REST request to get all PaymentItemTypes");
        return paymentItemTypeService.findAll();
    }

    /**
     * {@code GET  /payment-item-types/:id} : get the "id" paymentItemType.
     *
     * @param id the id of the paymentItemTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentItemTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentItemTypeDTO> getPaymentItemType(@PathVariable("id") Long id) {
        log.debug("REST request to get PaymentItemType : {}", id);
        Optional<PaymentItemTypeDTO> paymentItemTypeDTO = paymentItemTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentItemTypeDTO);
    }

    /**
     * {@code DELETE  /payment-item-types/:id} : delete the "id" paymentItemType.
     *
     * @param id the id of the paymentItemTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentItemType(@PathVariable("id") Long id) {
        log.debug("REST request to delete PaymentItemType : {}", id);
        paymentItemTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
