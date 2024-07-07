package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftUsedAssuranceRepository;
import com.dotin.tfbita.service.DraftUsedAssuranceService;
import com.dotin.tfbita.service.dto.DraftUsedAssuranceDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftUsedAssurance}.
 */
@RestController
@RequestMapping("/api/draft-used-assurances")
public class DraftUsedAssuranceResource {

    private static final Logger log = LoggerFactory.getLogger(DraftUsedAssuranceResource.class);

    private static final String ENTITY_NAME = "draftUsedAssurance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftUsedAssuranceService draftUsedAssuranceService;

    private final DraftUsedAssuranceRepository draftUsedAssuranceRepository;

    public DraftUsedAssuranceResource(
        DraftUsedAssuranceService draftUsedAssuranceService,
        DraftUsedAssuranceRepository draftUsedAssuranceRepository
    ) {
        this.draftUsedAssuranceService = draftUsedAssuranceService;
        this.draftUsedAssuranceRepository = draftUsedAssuranceRepository;
    }

    /**
     * {@code POST  /draft-used-assurances} : Create a new draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the draftUsedAssuranceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftUsedAssuranceDTO, or with status {@code 400 (Bad Request)} if the draftUsedAssurance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftUsedAssuranceDTO> createDraftUsedAssurance(@RequestBody DraftUsedAssuranceDTO draftUsedAssuranceDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftUsedAssurance : {}", draftUsedAssuranceDTO);
        if (draftUsedAssuranceDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftUsedAssurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftUsedAssuranceDTO = draftUsedAssuranceService.save(draftUsedAssuranceDTO);
        return ResponseEntity.created(new URI("/api/draft-used-assurances/" + draftUsedAssuranceDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftUsedAssuranceDTO.getId().toString()))
            .body(draftUsedAssuranceDTO);
    }

    /**
     * {@code PUT  /draft-used-assurances/:id} : Updates an existing draftUsedAssurance.
     *
     * @param id the id of the draftUsedAssuranceDTO to save.
     * @param draftUsedAssuranceDTO the draftUsedAssuranceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftUsedAssuranceDTO,
     * or with status {@code 400 (Bad Request)} if the draftUsedAssuranceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftUsedAssuranceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftUsedAssuranceDTO> updateDraftUsedAssurance(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftUsedAssuranceDTO draftUsedAssuranceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftUsedAssurance : {}, {}", id, draftUsedAssuranceDTO);
        if (draftUsedAssuranceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftUsedAssuranceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftUsedAssuranceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftUsedAssuranceDTO = draftUsedAssuranceService.update(draftUsedAssuranceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftUsedAssuranceDTO.getId().toString()))
            .body(draftUsedAssuranceDTO);
    }

    /**
     * {@code PATCH  /draft-used-assurances/:id} : Partial updates given fields of an existing draftUsedAssurance, field will ignore if it is null
     *
     * @param id the id of the draftUsedAssuranceDTO to save.
     * @param draftUsedAssuranceDTO the draftUsedAssuranceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftUsedAssuranceDTO,
     * or with status {@code 400 (Bad Request)} if the draftUsedAssuranceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftUsedAssuranceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftUsedAssuranceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftUsedAssuranceDTO> partialUpdateDraftUsedAssurance(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftUsedAssuranceDTO draftUsedAssuranceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftUsedAssurance partially : {}, {}", id, draftUsedAssuranceDTO);
        if (draftUsedAssuranceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftUsedAssuranceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftUsedAssuranceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftUsedAssuranceDTO> result = draftUsedAssuranceService.partialUpdate(draftUsedAssuranceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftUsedAssuranceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-used-assurances} : get all the draftUsedAssurances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftUsedAssurances in body.
     */
    @GetMapping("")
    public List<DraftUsedAssuranceDTO> getAllDraftUsedAssurances() {
        log.debug("REST request to get all DraftUsedAssurances");
        return draftUsedAssuranceService.findAll();
    }

    /**
     * {@code GET  /draft-used-assurances/:id} : get the "id" draftUsedAssurance.
     *
     * @param id the id of the draftUsedAssuranceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftUsedAssuranceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftUsedAssuranceDTO> getDraftUsedAssurance(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftUsedAssurance : {}", id);
        Optional<DraftUsedAssuranceDTO> draftUsedAssuranceDTO = draftUsedAssuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftUsedAssuranceDTO);
    }

    /**
     * {@code DELETE  /draft-used-assurances/:id} : delete the "id" draftUsedAssurance.
     *
     * @param id the id of the draftUsedAssuranceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftUsedAssurance(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftUsedAssurance : {}", id);
        draftUsedAssuranceService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
