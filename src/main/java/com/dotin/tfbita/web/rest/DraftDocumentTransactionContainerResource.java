package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftDocumentTransactionContainerRepository;
import com.dotin.tfbita.service.DraftDocumentTransactionContainerService;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftDocumentTransactionContainer}.
 */
@RestController
@RequestMapping("/api/draft-document-transaction-containers")
public class DraftDocumentTransactionContainerResource {

    private final Logger log = LoggerFactory.getLogger(DraftDocumentTransactionContainerResource.class);

    private static final String ENTITY_NAME = "draftDocumentTransactionContainer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftDocumentTransactionContainerService draftDocumentTransactionContainerService;

    private final DraftDocumentTransactionContainerRepository draftDocumentTransactionContainerRepository;

    public DraftDocumentTransactionContainerResource(
        DraftDocumentTransactionContainerService draftDocumentTransactionContainerService,
        DraftDocumentTransactionContainerRepository draftDocumentTransactionContainerRepository
    ) {
        this.draftDocumentTransactionContainerService = draftDocumentTransactionContainerService;
        this.draftDocumentTransactionContainerRepository = draftDocumentTransactionContainerRepository;
    }

    /**
     * {@code POST  /draft-document-transaction-containers} : Create a new draftDocumentTransactionContainer.
     *
     * @param draftDocumentTransactionContainerDTO the draftDocumentTransactionContainerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftDocumentTransactionContainerDTO, or with status {@code 400 (Bad Request)} if the draftDocumentTransactionContainer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftDocumentTransactionContainerDTO> createDraftDocumentTransactionContainer(
        @RequestBody DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DraftDocumentTransactionContainer : {}", draftDocumentTransactionContainerDTO);
        if (draftDocumentTransactionContainerDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new draftDocumentTransactionContainer cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerService.save(draftDocumentTransactionContainerDTO);
        return ResponseEntity.created(new URI("/api/draft-document-transaction-containers/" + draftDocumentTransactionContainerDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    draftDocumentTransactionContainerDTO.getId().toString()
                )
            )
            .body(draftDocumentTransactionContainerDTO);
    }

    /**
     * {@code PUT  /draft-document-transaction-containers/:id} : Updates an existing draftDocumentTransactionContainer.
     *
     * @param id the id of the draftDocumentTransactionContainerDTO to save.
     * @param draftDocumentTransactionContainerDTO the draftDocumentTransactionContainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftDocumentTransactionContainerDTO,
     * or with status {@code 400 (Bad Request)} if the draftDocumentTransactionContainerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftDocumentTransactionContainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftDocumentTransactionContainerDTO> updateDraftDocumentTransactionContainer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftDocumentTransactionContainer : {}, {}", id, draftDocumentTransactionContainerDTO);
        if (draftDocumentTransactionContainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftDocumentTransactionContainerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftDocumentTransactionContainerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerService.update(draftDocumentTransactionContainerDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    draftDocumentTransactionContainerDTO.getId().toString()
                )
            )
            .body(draftDocumentTransactionContainerDTO);
    }

    /**
     * {@code PATCH  /draft-document-transaction-containers/:id} : Partial updates given fields of an existing draftDocumentTransactionContainer, field will ignore if it is null
     *
     * @param id the id of the draftDocumentTransactionContainerDTO to save.
     * @param draftDocumentTransactionContainerDTO the draftDocumentTransactionContainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftDocumentTransactionContainerDTO,
     * or with status {@code 400 (Bad Request)} if the draftDocumentTransactionContainerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftDocumentTransactionContainerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftDocumentTransactionContainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftDocumentTransactionContainerDTO> partialUpdateDraftDocumentTransactionContainer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update DraftDocumentTransactionContainer partially : {}, {}",
            id,
            draftDocumentTransactionContainerDTO
        );
        if (draftDocumentTransactionContainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftDocumentTransactionContainerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftDocumentTransactionContainerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftDocumentTransactionContainerDTO> result = draftDocumentTransactionContainerService.partialUpdate(
            draftDocumentTransactionContainerDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftDocumentTransactionContainerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-document-transaction-containers} : get all the draftDocumentTransactionContainers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftDocumentTransactionContainers in body.
     */
    @GetMapping("")
    public List<DraftDocumentTransactionContainerDTO> getAllDraftDocumentTransactionContainers() {
        log.debug("REST request to get all DraftDocumentTransactionContainers");
        return draftDocumentTransactionContainerService.findAll();
    }

    /**
     * {@code GET  /draft-document-transaction-containers/:id} : get the "id" draftDocumentTransactionContainer.
     *
     * @param id the id of the draftDocumentTransactionContainerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftDocumentTransactionContainerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftDocumentTransactionContainerDTO> getDraftDocumentTransactionContainer(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftDocumentTransactionContainer : {}", id);
        Optional<DraftDocumentTransactionContainerDTO> draftDocumentTransactionContainerDTO =
            draftDocumentTransactionContainerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftDocumentTransactionContainerDTO);
    }

    /**
     * {@code DELETE  /draft-document-transaction-containers/:id} : delete the "id" draftDocumentTransactionContainer.
     *
     * @param id the id of the draftDocumentTransactionContainerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftDocumentTransactionContainer(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftDocumentTransactionContainer : {}", id);
        draftDocumentTransactionContainerService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
