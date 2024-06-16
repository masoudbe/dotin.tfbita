package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftRepository;
import com.dotin.tfbita.service.DraftService;
import com.dotin.tfbita.service.dto.DraftDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.Draft}.
 */
@RestController
@RequestMapping("/api/drafts")
public class DraftResource {

    private final Logger log = LoggerFactory.getLogger(DraftResource.class);

    private static final String ENTITY_NAME = "draft";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftService draftService;

    private final DraftRepository draftRepository;

    public DraftResource(DraftService draftService, DraftRepository draftRepository) {
        this.draftService = draftService;
        this.draftRepository = draftRepository;
    }

    /**
     * {@code POST  /drafts} : Create a new draft.
     *
     * @param draftDTO the draftDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftDTO, or with status {@code 400 (Bad Request)} if the draft has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftDTO> createDraft(@RequestBody DraftDTO draftDTO) throws URISyntaxException {
        log.debug("REST request to save Draft : {}", draftDTO);
        if (draftDTO.getId() != null) {
            throw new BadRequestAlertException("A new draft cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftDTO = draftService.save(draftDTO);
        return ResponseEntity.created(new URI("/api/drafts/" + draftDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftDTO.getId().toString()))
            .body(draftDTO);
    }

    /**
     * {@code PUT  /drafts/:id} : Updates an existing draft.
     *
     * @param id the id of the draftDTO to save.
     * @param draftDTO the draftDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftDTO,
     * or with status {@code 400 (Bad Request)} if the draftDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftDTO> updateDraft(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftDTO draftDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Draft : {}, {}", id, draftDTO);
        if (draftDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftDTO = draftService.update(draftDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftDTO.getId().toString()))
            .body(draftDTO);
    }

    /**
     * {@code PATCH  /drafts/:id} : Partial updates given fields of an existing draft, field will ignore if it is null
     *
     * @param id the id of the draftDTO to save.
     * @param draftDTO the draftDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftDTO,
     * or with status {@code 400 (Bad Request)} if the draftDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftDTO> partialUpdateDraft(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftDTO draftDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Draft partially : {}, {}", id, draftDTO);
        if (draftDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftDTO> result = draftService.partialUpdate(draftDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /drafts} : get all the drafts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drafts in body.
     */
    @GetMapping("")
    public List<DraftDTO> getAllDrafts() {
        log.debug("REST request to get all Drafts");
        return draftService.findAll();
    }

    /**
     * {@code GET  /drafts/:id} : get the "id" draft.
     *
     * @param id the id of the draftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftDTO> getDraft(@PathVariable("id") Long id) {
        log.debug("REST request to get Draft : {}", id);
        Optional<DraftDTO> draftDTO = draftService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftDTO);
    }

    /**
     * {@code DELETE  /drafts/:id} : delete the "id" draft.
     *
     * @param id the id of the draftDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraft(@PathVariable("id") Long id) {
        log.debug("REST request to delete Draft : {}", id);
        draftService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
