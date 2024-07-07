package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftCustomJustificationRepository;
import com.dotin.tfbita.service.DraftCustomJustificationService;
import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftCustomJustification}.
 */
@RestController
@RequestMapping("/api/draft-custom-justifications")
public class DraftCustomJustificationResource {

    private final Logger log = LoggerFactory.getLogger(DraftCustomJustificationResource.class);

    private static final String ENTITY_NAME = "draftCustomJustification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftCustomJustificationService draftCustomJustificationService;

    private final DraftCustomJustificationRepository draftCustomJustificationRepository;

    public DraftCustomJustificationResource(
        DraftCustomJustificationService draftCustomJustificationService,
        DraftCustomJustificationRepository draftCustomJustificationRepository
    ) {
        this.draftCustomJustificationService = draftCustomJustificationService;
        this.draftCustomJustificationRepository = draftCustomJustificationRepository;
    }

    /**
     * {@code POST  /draft-custom-justifications} : Create a new draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the draftCustomJustificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftCustomJustificationDTO, or with status {@code 400 (Bad Request)} if the draftCustomJustification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftCustomJustificationDTO> createDraftCustomJustification(
        @RequestBody DraftCustomJustificationDTO draftCustomJustificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DraftCustomJustification : {}", draftCustomJustificationDTO);
        if (draftCustomJustificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftCustomJustification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftCustomJustificationDTO = draftCustomJustificationService.save(draftCustomJustificationDTO);
        return ResponseEntity.created(new URI("/api/draft-custom-justifications/" + draftCustomJustificationDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftCustomJustificationDTO.getId().toString())
            )
            .body(draftCustomJustificationDTO);
    }

    /**
     * {@code PUT  /draft-custom-justifications/:id} : Updates an existing draftCustomJustification.
     *
     * @param id the id of the draftCustomJustificationDTO to save.
     * @param draftCustomJustificationDTO the draftCustomJustificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftCustomJustificationDTO,
     * or with status {@code 400 (Bad Request)} if the draftCustomJustificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftCustomJustificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftCustomJustificationDTO> updateDraftCustomJustification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftCustomJustificationDTO draftCustomJustificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftCustomJustification : {}, {}", id, draftCustomJustificationDTO);
        if (draftCustomJustificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftCustomJustificationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftCustomJustificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftCustomJustificationDTO = draftCustomJustificationService.update(draftCustomJustificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftCustomJustificationDTO.getId().toString()))
            .body(draftCustomJustificationDTO);
    }

    /**
     * {@code PATCH  /draft-custom-justifications/:id} : Partial updates given fields of an existing draftCustomJustification, field will ignore if it is null
     *
     * @param id the id of the draftCustomJustificationDTO to save.
     * @param draftCustomJustificationDTO the draftCustomJustificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftCustomJustificationDTO,
     * or with status {@code 400 (Bad Request)} if the draftCustomJustificationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftCustomJustificationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftCustomJustificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftCustomJustificationDTO> partialUpdateDraftCustomJustification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftCustomJustificationDTO draftCustomJustificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftCustomJustification partially : {}, {}", id, draftCustomJustificationDTO);
        if (draftCustomJustificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftCustomJustificationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftCustomJustificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftCustomJustificationDTO> result = draftCustomJustificationService.partialUpdate(draftCustomJustificationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftCustomJustificationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-custom-justifications} : get all the draftCustomJustifications.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftCustomJustifications in body.
     */
    @GetMapping("")
    public List<DraftCustomJustificationDTO> getAllDraftCustomJustifications(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all DraftCustomJustifications");
        return draftCustomJustificationService.findAll();
    }

    /**
     * {@code GET  /draft-custom-justifications/:id} : get the "id" draftCustomJustification.
     *
     * @param id the id of the draftCustomJustificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftCustomJustificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftCustomJustificationDTO> getDraftCustomJustification(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftCustomJustification : {}", id);
        Optional<DraftCustomJustificationDTO> draftCustomJustificationDTO = draftCustomJustificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftCustomJustificationDTO);
    }

    /**
     * {@code DELETE  /draft-custom-justifications/:id} : delete the "id" draftCustomJustification.
     *
     * @param id the id of the draftCustomJustificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftCustomJustification(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftCustomJustification : {}", id);
        draftCustomJustificationService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
