package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CreditTypeConditionRepository;
import com.dotin.tfbita.service.CreditTypeConditionService;
import com.dotin.tfbita.service.dto.CreditTypeConditionDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.CreditTypeCondition}.
 */
@RestController
@RequestMapping("/api/credit-type-conditions")
public class CreditTypeConditionResource {

    private final Logger log = LoggerFactory.getLogger(CreditTypeConditionResource.class);

    private static final String ENTITY_NAME = "creditTypeCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditTypeConditionService creditTypeConditionService;

    private final CreditTypeConditionRepository creditTypeConditionRepository;

    public CreditTypeConditionResource(
        CreditTypeConditionService creditTypeConditionService,
        CreditTypeConditionRepository creditTypeConditionRepository
    ) {
        this.creditTypeConditionService = creditTypeConditionService;
        this.creditTypeConditionRepository = creditTypeConditionRepository;
    }

    /**
     * {@code POST  /credit-type-conditions} : Create a new creditTypeCondition.
     *
     * @param creditTypeConditionDTO the creditTypeConditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditTypeConditionDTO, or with status {@code 400 (Bad Request)} if the creditTypeCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CreditTypeConditionDTO> createCreditTypeCondition(@RequestBody CreditTypeConditionDTO creditTypeConditionDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreditTypeCondition : {}", creditTypeConditionDTO);
        if (creditTypeConditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditTypeCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        creditTypeConditionDTO = creditTypeConditionService.save(creditTypeConditionDTO);
        return ResponseEntity.created(new URI("/api/credit-type-conditions/" + creditTypeConditionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, creditTypeConditionDTO.getId().toString()))
            .body(creditTypeConditionDTO);
    }

    /**
     * {@code PUT  /credit-type-conditions/:id} : Updates an existing creditTypeCondition.
     *
     * @param id the id of the creditTypeConditionDTO to save.
     * @param creditTypeConditionDTO the creditTypeConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditTypeConditionDTO,
     * or with status {@code 400 (Bad Request)} if the creditTypeConditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditTypeConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CreditTypeConditionDTO> updateCreditTypeCondition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreditTypeConditionDTO creditTypeConditionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreditTypeCondition : {}, {}", id, creditTypeConditionDTO);
        if (creditTypeConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditTypeConditionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!creditTypeConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        creditTypeConditionDTO = creditTypeConditionService.update(creditTypeConditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditTypeConditionDTO.getId().toString()))
            .body(creditTypeConditionDTO);
    }

    /**
     * {@code PATCH  /credit-type-conditions/:id} : Partial updates given fields of an existing creditTypeCondition, field will ignore if it is null
     *
     * @param id the id of the creditTypeConditionDTO to save.
     * @param creditTypeConditionDTO the creditTypeConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditTypeConditionDTO,
     * or with status {@code 400 (Bad Request)} if the creditTypeConditionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the creditTypeConditionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the creditTypeConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreditTypeConditionDTO> partialUpdateCreditTypeCondition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreditTypeConditionDTO creditTypeConditionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreditTypeCondition partially : {}, {}", id, creditTypeConditionDTO);
        if (creditTypeConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditTypeConditionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!creditTypeConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreditTypeConditionDTO> result = creditTypeConditionService.partialUpdate(creditTypeConditionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditTypeConditionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /credit-type-conditions} : get all the creditTypeConditions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditTypeConditions in body.
     */
    @GetMapping("")
    public List<CreditTypeConditionDTO> getAllCreditTypeConditions() {
        log.debug("REST request to get all CreditTypeConditions");
        return creditTypeConditionService.findAll();
    }

    /**
     * {@code GET  /credit-type-conditions/:id} : get the "id" creditTypeCondition.
     *
     * @param id the id of the creditTypeConditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditTypeConditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CreditTypeConditionDTO> getCreditTypeCondition(@PathVariable("id") Long id) {
        log.debug("REST request to get CreditTypeCondition : {}", id);
        Optional<CreditTypeConditionDTO> creditTypeConditionDTO = creditTypeConditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditTypeConditionDTO);
    }

    /**
     * {@code DELETE  /credit-type-conditions/:id} : delete the "id" creditTypeCondition.
     *
     * @param id the id of the creditTypeConditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditTypeCondition(@PathVariable("id") Long id) {
        log.debug("REST request to delete CreditTypeCondition : {}", id);
        creditTypeConditionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
