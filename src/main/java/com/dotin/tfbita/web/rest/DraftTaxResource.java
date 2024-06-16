package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftTaxRepository;
import com.dotin.tfbita.service.DraftTaxService;
import com.dotin.tfbita.service.dto.DraftTaxDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftTax}.
 */
@RestController
@RequestMapping("/api/draft-taxes")
public class DraftTaxResource {

    private final Logger log = LoggerFactory.getLogger(DraftTaxResource.class);

    private static final String ENTITY_NAME = "draftTax";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftTaxService draftTaxService;

    private final DraftTaxRepository draftTaxRepository;

    public DraftTaxResource(DraftTaxService draftTaxService, DraftTaxRepository draftTaxRepository) {
        this.draftTaxService = draftTaxService;
        this.draftTaxRepository = draftTaxRepository;
    }

    /**
     * {@code POST  /draft-taxes} : Create a new draftTax.
     *
     * @param draftTaxDTO the draftTaxDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftTaxDTO, or with status {@code 400 (Bad Request)} if the draftTax has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftTaxDTO> createDraftTax(@RequestBody DraftTaxDTO draftTaxDTO) throws URISyntaxException {
        log.debug("REST request to save DraftTax : {}", draftTaxDTO);
        if (draftTaxDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftTax cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftTaxDTO = draftTaxService.save(draftTaxDTO);
        return ResponseEntity.created(new URI("/api/draft-taxes/" + draftTaxDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftTaxDTO.getId().toString()))
            .body(draftTaxDTO);
    }

    /**
     * {@code PUT  /draft-taxes/:id} : Updates an existing draftTax.
     *
     * @param id the id of the draftTaxDTO to save.
     * @param draftTaxDTO the draftTaxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTaxDTO,
     * or with status {@code 400 (Bad Request)} if the draftTaxDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftTaxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftTaxDTO> updateDraftTax(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTaxDTO draftTaxDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftTax : {}, {}", id, draftTaxDTO);
        if (draftTaxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTaxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTaxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftTaxDTO = draftTaxService.update(draftTaxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTaxDTO.getId().toString()))
            .body(draftTaxDTO);
    }

    /**
     * {@code PATCH  /draft-taxes/:id} : Partial updates given fields of an existing draftTax, field will ignore if it is null
     *
     * @param id the id of the draftTaxDTO to save.
     * @param draftTaxDTO the draftTaxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTaxDTO,
     * or with status {@code 400 (Bad Request)} if the draftTaxDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftTaxDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftTaxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftTaxDTO> partialUpdateDraftTax(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTaxDTO draftTaxDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftTax partially : {}, {}", id, draftTaxDTO);
        if (draftTaxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTaxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTaxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftTaxDTO> result = draftTaxService.partialUpdate(draftTaxDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTaxDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-taxes} : get all the draftTaxes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftTaxes in body.
     */
    @GetMapping("")
    public List<DraftTaxDTO> getAllDraftTaxes() {
        log.debug("REST request to get all DraftTaxes");
        return draftTaxService.findAll();
    }

    /**
     * {@code GET  /draft-taxes/:id} : get the "id" draftTax.
     *
     * @param id the id of the draftTaxDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftTaxDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftTaxDTO> getDraftTax(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftTax : {}", id);
        Optional<DraftTaxDTO> draftTaxDTO = draftTaxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftTaxDTO);
    }

    /**
     * {@code DELETE  /draft-taxes/:id} : delete the "id" draftTax.
     *
     * @param id the id of the draftTaxDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftTax(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftTax : {}", id);
        draftTaxService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
