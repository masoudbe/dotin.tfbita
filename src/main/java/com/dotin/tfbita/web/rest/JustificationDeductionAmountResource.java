package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.JustificationDeductionAmountRepository;
import com.dotin.tfbita.service.JustificationDeductionAmountService;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.JustificationDeductionAmount}.
 */
@RestController
@RequestMapping("/api/justification-deduction-amounts")
public class JustificationDeductionAmountResource {

    private static final Logger log = LoggerFactory.getLogger(JustificationDeductionAmountResource.class);

    private static final String ENTITY_NAME = "justificationDeductionAmount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JustificationDeductionAmountService justificationDeductionAmountService;

    private final JustificationDeductionAmountRepository justificationDeductionAmountRepository;

    public JustificationDeductionAmountResource(
        JustificationDeductionAmountService justificationDeductionAmountService,
        JustificationDeductionAmountRepository justificationDeductionAmountRepository
    ) {
        this.justificationDeductionAmountService = justificationDeductionAmountService;
        this.justificationDeductionAmountRepository = justificationDeductionAmountRepository;
    }

    /**
     * {@code POST  /justification-deduction-amounts} : Create a new justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the justificationDeductionAmountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new justificationDeductionAmountDTO, or with status {@code 400 (Bad Request)} if the justificationDeductionAmount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JustificationDeductionAmountDTO> createJustificationDeductionAmount(
        @RequestBody JustificationDeductionAmountDTO justificationDeductionAmountDTO
    ) throws URISyntaxException {
        log.debug("REST request to save JustificationDeductionAmount : {}", justificationDeductionAmountDTO);
        if (justificationDeductionAmountDTO.getId() != null) {
            throw new BadRequestAlertException("A new justificationDeductionAmount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        justificationDeductionAmountDTO = justificationDeductionAmountService.save(justificationDeductionAmountDTO);
        return ResponseEntity.created(new URI("/api/justification-deduction-amounts/" + justificationDeductionAmountDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, justificationDeductionAmountDTO.getId().toString())
            )
            .body(justificationDeductionAmountDTO);
    }

    /**
     * {@code PUT  /justification-deduction-amounts/:id} : Updates an existing justificationDeductionAmount.
     *
     * @param id the id of the justificationDeductionAmountDTO to save.
     * @param justificationDeductionAmountDTO the justificationDeductionAmountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated justificationDeductionAmountDTO,
     * or with status {@code 400 (Bad Request)} if the justificationDeductionAmountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the justificationDeductionAmountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JustificationDeductionAmountDTO> updateJustificationDeductionAmount(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JustificationDeductionAmountDTO justificationDeductionAmountDTO
    ) throws URISyntaxException {
        log.debug("REST request to update JustificationDeductionAmount : {}, {}", id, justificationDeductionAmountDTO);
        if (justificationDeductionAmountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, justificationDeductionAmountDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!justificationDeductionAmountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        justificationDeductionAmountDTO = justificationDeductionAmountService.update(justificationDeductionAmountDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, justificationDeductionAmountDTO.getId().toString())
            )
            .body(justificationDeductionAmountDTO);
    }

    /**
     * {@code PATCH  /justification-deduction-amounts/:id} : Partial updates given fields of an existing justificationDeductionAmount, field will ignore if it is null
     *
     * @param id the id of the justificationDeductionAmountDTO to save.
     * @param justificationDeductionAmountDTO the justificationDeductionAmountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated justificationDeductionAmountDTO,
     * or with status {@code 400 (Bad Request)} if the justificationDeductionAmountDTO is not valid,
     * or with status {@code 404 (Not Found)} if the justificationDeductionAmountDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the justificationDeductionAmountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JustificationDeductionAmountDTO> partialUpdateJustificationDeductionAmount(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JustificationDeductionAmountDTO justificationDeductionAmountDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update JustificationDeductionAmount partially : {}, {}", id, justificationDeductionAmountDTO);
        if (justificationDeductionAmountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, justificationDeductionAmountDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!justificationDeductionAmountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JustificationDeductionAmountDTO> result = justificationDeductionAmountService.partialUpdate(
            justificationDeductionAmountDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, justificationDeductionAmountDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /justification-deduction-amounts} : get all the justificationDeductionAmounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of justificationDeductionAmounts in body.
     */
    @GetMapping("")
    public List<JustificationDeductionAmountDTO> getAllJustificationDeductionAmounts() {
        log.debug("REST request to get all JustificationDeductionAmounts");
        return justificationDeductionAmountService.findAll();
    }

    /**
     * {@code GET  /justification-deduction-amounts/:id} : get the "id" justificationDeductionAmount.
     *
     * @param id the id of the justificationDeductionAmountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the justificationDeductionAmountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JustificationDeductionAmountDTO> getJustificationDeductionAmount(@PathVariable("id") Long id) {
        log.debug("REST request to get JustificationDeductionAmount : {}", id);
        Optional<JustificationDeductionAmountDTO> justificationDeductionAmountDTO = justificationDeductionAmountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(justificationDeductionAmountDTO);
    }

    /**
     * {@code DELETE  /justification-deduction-amounts/:id} : delete the "id" justificationDeductionAmount.
     *
     * @param id the id of the justificationDeductionAmountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJustificationDeductionAmount(@PathVariable("id") Long id) {
        log.debug("REST request to delete JustificationDeductionAmount : {}", id);
        justificationDeductionAmountService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
