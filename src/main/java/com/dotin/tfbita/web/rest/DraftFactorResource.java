package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftFactorRepository;
import com.dotin.tfbita.service.DraftFactorService;
import com.dotin.tfbita.service.dto.DraftFactorDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftFactor}.
 */
@RestController
@RequestMapping("/api/draft-factors")
public class DraftFactorResource {

    private final Logger log = LoggerFactory.getLogger(DraftFactorResource.class);

    private static final String ENTITY_NAME = "draftFactor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftFactorService draftFactorService;

    private final DraftFactorRepository draftFactorRepository;

    public DraftFactorResource(DraftFactorService draftFactorService, DraftFactorRepository draftFactorRepository) {
        this.draftFactorService = draftFactorService;
        this.draftFactorRepository = draftFactorRepository;
    }

    /**
     * {@code POST  /draft-factors} : Create a new draftFactor.
     *
     * @param draftFactorDTO the draftFactorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftFactorDTO, or with status {@code 400 (Bad Request)} if the draftFactor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftFactorDTO> createDraftFactor(@RequestBody DraftFactorDTO draftFactorDTO) throws URISyntaxException {
        log.debug("REST request to save DraftFactor : {}", draftFactorDTO);
        if (draftFactorDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftFactor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftFactorDTO = draftFactorService.save(draftFactorDTO);
        return ResponseEntity.created(new URI("/api/draft-factors/" + draftFactorDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftFactorDTO.getId().toString()))
            .body(draftFactorDTO);
    }

    /**
     * {@code PUT  /draft-factors/:id} : Updates an existing draftFactor.
     *
     * @param id the id of the draftFactorDTO to save.
     * @param draftFactorDTO the draftFactorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftFactorDTO,
     * or with status {@code 400 (Bad Request)} if the draftFactorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftFactorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftFactorDTO> updateDraftFactor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftFactorDTO draftFactorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftFactor : {}, {}", id, draftFactorDTO);
        if (draftFactorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftFactorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftFactorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftFactorDTO = draftFactorService.update(draftFactorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftFactorDTO.getId().toString()))
            .body(draftFactorDTO);
    }

    /**
     * {@code PATCH  /draft-factors/:id} : Partial updates given fields of an existing draftFactor, field will ignore if it is null
     *
     * @param id the id of the draftFactorDTO to save.
     * @param draftFactorDTO the draftFactorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftFactorDTO,
     * or with status {@code 400 (Bad Request)} if the draftFactorDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftFactorDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftFactorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftFactorDTO> partialUpdateDraftFactor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftFactorDTO draftFactorDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftFactor partially : {}, {}", id, draftFactorDTO);
        if (draftFactorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftFactorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftFactorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftFactorDTO> result = draftFactorService.partialUpdate(draftFactorDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftFactorDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-factors} : get all the draftFactors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftFactors in body.
     */
    @GetMapping("")
    public List<DraftFactorDTO> getAllDraftFactors() {
        log.debug("REST request to get all DraftFactors");
        return draftFactorService.findAll();
    }

    /**
     * {@code GET  /draft-factors/:id} : get the "id" draftFactor.
     *
     * @param id the id of the draftFactorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftFactorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftFactorDTO> getDraftFactor(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftFactor : {}", id);
        Optional<DraftFactorDTO> draftFactorDTO = draftFactorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftFactorDTO);
    }

    /**
     * {@code DELETE  /draft-factors/:id} : delete the "id" draftFactor.
     *
     * @param id the id of the draftFactorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftFactor(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftFactor : {}", id);
        draftFactorService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
