package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftExtendRepository;
import com.dotin.tfbita.service.DraftExtendService;
import com.dotin.tfbita.service.dto.DraftExtendDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftExtend}.
 */
@RestController
@RequestMapping("/api/draft-extends")
public class DraftExtendResource {

    private final Logger log = LoggerFactory.getLogger(DraftExtendResource.class);

    private static final String ENTITY_NAME = "draftExtend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftExtendService draftExtendService;

    private final DraftExtendRepository draftExtendRepository;

    public DraftExtendResource(DraftExtendService draftExtendService, DraftExtendRepository draftExtendRepository) {
        this.draftExtendService = draftExtendService;
        this.draftExtendRepository = draftExtendRepository;
    }

    /**
     * {@code POST  /draft-extends} : Create a new draftExtend.
     *
     * @param draftExtendDTO the draftExtendDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftExtendDTO, or with status {@code 400 (Bad Request)} if the draftExtend has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftExtendDTO> createDraftExtend(@RequestBody DraftExtendDTO draftExtendDTO) throws URISyntaxException {
        log.debug("REST request to save DraftExtend : {}", draftExtendDTO);
        if (draftExtendDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftExtend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftExtendDTO = draftExtendService.save(draftExtendDTO);
        return ResponseEntity.created(new URI("/api/draft-extends/" + draftExtendDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftExtendDTO.getId().toString()))
            .body(draftExtendDTO);
    }

    /**
     * {@code PUT  /draft-extends/:id} : Updates an existing draftExtend.
     *
     * @param id the id of the draftExtendDTO to save.
     * @param draftExtendDTO the draftExtendDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftExtendDTO,
     * or with status {@code 400 (Bad Request)} if the draftExtendDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftExtendDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftExtendDTO> updateDraftExtend(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftExtendDTO draftExtendDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftExtend : {}, {}", id, draftExtendDTO);
        if (draftExtendDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftExtendDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftExtendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftExtendDTO = draftExtendService.update(draftExtendDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftExtendDTO.getId().toString()))
            .body(draftExtendDTO);
    }

    /**
     * {@code PATCH  /draft-extends/:id} : Partial updates given fields of an existing draftExtend, field will ignore if it is null
     *
     * @param id the id of the draftExtendDTO to save.
     * @param draftExtendDTO the draftExtendDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftExtendDTO,
     * or with status {@code 400 (Bad Request)} if the draftExtendDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftExtendDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftExtendDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftExtendDTO> partialUpdateDraftExtend(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftExtendDTO draftExtendDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftExtend partially : {}, {}", id, draftExtendDTO);
        if (draftExtendDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftExtendDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftExtendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftExtendDTO> result = draftExtendService.partialUpdate(draftExtendDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftExtendDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-extends} : get all the draftExtends.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftExtends in body.
     */
    @GetMapping("")
    public List<DraftExtendDTO> getAllDraftExtends() {
        log.debug("REST request to get all DraftExtends");
        return draftExtendService.findAll();
    }

    /**
     * {@code GET  /draft-extends/:id} : get the "id" draftExtend.
     *
     * @param id the id of the draftExtendDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftExtendDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftExtendDTO> getDraftExtend(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftExtend : {}", id);
        Optional<DraftExtendDTO> draftExtendDTO = draftExtendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftExtendDTO);
    }

    /**
     * {@code DELETE  /draft-extends/:id} : delete the "id" draftExtend.
     *
     * @param id the id of the draftExtendDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftExtend(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftExtend : {}", id);
        draftExtendService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
