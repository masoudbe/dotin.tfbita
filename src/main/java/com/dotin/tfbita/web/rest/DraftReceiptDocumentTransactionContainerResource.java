package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftReceiptDocumentTransactionContainerRepository;
import com.dotin.tfbita.service.DraftReceiptDocumentTransactionContainerService;
import com.dotin.tfbita.service.dto.DraftReceiptDocumentTransactionContainerDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer}.
 */
@RestController
@RequestMapping("/api/draft-receipt-document-transaction-containers")
public class DraftReceiptDocumentTransactionContainerResource {

    private static final Logger log = LoggerFactory.getLogger(DraftReceiptDocumentTransactionContainerResource.class);

    private static final String ENTITY_NAME = "draftReceiptDocumentTransactionContainer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftReceiptDocumentTransactionContainerService draftReceiptDocumentTransactionContainerService;

    private final DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository;

    public DraftReceiptDocumentTransactionContainerResource(
        DraftReceiptDocumentTransactionContainerService draftReceiptDocumentTransactionContainerService,
        DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository
    ) {
        this.draftReceiptDocumentTransactionContainerService = draftReceiptDocumentTransactionContainerService;
        this.draftReceiptDocumentTransactionContainerRepository = draftReceiptDocumentTransactionContainerRepository;
    }

    /**
     * {@code POST  /draft-receipt-document-transaction-containers} : Create a new draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the draftReceiptDocumentTransactionContainerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftReceiptDocumentTransactionContainerDTO, or with status {@code 400 (Bad Request)} if the draftReceiptDocumentTransactionContainer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftReceiptDocumentTransactionContainerDTO> createDraftReceiptDocumentTransactionContainer(
        @RequestBody DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DraftReceiptDocumentTransactionContainer : {}", draftReceiptDocumentTransactionContainerDTO);
        if (draftReceiptDocumentTransactionContainerDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new draftReceiptDocumentTransactionContainer cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        draftReceiptDocumentTransactionContainerDTO = draftReceiptDocumentTransactionContainerService.save(
            draftReceiptDocumentTransactionContainerDTO
        );
        return ResponseEntity.created(
            new URI("/api/draft-receipt-document-transaction-containers/" + draftReceiptDocumentTransactionContainerDTO.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    draftReceiptDocumentTransactionContainerDTO.getId().toString()
                )
            )
            .body(draftReceiptDocumentTransactionContainerDTO);
    }

    /**
     * {@code PUT  /draft-receipt-document-transaction-containers/:id} : Updates an existing draftReceiptDocumentTransactionContainer.
     *
     * @param id the id of the draftReceiptDocumentTransactionContainerDTO to save.
     * @param draftReceiptDocumentTransactionContainerDTO the draftReceiptDocumentTransactionContainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftReceiptDocumentTransactionContainerDTO,
     * or with status {@code 400 (Bad Request)} if the draftReceiptDocumentTransactionContainerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftReceiptDocumentTransactionContainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftReceiptDocumentTransactionContainerDTO> updateDraftReceiptDocumentTransactionContainer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update DraftReceiptDocumentTransactionContainer : {}, {}",
            id,
            draftReceiptDocumentTransactionContainerDTO
        );
        if (draftReceiptDocumentTransactionContainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftReceiptDocumentTransactionContainerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftReceiptDocumentTransactionContainerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftReceiptDocumentTransactionContainerDTO = draftReceiptDocumentTransactionContainerService.update(
            draftReceiptDocumentTransactionContainerDTO
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    draftReceiptDocumentTransactionContainerDTO.getId().toString()
                )
            )
            .body(draftReceiptDocumentTransactionContainerDTO);
    }

    /**
     * {@code PATCH  /draft-receipt-document-transaction-containers/:id} : Partial updates given fields of an existing draftReceiptDocumentTransactionContainer, field will ignore if it is null
     *
     * @param id the id of the draftReceiptDocumentTransactionContainerDTO to save.
     * @param draftReceiptDocumentTransactionContainerDTO the draftReceiptDocumentTransactionContainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftReceiptDocumentTransactionContainerDTO,
     * or with status {@code 400 (Bad Request)} if the draftReceiptDocumentTransactionContainerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftReceiptDocumentTransactionContainerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftReceiptDocumentTransactionContainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftReceiptDocumentTransactionContainerDTO> partialUpdateDraftReceiptDocumentTransactionContainer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update DraftReceiptDocumentTransactionContainer partially : {}, {}",
            id,
            draftReceiptDocumentTransactionContainerDTO
        );
        if (draftReceiptDocumentTransactionContainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftReceiptDocumentTransactionContainerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftReceiptDocumentTransactionContainerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftReceiptDocumentTransactionContainerDTO> result = draftReceiptDocumentTransactionContainerService.partialUpdate(
            draftReceiptDocumentTransactionContainerDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                draftReceiptDocumentTransactionContainerDTO.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /draft-receipt-document-transaction-containers} : get all the draftReceiptDocumentTransactionContainers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftReceiptDocumentTransactionContainers in body.
     */
    @GetMapping("")
    public List<DraftReceiptDocumentTransactionContainerDTO> getAllDraftReceiptDocumentTransactionContainers() {
        log.debug("REST request to get all DraftReceiptDocumentTransactionContainers");
        return draftReceiptDocumentTransactionContainerService.findAll();
    }

    /**
     * {@code GET  /draft-receipt-document-transaction-containers/:id} : get the "id" draftReceiptDocumentTransactionContainer.
     *
     * @param id the id of the draftReceiptDocumentTransactionContainerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftReceiptDocumentTransactionContainerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftReceiptDocumentTransactionContainerDTO> getDraftReceiptDocumentTransactionContainer(
        @PathVariable("id") Long id
    ) {
        log.debug("REST request to get DraftReceiptDocumentTransactionContainer : {}", id);
        Optional<DraftReceiptDocumentTransactionContainerDTO> draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftReceiptDocumentTransactionContainerDTO);
    }

    /**
     * {@code DELETE  /draft-receipt-document-transaction-containers/:id} : delete the "id" draftReceiptDocumentTransactionContainer.
     *
     * @param id the id of the draftReceiptDocumentTransactionContainerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftReceiptDocumentTransactionContainer(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftReceiptDocumentTransactionContainer : {}", id);
        draftReceiptDocumentTransactionContainerService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
