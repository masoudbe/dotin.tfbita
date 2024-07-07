package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.JustificationDeductionAmountPartRepository;
import com.dotin.tfbita.service.JustificationDeductionAmountPartService;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountPartDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.JustificationDeductionAmountPart}.
 */
@RestController
@RequestMapping("/api/justification-deduction-amount-parts")
public class JustificationDeductionAmountPartResource {

    private static final Logger log = LoggerFactory.getLogger(JustificationDeductionAmountPartResource.class);

    private static final String ENTITY_NAME = "justificationDeductionAmountPart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JustificationDeductionAmountPartService justificationDeductionAmountPartService;

    private final JustificationDeductionAmountPartRepository justificationDeductionAmountPartRepository;

    public JustificationDeductionAmountPartResource(
        JustificationDeductionAmountPartService justificationDeductionAmountPartService,
        JustificationDeductionAmountPartRepository justificationDeductionAmountPartRepository
    ) {
        this.justificationDeductionAmountPartService = justificationDeductionAmountPartService;
        this.justificationDeductionAmountPartRepository = justificationDeductionAmountPartRepository;
    }

    /**
     * {@code POST  /justification-deduction-amount-parts} : Create a new justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the justificationDeductionAmountPartDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new justificationDeductionAmountPartDTO, or with status {@code 400 (Bad Request)} if the justificationDeductionAmountPart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JustificationDeductionAmountPartDTO> createJustificationDeductionAmountPart(
        @RequestBody JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO
    ) throws URISyntaxException {
        log.debug("REST request to save JustificationDeductionAmountPart : {}", justificationDeductionAmountPartDTO);
        if (justificationDeductionAmountPartDTO.getId() != null) {
            throw new BadRequestAlertException("A new justificationDeductionAmountPart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        justificationDeductionAmountPartDTO = justificationDeductionAmountPartService.save(justificationDeductionAmountPartDTO);
        return ResponseEntity.created(new URI("/api/justification-deduction-amount-parts/" + justificationDeductionAmountPartDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    justificationDeductionAmountPartDTO.getId().toString()
                )
            )
            .body(justificationDeductionAmountPartDTO);
    }

    /**
     * {@code PUT  /justification-deduction-amount-parts/:id} : Updates an existing justificationDeductionAmountPart.
     *
     * @param id the id of the justificationDeductionAmountPartDTO to save.
     * @param justificationDeductionAmountPartDTO the justificationDeductionAmountPartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated justificationDeductionAmountPartDTO,
     * or with status {@code 400 (Bad Request)} if the justificationDeductionAmountPartDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the justificationDeductionAmountPartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JustificationDeductionAmountPartDTO> updateJustificationDeductionAmountPart(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO
    ) throws URISyntaxException {
        log.debug("REST request to update JustificationDeductionAmountPart : {}, {}", id, justificationDeductionAmountPartDTO);
        if (justificationDeductionAmountPartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, justificationDeductionAmountPartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!justificationDeductionAmountPartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        justificationDeductionAmountPartDTO = justificationDeductionAmountPartService.update(justificationDeductionAmountPartDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    justificationDeductionAmountPartDTO.getId().toString()
                )
            )
            .body(justificationDeductionAmountPartDTO);
    }

    /**
     * {@code PATCH  /justification-deduction-amount-parts/:id} : Partial updates given fields of an existing justificationDeductionAmountPart, field will ignore if it is null
     *
     * @param id the id of the justificationDeductionAmountPartDTO to save.
     * @param justificationDeductionAmountPartDTO the justificationDeductionAmountPartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated justificationDeductionAmountPartDTO,
     * or with status {@code 400 (Bad Request)} if the justificationDeductionAmountPartDTO is not valid,
     * or with status {@code 404 (Not Found)} if the justificationDeductionAmountPartDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the justificationDeductionAmountPartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JustificationDeductionAmountPartDTO> partialUpdateJustificationDeductionAmountPart(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update JustificationDeductionAmountPart partially : {}, {}",
            id,
            justificationDeductionAmountPartDTO
        );
        if (justificationDeductionAmountPartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, justificationDeductionAmountPartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!justificationDeductionAmountPartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JustificationDeductionAmountPartDTO> result = justificationDeductionAmountPartService.partialUpdate(
            justificationDeductionAmountPartDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, justificationDeductionAmountPartDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /justification-deduction-amount-parts} : get all the justificationDeductionAmountParts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of justificationDeductionAmountParts in body.
     */
    @GetMapping("")
    public List<JustificationDeductionAmountPartDTO> getAllJustificationDeductionAmountParts() {
        log.debug("REST request to get all JustificationDeductionAmountParts");
        return justificationDeductionAmountPartService.findAll();
    }

    /**
     * {@code GET  /justification-deduction-amount-parts/:id} : get the "id" justificationDeductionAmountPart.
     *
     * @param id the id of the justificationDeductionAmountPartDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the justificationDeductionAmountPartDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JustificationDeductionAmountPartDTO> getJustificationDeductionAmountPart(@PathVariable("id") Long id) {
        log.debug("REST request to get JustificationDeductionAmountPart : {}", id);
        Optional<JustificationDeductionAmountPartDTO> justificationDeductionAmountPartDTO = justificationDeductionAmountPartService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(justificationDeductionAmountPartDTO);
    }

    /**
     * {@code DELETE  /justification-deduction-amount-parts/:id} : delete the "id" justificationDeductionAmountPart.
     *
     * @param id the id of the justificationDeductionAmountPartDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJustificationDeductionAmountPart(@PathVariable("id") Long id) {
        log.debug("REST request to delete JustificationDeductionAmountPart : {}", id);
        justificationDeductionAmountPartService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
